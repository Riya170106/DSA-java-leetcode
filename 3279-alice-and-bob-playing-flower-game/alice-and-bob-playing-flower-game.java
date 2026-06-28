class Solution {
    public long flowerGame(int n, int m) {
        long a=((long)(n+1)/2)*(m/2);
        long b=((long)(n/2))*((m+1)/2);
        return a+b;
    }
}