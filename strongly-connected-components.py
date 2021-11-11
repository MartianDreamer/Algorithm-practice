import threading
import time
from typing import Dict, List
import sys


def read_file(file_path) -> List[List[int]]:
    file = open(file_path, "r")
    lines = file.readlines()
    result = []
    max_number = 0
    for line in lines:
        num1 = int(line.split()[0])
        max_number = num1 if num1 > max_number else max_number

    for i in range(0, max_number):
        result.append([])
    for line in lines:
        num1 = int(line.split()[0])
        num2 = int(line.split()[1])
        result[num1-1].append(num2-1)
    return result


def first_dfs(graph: List):
    count_element = len(graph)
    is_visited = []
    finish_time = []
    for i in range(0, count_element):
        is_visited.append(False)
        finish_time.append(0)
    result = None
    time = 0
    for vertex in range(len(graph)):
        if not is_visited[vertex]:
            result = first_dfs_visit(
                graph, vertex, is_visited, finish_time, time)
            time = result[0]
    return result


def first_dfs_visit(graph: List, vertex: int, is_visited: List, finish_time: List, time: int):
    is_visited[vertex] = True
    for node in graph[vertex]:
        if not is_visited[node]:
            time = first_dfs_visit(
                graph, node, is_visited, finish_time, time)[0]
    time += 1
    finish_time[vertex] = time
    return [time, finish_time]


def second_dfs(graph: List):
    count_element = len(graph)
    is_visited = []
    leaders = []
    counts = []
    for i in range(0, count_element):
        is_visited.append(False)
        leaders.append(i)
    for vertex in range(len(graph)-1, -1, -1):
        if not is_visited[vertex]:
            count = second_dfs_visit(
                graph, vertex, is_visited, leaders, vertex, 0)
            counts.append(count)
    return counts


def second_dfs_visit(graph: List, vertex: int, is_visited: List, leaders: List, leader: int, count: int):
    leaders[vertex] = leader
    count += 1
    is_visited[vertex] = True
    for node in graph[vertex]:
        if not is_visited[node]:
            count = second_dfs_visit(
                graph, node, is_visited, leaders, leader, count)
    return count


def reverse_arc(graph: List):
    new_graph = []
    for i in range(len(graph)):
        new_graph.append([])
    for i in range(len(graph)):
        for vertex in graph[i]:
            new_graph[vertex].append(i)
    return new_graph


def adjust_vertices(graph: List, new_names: List):
    new_graph = []
    for i in range(len(graph)):
        new_graph.append([])
    for i in range(len(new_names)):
        for vertex in graph[i]:
            new_graph[new_names[i]].append(new_names[vertex])
    return new_graph


def scc(graph: List):
    finish_time: List = first_dfs(graph)[1]
    for i in range(len(finish_time)):
        finish_time[i] -= 1
    graph = reverse_arc(graph)
    graph = adjust_vertices(graph, finish_time)
    result = second_dfs(graph)
    return result


def main():
    print(sys.getrecursionlimit())
    graph = read_file("../Coursera/scc.txt")
    start = time.time()
    result = scc(graph)
    result.sort(reverse=True)
    result = result[:5]
    print(result)
    print('elapsed time: ', time.time() - start)


sys.setrecursionlimit(2097152)    # adjust numbers
threading.stack_size(134217728)   # for your needs
thread = threading.Thread(target=main)
thread.start()
