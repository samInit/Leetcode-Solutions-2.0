class Solution {
    int len = 0;
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n+1];
        for(int i =0; i < n; i++){
            for(int j = 0; j <=n; j++){
                dp[i][j] = -1;
            }
        }
        return helper(dp, n, nums, 0,-1);
    }
    private int helper(int[][] dp, int n, int[] nums, int indx, int prev_index){
        if(indx==n) return 0;
        if(dp[indx][prev_index+1] != -1) return dp[indx][prev_index+1];
        len = 0 + helper(dp, n, nums, indx+1, prev_index);
        if(prev_index == -1 || nums[indx]>nums[prev_index]){
            len = Math.max(len, 1 + helper(dp, n, nums, indx+1, indx));
        }
        return dp[indx][prev_index+1]=len;
    }
}