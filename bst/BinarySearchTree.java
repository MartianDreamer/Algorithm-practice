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

    public String createString(Node node) {
        StringBuilder result = new StringBuilder();
        result.append(node.getItem() + " ");
        if (node.getLeft() != null)
            result.append(createString(node.getLeft()));
        if (node.getRight() != null)
            result.append(createString(node.getRight()));
        return result.toString();
    }

    @Override
    public String toString() {
        return createString(root);
    }

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

}
