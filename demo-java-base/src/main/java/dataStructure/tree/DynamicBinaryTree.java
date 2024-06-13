package dataStructure.tree;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * @author masuo
 * @data 2021/9/29 10:07
 * @Description 动态二叉树--提供构建二叉树的结构，再增加数据时自动构建二叉树
 * 平衡方法参考：https://zhuanlan.zhihu.com/p/165939383
 */

public class DynamicBinaryTree<E> {

    // 树根
    transient Node<E> root;

    // 修改次数
    transient int modCount;

    // 节点个数
    transient int size;

    // 是否被修改
    transient boolean modFlag;

    /****************外部接口****************/
    // 构造函数
    public DynamicBinaryTree() {
        this.modFlag = false;
    }

    // 增加节点
    public boolean add(E item) {
        if (root == null) {
            root = new Node<>(item, 1);
        } else {
            // 调用
            add(root, item);
        }
        // 修改次数+1
        ++modCount;
        ++size;
        return true;
    }

    // 获取树高
    public int getDepth() {
        return root == null ? 0 : root.depth;
    }

    // 查找节点
    public Node<E> get(E item) {
        return findN(item);
    }

    // 删除节点
    public void del(E item) {
        // 先判断是否存在该节点
        if (!checkNode(findN(item))) {
            throw new NoSuchElementException("item = " + item);
        }
        // 存在则继续下面的步骤，由于需要递归删除，我们再次采用这个方法
        // 删除节点时需要将所有经过的点都判断是否失衡
        Stack<Node<E>> nodes = new Stack<>();
        del(root, item, nodes, null);
        // 删除之后，可以在递归时处理，也可以将经过的节点入栈，循环处理。
        // 这里我们选择将其入栈，之后循环处理栈中的节点,栈中第一个节点必是真正删除的节点的父节点
        if (nodes.size() > 0) {
            // 处理删除时经过的节点
            reBalance(nodes);
        }
        --size;
        ++modCount;
    }

    /**
     * 私有增加节点函数
     *
     * @param node 待增加节点的某一个上层节点
     * @param item 待增加节点的值
     * @return 增加后的节点
     */
    private Node<E> add(Node<E> node, E item) {
        if (node == null) {
            return new Node<>(item, 1);
        }
        if (item.hashCode() < node.item.hashCode()) {
            // 插入左
            node.leftSon = add(node.leftSon, item);
            if (node.leftSon.parent == null) {
                node.leftSon.parent = node;
            }
        } else {
            // 插入右
            node.rightSon = add(node.rightSon, item);
            if (node.rightSon.parent == null) {
                node.rightSon.parent = node;
            }
        }
        // 判断平衡，计算高度，计算平衡因子
        int left = node.leftSon == null ? 0 : node.leftSon.depth;
        int right = node.rightSon == null ? 0 : node.rightSon.depth;
        node.depth = Math.max(left, right) + 1;

        int bf = left - right;// 平衡因子
        if (Math.abs(bf) > 1) {
            // 不平衡，需要旋转
            node = rotate(node, item);
        }
        return node;
    }

    /**
     * 添加节点时，失衡旋转方法I
     * 此方法只适合在添加节点时使用，可节省一定的时间，因为在插入节点时，
     * 我们知道插入节点得值，所以我们直接使用该值来判断旋转方方向
     * 从失衡节点往下寻找插入节点，只找前两步即可，前两步即可确定旋转方式
     * 需要根据item的值来判断前两步的方向
     *
     * @param node 失衡节点
     * @param item 插入节点的值
     * @return 调整后的节点的根节点
     */
    private Node<E> rotate(Node<E> node, E item) {
        // 先找插入节点
        Node<E> temp = node;
        StringBuilder RL = new StringBuilder();
        while (RL.length() < 2) {
            if (item.hashCode() < temp.item.hashCode()) {
                // 左高
                RL.append("L");
                temp = temp.leftSon;
            } else {
                // 右高
                RL.append("R");
                temp = temp.rightSon;
            }
        }
        // System.out.println(size);
        // 更新高度
        // node.depth = reSetDepth(node);
        return rotate(node, RL.toString());
    }

    /**
     * 删除节点时，失衡节点旋转方法II
     * 与第一种方法不一样的是这种方法通过判断节点的平衡因子来判断旋转方向
     *
     * @param node 失衡节点
     */
    private void rotate(Node<E> node) {
        // 根据平衡因子判断旋转方式
        Node<E> temp = node;
        StringBuilder RL = new StringBuilder();
        while (RL.length() < 2) {
            if (getBF(temp) > 0) {
                RL.append("R");
                temp = temp.leftSon;
            } else if (getBF(temp) < 0) {
                RL.append("L");
                temp = temp.rightSon;
            }
        }
        rotate(node, RL.toString());
    }

    /**
     * 具体旋转方法
     *
     * @param node 旋转时以node为固定节点进行旋转
     * @param RL   左右    所有的旋转，不管是LL、RR、LR、RL，都可以拆分为R，或者L旋转
     *             需要注意的是，在我这里的L代表的是左高，R代表的是右高，
     *             即树倾斜方向，不是旋转的方向，旋转方向与倾斜方向相反
     * @return 调整后的node节点
     */
    private Node<E> rotate(Node<E> node, String RL) {
        switch (RL) {
            case "LL":
                // 单旋，左高，需顺时针旋转
                node = L_rotate(node);
                break;
            case "RR":
                node = R_rotate(node);
                break;
            case "LR":
                // 先左旋，在右旋
                // 注意此时需要先调整插入节点与其父节点，让其变成单旋
                node.leftSon = R_rotate(node.leftSon);
                node = L_rotate(node);
                break;
            case "RL":
                // 先右旋，在左旋
                node.rightSon = L_rotate(node.rightSon);
                node = R_rotate(node);
                break;
        }
        return node;
    }

    // 这个R代表右侧高，右侧高需左旋，左单旋
    private Node<E> R_rotate(Node<E> node) {
        // Node<E> A = node;
        Node<E> B = node.rightSon;
        Node<E> Bl = B.leftSon;

        if (Bl != null) {
            Bl.parent = node;
        }
        // node的父节点
        B.parent = node.parent;
        if (root == node) {
            root = B;
        } else {
            if (node.isLeftChild()) {
                node.parent.leftSon = B;
            } else {
                node.parent.rightSon = B;
            }
        }
        B.leftSon = node;
        node.parent = B;
        node.rightSon = Bl;
        // Node<E> temp = new Node<>(node.item, node.leftSon, node.rightSon.leftSon, null);
        // if (temp.leftSon != null) {
        //    temp.leftSon.parent = temp;
        //}
        // if (temp.rightSon != null) {
        //    temp.rightSon.parent = temp;
        //}
        // temp.parent = node;
        // node.item = node.rightSon.item;
        // node.rightSon = node.rightSon.rightSon;
        // node.leftSon = temp;

        resetDepth(node);
        resetDepth(B);
        return B;
    }

    // 这个 右单旋
    private Node<E> L_rotate(Node<E> node) {
        // Node<E> B = node;
        Node<E> A = node.leftSon;
        Node<E> Ar = A.rightSon;

        if (Ar != null) {
            Ar.parent = node;
        }
        A.parent = node.parent;
        if (root == node) {
            root = A;
        } else {
            if (node.isLeftChild()) {
                node.parent.leftSon = A;
            } else {
                node.parent.rightSon = A;
            }
        }

        A.rightSon = node;
        node.parent = A;
        node.leftSon = Ar;

        // Node<E> temp = new Node<>(node.item,0);
        // if (temp.leftSon != null) {
        //    temp.leftSon.parent = temp;
        //}
        // if (temp.rightSon != null) {
        //    temp.rightSon.parent = temp;
        //}
        // temp.parent = node;
        // node.item = node.leftSon.item;
        // node.leftSon = node.leftSon.leftSon;
        // node.rightSon = temp;
        // 重置高度，只需调整高度变化得节点
        resetDepth(node);
        resetDepth(A);
        return A;
    }

    /**
     * 旋转时，高度改变的只有两个节点，其余节点高度不变，利用这一特性
     * 我们只需在旋转后根据其子节点高度的最大值就能获得其高度
     *
     * @param node 旋转后的根节点
     */
    private void resetDepth(Node<E> node) {
        if (node != null) {
            int left = node.leftSon == null ? 0 : node.leftSon.depth;
            int right = node.rightSon == null ? 0 : node.rightSon.depth;
            int depth = Math.max(left, right) + 1;
            if (node.depth != depth) {
                node.depth = depth;
            }
        }
    }

    /**
     * 私有查询节点
     *
     * @param item 节点值
     * @return 节点
     */
    private Node<E> findN(E item) {
        Node<E> temp = root;
        while (temp != null && temp.item != item) {
            if (item.hashCode() < temp.item.hashCode()) {
                // 在左侧找
                temp = temp.leftSon;
            } else {
                // 在右侧找
                temp = temp.rightSon;
            }
        }
        return temp;
    }

    /**
     * 处理经过的节点
     * 调整父节点高度
     *
     * @param nodes 删除经过的节点
     */
    private void reBalance(Stack<Node<E>> nodes) {
        do {
            Node<E> temp = nodes.pop();// 真正删除的节点的父节点,其高度可能没有发生改变
            // 调整高度
            resetDepth(temp);
            // int left = temp.leftSon == null ? 0 : temp.leftSon.depth;
            // int right = temp.rightSon == null ? 0 : temp.rightSon.depth;
            // temp.depth = Math.max(left, right) + 1;
            // 判断平衡因子
            if (Math.abs(getBF(temp)) > 1) {
                // 不平衡，需要旋转
                rotate(temp);
            }
        } while (!nodes.empty());
    }

    /**
     * 删除某一结点
     *
     * @param node 删除节点所在的树的某一结点
     * @param item 删除节点的值
     *             左子树存在：则使用删除节点的左子树的最大值替换待删除结点 *
     *             右子树存在：则使用删除节点的右子树的最小值替换待删除节点
     *             左右子树都为空：父节点的子节点为空
     */
    private void del(Node<E> node, E item, Stack<Node<E>> nodes, String LR) {
        if (node.item != item) {
            // 这个不是待删除节点
            nodes.add(node);
            if (item.hashCode() < node.item.hashCode()) {
                // 待删除节点位与此节点的左侧
                del(node.leftSon, item, nodes, "L");
            } else {
                // 待删除节点位于此节点的右侧
                del(node.rightSon, item, nodes, "R");
            }
        } else {
            // 是待删除节点
            // 1. 没有子节点，叶子节点直接删除，从删除节点向上调整平衡
            // 2. 有一个子节点，则用子节点直接替代该节点
            // 3. 有两个子节点，当有两个子节点的时候，有多种删除方法，
            //   3.1、使用待删除节点的左子树的最大值，最好为叶子节点
            //   3.2、使用待删除节点的右子树的最小值，最好为叶子节点
            //   3.3、使用待删除节点左右子树中较高的子树，且满足上面相应的条件
            if (node.leftSon == null && node.rightSon == null) {
                // 没有子节点，叶子节点直接删除（根节点也是）
                delWithOutSon(node, LR);
            } else if (node.rightSon != null && node.leftSon != null) {
                // 左右子树都不为空,有两个子节点，
                delWithTwoSon(node, nodes);
            } else {
                // 左右有一个为空
                delWithOneSon(node, LR);
            }
        }
    }

    /**
     * 待删除节点有两个子节点
     * 删除有两个子节点的节点可以选择让他的【前驱】或【后继】来代替它
     * 在这里我们选择从左侧选择待删除节点的前驱进行删除，
     * 参考自旧金山大学计算机科学与技术学院--https://myusf.usfca.edu/arts-sciences/computer-science
     *
     * @param delNode 待删除节点
     * @param nodes   经过的结点
     */
    private void delWithTwoSon(Node<E> delNode, Stack<Node<E>> nodes) {
        // 需要找到左子树的最大值
        nodes.add(delNode);
        // 待删除结点的左子树
        Node<E> temp = delNode.leftSon;// 因为有两个子节点，所以左子节点必不为空
        // 先找左子树上的最大值，因为二叉树的特性，所以从右子树寻找
        while (temp.rightSon != null) {
            nodes.add(temp);
            temp = temp.rightSon;
        }
        // 找到最大值之后，替换del的item
        delNode.item = temp.item;
        // 替换节点值之后删除最大值节点，此处，最大值节点有以下情况
        // 1。最大节点为叶子节点
        // 2。最大节点为非叶子节点，此时最大节点只有左子树（可能为空），无右子树

        // 判断最大值节点是否是叶子节点，
        if (isLeaf(temp)) {
            // 是叶子节点，则直接删除该节点，父节点高度不一定改变
            delLeaf(temp, (temp.parent.leftSon == temp) ? "L" : "R");
        } else {
            // 不是叶子节点，则将该节点的左子树放到其父节点的左子树上，父节点高度不一定改变
            temp.parent.leftSon = temp.leftSon;
        }
    }

    /**
     * 待删除节点有一个子节点，删除并更新父节点高度
     *
     * @param delNode 待删除节点
     * @param lr      待删除节点是父节点的左儿子还是右儿子
     */
    private void delWithOneSon(Node<E> delNode, String lr) {
        Node<E> node = delNode.rightSon == null ? delNode.leftSon : delNode.rightSon;
        if (lr == null) {
            // lr为空说明待删除节点为根节点
            root = node;
        } else {
            if (lr.equals("L")) {
                delNode.parent.leftSon = node;
            } else {
                delNode.parent.rightSon = node;
            }
            // 因为只有一个子节点，所以父节点的高度需要降低1
            --delNode.parent.depth;
        }
    }

    /**
     * 待删除节点无子树,即叶子节点，删除并更新父节点高度
     *
     * @param delNode 待删除节点
     * @param lr      待删除节点是父节点的左儿子还是右儿子
     */
    private void delWithOutSon(Node<E> delNode, String lr) {
        if (lr == null) root = null;
            // lr为空说明没有父节点，即为根节点，删除根结点所以将根节点设置为空
        else delLeaf(delNode, lr);
    }

    /**
     * 删除叶子节点
     *
     * @param temp 待删除的叶子节点
     */
    private void delLeaf(Node<E> temp, String lr) {
        if (lr.equals("L")) {
            // 待删除节点为左子树
            temp.parent.leftSon = null;
        } else {
            // 待删除节点为右子树
            temp.parent.rightSon = null;
        }
    }

    /**
     * 是否叶子节点
     *
     * @param temp 待判断节点
     * @return true/false
     */
    private boolean isLeaf(Node<E> temp) {
        return temp.leftSon == null && temp.rightSon == null;
    }

    /**
     * 计算平衡因子
     * 左高 - 右高
     *
     * @param node 节点
     * @return int 平衡因子 -2 -1 0 1 2
     */
    public int getBF(Node<E> node) {
        // 判断平衡，计算高度，计算平衡因子
        int left = node.leftSon == null ? 0 : node.leftSon.depth;
        int right = node.rightSon == null ? 0 : node.rightSon.depth;
        return left - right;
    }

    /**
     * 获取自 node 结点开始的最大的节点
     *
     * @param node 开始节点
     * @return 最大节点
     */
    public Node<E> getMaxLeaf(Node<E> node) {
        // 根据二叉查找树的特点，大的都在节点右侧
        // 首先判断右子树是否为空
        while (node.rightSon != null) {
            node = node.rightSon;
        }
        // 右子树为空则返回node本身
        return node;
    }

    /**
     * 检查节点是否存在
     *
     * @param temp 待检查节点
     */
    private boolean checkNode(Node<E> temp) {
        return temp != null;
    }

    @Deprecated
    private int calDepth(Node<E> n) {
        if (n == null) {
            return 0;
        }
        int left = 0, right = 0;
        if (n.leftSon != null) {
            left = calDepth(n.leftSon);
        }
        if (n.rightSon != null) {
            right = calDepth(n.rightSon);
        }
        n.depth = Math.max(left, right) + 1;
        return n.depth;
    }

    /**
     * 重置 节点高度
     * 由旋转后调整变化节点代替
     *
     * @param node 自该节点往下重置高度
     * @return 节点高度
     */
    @Deprecated
    private int reSetDepth(Node<E> node) {
        // 递归
        if (node == null) {
            return 0;
        }
        // 判断平衡，计算高度，计算平衡因子
        int leftH = node.leftSon == null ? 0 : reSetDepth(node.leftSon);
        int rightH = node.rightSon == null ? 0 : reSetDepth(node.rightSon);
        if (node.leftSon != null) node.leftSon.depth = leftH;
        if (node.rightSon != null) node.rightSon.depth = rightH;
        return Math.max(leftH, rightH) + 1;
    }

    // 前序遍历
    public void preList() {
        System.out.print("前序遍历：");
        this.preList(root);
        System.out.println();
    }

    private void preList(Node<E> node) {
        if (node != null) {
            System.out.print(node.item + "  ");
            if (node.leftSon != null) {
                preList(node.leftSon);
            }
            if (node.rightSon != null) {
                preList(node.rightSon);
            }
        }
    }

    // 中序遍历
    public void midList() {
        System.out.print("中序遍历：");
        this.midList(root);
        System.out.println();
    }

    private void midList(Node<E> node) {
        if (node != null) {
            if (node.leftSon != null) {
                tailList(node.leftSon);
            }
            System.out.print(node.item + "  ");
            if (node.rightSon != null) {
                tailList(node.rightSon);
            }
        }
    }

    // 后序遍历
    public void tailList() {
        System.out.print("后序遍历：");
        this.tailList(root);
        System.out.println();
    }

    private void tailList(Node<E> node) {
        if (node.leftSon != null) {
            tailList(node.leftSon);
        }
        if (node.rightSon != null) {
            tailList(node.rightSon);
        }
        System.out.print(node.item + "  ");
    }


    // 二叉树节点
    static class Node<E> implements Cloneable {
        transient E item;
        int depth;// 计算Balance Factor 平衡因子
        Node<E> parent;
        Node<E> leftSon;
        Node<E> rightSon;

        public Node(E item, int depth) {
            this.item = item;
            this.depth = depth;
        }

        public Node(E item, Node<E> leftSon, Node<E> rightSon, Node<E> parent) {
            this.item = item;
            this.leftSon = leftSon;
            this.rightSon = rightSon;
            this.parent = parent;
        }

        public boolean isLeftChild() {
            if (this.parent == null) {
                return true;
            }
            return this.parent.leftSon == this;
        }
    }
}