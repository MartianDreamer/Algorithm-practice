package springcollection;

public class StandardVector<T> implements Vector<T> {

    private T[] array;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public StandardVector(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        size = 0;
        capacity = initialCapacity;
        array = (T[]) new Object[initialCapacity];
    }

    @SuppressWarnings("unchecked")
    public StandardVector() {
        size = 0;
        capacity = 1;
        array = (T[]) new Object[1];
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public T get(int index) {
        if (index >= this.size || index < 0)
            throw new IndexOutOfBoundsException();
        return array[index];
    }

    @Override
    @SuppressWarnings("unchecked")
    public void push(T item) {
        if (this.size >= this.capacity) {
            this.capacity *= 2;
            T[] tempArray = (T[]) new Object[this.capacity];
            for (int i = 0; i < this.size; i++) {
                tempArray[i] = this.array[i];
            }
            tempArray[this.size++] = item;
            this.array = tempArray;
            return;
        }
        this.array[this.size++] = item;
    }

    @Override
    @SuppressWarnings("unchecked")

    public void insert(int index, T item) {
        if (index > this.size || index < 0)
            throw new IndexOutOfBoundsException();
        if (this.size >= this.capacity) {
            this.capacity *= 2;
            T[] tempArray = (T[]) new Object[this.capacity];
            for (int i = 0; i < index; i++) {
                tempArray[i] = this.array[i];
            }
            tempArray[index] = item;
            for (int i = index + 1; i < this.size + 1; i++) {
                tempArray[i] = this.array[i - 1];
            }
            this.array = tempArray;
            this.size++;
            return;
        }
        for (int i = this.size; i > index; i--) {
            this.array[i] = this.array[i - 1];
        }
        this.array[index] = item;
        this.size++;
    }

    @Override
    public T pop() {
        T result = this.array[size - 1];
        this.array[--size] = null;
        return result;
    }

    @Override
    public T delete(int index) {
        if (index >= this.size || index < 0)
            throw new IndexOutOfBoundsException();
        T result = this.array[index];
        this.size--;
        for (int i = index; i < this.size; i++) {
            this.array[i] = this.array[i + 1];
        }
        this.array[size] = null;
        return result;
    }

    @Override
    public T remove(T item) {
        return delete(find(item));
    }

    @Override
    public int find(T item) {
        for (int i = 0; i < size; i++) {
            if (this.array[i].equals(item))
                return i;
        }
        return -1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        for (int i = 0; i < this.size; i++) {
            stringBuilder.append(this.array[i] + ", ");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 2);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

}
