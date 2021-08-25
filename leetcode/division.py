class Solution:
    def divide(self, dividend: int, divisor: int) -> int:
        dividend_is_positive = dividend > 0
        divisor_is_positive = divisor > 0
        abs_dividend = dividend if dividend_is_positive else -dividend
        abs_divisor = divisor if divisor_is_positive else -divisor
        if abs_dividend < abs_divisor or abs_dividend == 0:
            return 0
        result = 1
        new_abs_divisor = abs_divisor
        while(new_abs_divisor < abs_dividend >> 1):
            result = result << 1
            new_abs_divisor = new_abs_divisor << 1
        abs_dividend -= new_abs_divisor
        remaining_result = self.divide(abs_dividend, abs_divisor)
        result += remaining_result
        limit = 2147483648
        if dividend_is_positive != divisor_is_positive:
            result = -result
        if (result > (limit - 1)):
            return limit - 1
        if (result < -(limit)):
            return - (limit)
        return result


print(Solution().divide( 2147483648, - 1))
