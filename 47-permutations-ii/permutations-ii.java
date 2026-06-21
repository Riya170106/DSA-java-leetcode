class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>>ans=new ArrayList<>();
        Arrays.sort(nums);
        getperms(nums,0,ans);
        return ans;
    }
    public void getperms(int[]nums,int idx,List<List<Integer>>ans){
        int n=nums.length;
        if(idx==n){
            List<Integer>perms=new ArrayList<>();
            for(int i=0;i<n;i++){
              perms.add(nums[i]);
            }
            ans.add(perms);
            return;
        }
        HashSet<Integer>set=new HashSet<>();
        for(int i=idx;i<n;i++){
          if(set.contains(nums[i])){
          continue;
          }
          set.add(nums[i]);
          swap(nums,i,idx);
          getperms(nums,idx+1,ans);
          swap(nums,i,idx);
        }
    }
    public void swap(int[]nums,int i,int idx){
        int temp=nums[i];
        nums[i]=nums[idx];
        nums[idx]=temp;
    }
}