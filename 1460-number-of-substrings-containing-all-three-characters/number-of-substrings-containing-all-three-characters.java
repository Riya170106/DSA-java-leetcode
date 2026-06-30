class Solution {
    public int numberOfSubstrings(String s) {
      int result=0;
      int n=s.length();
      int[]freq=new int [3];
      int i=0;
      int j=0;
      while(j<n){
        char ch=s.charAt(j);
        freq[ch-'a']++;
        //a->0,b->1,c->2
        while(freq[0]>0&&freq[1]>0&&freq[2]>0){
            result+=(n-j);
            freq[s.charAt(i)-'a']--;
            i++;
        }
        j++;
      }  
      return result;
    }
}