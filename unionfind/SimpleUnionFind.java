package unionfind;

/**
 * SimpleUnionFind
 */
public class SimpleUnionFind {

    private int[] array;
    private int[] size;
    private int componentCount;

    /**
     * 
     */
    public SimpleUnionFind(int numberOfElement) {
        if (!isValid(numberOfElement)) {
            throw new IllegalArgumentException();
        }
        this.array = new int[numberOfElement];
        this.size = new int[numberOfElement];
        this.componentCount = numberOfElement;
        for (int i = 0; i < numberOfElement; i++) {
            this.array[i] = i;
            this.size[i] = 1;
        }
    }

    public int getComponentCount() {
        return this.componentCount;
    }

    public int find(int index) {
        if (!isValid(index))
            throw new IllegalArgumentException();
        while (array[index] != index) {
            index = array[array[index]];
        }
        return index;
    }

    public void union(int first, int second) {
        int firstRoot = find(first);
        int secondRoot = find(second);
        if (firstRoot == secondRoot)
            return;

        if (size[firstRoot] >= size[secondRoot]) {
            array[secondRoot] = firstRoot;
            size[firstRoot] += size[secondRoot];
        } else {
            array[firstRoot] = secondRoot;
            size[secondRoot] += size[firstRoot];
        }
        componentCount--;
    }

    private boolean isValid(int number) {
        return number >= 0;
    }
}