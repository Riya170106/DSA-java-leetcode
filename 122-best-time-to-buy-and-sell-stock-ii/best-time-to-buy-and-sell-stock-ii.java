class Solution {
    public int maxProfit(int[] prices) {
        int n=prices.length;
        int maxprofit=0;
        int first=prices[0];
        for(int i=1;i<n;i++)
        {
            if(prices[i]>prices[i-1])
            {
                int diff=prices[i]-prices[i-1];
                maxprofit+=diff;
            }
        }
        return maxprofit;
    }
}