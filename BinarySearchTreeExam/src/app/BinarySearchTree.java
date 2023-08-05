package app;

public class BinarySearchTree {
    // just a root variable
    Node root;

    /**
     * Empty constructor is all we need for now
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Adds the data to the tree, duplicates are not allowed
     *
     * @param data value that you want to insert into the tree
     */
    public void add(int data) {
        this.root = this.addNode(root, data);
    }

    public boolean swapNodes(int firstValue, int secondValue) {
        Node firstNode = findNode(root, firstValue);
        Node secondNode = findNode(root, secondValue);

        if (firstNode == null || secondNode == null) {
            return false;
        }

        int temp = firstNode.data;
        firstNode.data = secondNode.data;
        secondNode.data = temp;

        return true;
    }

    public boolean isValid() {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean fixTree() {
        Node[] nodes = new Node[2];
        findViolatingNodes(root, nodes, null);
        if (nodes[0] != null && nodes[1] != null) {
            int temp = nodes[0].data;
            nodes[0].data = nodes[1].data;
            nodes[1].data = temp;
            return true;
        }
        return false;
    }

    private Node addNode(Node current, int data) {
        // time to insert node
        if (current == null) {
            return new Node(data);
        }

        // compare the data to the current node to see which way to traverse
        if (data < current.data) {
            current.left = this.addNode(current.left, data);
        } else if (data > current.data) {
            current.right = this.addNode(current.right, data);
        }

        // if the data is already there, just return current
        return current;
    }

    private Node findNode(Node root, int value) {
        if (root == null) {
            return null;
        }
        if (root.data == value) {
            return root;
        }
        if (value < root.data) {
            return findNode(root.left, value);
        } else {
            return findNode(root.right, value);
        }
    }

    private boolean isValidBST(Node node, int min, int max) {
        if (node == null) {
            return true;
        }
        if (node.data <= min || node.data >= max) {
            return false;
        }
        return isValidBST(node.left, min, node.data) && isValidBST(node.right, node.data, max);
    }

    private void findViolatingNodes(Node root, Node[] nodes, Node prev) {
        if (root == null) {
            return;
        }
        findViolatingNodes(root.left, nodes, prev);
        if (prev != null && prev.data > root.data) {
            if (nodes[0] == null) {
                nodes[0] = prev;
            }
            nodes[1] = root;
        }
        findViolatingNodes(root.right, nodes, root);
    }

    @Override
    public String toString() {
        String result = this.inOrderTraversal(this.root);
        return result.trim();
    }

    private String inOrderTraversal(Node current) {
        StringBuilder strBldr = new StringBuilder();

        // check if we have anything to add to the string
        if (current != null) {
            // go left first because this is inorder
            strBldr.append(this.inOrderTraversal(current.left));

            // no print the current node
            strBldr.append(current.data + " ");

            // go right last because inorder
            strBldr.append(this.inOrderTraversal(current.right));
        }

        return strBldr.toString();
    }
}