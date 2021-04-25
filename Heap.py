import abc
from typing import List


class Heap:
    def __init__(self, arr: List[int]):
        self.__arr = arr
        self.__heapSize = len(arr)
        self.__arrSize = len(arr)
        self.buildHeap()

    def parentOf(self, i):
        return i//2

    def rightOf(self, i):
        return i*2+2

    def leftOf(self, i):
        return i*2+1

    def heapify(self, i: int):
        largest = i
        left = i*2 + 1
        right = i*2 + 2
        if left < self.__heapSize and self.__arr[i] < self.__arr[left]:
            largest = left
        if right < self.__heapSize and self.__arr[largest] < self.__arr[right]:
            largest = right
        if largest != i:
            temp = self.__arr[i]
            self.__arr[i] = self.__arr[largest]
            self.__arr[largest] = temp
            self.heapify(largest)

    def buildHeap(self):
        lastIndex = len(self.__arr)//2
        for i in range(lastIndex + 1):
            self.heapify(lastIndex - i)
        return self

    def heapSort(self) -> List[int]:
        lastIndex = self.__arrSize - 1
        for i in range(lastIndex):
            temp = self.__arr[lastIndex - i]
            self.__arr[lastIndex - i] = self.__arr[0]
            self.__arr[0] = temp
            self.__heapSize -= 1
            self.heapify(0)
        self.__heapSize = self.__arrSize
        return self.__arr

    def __str__(self) -> str:
        return self.__arr.__str__()


heap = Heap([5, 13, 2, 25, 7, 17, 20, 8, 4])
arr = heap.heapSort()
print(arr)
print(heap.buildHeap())
