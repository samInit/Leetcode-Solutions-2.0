class Solution {
    public int smallestNumber(int n) {
        int totalNumBits = (int)( Math.floor (Math.log(n)/Math.log(2))) + 1;
        return (1 << totalNumBits)-1;
    }
}