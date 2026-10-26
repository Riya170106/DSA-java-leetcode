class Solution {
    public int[] sortedSquares(int[] nums) {
        int n=nums.length;
        int[]result=new int[n];
        int left=0;
        int right=n-1;
        int leftsq;
        int rightsq;
        int k=n-1;
        while(left<=right)
        {
            leftsq=nums[left]*nums[left];
            rightsq=nums[right]*nums[right];
            if(leftsq<rightsq)
            {
                result[k--]=rightsq;
                right--;
            }
            else
            {
                result[k--]=leftsq;
                left++;
            }
        }
        return result;
    }
}