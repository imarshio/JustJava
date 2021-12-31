package dataStructure.tree;

/**
 * @author masuo
 * @data 2021/9/28 10:03
 * @Description 树型数据结构应用非常广泛，尤其是在大数据查询方面，其次文件结构大多也是树形结构
 */

public class SimpleBinaryTree<E> {

    //简单二叉树结构---静态二叉树，提供构建二叉树的结构，但是需要自己构建二叉树，
    //二叉树通用结构有两个子节点，一个父节点，值
    transient Node<E> root;

    //高度
    transient int height;

    public SimpleBinaryTree() {
        this.root = new Node<>();
        this.height = 0;
    }

    public static void main(String[] args) {

        //{ 2 , 5 , 9 , 7 , 3}
        Node<Integer> n1 = new Node<>(2);
        Node<Integer> n2 = new Node<>(5);
        Node<Integer> n3 = new Node<>(9);
        Node<Integer> n4 = new Node<>(7);
        Node<Integer> n5 = new Node<>(3);

        //树结构
        //      2
        //  5       9
        //       7      3
        n1.leftSon = n2;
        n1.rightSon = n3;
        n3.leftSon = n4;
        n3.rightSon = n5;

        SimpleBinaryTree<Integer> sbt = new SimpleBinaryTree<>();
        sbt.root = n1;
        sbt.test();

    }

    private void test() {
        //先序
        firstOrder(root);
        System.out.println("先序结束");
        //中序
        midOrder(root);
        System.out.println("中序结束");
        //后序
        lastOrder(root);
        System.out.println("后序结束");
    }

    //这些遍历输出的思想其实很简单，就是整体思想，将左儿子与右儿子当成一个整体去代入
    private void firstOrder(Node<E> node) {
        if (null != node) {
            System.out.println(node.item);
            Node<E> left = node.leftSon;
            Node<E> right = node.rightSon;
            if (left != null) {
                firstOrder(left);
            }
            if (right != null) {
                firstOrder(right);
            }
        }
    }

    private void midOrder(Node<E> node) {
        if (node != null) {
            Node<E> left = node.leftSon;
            Node<E> right = node.rightSon;
            if (left != null) {
                midOrder(left);
            }
            System.out.println(node.item);
            if (right != null) {
                midOrder(right);
            }
        }
    }

    private void lastOrder(Node<E> node) {
        if (node != null) {
            Node<E> left = node.leftSon;
            Node<E> right = node.rightSon;
            if (left != null) {
                lastOrder(left);
            }
            if (right != null) {
                lastOrder(right);
            }
            System.out.println(node.item);
        }

    }


    //树是由 节点 和 边 构成
    static class Node<E> {
        //值
        E item;
        //父节点，可有可无
        //Node<E> father;
        //左儿子
        Node<E> leftSon;
        //右儿子
        Node<E> rightSon;

        public Node() {
        }

        public Node(E item) {
            this.item = item;
        }
    }

}

