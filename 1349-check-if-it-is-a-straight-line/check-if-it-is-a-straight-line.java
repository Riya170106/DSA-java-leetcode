class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        int n=coordinates.length;
        int d_y=coordinates[1][1]-coordinates[0][1];
        int d_x=coordinates[1][0]-coordinates[0][0];
        for(int i=2;i<n;i++){
            int d_y_i=coordinates[i][1]-coordinates[0][1];
            int d_x_i=coordinates[i][0]-coordinates[0][0];
            if(d_y*d_x_i!=d_y_i*d_x){
                return false;
            }
        }
        return true;
    }
}