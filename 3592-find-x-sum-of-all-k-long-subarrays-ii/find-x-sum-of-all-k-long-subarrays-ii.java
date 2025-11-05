class Pair {
    int count;
    int num;
    public Pair(int n, int c) { count = c; num = n; }
    public void increOccur(){
        count++;
    }
    public void decrOccur(){
        count--;
    }
    public long getCal(){
        return (long) num*count;
    }
    @Override
    public String toString(){
        return "num "+num+" count"+count;
    }
}

class PairComparator implements Comparator<Pair> {
    public int compare(Pair a, Pair b) {
        return CompareHelper.getComparedValue(a, b);
    }
}
class CompareHelper{
    public static int getComparedValue(Pair a, Pair b){
        if (a.count != b.count)
            return Integer.compare(b.count, a.count); 
        if (a.num != b.num)
            return Integer.compare(b.num, a.num);   
        return 0;
    }
}
class Solution {
    public long[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        long xSum=0;
        long[] answers = new long[(n-k)+1];
        Map<Integer, Pair> map = new HashMap<>();
        TreeSet<Pair> top = new TreeSet<>(new PairComparator());
        TreeSet<Pair> bottom = new TreeSet<>(new PairComparator());

        for(int i=0; i<k-1; i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i], new Pair(nums[i], 0));
            }
            Pair pair = map.get(nums[i]);
            boolean isTop = top.remove(pair);
            bottom.remove(pair);
            pair.increOccur();
            if(isTop){
                xSum+=nums[i];
            } else {
                xSum+=pair.getCal();
            }
            top.add(pair);
            if(top.size()>x){
                xSum-=top.last().getCal();
                bottom.add(top.pollLast());
            }

        }
        for(int i=k-1; i<n; i++){
            int startIndex=i-(k-1);
            int endIndex=i;

            if(!map.containsKey(nums[i])){
                map.put(nums[i], new Pair(nums[i], 0));
            }
            Pair pair = map.get(nums[i]); 
            boolean isTop=top.remove(pair);
            bottom.remove(pair);
            pair.increOccur();

            if(isTop){
                xSum+=nums[i];
            } else xSum+=pair.getCal();

            top.add(pair);
            if(top.size()>x){
                xSum-=top.last().getCal();
                bottom.add(top.pollLast());
            }
            answers[startIndex]=xSum;
            Pair removePair = map.get(nums[startIndex]);
            boolean isBottom = bottom.remove(removePair);
            isTop = top.remove(removePair);
            removePair.decrOccur();

            if(isBottom){
                if(removePair.count>0)
                bottom.add(removePair);
            } else if(isTop){
                xSum-=removePair.num;
                if(bottom.size()>0 && 
                CompareHelper.getComparedValue(removePair, bottom.first())>=0){
                    xSum-=removePair.getCal();
                    xSum+=bottom.first().getCal();
                    top.add(bottom.pollFirst());
                    if(removePair.count>0)
                    bottom.add(removePair);
                } else {
                    if(removePair.count>0)
                    top.add(removePair);
                }
            }


        }
        return answers;
    }
}
 