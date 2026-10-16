class Solution {
    public int countPoints(String rings) {
        int count=0;
        for(int i=0;i<10;i++){
            if(rings.contains("R"+i)&&rings.contains("G"+i)&&rings.contains("B"+i))
            count++;
        }
        return count;
    }
}