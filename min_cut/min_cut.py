from typing import Dict, List, Tuple
import random


def load_graph():
    graph = {}
    file = open("./kargerMinCut.txt")
    lines = file.readlines()
    file.close()
    lines = [line.split("\t")[:-1] for line in lines]
    for line in lines:
        graph[line[0]] = line[1:]
    return graph


def choose_random_edge(graph: Dict):
    v1 = [*graph.keys()][random.randint(0, len(graph) - 1)]
    v2 = graph[v1][random.randint(0, len(graph[v1])-1)]
    return (v1, v2)


def contract_edge(edge: Tuple, graph: Dict):
    v1 = edge[0]
    graph[v1].extend(graph[edge[1]])
    graph.pop(edge[1])

    for k, v in graph.items():
        if edge[1] not in v:
            continue
        while (edge[1] in v):
            v[v.index(edge[1])] = v1

    while (v1 in graph[v1]):
        graph[v1].pop(graph[v1].index(v1))


oput = open("./output.txt", "w")
for i in range(20):
    g = load_graph()
    while (len(g) > 2):
        edge = choose_random_edge(g)
        contract_edge(edge, g)
    oput.write(str(len(g[[*g.keys()][0]])) + " \n")
oput.close()
