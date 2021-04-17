class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        if len(s) <= 1:
            return len(s)
        count = 0
        start = 0
        longest = 0
        for i in range(0, len(s)):
            if s[i] not in s[start:i]:
                count += 1
                continue
            longest = count if count > longest else longest
            count = count - s[start:i].index(s[i])
            start = s.index(s[i], start, i) + 1
        longest = count if count > longest else longest
        return longest


print(Solution().lengthOfLongestSubstring("ddvdfam"))
