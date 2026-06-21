class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>>ans=new ArrayList<>();
        getAllcombinations(candidates,0,new ArrayList<>(),ans,target);
        return ans;
    }
     public void getAllcombinations(int[]candidates,int idx,List<Integer>combin,List<List<Integer>>ans,int target){
        int n=candidates.length;
        if(idx>=n||target<0){
            return;
        }
        if(target==0){
            ans.add(new ArrayList<>(combin));
            return;
        }
        combin.add(candidates[idx]);
        //single
        getAllcombinations(candidates,idx,combin,ans,target-candidates[idx]);
        combin.remove(combin.size()-1);
        //exclude
        getAllcombinations(candidates,idx+1,combin,ans,target);
    }
}