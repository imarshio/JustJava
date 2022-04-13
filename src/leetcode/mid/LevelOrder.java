package leetcode.mid;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author masuo
 * @data 8/4/2022 上午9:19
 * @Description N 叉树的层序遍历
 */

public class LevelOrder {

    protected int i = 0;
    protected void f(){
        System.out.println(1);
    }
    /**
     * 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
     *
     * @param root 根节点
     * @return List
     */
    public List<List<Integer>> levelOrder(Node root) {
        // 这是一个典型的广度优先搜索，广度优先搜索使用队列实现，队列中存放节点
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> rt = new ArrayList<>();
        // 声明广度优先搜索的队列
        Queue<Node> queue = new ArrayDeque<>();
        // 根节点放入队列，注意offer和add没有太大的区别，只是一个不会抛出异常，一个会抛出异常
        queue.offer(root);
        // queue.add(root);

        // 循环队列
        while (!queue.isEmpty()) {
            // 队列不为空，声明存放同级节点值的数组
            List<Integer> level = new ArrayList<>();
            // 队列的大小
            int size = queue.size();
            // 循环取出队列中的节点
            for (int i = 0; i < size; i++) {
                // 取出当前队列中的第一个节点
                Node cur = queue.poll();

                level.add(cur.val);
                // 接下来要做的就是将节点的子节点放入队列，然后将节点的值放入同级数组
                List<Node> children = cur.children;
                for (Node child : children) {
                    if(child != null){
                        queue.offer(child);
                    }
                }
            }
            rt.add(level);
        }
        return rt;
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
