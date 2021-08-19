from typing import List


class Heap:
    def __init__(self, arr: List[int]):
        self.__arr = arr
        self.__heapSize = len(arr)
        self.__arrSize = len(arr)
        self.buildHeap()

    def size(self) -> int:
        return self.__heapSize

    def __heapify(self, i: int):
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
            self.__heapify(largest)

    def buildHeap(self):
        lastIndex = len(self.__arr)//2
        for i in range(lastIndex + 1):
            self.__heapify(lastIndex - i)
        return self

    def maxHeap(self) -> int:
        return self.__arr[0]

    def extractMax(self) -> int:
        if self.__heapSize < 1:
            raise OutOfBoundException("No element in heap")
        maxElement = self.__arr[0]
        self.__arr[0] = self.__arr[self.__heapSize - 1]
        self.__arr[self.__heapSize - 1] = maxElement
        self.__heapSize -= 1
        self.__heapify(0)
        return maxElement

    def insert(self, number):
        if self.__heapSize < self.__arrSize:
            index = self.__heapSize
            self.__arr[index] = number
            self.__heapSize += 1
            parentIndex = (index - 1) / \
                2 if index % 2 == 1 else (index - 2) / 2
            while(parentIndex != 0):
                self.__heapify(parentIndex)
                parentIndex = (parentIndex - 1) / \
                    2 if parentIndex % 2 == 1 else (parentIndex - 2)/2
            return
        self.__arr.append(number)
        index = self.__heapSize
        self.__heapSize += 1
        self.__arrSize += 1
        parentIndex = (index - 1) / \
            2 if index % 2 == 1 else (index - 2) / 2
        while(parentIndex != 0):
            self.__heapify(parentIndex)
            parentIndex = (parentIndex - 1) / \
                2 if parentIndex % 2 == 1 else (parentIndex - 2)/2

    def __str__(self) -> str:
        return self.__arr.__str__()


class OutOfBoundException(Exception):
    pass


def heapSort(numbers: List[int]) -> List[int]:
    heap = Heap(numbers)
    result = []
    for i in range(heap.size()):
        result.insert(0, heap.extractMax())
    return result
