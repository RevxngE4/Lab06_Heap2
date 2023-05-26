
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

    public void EnOrdenTranversalRecursiva() {
        System.out.println("En orden tranversal recursiva:");
        EnOrdenRecursiva(root);
        System.out.println();
    }

    private void EnOrdenRecursiva(Node root) {
        if (root != null) {
            EnOrdenRecursiva(root.left);
            System.out.print(root.data + " ");
            EnOrdenRecursiva(root.right);
        }
    }

    public void EnOrdenTranversalINterativa() {
        System.out.println("En orden tranversal interativa:");
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

    public void preordentransversalrecursiva() {
        System.out.println("pre orden tranversal recursiva :");
        preoordenrecursiva(root);
        System.out.println();
    }

    private void preoordenrecursiva(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preoordenrecursiva(root.left);
            preoordenrecursiva(root.right);
        }
    }

    public void preorderTranversalInterativa() {
        System.out.println("pre orden tranversal interativa:");
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

    public void postordenTraversalRecursivo() {
        System.out.println("Postorden Traversal (Recursivo):");
        postordenRecursivo(root);
        System.out.println();
    }

    private void postordenRecursivo(Node root) {
        if (root != null) {
            postordenRecursivo(root.left);
            postordenRecursivo(root.right);
            System.out.print(root.data + " ");
        }
    }

    public void postordenTraversalIterativo() {
        System.out.println("Postorden Traversal (Iterativo):");
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

    public void recorridodeOrdendeNivel() {
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
        tree.insert(5);
        tree.insert(1);
        tree.insert(9);
        tree.insert(10);
        tree.insert(7);
        tree.insert(3);
        tree.insert(2);

        tree.EnOrdenTranversalRecursiva();
        tree.EnOrdenTranversalINterativa();

        tree.preordentransversalrecursiva();
        tree.preorderTranversalInterativa();

        tree.postordenTraversalRecursivo();
        tree.postordenTraversalIterativo();

        tree.recorridodeOrdendeNivel();

        System.out.println("\nDoes the tree have duplicates? " + tree.duplica());
        tree.NivelesDeImpresiónConHojas();
        tree.printVerticesWithImbalancedDescendants();
        tree.imprimirVérticesConDesequilibrioEn15();
        System.out.println(tree.encontrarVérticePromedio());
    }

    public boolean duplica() {
        Set<Integer> set = new HashSet<>();
        return haydobles(root, set);
    }

    private boolean haydobles(Node root, Set<Integer> set) {
        if (root == null) {
            return false;
        }

        if (set.contains(root.data)) {
            return true;
        }

        set.add(root.data);

        return haydobles(root.left, set) || haydobles(root.right, set);
    }

    public void NivelesDeImpresiónConHojas() {
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
        imprimirVérticesConDescendientesDesequilibrados(root);
    }

    private int imprimirVérticesConDescendientesDesequilibrados(Node root) {
        if (root == null) {
            return 0;
        }

        int leftDescendants = imprimirVérticesConDescendientesDesequilibrados(root.left);
        int rightDescendants = imprimirVérticesConDescendientesDesequilibrados(root.right);

        int totalDescendants = leftDescendants + rightDescendants;

        if (leftDescendants != rightDescendants) {
            System.out.println(root.data);
        }

        return totalDescendants + 1;
    }

    public void imprimirVérticesConDesequilibrioEn15() {
        System.out.println("Vertices with Imbalance by 15:");
        imprimirVérticesConDesequilibrioEn15(root);
    }

    private int imprimirVérticesConDesequilibrioEn15(Node root) {
        if (root == null) {
            return 0;
        }

        int leftDescendants = imprimirVérticesConDesequilibrioEn15(root.left);
        int rightDescendants = imprimirVérticesConDesequilibrioEn15(root.right);

        int totalDescendants = leftDescendants + rightDescendants;

        if (Math.abs(leftDescendants - rightDescendants) > 15) {
            System.out.println(root.data);
        }

        return totalDescendants + 1;
    }

    public int encontrarVérticePromedio() {
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
