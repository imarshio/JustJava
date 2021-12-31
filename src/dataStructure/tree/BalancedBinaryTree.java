package dataStructure.tree;

import java.util.NoSuchElementException;

/**
 * @author masuo
 * @data 2021/9/29 13:33
 * @Description AVL, 平衡二叉树
 * 平衡二叉树是二叉查找树的进阶版，它可以实现自平衡，所以也叫自平衡二叉搜索树（Self-Balanced Binary Search Tree）
 * 平衡二叉树最大的特点就是自平衡，它可以通过自旋来保证自己的平衡性
 * 所以这里最主要的就是完成自旋操作，在每次增加或删除操作后
 * <p>
 * 其次其一大特点是左右子树的高度差的绝对值小于等于1，以此来保证搜索的效率尽量可以达到最大化
 * 我们可以通过计算左右子树的高度来保证
 * <p>
 * 他的增加删除操作和二叉搜索树是一样的，只是在操作之后增加了判断，
 */

public class BalancedBinaryTree<E> {

    //根节点
    Node<E> root;

    //增加一个节点
    public void add(E item) {
        if (root == null) {
            root = new Node<>(item);
        } else {
            //
            Node<E> temp = root;
            Node<E> newNode = new Node<>(item);
            while (true) {
                if (item.hashCode() < temp.item.hashCode()) {
                    //插入左侧
                    if (temp.leftSon == null) {
                        temp.leftSon = newNode;
                        newNode.parent = temp;
                        break;
                    }
                    temp = temp.leftSon;
                } else {
                    //插入右侧
                    if (temp.rightSon == null) {
                        temp.rightSon = newNode;
                        newNode.parent = temp;
                        break;
                    }
                    temp = temp.rightSon;
                }
            }
        }
    }

    //查询一个节点
    public Node<E> get(E value) {
        Node<E> temp = root;
        while (temp != null) {
            if (temp.item.hashCode() == value.hashCode()) {
                return temp;
            } else {
                if (value.hashCode() < temp.item.hashCode()) {
                    //左侧查询
                    temp = temp.leftSon;
                } else {
                    //右侧
                    temp = temp.rightSon;
                }
            }
        }
        return temp;
    }

    //删除一个节点
    public void del(E item) {
        //获取待删除节点
        Node<E> delNode = get(item);
        //首先判断该节点是否为空
        checkExit(delNode);
        //删除是要先判断该节点是否含有子节点，子结点中优先判断有没有左子节点，其次在判断右子节点
        if(delNode.leftSon != null){
            //左子树不为空，有两种情况，但都是找到最大的节点，代替待删除节点
            Node<E> maxNode = getMaxNode(delNode.leftSon);
            //值更新，结构没有发生改变，所以不需要考虑改变链接
            delNode.item = maxNode.item;
            if(delNode.leftSon==maxNode){
                //说明左子树没有右子树
                delNode.leftSon = maxNode.leftSon;
            }else {
                maxNode.parent.rightSon = null;
            }
        }else {
            //左子树为空,可直接挂载右子树（null），右子树为空也可
            if(delNode.parent.leftSon == delNode){
                delNode.parent.leftSon = delNode.rightSon;
            }else {
                delNode.parent.rightSon = delNode.rightSon;
            }
            if(delNode.rightSon != null){
                delNode.rightSon.parent = delNode.parent;
            }
        }

    }

    /**
     * 获取自node结点开始的树的最大值，主要判断其是否有右子树，
     * 如果有，则找右子树的最右侧叶子节点，
     * 如果没有，则返回节点，因为此时最大的节点就是他自己
     * @param node 开始节点
     * @return maxNode 最大节点
     */
    private Node<E> getMaxNode(Node<E> node) {
        while (node.rightSon != null){
            //
            node = node.rightSon;
        }
        return node;
    }

    private void checkExit(Node<E> node) {
        if (node == null) {
            throw new NoSuchElementException();
        }
    }

    //在进行插入删除操作后，如果造成树不平衡则需要进行旋转操作
    //自旋操作，根据不同的树形状，有不同的旋转方案


    //RR平衡旋转（左单旋转）
    //条件：右子树高度减去左子树高度大于1

    //LL平衡旋转（右单旋转）
    //条件：左子树高度减去右子树高度大于1

    //LR平衡旋转（先左后右旋转）
    //条件：符合RR平衡旋转（左单旋转）前提下，当前结点的左子树的右子树高度大于它的左子树的左子树的高度

    //RL平衡旋转（先右后左旋转）
    //条件：符合LL平衡旋转（右单旋转）前提下，当前结点的右子树的左子树的高度大于它的右子树的右子树的高度

    //获得自node节点开始的高度
    public int getHeight(Node<E> node) {
        if (node == null) {
            return 0;
        } else {
            int left = 0, right = 0;
            if (node.leftSon != null) {
                left = getHeight(node.leftSon);
            }
            if (node.rightSon != null) {
                right = getHeight(node.rightSon);
            }
            return Math.max(left, right) + 1;
        }
    }

    //平衡二叉树节点
    static class Node<E> {
        transient int height;
        transient E item;
        Node<E> parent;
        Node<E> leftSon;
        Node<E> rightSon;

        public Node() {
        }

        public Node(E item) {
            this.item = item;
        }
    }
}
