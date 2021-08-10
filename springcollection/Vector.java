package springcollection;

public interface Vector<T> {
    int size();

    boolean isEmpty();

    T get(int index);

    void push(T item);

    void insert(int index, T item);

    T pop();

    T delete(int index);

    T remove(T item);

    int find(T item);
}
