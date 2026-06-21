class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>>ans=new ArrayList<>();
        getperms(nums,0,ans);
        return ans;
    }
    public void getperms(int[]nums,int idx,List<List<Integer>>ans){
        List<Integer>perms=new ArrayList<>();
        if(idx==nums.length){
            for(int i=0;i<nums.length;i++){
                perms.add(nums[i]);
            }
            ans.add(perms);
            return;
        }
        for(int i=idx;i<nums.length;i++){
            swap(nums,i,idx);
            getperms(nums,idx+1,ans);
            swap(nums,i,idx); //backtrack
        }
    }
    public void swap(int[]nums,int i,int idx){
        int temp=nums[i];
        nums[i]=nums[idx];
        nums[idx]=temp;
    }
}