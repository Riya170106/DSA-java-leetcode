class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans= new ArrayList();
        int n=nums.length;
        int i=0;
        while(i<n){
           int j=nums[i]-1;
           if(nums[i]!=nums[j]){
              swap(nums,i,j);
            }
           else{
                i++;
            }
        }
           for(i=0;i<n;i++){
              if(nums[i]!=i+1){
                    ans.add(i+1);
                }
            }
        return ans;
    }
    void swap(int []nums,int i,int j)
    {
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}