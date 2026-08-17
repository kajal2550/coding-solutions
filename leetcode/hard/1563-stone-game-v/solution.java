 

class Solution {
        public int stoneGameV(int[] stoneValue) {
                int n = stoneValue.length;
                        int[] prefix = new int[n + 1];
                                for (int i = 0; i < n; i++) {
                                            prefix[i + 1] = prefix[i] + stoneValue[i];
                                                    }
                                                            Integer[][] memo = new Integer[n][n];
                                                                    return dfs(stoneValue, prefix, 0, n - 1, memo);
                                                                        }

                                                                            private int dfs(int[] stoneValue, int[] prefix, int i, int j, Integer[][] memo) {
                                                                                    if (i >= j) return 0;
                                                                                            if (memo[i][j] != null) return memo[i][j];
                                                                                                    int maxScore = 0;
                                                                                                            for (int k = i; k < j; k++) {
                                                                                                                        int leftSum = prefix[k + 1] - prefix[i];
                                                                                                                                    int rightSum = prefix[j + 1] - prefix[k + 1];
                                                                                                                                                if (leftSum < rightSum) {
                                                                                                                                                                maxScore = Math.max(maxScore, leftSum + dfs(stoneValue, prefix, i, k, memo));
                                                                                                                                                                            } else if (leftSum > rightSum) {
                                                                                                                                                                                            maxScore = Math.max(maxScore, rightSum + dfs(stoneValue, prefix, k + 1, j, memo));
                                                                                                                                                                                                        } else {
                                                                                                                                                                                                                        maxScore = Math.max(maxScore, leftSum + Math.max(dfs(stoneValue, prefix, i, k, memo), dfs(stoneValue, prefix, k + 1, j, memo)));
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                    return memo[i][j] = maxScore;
                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                        }
 









