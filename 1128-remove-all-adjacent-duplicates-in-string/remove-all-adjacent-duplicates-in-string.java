class Solution {
    public String removeDuplicates(String s) {
        StringBuilder temp=new StringBuilder();
        for(int i=0;i<s.length();i++)
        {
          if(temp.length()>0&&temp.charAt(temp.length()-1)==s.charAt(i))
          {
            temp.deleteCharAt(temp.length()-1);
          }
          else{
            temp.append(s.charAt(i));
          }
        }
        return temp.toString();
    }
}