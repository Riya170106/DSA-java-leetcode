class Solution {
    public int totalNumbers(int[] digits) {
        int d=digits.length;
        int ans=0;
        for(int num=100;num<=998;num+=2){
            int n=num;
            int a=n%10;
            n/=10;
            int b=n%10;
            n/=10;
            int c=n%10;
            int []freq=new int [10];
            for(int i=0;i<d;i++){
                freq[digits[i]]++;
            }
            if(freq[a]>0){
                freq[a]--;
            }else{
                continue;
            }
            if(freq[b]>0){
                freq[b]--;
            }else{
                continue;
            }
            if(freq[c]>0){
                ans++;
            }
        }
        return ans;
    }
}