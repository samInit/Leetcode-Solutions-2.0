class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        final int MAX_VALUE = 50;
        int[] freq = new int[MAX_VALUE + 1];
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            freq[nums[i]]++;
            windowSum += nums[i];
        }
        for (int start = 0; start < n - k + 1; start++) {
            result[start] = computeXSum(freq, x, windowSum);
            if (start + k < n) {
                int out = nums[start];
                int in = nums[start + k];
                freq[out]--;
                freq[in]++;
                windowSum += in - out;
            }
        }
        return result;
    }
    private int computeXSum(int[] freq, int x, int windowSum) {
        List<int[]> list = new ArrayList<>();
        for (int val = 1; val < freq.length; val++) {
            if (freq[val] > 0) {
                list.add(new int[]{freq[val], val});
            }
        }
        if (list.size() < x) {
            return windowSum;
        }
        list.sort((a, b) -> {
            if (a[0] != b[0]) return b[0] - a[0];
            return b[1] - a[1];
        });
        int sum = 0;
        for (int i = 0; i < x; i++) {
            sum += list.get(i)[0] * list.get(i)[1];
        }
        return sum;
    }
}
