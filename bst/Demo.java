package bst;

public class Demo {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(new bst.Node(100));
        bst.insert(new bst.Node(160));
        bst.insert(new bst.Node(50));
        bst.insert(new bst.Node(40));
        bst.insert(new bst.Node(60));
        bst.insert(new bst.Node(130));
        bst.insert(new bst.Node(200));
        bst.insert(new bst.Node(140));
        bst.insert(new bst.Node(120));
        bst.insert(new bst.Node(210));
        bst.insert(new bst.Node(180));
        bst.insert(new bst.Node(240));
        bst.insert(new bst.Node(230));
        System.out.println(bst.getDepth());
        System.out.println(bst);
    }
}
