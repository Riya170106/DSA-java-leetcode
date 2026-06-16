class Solution {
    public int lengthOfLastWord(String s) {
        int count=0;
        int n=s.length();
        for(int i=n-1;i>=0;i--)
        {
            if(s.charAt(i)==' '&&count>0)
            {
               break;
            }
           else if(s.charAt(i)!=' ')
            {
               count++;
            }
        }
        return count;
    }
}