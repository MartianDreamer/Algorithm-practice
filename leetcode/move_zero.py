from typing import List


class Solution:
    def moveZeroes(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        i = 0
        lennums = len(nums)
        while(i < lennums):
            if nums[i] == 0:
                num = nums.pop(i)
                nums.append(num)
                lennums -= 1
                continue
            i += 1
