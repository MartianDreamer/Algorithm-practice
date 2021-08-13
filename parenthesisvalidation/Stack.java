package parenthesisvalidation;

import java.util.Arrays;

public class Stack<T> {
    private T[] values;
    private int size;
    private int capacity;

    /**
     * 
     */
    @SuppressWarnings("unchecked")
    public Stack() {
        capacity = 8;
        size = 0;
        values = (T[]) new Object[capacity];
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public void push(T item) {
        if (size >= capacity) {
            capacity *= 2;
            T[] newValues = (T[]) new Object[capacity];
            for (int i = 0; i < size; i++) {
                newValues[i] = values[i];
            }
            newValues[size] = item;
            size++;
            values = newValues;
            return;
        }
        values[size] = item;
        size++;
    }

    public T pop() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        var result = values[size - 1];
        values[--size] = null;
        return result;
    }

    public T peek(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return values[index];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "Stack [values=" + Arrays.toString(values) + "]";
    }

}
