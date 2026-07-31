class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer>ans=new ArrayList<>();
        int n=num.length;
        int i=n-1;
        while(i>=0||k>0){
            int sum=k;
            if(i>=0){
                sum+=num[i];
                i--;
            }
            ans.add(sum%10);
            k=sum/10;
        }
        Collections.reverse(ans);
        return ans;
    }
}