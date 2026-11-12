class Solution {
    public int[] numberOfLines(int[] widths, String s) {
        int line=1;
        int sum=0;
        for(int i=0;i<s.length();i++){
            int w=widths[s.charAt(i)-'a'];
            if(sum+w>100){
                line++;
                sum=w;
            }else{
                sum+=w;
            }
        }
        int[]result=new int[2];
        result[0]=line;
        result[1]=sum;
        return result;
    }
}