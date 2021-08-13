package searchinbitomicarray;

/**
 * SearchInBitomicArray
 */
public class SearchInBitomicArray {

    public int search(int number, int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;
        if (number < numbers[left] && number < numbers[right])
            return -1;
        if (number == numbers[left])
            return left;
        if (number == numbers[right])
            return right;
        int changePoint = (left + right) / 2;
        while (!(numbers[changePoint - 1] < numbers[changePoint] && numbers[changePoint + 1] < numbers[changePoint])) {
            if (numbers[changePoint] > numbers[changePoint - 1]) {
                left = changePoint;
                changePoint = (left + right) / 2;
                continue;
            }
            if (numbers[changePoint] > numbers[changePoint + 1]) {
                right = changePoint;
                changePoint = (left + right) / 2;
            }
        }
        if (number == numbers[changePoint])
            return changePoint;
        if (number > numbers[0]) {
            left = 0;
            right = changePoint;
            int mid = (left + right) / 2;
            while (numbers[mid] != number) {
                if (number == numbers[left]) {
                    return left;
                }
                if (number == numbers[right]) {
                    return right;
                }
                if (right - left == 1) {
                    break;
                }
                if (number < numbers[mid]) {
                    right = mid;
                    mid = (left + right) / 2;
                    continue;
                }
                if (number > numbers[mid]) {
                    left = mid;
                    mid = (left + right) / 2;
                }
            }
            if (number == numbers[mid])
                return mid;
        }
        if (number > numbers[0]) {
            left = changePoint;
            right = numbers.length - 1;
            int mid = (left + right) / 2;
            while (numbers[mid] != number) {
                if (number == numbers[left]) {
                    return left;
                }
                if (number == numbers[right]) {
                    return right;
                }
                if (right - left == 1) {
                    break;
                }
                if (number < numbers[mid]) {
                    left = mid;
                    mid = (left + right) / 2;
                    continue;
                }
                if (number > numbers[mid]) {
                    right = mid;
                    mid = (left + right) / 2;
                }
            }
            if (number == numbers[mid])
                return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = { 1, 4, 5, 6, 8, 9, 22, 18, 16, 14 };
        System.out.println(new SearchInBitomicArray().search(16, array));
    }
}