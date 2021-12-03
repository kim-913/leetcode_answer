class Solution {
    private int m;
    private int n;

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        m = image.length;
        n = image[0].length;
        if (image[sr][sc] != newColor)
            dfs(image, image[sr][sc], sr, sc, newColor);
        return image;
    }

    private void dfs(int[][] image, int color, int x, int y, int newColor) {
        if (x < 0 || x >= m || y < 0 || y >= n || image[x][y] != color)
            return;
        image[x][y] = newColor;
        dfs(image, color, x - 1, y, newColor);
        dfs(image, color, x + 1, y, newColor);
        dfs(image, color, x, y - 1, newColor);
        dfs(image, color, x, y + 1, newColor);
    }
}