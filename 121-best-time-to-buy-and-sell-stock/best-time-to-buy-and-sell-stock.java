class Solution {
    public int maxProfit(int[] prices) {
        int n=prices.length;
       int maxprofit=0;
       int min=Integer.MAX_VALUE;
       for(int i=0;i<n;i++)
       {
        if(prices[i]>min)
        {
          int profit=prices[i]-min;
          maxprofit=Math.max(maxprofit,profit);
        }
        else
        {
            min=prices[i];
        }
       }
       return maxprofit;
    }
}