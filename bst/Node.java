package bst;


public class Node {
    private int item;
    private Node left;
    private Node right;
    private Node parent;

    /**
     * @param item
     */
    public Node(int item) {
        this.item = item;
    }

    /**
     * @return the item
     */
    public int getItem() {
        return item;
    }

    /**
     * @return the left
     */
    public Node getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    void setLeft(Node left) {
        this.left = left;
    }

    /**
     * @return the right
     */
    public Node getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    void setRight(Node right) {
        this.right = right;
    }

    /**
     * @return the parent
     */
    public Node getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    void setParent(Node parent) {
        this.parent = parent;
    }

}
