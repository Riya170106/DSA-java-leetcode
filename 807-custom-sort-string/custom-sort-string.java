class Solution {
    public String customSortString(String order, String s) {
        StringBuilder result=new StringBuilder();
        int n=order.length();
        int p=s.length();
        for(int i=0;i<n;i++){
            char ch=order.charAt(i);
            for(int j=0;j<p;j++){
                if(s.charAt(j)==ch){
                    result.append(ch);
                }
            }
        }
        for(int i=0;i<p;i++){
            char ch=s.charAt(i);
            if(order.indexOf(ch)==-1){
                result.append(ch);
            }
        }
        return new String(result);
    }
}