package dataStructure.tree;

/**
 * @author masuo
 * @data 2021/12/8 17:59
 * @Description 通用tree 基础 原始树 originalTree
 */

abstract class BaseTree<E> {

    Node<E> root;

    int height;

    int size;

    abstract public boolean add(E item);

    abstract public boolean del(E item);

    abstract public void preList();

    abstract public void midList();

    abstract public void tailList();

    abstract static class Node<E>{
        E item;
        Node<E> parent;

        public Node(E item) {
            this.item = item;
        }

        public Node(E item, Node<E> parent) {
            this.item = item;
            this.parent = parent;
        }
    }
}
