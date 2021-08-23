from typing import Dict, List


class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        dic = {}
        for i in range(len(nums)):
            if target - nums[i] in dic:
                dic[target - nums[i]].append(i)
                continue
            dic.setdefault(nums[i], [i])
        for (k, v) in dic.items():
            if (len(v) == 2):
                return v

a = [9,3,5,8,11,60]

solution = Solution()
print(solution.twoSum(a,69))
