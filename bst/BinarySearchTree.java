package bst;

public class BinarySearchTree {
    private Node root;

    public void insert(Node node) {
        if (this.root == null) {
            this.root = node;
            return;
        }
        var traversalNode = this.root;
        var keepLoop = true;
        while (keepLoop) {
            if (traversalNode.getItem() == node.getItem()) {
                keepLoop = false;
            }
            if (traversalNode.getItem() > node.getItem()) {
                if (traversalNode.getLeft() == null) {
                    traversalNode.setLeft(node);
                    node.setParent(traversalNode);
                    keepLoop = false;
                }
                traversalNode = traversalNode.getLeft();
            }
            if (traversalNode.getItem() < node.getItem()) {
                if (traversalNode.getRight() == null) {
                    traversalNode.setRight(node);
                    node.setParent(traversalNode);
                    keepLoop = false;
                }
                traversalNode = traversalNode.getRight();
            }
        }
    }

    public String createString(Node node, int level) {
        StringBuilder result = new StringBuilder();
        result.append(level + ". " + node.getItem() + " ");
        if (node.getLeft() != null)
            result.append(createString(node.getLeft(), level + 1));
        if (node.getRight() != null)
            result.append(createString(node.getRight(), level + 1));
        return result.toString();
    }

    @Override
    public String toString() {
        return createString(root, 1);
    }

    // count depth of the tree from a certain node.
    public int countDepth(Node node, int count) {
        int countLeft = count;
        int countRight = count;
        if (node.getLeft() != null) {
            countLeft = countDepth(node.getLeft(), count + 1);
        }
        if (node.getRight() != null) {
            countRight = countDepth(node.getRight(), count + 1);
        }
        return countLeft >= countRight ? countLeft : countRight;
    }

    public int getDepth() {
        return countDepth(root, 1);
    }

    public Node getNode(Node node, int key) {
        if (node != null && node.getItem() == key) {
            return node;
        }
        if (node != null && node.getItem() < key) {
            return getNode(node.getRight(), key);
        }
        if (node != null && node.getItem() > key) {
            return getNode(node.getLeft(), key);
        }
        return null;
    }

    public Node get(int key) {
        return getNode(root, key);
    }

    public Node delete(Node node) {

        if (node == null) {
            return null;
        }
        if (node.getLeft() == null && node.getRight() == null) {
            if (node.equals(node.getParent().getLeft())) {
                node.getParent().setLeft(null);
                node.setParent(null);
                return node;
            }
            node.getParent().setRight(null);
            node.setParent(null);
            return node;
        }
        if (node.getLeft() == null ^ node.getRight() == null) {
            var successor = node.getLeft() == null ? node.getRight() : node.getLeft();
            node.setLeft(null);
            node.setRight(null);
            if (node.equals(node.getParent().getLeft())) {
                node.getParent().setLeft(successor);
                node.setParent(null);
                return node;
            }
            node.getParent().setRight(successor);
            node.setParent(null);
            return node;
        }
        var successor = node.getRight();
        while (successor.getLeft() != null) {
            successor = successor.getLeft();
        }
        delete(successor);
        successor.setLeft(node.getLeft());
        successor.setRight(node.getRight());
        node.setLeft(null);
        node.setRight(null);
        if (node.equals(node.getParent().getLeft())) {
            node.getParent().setLeft(successor);
            node.setParent(null);
            return node;
        }
        node.getParent().setRight(successor);
        node.setParent(null);
        return node;
    }
}
