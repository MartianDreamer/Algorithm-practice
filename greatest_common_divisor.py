def greatest_common_divisor(number1, number2):
    if number2 % number1 == 0:
        return number1
    if number1 % number2 == 0:
        return number2
    if number2 > number1:
        return greatest_common_divisor(number1, number2 - number1)
    else:
        return greatest_common_divisor(number1 - number2, number2)


print(greatest_common_divisor(3*123, 4*123))
