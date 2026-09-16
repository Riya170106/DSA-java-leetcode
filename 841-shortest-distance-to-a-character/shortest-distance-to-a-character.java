class Solution {
    public int[] shortestToChar(String s, char c) {
        int n=s.length();
        int[]ans=new int [n];
        for(int i=0;i<n;i++){
            int min=n;
            for(int j=0;j<n;j++){
                if(s.charAt(j)==c){
                    min=Math.min(min,Math.abs(i-j));
                }
            }
            ans[i]=min;
        }
        return ans;
    }
}