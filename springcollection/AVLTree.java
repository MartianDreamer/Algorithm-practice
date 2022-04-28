package springcollection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AVLTree<T extends Comparable<T>> {
    private Node<T> root;
    private int size;

    /**
     * @return the root
     */
    public Node<T> getRoot() {
        return root;
    }

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

    private Node<T> balanceNode(Node<T> node) {
        assert node != null;
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

    private Node<T> insertAtNode(Node<T> node, T key) {
        assert key != null;
        if (node == null) {
            node = new Node<>(key);
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
        node = balanceNode(node);
        return node;
    }

    private Node<T> search(Node<T> node, T key) {
        assert key != null;
        if (node == null || node.getKey().equals(key)) {
            return node;
        }
        if (node.getKey().compareTo(key) > 0) {
            return search(node.getLeft(), key);
        }
        if (node.getKey().compareTo(key) < 0) {
            return search(node.getRight(), key);
        }
        return node;
    }

    private Node<T> successor(Node<T> node) {
        node = node.getRight();
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    private Node<T> delete(Node<T> node, T key) {
        assert key != null;
        if (node == null)
            return node;
        if (node.getKey().compareTo(key) > 0) {
            node.setLeft(delete(node.getLeft(), key));
        }
        if (node.getKey().compareTo(key) < 0) {
            node.setRight(delete(node.getRight(), key));
        }
        if (node.getKey().compareTo(key) == 0) {
            if (node.getLeft() == null && node.getRight() == null) {
                node = null;
            } else if (node.getLeft() == null ^ node.getRight() == null) {
                Node<T> temp = node.getLeft() == null ? node.getRight() : node.getLeft();
                node = temp;
            } else {
                Node<T> successor = successor(node);

                node.setKey(successor.getKey());

                node.setRight(delete(node.getRight(), successor.getKey()));
            }
        }
        if (node == null)
            return node;
        updateHeight(node);
        node = balanceNode(node);
        return node;
    }

    public void insert(T key) {
        this.root = insertAtNode(root, key);
        size++;
    }

    public Node<T> search(T key) {
        return search(root, key);
    }

    public void delete(T key) {
        this.root = delete(root, key);
        size--;
    }

    private void inOrderTraverse(Node<T> node, List<Node<T>> list) {

        if (node.getLeft() != null)
            inOrderTraverse(node.getLeft(), list);
        list.add(node);
        if (node.getRight() != null)
            inOrderTraverse(node.getRight(), list);
    }

    public List<Node<T>> inOrderTraverse() {
        List<Node<T>> list = new ArrayList<>(size);
        inOrderTraverse(root, list);
        return list;
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

        @Override
        public String toString() {
            return "Node [height=" + height + ", key=" + key + "]";
        }

    }
}
