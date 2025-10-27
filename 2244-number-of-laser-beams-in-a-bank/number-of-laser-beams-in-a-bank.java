class Solution {
    public int numberOfBeams(String[] bank) {
        int prev = 0;
        int total = 0;
        for(String s:bank){
            int count = 0;
            for(char ch:s.toCharArray()){
                if(ch=='1')count++;
            }
            if(count==0) continue;
            total += prev*count;
            prev = count;
        }
        return total;
    }
}