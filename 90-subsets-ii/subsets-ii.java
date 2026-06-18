class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>>ans=new ArrayList<>();
        Arrays.sort(nums);
        subsets(ans,new ArrayList<>(),nums,0);
        return ans;
    }
     public void subsets(List<List<Integer>>ans,List<Integer>set,int[]nums,int idx){
        if(ans.contains(set)){ 
          return;
        }
        ans.add(new ArrayList<>(set));
        for(int i=idx;i<nums.length;i++){
            set.add(nums[i]);
            subsets(ans,set,nums,i+1);
            set.remove(set.size()-1);
        }
    }
}