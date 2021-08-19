def multiply(number1, number2):
    number1_len = len(str(number1))
    number2_len = len(str(number2))
    if (number1_len == 1 and number2_len == 1):
        return number1*number2
    m = max(number1_len, number2_len)
    m2 = m//2
    (a, b) = split(m2, number1)
    (c, d) = split(m2, number2)
    ac = multiply(a, c)
    bd = multiply(b, d)
    adbc = multiply(a+b, c+d)
    adbc -= (ac+bd)
    return ac*pow(10, m2*2) + adbc*pow(10, m2) + bd


def split(m, number):
    str_number = str(number)
    number_len = len(str_number)
    if m < number_len:
        return (int(str_number[:number_len-m]), int(str_number[number_len-m:]))
    if m >= number_len:
        return (0, number)


result = multiply(3141592653589793238462643383279502884197169399375105820974944592,
                  2718281828459045235360287471352662497757247093699959574966967627)
print(result == 3141592653589793238462643383279502884197169399375105820974944592 *
      2718281828459045235360287471352662497757247093699959574966967627)
print(result)
