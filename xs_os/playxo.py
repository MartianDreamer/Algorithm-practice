from typing import List
from typing import Set


def is_winnable(list: List) -> bool:
    check = dict()
    for symbol in list:
        if symbol not in check.keys():
            check[symbol] = 1
        else:
            check[symbol] += 1
    return len(check) <= 2 and "." in check.keys() and "O" not in check.keys()


def count_character(list: List, target):
    count = 0
    for char in list:
        count += 1 if char == target else 0
    return count


def cheat(size: int, string: str):
    rows: List[List] = []
    columns: List[List] = []
    for i in range(size):
        rows.append(string[i*(size+1):(i+1)*(size+1)])
        temp = []
        temp[:0] = rows[i][:size]
        rows[i] = temp
        columns.append([])
        for row in range(size):
            columns[i].append(string[row*(size+1)+i])

    winnable = dict()
    least_play = None
    for i in range(size):
        if is_winnable(rows[i]):
            play_count = count_character(rows[i], ".")
            if least_play == None or least_play > play_count:
                least_play = play_count
            if play_count not in winnable.keys():
                if play_count == 1:
                    winnable[play_count] = set()
                    winnable[play_count].add(i*size+rows[i].index("."))
                else:
                    winnable[play_count] = 1
            else:
                if play_count == 1:
                    winnable[play_count].add(i*size+rows[i].index("."))
                else:
                    winnable[play_count] += 1
        if is_winnable(columns[i]):
            play_count = count_character(columns[i], ".")
            if least_play == None or least_play > play_count:
                least_play = play_count
            if play_count not in winnable.keys():
                if play_count == 1:
                    winnable[play_count] = set()
                    winnable[play_count].add(columns[i].index(".")*size+i)
                else:
                    winnable[play_count] = 1
            else:
                if play_count == 1:
                    winnable[play_count].add(columns[i].index(".")*size+i)
                else:
                    winnable[play_count] += 1

    if least_play == 1:
        return (least_play, len(winnable[least_play]))
    return (least_play, winnable[least_play]) if least_play != None else "Impossible"


# print(cheat(3, "OXO\nX.X\nOXO\n"))

fopen = open("./xs_and_os_input.txt", "r")
ouput = open("./output.txt", "w")
count = 1
count_line = 0
size = 0
lines = fopen.readlines()
while(count_line < len(lines)):
    if count_line == 0:
        count_line += 1
        continue
    size_line = lines[count_line][:len(lines[count_line])-1]
    size = int(size_line)
    i = 0
    string = ""
    count_line += 1
    while (i < size):
        string += lines[count_line + i]
        i += 1
    result = cheat(size, string)
    if result == "Impossible":
        ouput.write(f"Case #{count}: {result} \n")
    else:
        ouput.write(f"Case #{count}: {result[0]} {result[1]} \n")
    count += 1
    count_line += i
