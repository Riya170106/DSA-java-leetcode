class Solution {
    public boolean checkDivisibility(int n) {
        int sum=0;
        int product=1;
        int ans;
        int x=n;
        while(x>0){
            int digit=x%10;
            sum+=digit;
            product*=digit;
            x/=10;
        }
        ans=sum+product;
        int result=n%ans;
        if(result==0){
            return true;
        }
        return false;
    }
}