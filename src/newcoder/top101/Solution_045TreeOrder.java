package newcoder.top101;

import org.junit.Test;

import java.util.Stack;

/**
 * @author masuo
 * @data 8/4/2022 下午2:28
 * @Description 实现二叉树先序，中序和后序遍历
 */

public class Solution_045TreeOrder {

    @Test
    public void test(){
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        threeOrders(node);
    }

    public int[][] threeOrders(TreeNode root) {

        if(root == null){
            return new int[3][0];
        }

        Stack<TreeNode> stack = new Stack<>();
        // 先序栈
        eqPreStack(stack, root);
        int size = stack.size();
        // 有size之后，声明
        int[][] treeOrder = new int[3][size];
        // 遍历，放到int[]
        int index = size;
        while (!stack.isEmpty()){
            TreeNode pop = stack.pop();
            treeOrder[0][--index] = pop.val;
        }
        // 中序栈
        eqMidStack(stack, root);
        // 清空栈
        index = size;
        while (!stack.isEmpty()){
            TreeNode pop = stack.pop();
            treeOrder[1][--index] = pop.val;
        }
        eqEndStack(stack, root);
        // 清空栈
        index = size;
        while (!stack.isEmpty()){
            TreeNode pop = stack.pop();
            treeOrder[2][--index] = pop.val;
        }
        return treeOrder;
    }


    private void eqPreStack(Stack<TreeNode> stack, TreeNode node) {
        stack.add(node);
        if (node.left != null) {
            eqPreStack(stack, node.left);
        }
        if (node.right != null) {
            eqPreStack(stack, node.right);
        }
    }

    private void eqMidStack(Stack<TreeNode> stack, TreeNode node) {
        if (node.left != null) {
            eqMidStack(stack, node.left);
        }
        stack.add(node);
        if (node.right != null) {
            eqMidStack(stack, node.right);
        }
    }

    private void eqEndStack(Stack<TreeNode> stack, TreeNode node) {
        if (node.left != null) {
            eqEndStack(stack, node.left);
        }
        if (node.right != null) {
            eqEndStack(stack, node.right);
        }
        stack.add(node);
    }

    public static class TreeNode {
        int val = 0;

        public TreeNode(int val) {
            this.val = val;
        }

        TreeNode left = null;

        TreeNode right = null;
    }
}


