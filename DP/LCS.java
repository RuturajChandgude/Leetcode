//recursion
class Solution {
    public int f(int i, int j, String s1, String s2) {
        if (i < 0 || j < 0)
            return 0;

        if (s1.charAt(i) == s2.charAt(j))
            return 1 + f(i - 1, j - 1, s1, s2);

        return 0 + Math.max(f(i - 1, j, s1, s2), f(i, j - 1, s1, s2));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        return f(m - 1, n - 1, text1, text2);
    }
}

// memoization
class Solution {
    public int f(int i, int j, String s1, String s2, int dp[][]) {
        if (i < 0 || j < 0)
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];
        if (s1.charAt(i) == s2.charAt(j))
            return dp[i][j] = 1 + f(i - 1, j - 1, s1, s2, dp);

        return dp[i][j] = 0 + Math.max(f(i - 1, j, s1, s2, dp), f(i, j - 1, s1, s2, dp));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int dp[][] = new int[m][n];

        for (int row[] : dp) {
            Arrays.fill(row, -1);
        }
        return f(m - 1, n - 1, text1, text2, dp);
    }
}

// tabulation
class Solution {

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int dp[][] = new int[m + 1][n + 1];

        for (int row[] : dp) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i <= m; i++)
            dp[i][0] = 0;

        for (int j = 0; j <= n; j++)
            dp[0][j] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];

                else
                    dp[i][j] = 0 + Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[m][n];
    }
}

// space optimization
class Solution {

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int prev[] = new int[n + 1];
        int cur[] = new int[n + 1];

        for (int j = 0; j <= n; j++)
            prev[j] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    cur[j] = 1 + prev[j - 1];

                else
                    cur[j] = 0 + Math.max(prev[j], cur[j - 1]);
            }
            prev = cur.clone();
        }

        return prev[n];
    }
}