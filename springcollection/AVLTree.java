import java.util.LinkedList;
import java.util.List;

public class AVLTree<T extends Comparable<T>> {
    private Node<T> root;
    private int size;

    /**
     * 
     */
    public AVLTree() {
        size = 0;
    }

    /**
     * Return size of the tree;
     */
    public int size() {
        return this.size;
    }

    private int getHeight(Node<T> node) {
        if (node == null)
            return -1;
        return node.getHeight();
    }

    private void updateHeight(Node<T> node) {
        node.setHeight(Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1);
    }

    private boolean isViolated(Node<T> node) {
        return Math.abs(getHeight(node.getLeft()) - getHeight(node.getRight())) > 1;
    }

    private boolean isBalanced(Node<T> node) {
        return getHeight(node.getLeft()) - getHeight(node.getRight()) == 0;
    }

    private boolean isLeftHeavy(Node<T> node) {
        return getHeight(node.getLeft()) - getHeight(node.getRight()) > 0;
    }

    private boolean isRightHeavy(Node<T> node) {
        return getHeight(node.getRight()) - getHeight(node.getLeft()) > 0;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> rightChild = node.getRight();
        Node<T> leftGrandChild = rightChild.getLeft();
        node.setRight(leftGrandChild);
        updateHeight(node);
        rightChild.setLeft(node);
        updateHeight(rightChild);
        return rightChild;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> leftChild = node.getLeft();
        Node<T> rightGrandChild = leftChild.getRight();
        node.setLeft(rightGrandChild);
        updateHeight(node);
        leftChild.setRight(node);
        updateHeight(leftChild);
        return leftChild;
    }

    public Node<T> insertAtNode(Node<T> node, T key) {
        assert key != null;
        if (node == null) {
            node = new Node<>(key);
            size++;
            return node;
        }

        if (node.getKey().compareTo(key) > 0) {
            node.setLeft(insertAtNode(node.getLeft(), key));
        } else if (node.getKey().compareTo(key) < 0) {
            node.setRight(insertAtNode(node.getRight(), key));
        } else {
            return node;
        }

        updateHeight(node);
        if (isViolated(node)) {
            if (isLeftHeavy(node)) {
                Node<T> leftChild = node.getLeft();
                if (isLeftHeavy(leftChild) || isBalanced(leftChild)) {
                    node = rotateRight(node);
                    return node;
                } else if (isRightHeavy(leftChild)) {
                    leftChild = rotateLeft(leftChild);
                    node = rotateRight(node);
                    return node;
                }
            }
            if (isRightHeavy(node)) {
                Node<T> rightChild = node.getRight();
                if (isRightHeavy(node) || isBalanced(node)) {
                    node = rotateLeft(node);
                    return node;
                } else if (isLeftHeavy(node)) {
                    rightChild = rotateRight(rightChild);
                    node = rotateLeft(node);
                    return node;
                }
            }
        }
        return node;
    }

    public void insert(T value) {
        this.root = insertAtNode(root, value);
    }

    public void inOrderTraverse(Node<T> node, List<Node<T>> list) {
        if (node.getLeft() != null)
            inOrderTraverse(node.getLeft(), list);
        list.add(node);
        if (node.getRight() != null)
            inOrderTraverse(node.getRight(), list);
    }

    @Override
    public String toString() {
        List<Node<T>> list = new LinkedList<>();
        inOrderTraverse(root, list);
        return list.toString();
    }

    public class Node<E extends Comparable<E>> {
        private E key;
        private int height;
        private Node<E> right;
        private Node<E> left;

        /**
         * @param key
         * @param height
         */
        public Node(E key) {
            this.key = key;
            this.height = 0;
        }

        /**
         * @return the key
         */
        public E getKey() {
            return key;
        }

        /**
         * @param key the key to set
         */
        private void setKey(E key) {
            this.key = key;
        }

        /**
         * @return the height
         */
        public int getHeight() {
            return height;
        }

        /**
         * @param height the height to set
         */
        private void setHeight(int height) {
            this.height = height;
        }

        /**
         * @return the right
         */
        public Node<E> getRight() {
            return right;
        }

        /**
         * @param right the right to set
         */
        private void setRight(Node<E> right) {
            this.right = right;
        }

        /**
         * @return the left
         */
        public Node<E> getLeft() {
            return left;
        }

        /**
         * @param left the left to set
         */
        private void setLeft(Node<E> left) {
            this.left = left;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */

        @Override
        public String toString() {
            return "Node [height=" + height + ", key=" + key + "]";
        }

    }
}
