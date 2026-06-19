class Solution {
    public int largestAltitude(int[] gain) {
        int highest=0;
        int alt=0;
        int n=gain.length;
        for(int i=0;i<n;i++){
           alt+=gain[i];
           if(alt>highest){
            highest=alt;
           }
        }
        return highest;
    }
}