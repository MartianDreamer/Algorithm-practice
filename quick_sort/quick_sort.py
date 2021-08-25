from typing import List


def buble_sort(list: List[List[int]]):
    for i in range(len(list)-1):
        for j in range(i+1, len(list)):
            if list[i][0] > list[j][0]:
                temp = list[i]
                list[i] = list[j]
                list[j] = temp
    return list


def choose_a_pivot(list: List[int], start, end, mode):
    if mode == 0:
        return start
    if mode == 1:
        return end
    if mode == 2:
        middle = (start + end + 1)//2 if (start + end +
                                          1) % 2 == 1 else (start + end + 1)//2 - 1
        values = buble_sort(
            [[list[start], start], [list[middle], middle], [list[end], end]])
        return values[1][1]


def partition(list: List[int], start_index, end_index):
    comparison = 0
    pivot_index = start_index
    for i in range(start_index+1, end_index+1):
        comparison += 1
        if list[i] < list[start_index]:
            pivot_index += 1
            if (i != pivot_index):
                temp = list[pivot_index]
                list[pivot_index] = list[i]
                list[i] = temp
    temp = list[start_index]
    list[start_index] = list[pivot_index]
    list[pivot_index] = temp
    return (pivot_index, comparison)


def quick_sort(list: List[int], start_index, end_index):
    if(start_index >= end_index):
        return 0
    pivot_index = choose_a_pivot(list, start_index, end_index, 2)
    if pivot_index != start_index:
        temp = list[start_index]
        list[start_index] = list[pivot_index]
        list[pivot_index] = temp
    (pivot_index, comparison) = partition(list, start_index, end_index)
    comparison += quick_sort(list, start_index, pivot_index-1)
    comparison += quick_sort(list, pivot_index+1, end_index)
    return comparison
