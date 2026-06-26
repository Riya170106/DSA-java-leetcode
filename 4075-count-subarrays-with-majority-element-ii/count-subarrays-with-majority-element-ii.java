class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        HashMap<Integer,Integer>mp=new HashMap<>();
        int n=nums.length;
        int prefixsum=0;
        mp.put(0,1);
        long validleftpoints=0;
        long result=0;
        for(int i=0;i<n;i++){
            if(nums[i]==target){
                validleftpoints+=mp.getOrDefault(prefixsum,0);
                prefixsum+=1;
            }
            else{
                prefixsum-=1;
                validleftpoints-=mp.getOrDefault(prefixsum,0);
            }
            mp.put(prefixsum,mp.getOrDefault(prefixsum,0)+1);
            result+=validleftpoints;
        }
        return result;
    }
}