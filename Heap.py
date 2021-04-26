import abc
from typing import List


class Heap:
    def __init__(self, arr: List[int]):
        self.__arr = arr
        self.__heapSize = len(arr)
        self.__arrSize = len(arr)
        self.buildHeap()

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

    def heapSort(self) -> List[int]:
        lastIndex = self.__arrSize - 1
        for i in range(lastIndex):
            temp = self.__arr[lastIndex - i]
            self.__arr[lastIndex - i] = self.__arr[0]
            self.__arr[0] = temp
            self.__heapSize -= 1
            self.__heapify(0)
        self.__heapSize = self.__arrSize
        return self.__arr

    def maxHeap(self) -> int:
        return self.__arr[0]

    def extractMax(self) -> int:
        if self.__heapSize < 1:
            raise Exception("No element in heap")
        maxElement = self.__arr[0]
        self.__arr[0] = self.__arr[self.__heapSize - 1]
        self.__arr[self.__heapSize - 1] = maxElement
        self.__heapSize -= 1
        self.__heapify(0)
        return maxElement

    def __str__(self) -> str:
        return self.__arr.__str__()
