class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer>ans=new ArrayList<>();
        for(int i=left;i<=right;i++){
            int x=i;
            while(x>0){
                int digit=x%10;
                if(digit==0||i%digit!=0){
                    break;
                }
                x/=10;
            }
            if(x==0){
                 ans.add(i);
            }
        }
        return ans;
    }
}