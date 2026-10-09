class Solution {
    public int maxVowels(String s, int k) {
        int n=s.length();
        int maxcount=0;
        int count=0;
        for(int i=0;i<k;i++)
        {
            if(isVowel(s.charAt(i)))
            {
                count++;
            }
          }
          maxcount=count;
        for(int i=k;i<n;i++)
        {
            if(isVowel(s.charAt(i)))
            {
                count++;
            }
            if(isVowel(s.charAt(i-k)))
            {
                count--;
            }
         maxcount=Math.max(maxcount,count);
        }
        return maxcount;
    }
        public boolean isVowel(char c)
        {
            return c=='a'||c=='e'||c=='i'||c=='o'||c=='u';
        }
}