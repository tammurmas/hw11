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
      System.out.println(editDistance("abarabckakcadkcdabbaarra","barackadabadckdbaabararaa"));
  }
    
  /**
   * HINT: http://professorjava.weebly.com/edit-distance.html
   * @param s
   * @param t
   * @return 
   */
  public static int editDistance(String s, String t){
    int m=s.length();
    int n=t.length();
    int[][]d=new int[m+1][n+1];
    for(int i=0;i<=m;i++){
      d[i][0]=i;
    }
    for(int j=0;j<=n;j++){
      d[0][j]=j;
    }
    for(int j=1;j<=n;j++){
      for(int i=1;i<=m;i++){
        if(s.charAt(i-1)==t.charAt(j-1)){
          d[i][j]=d[i-1][j-1];
        }
        else{
          d[i][j]=min((d[i-1][j]+1),(d[i][j-1]+1),(d[i-1][j-1]+1));
        }
      }
    }
    return(d[m][n]);
  }
  public static int min(int a,int b,int c){
    return(Math.min(Math.min(a,b),c));
  }
}
