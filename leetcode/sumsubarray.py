def max_sum(number1, number2):
    total = number1 + number2
    if total >= number1 and total >= number2:
        return total
    elif number1 >= number2:
        return number1
    else:
        return number2


def sum_sub_array(number_list):
    if len(number_list) == 1:
        return number_list[0]
    mid = len(number_list)//2
    left = number_list[:mid]
    right = number_list[mid:]
    sum_left = sum_sub_array(left)
    sum_right = sum_sub_array(right)
    result = max_sum(sum_left, sum_right)
    return result


print(sum_sub_array([2, 13, 9, 6, 7, 3, 5, 15]))
