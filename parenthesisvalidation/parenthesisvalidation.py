class Solution(object):
    def isValid(self, s: str):
        pList = []
        pPair = {"{": "}", "[": "]", "(": ")"}
        for i in range(0, len(s)):
            char = s[i]
            if char in pPair.keys():
                pList.append(char)
            if char in pPair.values():
                if len(pList) > 0 and char == pPair.get(pList[len(pList) - 1]):
                    pList.pop()
                    continue
                return False
        return len(pList) == 0

print(Solution().isValid('{[}]'))
