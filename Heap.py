from typing import List


class Heap:
    def __init__(self, arr: List):
        self.arr = arr

    def parentOf(self, i):
        return i//2

    def rightOf(self, i):
        return i*2+2

    def leftOf(self, i):
        return i*2+1

    def size(self):
        return len(self.arr)

    def heapify(self, i):
        left = self.leftOf(i)
        right = self.rightOf(i)
        largest = None
        if left < self.size() and self.arr[i] < self.arr[left]:
            largest = left
        else:
            largest = i
        if right < self.size() and self.arr[largest] < self.arr[right]:
            largest = right
        if largest != i:
            temp = self.arr[i]
            self.arr[i] = self.arr[largest]
            self.arr[largest] = temp
            self.heapify(largest)


heap = Heap([27, 17, 3, 16, 13, 10, 1, 5, 7, 12, 4, 8, 9, 0])
print(heap.arr)
heap.heapify(8)
print(heap.arr)
