def count_inversions(count_list):
    if len(count_list) <= 1:
        return (0, count_list)
    (left_inversion_count, left_count_list) = count_inversions(
        count_list[:len(count_list)//2])
    (right_inversion_count, right_count_list) = count_inversions(
        count_list[len(count_list)//2:])
    split_inversion_count = 0
    i = 0
    j = 0
    sorted_count_list = []
    while (i < len(left_count_list) or j < len(right_count_list)):
        if (j < len(right_count_list) and i < len(left_count_list) and left_count_list[i] > right_count_list[j]):
            sorted_count_list.append(right_count_list[j])
            j += 1
            split_inversion_count += len(left_count_list) - i
            continue
        elif (j < len(right_count_list) and i < len(left_count_list) and left_count_list[i] <= right_count_list[j]):
            sorted_count_list.append(left_count_list[i])
            i += 1
            continue
        if (i == len(left_count_list)):
            sorted_count_list.append(right_count_list[j])
            j += 1
            continue
        if (j == len(right_count_list)):
            sorted_count_list.append(left_count_list[i])
            i += 1
    return (left_inversion_count + right_inversion_count + split_inversion_count, sorted_count_list)


arr = [82, 12, 38, 10, 15, 54, 51, 71, 95,
       12, 1, 21, 90, 10, 89, 97, 42, 1, 84, 92]

count = count_inversions(arr)
print(count)
