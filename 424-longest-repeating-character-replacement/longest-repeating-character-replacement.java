class Solution {
    public int characterReplacement(String s, int k) {
        int[] count=new int[26];
        int n=s.length();
        int start=0;
        int maxfreq=0;
        int maxlength=0;
        for(int end=0;end<n;end++)
        {
            count[s.charAt(end)-'A']++;
            maxfreq=Math.max(maxfreq,count[s.charAt(end)-'A']);
            while((end-start+1)-maxfreq>k)
            {
                count[s.charAt(start)-'A']--;
                start++;
            }
            maxlength=Math.max(end-start+1,maxlength);
        }
        return maxlength;
    }
}