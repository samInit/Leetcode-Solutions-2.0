class Solution {
    public int countValidSelections(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }
        int totalSum = prefix[n - 1];
        int total = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                int leftSum = prefix[i]; 
                if (Math.abs(totalSum - 2*leftSum) == 0)
                    total += 2;
                else if (Math.abs(totalSum - 2*leftSum) == 1)
                    total++;
            }
        }
        return total;
    }
}