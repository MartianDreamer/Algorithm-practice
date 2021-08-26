from typing import List


class Solution:
    def searchInsert(self, nums: List[int], target: int) -> int:
        middle = (len(nums))//2
        if target < nums[0]:
            return 0
        if target > nums[len(nums)-1]:
            return len(nums)
        if len(nums) == 1 and nums[0] < target:
            return -1
        if len(nums) == 1 and nums[0] > target:
            return 1
        if len(nums) == 1 and nums[0] == target:
            return 0
        if nums[middle] == target:
            return middle
        if (nums[middle] > target):
            return self.searchInsert(nums[:middle], target)
        if (nums[middle] < target):
            index = self.searchInsert(nums[middle:], target)
            if index == -1:
                return -1
            return middle + index
