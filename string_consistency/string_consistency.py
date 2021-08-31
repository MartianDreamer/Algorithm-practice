def time_cost(string: str) -> int:
    vowel = ('A', 'O', 'U', 'E', 'I')
    letter_dict = dict()
    most_appeared_vowel = None
    sum_second_first = 0
    sum_second_second = 0
    most_appeared_consonent = None
    for i in range(len(string)-1):
        if string[i] not in (letter_dict.keys()):
            letter_dict[string[i]] = 1
        else:
            letter_dict[string[i]] += 1
    for key in letter_dict.keys():
        if key in vowel and (most_appeared_vowel == None or letter_dict[key] > letter_dict[most_appeared_vowel]):
            most_appeared_vowel = key
        if key not in vowel and (most_appeared_consonent == None or letter_dict[key] > letter_dict[most_appeared_consonent]):
            most_appeared_consonent = key
    for k, v in letter_dict.items():
        if k != most_appeared_vowel:
            if k in vowel:
                sum_second_first += v*2
            else:
                sum_second_first += v
        if k != most_appeared_consonent:
            if k in vowel:
                sum_second_second += v
            else:
                sum_second_second += v*2
    return sum_second_first if sum_second_first < sum_second_second else sum_second_second


fopen = open("./consistency_chapter_1_input.txt", "r")
ouput = open("./output.txt", "w")
count = 1
count_line = 0
sum_second = 0
for line in fopen:
    if count_line == 0:
        count_line += 1
        continue
    sum_second = time_cost(line)
    ouput.write(f"Case #{count}: {sum_second} \n")
    count += 1

# print(time_cost("QPQWJXRJJXBTKKGBKVXNSCQBHZTSFZRYCDZFYQJQWHWHYJVDRXSGWRLJNTHBXYBRBTVXBBPPCXRBFVXVNDQQTHHBKXZDPQZGSHWF\n"))
