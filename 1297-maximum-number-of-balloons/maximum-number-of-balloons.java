class Solution {
    public int maxNumberOfBalloons(String text) {
        int b=0,a=0,l=0,o=0,n=0;
        int N=text.length();
        for(int i=0;i<N;i++){
            char ch=text.charAt(i);
            if(ch=='b') b++;
            else if(ch=='a') a++;
            else if(ch=='l') l++;
            else if(ch=='o') o++;
            else if(ch=='n') n++;
        }
        int [] arr={b,a,l/2,o/2,n};
        int min=arr[0];
        for(int i=1;i<5;i++){
          if(arr[i]<min){
            min=arr[i];
          }
        }
        return min;
    }
}