import java.util.LinkedList;
import java.util.List;

/**
 * AVLTree
 */
public class AVLTree<T extends Comparable<T>> {

    private Node<T> root;
    private int size;

    public int size() {
        return this.size;
    }

    /**
     * @return the root
     */
    public Node<T> getRoot() {
        return root;
    }

    private Node<T> traverse(T value) {
        assert value != null;
        Node<T> traversalNode = root;
        Node<T> traversalNodeParent = null;
        while (traversalNode != null && traversalNode.getValue().compareTo(value) != 0) {
            if (traversalNode.getValue().compareTo(value) > 0) {
                traversalNodeParent = traversalNode;
                traversalNode = traversalNode.getLeft();
            } else {
                traversalNodeParent = traversalNode;
                traversalNode = traversalNode.getRight();
            }
        }
        if (traversalNode != null)
            return traversalNode;
        return traversalNodeParent;
    }

    public void insert(T value) {
        if (root == null) {
            Node<T> node = new Node<>(value);
            root = node;
            size++;
            return;
        }
        Node<T> nodeParent = traverse(value);
        assert nodeParent != null;
        if (nodeParent.getValue().compareTo(value) == 0) {
            return;
        }
        Node<T> node = new Node<>(value);
        if (nodeParent.getValue().compareTo(value) > 0) {
            nodeParent.setLeft(node);
            node.setParent(nodeParent);
            fixTreeHeight(nodeParent);
        }
        if (nodeParent.getValue().compareTo(value) < 0) {
            nodeParent.setRight(node);
            node.setParent(nodeParent);
            fixTreeHeight(nodeParent);
        }
        Node<T> traversalNode = node;
        while (traversalNode != null) {
            if (isViolation(traversalNode)) {
                Node<T> traversalNodeLeft = traversalNode.getLeft();
                Node<T> traversalNodeRight = traversalNode.getRight();
                if (isLeftHeavy(traversalNode)) {
                    if (isLeftHeavy(traversalNodeLeft) || isBalance(traversalNodeLeft)) {
                        rotateRight(traversalNode);
                    } else {
                        rotateLeft(traversalNodeLeft);
                        rotateRight(traversalNode);
                    }
                }
                if (isRightHeavy(traversalNode)) {
                    if (isRightHeavy(traversalNodeRight) || isBalance(traversalNodeRight)) {
                        rotateLeft(traversalNode);
                    } else {
                        rotateRight(traversalNodeRight);
                        rotateLeft(traversalNode);
                    }
                }
            }
            traversalNode = traversalNode.getParent();
        }
        size++;
    }

    private boolean isLeft(Node<T> node) {
        assert node != null;
        assert node.getParent() != null;
        return node.getParent().getLeft() == node;
    }

    private void rotateLeft(Node<T> node) {
        assert node.getRight() != null;
        assert node != null;
        Node<T> nodeRightChild = node.getRight();
        Node<T> nodeParent = node.getParent();
        if (nodeParent != null) {
            boolean isLeft = isLeft(node);
            node.setRight(nodeRightChild.getLeft());
            node.setParent(nodeRightChild);
            nodeRightChild.setLeft(node);
            if (isLeft)
                nodeParent.setLeft(nodeRightChild);
            else
                nodeParent.setRight(nodeRightChild);
            nodeRightChild.setParent(nodeParent);
            fixTreeHeight(node);
            return;
        }
        node.setRight(nodeRightChild.getLeft());
        node.setParent(nodeRightChild);
        nodeRightChild.setLeft(node);
        nodeRightChild.setParent(nodeParent);
        root = nodeRightChild;
        fixTreeHeight(node);
    }

    private void rotateRight(Node<T> node) {
        assert node.getLeft() != null;
        assert node != null;
        Node<T> nodeLeftChild = node.getLeft();
        Node<T> nodeParent = node.getParent();
        if (nodeParent != null) {
            boolean isLeft = isLeft(node);
            node.setLeft(nodeLeftChild.getRight());
            node.setParent(nodeLeftChild);
            nodeLeftChild.setRight(node);
            if (isLeft)
                nodeParent.setLeft(nodeLeftChild);
            else
                nodeParent.setRight(nodeLeftChild);
            nodeLeftChild.setParent(nodeParent);
            fixTreeHeight(node);
        }
        node.setLeft(nodeLeftChild.getRight());
        node.setParent(nodeLeftChild);
        nodeLeftChild.setRight(node);
        nodeLeftChild.setParent(nodeParent);
        root = nodeLeftChild;
        fixTreeHeight(node);
    }

    private int getHeight(Node<T> node) {
        if (node == null)
            return -1;
        return node.getHeight();
    }

    private int computeHeight(Node<T> node) {
        int maxChildHeight = Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
        int nodeHeigt = ++maxChildHeight;
        node.setHeight(nodeHeigt);
        return nodeHeigt;
    }

    private void fixTreeHeight(Node<T> node) {
        Node<T> fixedNode = node;
        while (fixedNode != null) {
            computeHeight(fixedNode);
            fixedNode = fixedNode.getParent();
        }
    }

    private boolean isViolation(Node<T> node) {
        return Math.abs(getHeight(node.getLeft()) - getHeight(node.getRight())) > 1;
    }

    private boolean isLeftHeavy(Node<T> node) {
        return getHeight(node.getLeft()) - getHeight(node.getRight()) > 0;
    }

    private boolean isRightHeavy(Node<T> node) {
        return getHeight(node.getLeft()) - getHeight(node.getRight()) < 0;
    }

    private boolean isBalance(Node<T> node) {
        return getHeight(node.getLeft()) - getHeight(node.getRight()) == 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Node<T>> nodes = new LinkedList<>();
        inOrder(root, nodes);
        nodes.forEach((element) -> stringBuilder
                .append("height" + element.getHeight() + "value" + element.getValue() + ", "));
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }

    public void inOrder(Node<T> node, List<Node<T>> output) {
        if (node == null)
            return;
        inOrder(node.getLeft(), output);
        output.add(node);
        inOrder(node.getRight(), output);

    }

    public class Node<E> {
        private E value;
        private int height;
        private Node<E> left;
        private Node<E> right;
        private Node<E> parent;

        /**
         * @param value
         */
        public Node(E value) {
            this.value = value;
            this.height = 0;
        }

        /**
         * @param value the value to set
         */
        public void setValue(E value) {
            this.value = value;
        }

        /**
         * @param height the height to set
         */
        public void setHeight(int height) {
            this.height = height;
        }

        /**
         * @param left the left to set
         */
        public void setLeft(Node<E> left) {
            this.left = left;
        }

        /**
         * @param right the right to set
         */
        public void setRight(Node<E> right) {
            this.right = right;
        }

        /**
         * @param parent the parent to set
         */
        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        /**
         * @return the value
         */
        public E getValue() {
            return value;
        }

        /**
         * @return the height
         */
        public int getHeight() {
            return height;
        }

        /**
         * @return the left
         */
        public Node<E> getLeft() {
            return left;
        }

        /**
         * @return the right
         */
        public Node<E> getRight() {
            return right;
        }

        /**
         * @return the parent
         */
        public Node<E> getParent() {
            return parent;
        }

        @Override
        public String toString() {
            return this.getHeight() + " " + this.getValue();
        }

    }
}