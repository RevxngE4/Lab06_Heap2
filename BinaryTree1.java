
import java.util.*;

public class BinaryTree1 {
    private Node root;

    private static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public void insert(int data) {
        root = insertNode(root, data);
    }

    private Node insertNode(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data < root.data) {
            root.left = insertNode(root.left, data);
        } else if (data > root.data) {
            root.right = insertNode(root.right, data);
        }

        return root;
    }

    public void inorderTraversalRecursive() {
        System.out.println("Inorder Traversal (Recursive):");
        inorderRecursive(root);
        System.out.println();
    }

    private void inorderRecursive(Node root) {
        if (root != null) {
            inorderRecursive(root.left);
            System.out.print(root.data + " ");
            inorderRecursive(root.right);
        }
    }

    public void inorderTraversalIterative() {
        System.out.println("Inorder Traversal (Iterative):");
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            System.out.print(current.data + " ");

            current = current.right;
        }

        System.out.println();
    }

    public void preorderTraversalRecursive() {
        System.out.println("Preorder Traversal (Recursive):");
        preorderRecursive(root);
        System.out.println();
    }

    private void preorderRecursive(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorderRecursive(root.left);
            preorderRecursive(root.right);
        }
    }

    public void preorderTraversalIterative() {
        System.out.println("Preorder Traversal (Iterative):");
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.print(current.data + " ");

            if (current.right != null) {
                stack.push(current.right);
            }

            if (current.left != null) {
                stack.push(current.left);
            }
        }

        System.out.println();
    }

    public void postorderTraversalRecursive() {
        System.out.println("Postorder Traversal (Recursive):");
        postorderRecursive(root);
        System.out.println();
    }

    private void postorderRecursive(Node root) {
        if (root != null) {
            postorderRecursive(root.left);
            postorderRecursive(root.right);
            System.out.print(root.data + " ");
        }
    }

    public void postorderTraversalIterative() {
        System.out.println("Postorder Traversal (Iterative):");
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        Stack<Node> output = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            output.push(current);

            if (current.left != null) {
                stack.push(current.left);
            }

            if (current.right != null) {
                stack.push(current.right);
            }
        }

        while (!output.isEmpty()) {
            System.out.print(output.pop().data + " ");
        }

        System.out.println();
    }

    public void levelOrderTraversal() {
        System.out.println("Level Order Traversal:");
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.data + " ");

            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {
        BinaryTree1 tree = new BinaryTree1();
        tree.insert(4);
        tree.insert(2);
        tree.insert(6);
        tree.insert(1);
        tree.insert(3);
        tree.insert(5);
        tree.insert(7);

        tree.inorderTraversalRecursive();
        tree.inorderTraversalIterative();

        tree.preorderTraversalRecursive();
        tree.preorderTraversalIterative();

        tree.postorderTraversalRecursive();
        tree.postorderTraversalIterative();

        tree.levelOrderTraversal();

        System.out.println("\nDoes the tree have duplicates? " + tree.hasDuplicates());
        tree.printLevelsWithLeaves();
        tree.printVerticesWithImbalancedDescendants();
        tree.printVerticesWithImbalanceBy15();
        System.out.println(tree.findAverageVertex());
    }

    public boolean hasDuplicates() {
        Set<Integer> set = new HashSet<>();
        return hasDuplicates(root, set);
    }

    private boolean hasDuplicates(Node root, Set<Integer> set) {
        if (root == null) {
            return false;
        }

        if (set.contains(root.data)) {
            return true;
        }

        set.add(root.data);

        return hasDuplicates(root.left, set) || hasDuplicates(root.right, set);
    }

    public void printLevelsWithLeaves() {
        System.out.println("Levels with Leaves:");
        printLevelsWithLeaves(root, 0);
    }

    private void printLevelsWithLeaves(Node root, int level) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            System.out.println(root.data + " at level " + level);
        }

        printLevelsWithLeaves(root.left, level + 1);
        printLevelsWithLeaves(root.right, level + 1);
    }

    public void printVerticesWithImbalancedDescendants() {
        System.out.println("Vertices with Imbalanced Descendants:");
        printVerticesWithImbalancedDescendants(root);
    }

    private int printVerticesWithImbalancedDescendants(Node root) {
        if (root == null) {
            return 0;
        }

        int leftDescendants = printVerticesWithImbalancedDescendants(root.left);
        int rightDescendants = printVerticesWithImbalancedDescendants(root.right);

        int totalDescendants = leftDescendants + rightDescendants;

        if (leftDescendants != rightDescendants) {
            System.out.println(root.data);
        }

        return totalDescendants + 1;
    }

    public void printVerticesWithImbalanceBy15() {
        System.out.println("Vertices with Imbalance by 15:");
        printVerticesWithImbalanceBy15(root);
    }

    private int printVerticesWithImbalanceBy15(Node root) {
        if (root == null) {
            return 0;
        }

        int leftDescendants = printVerticesWithImbalanceBy15(root.left);
        int rightDescendants = printVerticesWithImbalanceBy15(root.right);

        int totalDescendants = leftDescendants + rightDescendants;

        if (Math.abs(leftDescendants - rightDescendants) > 15) {
            System.out.println(root.data);
        }

        return totalDescendants + 1;
    }

    public int findAverageVertex() {
        int sum = calculateSum(root);
        int count = countVertices(root);
        int average = sum / count;

        return findClosestVertex(root, average);
    }

    private int calculateSum(Node root) {
        if (root == null) {
            return 0;
        }

        return root.data + calculateSum(root.left) + calculateSum(root.right);
    }

    private int countVertices(Node root) {
        if (root == null) {
            return 0;
        }

        return 1 + countVertices(root.left) + countVertices(root.right);
    }

    private int findClosestVertex(Node root, int target) {
        if (root == null) {
            return -1;
        }

        int closest = root.data;
        int diff = Math.abs(root.data - target);

        if (root.left != null) {
            int leftClosest = findClosestVertex(root.left, target);
            int leftDiff = Math.abs(leftClosest - target);

            if (leftDiff < diff) {
                closest = leftClosest;
                diff = leftDiff;
            }
        }

        if (root.right != null) {
            int rightClosest = findClosestVertex(root.right, target);
            int rightDiff = Math.abs(rightClosest - target);

            if (rightDiff < diff) {
                closest = rightClosest;
            }
        }

        return closest;
    }
}
