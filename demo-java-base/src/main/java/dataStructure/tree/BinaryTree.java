package dataStructure.tree;

/**
 * @author masuo
 * @data 2021/12/17 14:53
 * @Description 二叉树
 */

public class BinaryTree<E> extends BaseTree<E> {
    @Override
    public boolean add(E item) {
        return false;
    }

    @Override
    public boolean del(E item) {
        return false;
    }

    @Override
    public void preList() {
        this.preList(root);
    }

    private void preList(Node<E> node) {
        if (node != null) {
            System.out.println(node.item);
            // if(node)
        }
    }

    @Override
    public void midList() {

    }

    @Override
    public void tailList() {

    }

    static class BNode<E> extends BaseTree.Node<E> {

        BNode<E> parent;
        BNode<E> left;
        BNode<E> right;
        // 避免被序列化
        transient int depth;

        public BNode(E item) {
            super(item);
        }

        public boolean isLeftSon(BNode<E> node) {
            if (node != null && node.parent != null) {
                return node.parent.left == node;
            }
            return true;
        }
    }
}
