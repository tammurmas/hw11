/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tamm.aa.hw11;

/**
 *
 * @author Urmas
 */

/**
 * Computes the edit distance between pairs of words.  Can be used for applications
 * like finding near-match names in Kevin Bacon or spelling correction.
 * 
 * There are two versions: a recursive version and a dynamic programming version 
 * that memoizes the function by storing previously solved problems in a map.
 * 
 * @author Scot Drysdale
 */
public class EditDistance {
    
  public static void main(String[] args)
  {
      String s = "democrat";
      String t = "republican";
      
      int[][] d = editDistance(s,t);
      printMatrix(d,s,t);
  }
    
  /**
   * HINT: http://professorjava.weebly.com/edit-distance.html
   * @param s
   * @param t
   * @return 
   */
  public static int[][] editDistance(String s, String t){
    int m=s.length();
    int n=t.length();
    
    int[][]d=new int[m+1][n+1];
    
    //fill in columns
    for(int i=0;i<=m;i++){
        d[i][0]=i;
    }
    
    //fill in rows
    for(int j=0;j<=n;j++){
        d[0][j]=j;
    }
    
    for(int j=1;j<=n;j++){
        for(int i=1;i<=m;i++){
            if(s.charAt(i-1)==t.charAt(j-1)){
                d[i][j]=d[i-1][j-1];//characters are equal, we don't have to change anything
            }
            else{
                d[i][j]=min((d[i-1][j]+1),(d[i][j-1]+1),(d[i-1][j-1]+1));//take the minimal neighbouring value
            }
      }
    }
    
    
    
    //return(d[m][n]);
    return d;
  }
  
  public static int min(int a,int b,int c){
    return(Math.min(Math.min(a,b),c));
  }
  
  public static void printMatrix(int[][] d, String s, String t)
  {
        int m=s.length();
        int n=t.length();
        
        System.out.print(". ");
        
        for(int j=0;j<=n;j++){
            if(j > 0)
                System.out.print(t.charAt(j-1)+" ");
            else
                System.out.print(". ");
        }
        System.out.println();
        
        for (int i=0; i<=m; i++)
        {
            if(i > 0)
                System.out.print(s.charAt(i-1)+" ");
            else
                System.out.print(". ");
            
            for (int j=0; j<=n; j++)
            {
                System.out.print(d[i][j]+" ");
            }
            System.out.println();
        }
  }
}
