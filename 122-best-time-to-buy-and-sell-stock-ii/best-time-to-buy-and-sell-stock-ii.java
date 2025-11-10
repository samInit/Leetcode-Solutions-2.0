class Solution {
    int profit = 0;
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for(int i = 0; i < n; i++){
            for(int j = 0; j<2; j++){
                dp[i][j] = -1;
            }
        }
        return helper(dp, prices, 0, 1);
    }
    private int helper(int[][] dp, int[] prices, int i, int buy){
        if(i==prices.length) return 0;
        if(dp[i][buy] != -1) return dp[i][buy];
        if(buy==1){ 
            profit = Math.max((-prices[i]+helper(dp, prices, i+1, 0)),(0+helper(dp, prices, i+1, 1)));
        }
        else{
            profit = Math.max((prices[i]+helper(dp, prices, i+1, 1)),(0+helper(dp, prices, i+1, 0)));
        }
        return dp[i][buy] = profit;
    }
}