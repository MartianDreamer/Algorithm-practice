

public class LinkedStack<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    /**
     * 
     */
    public LinkedStack() {
        this.size = 0;
    }

    public void push(T value) {
        Node<T> node = new Node<>(value);
        if (first == null) {
            first = node;
            last = node;
        }
        Node<T> oldFirst = first;
        first = node;
        node.next = oldFirst;
        oldFirst.previous = node;
        size++;
    }

    public T pop() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();
        T result = first.item;
        first = first.next;
        size--;
        return result;
    }

    public T peek(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Node<T> node = this.last;
        for (int i = 0; i < index; i++) {
            node = node.previous;
        }
        return node.item;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    /**
     * InnerLinkedStack
     */
    private class Node<V> {
        public T item;
        public Node<V> next;
        public Node<V> previous;

        Node(T item) {
            this.item = item;
        }
    }
}
