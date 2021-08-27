# The isBadVersion API is already defined for you.
# @param version, an integer
# @return an integer
# def isBadVersion(version):

def isBadVersion(number):
    if number >= pow(2, 28):
        return True
    return False


class Solution:
    def firstBadVersion(self, n):
        """
        :type n: int
        :rtype: int
        """
        if (isBadVersion(n) and not isBadVersion(n-1)):
            return n
        middle = n//2
        lastMiddle = 0
        if not isBadVersion(middle):
            while(not isBadVersion(middle)):
                lastMiddle = middle
                middle += (n - middle)//2
        else:
            while (isBadVersion(middle)):
                lastMiddle = middle
                middle = middle//2
        if (middle > lastMiddle):
            return self.firstBadVersion(middle)
        else:
            return self.firstBadVersion(lastMiddle)


bad = Solution().firstBadVersion(pow(2, 30))
print(bad)
print(pow(2, 28))
