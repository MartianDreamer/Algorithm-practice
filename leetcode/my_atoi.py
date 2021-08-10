class Solution:
    def myAtoi(self, s: str) -> int:
        number = ""
        result = ""
        for i in range(0, len(s)):
            if s[i] == "-" and (not number.isnumeric() and not number[1:].isnumeric()):
                number += "-"
                continue
            if s[i] == "+" and (not number.isnumeric() and not number[1:].isnumeric()):
                number += "+"
                continue
            if number == "" and s[i] == " ":
                continue
            if s[i].isnumeric():
                number += s[i]
                continue
            break
        result = number
        if result[1:].isnumeric() or result.isnumeric():
            int_result = int(result)
            if int_result >= 2**31:
                return 2**31 - 1
            if int_result < -2**31:
                return -2**31
            return int_result
        else:
            return 0


print(Solution().myAtoi("+12+-- "))
