package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author masuo
 * @data 8/4/2022 下午3:43
 * @Description 二叉树的前序、中序、后序
 */

public class TreeOrder {

    // 前序
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        List<Integer> preorder = new ArrayList<>();
        getPreList(preorder,root);
        return preorder;
    }

    private void getPreList(List<Integer> preorder, TreeNode node) {
        preorder.add(node.val);
        if(node.left != null){
            getPreList(preorder, node.left);
        }
        if(node.right != null){
            getPreList(preorder, node.right);
        }
    }

    // 中序
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        List<Integer> inorder = new ArrayList<>();
        getInorderList(inorder,root);
        return inorder;
    }

    private void getInorderList(List<Integer> inorder, TreeNode node) {

        if(node.left != null){
            getInorderList(inorder, node.left);
        }
        inorder.add(node.val);
        if(node.right != null){
            getInorderList(inorder, node.right);
        }
    }

    // 后序
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }
        List<Integer> postorder = new ArrayList<>();
        getPostorderList(postorder,root);
        return postorder;
    }

    private void getPostorderList(List<Integer> postorder, TreeNode node) {

        if(node.left != null){
            getPostorderList(postorder, node.left);
        }
        if(node.right != null){
            getPostorderList(postorder, node.right);
        }
        postorder.add(node.val);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
