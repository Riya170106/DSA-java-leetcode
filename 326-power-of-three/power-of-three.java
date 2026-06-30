class Solution {
    public boolean isPowerOfThree(int n) {
        if(n<=0){
            return false;
        }
        int x=(int)Math.round(Math.log(n)/Math.log(3));
        if(Math.pow(3,x)==n){
            return true;
        }
        return false;
    }
}