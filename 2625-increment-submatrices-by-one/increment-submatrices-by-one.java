class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] arr = new int[n][n];
        // for(int i = 0; i < n; i++){
        //     for(int j = 0; j<n; j++){
        //         arr[i][j] = 0;
        //     }
        // }

        for(int[] query: queries){
            for(int row1 = query[0]; row1 <= query[2]; row1++){
                for(int col1 = query[1];col1 <= query[3]; col1++){
                    arr[row1][col1] += 1;
                }
            }
        }
        return arr;
    }
}