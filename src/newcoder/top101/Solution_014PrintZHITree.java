package newcoder.top101;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * @author masuo
 * @data 18/4/2022 下午4:23
 * @Description 按之字形顺序打印二叉树
 */

public class Solution_014PrintZHITree {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        // 层序遍历，队列
        if(pRoot == null){
            return new ArrayList<ArrayList<Integer>>();
        }
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(pRoot);
        ArrayList<ArrayList<Integer>> rt = new ArrayList<>();
        // 层数
        int levelNum = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            ArrayList<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = deque.poll();
                if (tmp.left != null) {
                    deque.offer(tmp.left);
                }
                if (tmp.right != null) {
                    deque.offer(tmp.right);
                }
                level.add(tmp.val);
            }
            if (levelNum % 2 == 0) {
                rt.add(level);
            } else {
                rt.add(reverse(level));
            }
            levelNum++;
        }
        return rt;
    }

    public ArrayList<Integer> reverse(ArrayList<Integer> level) {
        int size = level.size();
        ArrayList<Integer> rt = new ArrayList<>(size);

        while (size > 0) {
            rt.add(level.get(size - 1));
            size--;
        }
        return rt;
    }

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    @Test
    public void test() {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        TreeNode node1 = new TreeNode(3);
        head.right = node1;
        node1.left = new TreeNode(4);
        node1.right = new TreeNode(5);
        Print(head);
    }
}
