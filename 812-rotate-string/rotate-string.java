class Solution {
    public boolean rotateString(String s, String goal) {
        if(s.length()!=goal.length())
        {
            return false;
        }
        String doubled=s+s;
        if(doubled.indexOf(goal)!=-1)
        {
            return true;
        }
       return false;
    }
}