package hard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName Solution.java
 * @Description TODO
 * @createTime 2018年11月28日 17:14:00
 */
public class Solution {
    /**
     * https://blog.csdn.net/mine_song/article/details/70200892
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        int[] queenList = new int[n];
        return placeQueen(queenList, 0, n);
    }

    public int placeQueen(int[] queenList, int row, int n) {
        if (row == n) {
            return 1;
        }

        int count = 0;
        for (int col = 0; col < n; col++) {
            if (isValid(queenList, row, col)) {
                queenList[row] = col;
                count += placeQueen(queenList, row + 1, n);
            }
        }

        return count;
    }

    public boolean isValid(int[] queenList, int row, int col) {
        for (int i = 0; i < row; i++) {
            int pos = queenList[i];
            if (pos == col) {
                return false;
            }
            if (pos + row - i == col) {
                return false;
            }
            if (pos - row + i == col) {
                return false;
            }
        }

        return true;
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<List<String>>();
        if (n <= 0) {
            return res;
        }

        int[] colVal = new int[n];
        placeQueen(n, res, 0, colVal);

        return res;
    }

    public void placeQueen(int n, List<List<String>> res, int row, int[] colVal) {
        if (row == n) {
            List<String> list = new ArrayList<String>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (j == colVal[i]) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                list.add(sb.toString());
            }
            res.add(list);
        } else {
            for (int i = 0; i < n; i++) {
                colVal[row] = i;
                if (isValid(colVal, row, i)) {
                    placeQueen(n, res, row + 1, colVal);
                }
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
    }
}
