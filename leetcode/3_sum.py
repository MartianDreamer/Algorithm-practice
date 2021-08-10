from typing import List


class Solution:

    def twoSum(self, nums: List, total: int) -> List[List[int]]:
        output = dict()
        result = []
        for num in nums:
            if (total - num) not in output.keys():
                output.setdefault(num, None)
                continue
            if (total - num) in output.keys():
                if output[total - num] != None:
                    continue
                output[total - num] = num
        for key, value in output.items():
            if value == None:
                continue
            result.append([key, value])
        return result

    def threeSum(self, nums: List) -> List[List[int]]:

        return [[3]]


print(Solution().twoSum([1, 2, 3, 4, 0, 5, -1, 6, -2, -2], 4))
