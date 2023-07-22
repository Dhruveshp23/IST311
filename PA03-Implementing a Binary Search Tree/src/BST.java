import java.util.ArrayList;
import java.util.List;

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class BST {
    protected Node root;

    public BST() {
        this.root = null;
    }

    public void insert(int data) {
        root = insertRecursive(root, data);
    }
    private Node insertRecursive(Node current, int data) {
        if (current == null) {
            return new Node(data);
        }

        if (data < current.data) {
            current.left = insertRecursive(current.left, data);
        } else if (data > current.data) {
            current.right = insertRecursive(current.right, data);
        }

        return current;
    }

    public boolean contains(int data) {
        return containsRecursive(root, data);
    }

    private boolean containsRecursive(Node current, int data) {
        if (current == null) {
            return false;
        }

        if (data == current.data) {
            return true;
        } else if (data < current.data) {
            return containsRecursive(current.left, data);
        } else {
            return containsRecursive(current.right, data);
        }
    }

    public void delete(int data) {
        root = deleteRecursive(root, data);
    }
    private Node deleteRecursive(Node current, int data) {
        if (current == null) {
            return null;
        }

        if (data == current.data) {
            // Case 1: Node to be deleted has no children or only one child
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }

            // Case 2: Node to be deleted has two children
            current.data = findMinValue(current.right);
            current.right = deleteRecursive(current.right, current.data);
        } else if (data < current.data) {
            current.left = deleteRecursive(current.left, data);
        } else {
            current.right = deleteRecursive(current.right, data);
        }

        return current;
    }
    private int findMinValue(Node node) {
        int minValue = node.data;
        while (node.left != null) {
            minValue = node.left.data;
            node = node.left;
        }
        return minValue;
    }

    public String toString() {
        List<Integer> values = new ArrayList<>();
        inOrderTraversal(root, values);
        return String.join(", ", values.stream().map(String::valueOf).toArray(String[]::new));
    }
    private void inOrderTraversal(Node node, List<Integer> values) {
        if (node != null) {
            inOrderTraversal(node.left, values);
            values.add(node.data);
            inOrderTraversal(node.right, values);
        }
    }

    public BST rebalance() {
        List<Integer> sortedValues = new ArrayList<>();
        inOrderTraversal(root, sortedValues);

        return buildBalancedBST(sortedValues, 0, sortedValues.size() - 1);
    }

    private BST buildBalancedBST(List<Integer> sortedValues, int start, int end) {
        if (start > end) {
            return new BST();
        }

        int mid = (start + end) / 2;
        BST balancedBST = new BST();
        balancedBST.insert(sortedValues.get(mid));

        balancedBST.root.left = buildBalancedBST(sortedValues, start, mid - 1).root;
        balancedBST.root.right = buildBalancedBST(sortedValues, mid + 1, end).root;

        return balancedBST;
    }

    public List<Integer> partition(int data) {
        List<Integer> values = new ArrayList<>();
        partitionRecursive(root, data, values);
        return values;
    }

    private void partitionRecursive(Node node, int data, List<Integer> values) {
        if (node != null) {
            partitionRecursive(node.left, data, values);
            if (node.data >= data) {
                values.add(node.data);
            }
            partitionRecursive(node.right, data, values);
        }
    }
}
