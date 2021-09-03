package leetcode.flood_fill;

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor)
            return image;
        int oldColor = image[sr][sc];
        image[sr][sc] = newColor;
        if (sc + 1 < image[sr].length && image[sr][sc + 1] == oldColor) {
            image = floodFill(image, sr, sc + 1, newColor);
        }
        if (sc - 1 >= 0 && image[sr][sc - 1] == oldColor) {
            image = floodFill(image, sr, sc - 1, newColor);
        }
        if (sr + 1 < image.length && image[sr + 1][sc] == oldColor) {
            image = floodFill(image, sr + 1, sc, newColor);
        }
        if (sr - 1 >= 0 && image[sr - 1][sc] == oldColor) {
            image = floodFill(image, sr - 1, sc, newColor);
        }
        return image;
    }

    public static void main(String[] args) {
        int[][] image = { { 0, 0, 0 }, { 0, 1, 1 } };
        Solution solution = new Solution();
        image = solution.floodFill(image, 1, 1, 2);
        System.out.println(image);
    }
}
