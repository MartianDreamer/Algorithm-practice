from typing import List


class Solution:
    def rotate(self, nums: List[int], k: int) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        while(k > len(nums)):
            k -= len(nums)
        if k == len(nums) or k == 0:
            return
        temp = []
        for i in range(len(nums)-k, len(nums)):
            temp.append(nums[i])
        for i in range(k, len(nums)):
            nums[len(nums)-1-(i-k)] = nums[len(nums)-1-i]
        for i in range(0, k):
            nums[i] = temp[i]


listt = [1, 2, 3, 4, 5, 6, 7, 8]
Solution().rotate(listt, 9)
print(listt)
