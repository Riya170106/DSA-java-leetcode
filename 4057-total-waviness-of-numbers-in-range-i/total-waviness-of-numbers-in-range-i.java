class Solution {
    public int totalWaviness(int num1, int num2) {
        int sum=0;
        for(int i=num1;i<=num2;i++){
            sum+=findwaviness(i);
        }
        return sum;
    }
    private int findwaviness(int num){
        String s=Integer.toString(num);
        int count=0;
        int l=s.length();
        if(l<3){
            return 0;
        }
        for(int i=1;i<l-1;i++){
            if(s.charAt(i)>s.charAt(i-1)&&s.charAt(i)>s.charAt(i+1)){
                count++;
            }
            else if(s.charAt(i)<s.charAt(i-1)&&s.charAt(i)<s.charAt(i+1)){
                count++;
            }
        }
        return count;
    }
}