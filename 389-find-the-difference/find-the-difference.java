class Solution {
    public char findTheDifference(String s, String t) {
       char[] sarr=s.toCharArray();
       char[] tarr=t.toCharArray();
       Arrays.sort(sarr);
       Arrays.sort(tarr);
       int n=sarr.length;
       int p=tarr.length;
        for(int i=0;i<n;i++)
        {
            if(sarr[i]!=tarr[i])
            {
                return tarr[i];
            }
        }
        return tarr[p-1];
    }
}