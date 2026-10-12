import java.util.HashMap;
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer, Integer> prefix = new HashMap<>();
        int n=nums.length;
        prefix.put(0,1);
        int currsum=0;
        int count=0;
        for(int i=0;i<n;i++)
        {
            currsum+=nums[i];
            if(prefix.containsKey(currsum-goal))
            {
                count+=prefix.get(currsum-goal);
            }
            prefix.put(currsum,prefix.getOrDefault(currsum,0)+1);
        }
        return count;
    }
}