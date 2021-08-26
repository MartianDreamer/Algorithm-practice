from typing import List


class Solution:
    def search(self, nums: List[int], target: int) -> int:
        middle = (len(nums))//2
        if (target < nums[0] or target > nums[len(nums)-1]):
            return -1
        if len(nums) == 1 and nums[0] != target:
            return -1
        if len(nums) == 1 and nums[0] == target:
            return 0
        if nums[middle] == target:
            return middle
        if (nums[middle] > target):
            return self.search(nums[:middle], target)
        if (nums[middle] < target):
            index = self.search(nums[middle:], target)
            if index == -1:
                return -1
            return middle + index


index = Solution().search([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12], 100)
print(index)
