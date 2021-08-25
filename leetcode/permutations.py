from typing import List


class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        if len(nums) == 1:
            return [nums]
        result = []
        for i in range(len(nums)):
            new_nums = []
            new_nums.extend(nums[:i])
            new_nums.extend(nums[i+1:])
            permuatations = self.permute(new_nums)
            for permutation in permuatations:
                permutation.append(nums[i])
            result.extend(permuatations)
        return result


print(Solution().permute([1, 2, 3]))
