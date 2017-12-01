package Searcha2DMatrix;


class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0)
            return false;

        int r = 0, c = matrix[r].length - 1;
        while (r < matrix.length && c >= 0) {
            if (matrix[r][c] == target)
                return true;
            if (matrix[r][c] < target) {
                r++;
            } else {
                c--;
            }
        }
        return false;
    }
}