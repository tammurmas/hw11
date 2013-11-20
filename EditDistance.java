/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tamm.aa.hw11;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Dell
 */
public class EditDistance {
        
        public static void main(String[] args)
        {
            /*String s = "abarabckakcadkcdabbaarra";
            String t = "barackadabadckdbaabararaa";
            
            int[][] d = diffMatrix(s,t);
            printMatrix(d,s,t);
            System.out.println("Distance: "+d[s.length()][t.length()]);*/
            
            //testing the edit algorithm
            StopWatch timer = new StopWatch();

            int time = 0;
            int comp = 0;
            int numOfTests = 30;
            
            for (int j=0; j<numOfTests; j++)
            {
                
                ArrayList<String> strings = new ArrayList<String>();
                for(int i=0; i<1000000; i++)
                {
                    int length = randInt(1, 24);//create a random string length, max is the number of chars in the alphabet
                    strings.add(genString(length));
                }
                
                timer.start();
                int count = 0;
                for(int i=0; i<strings.size()-1; i++)
                {
                    if(timer.getElapsedTimeSecs() < 1.0)
                    {
                        diffMatrix(strings.get(i),strings.get(i+1));
                        count++;
                    }
                    else
                        break;
                }
                timer.stop();
                
                time += timer.getElapsedTimeSecs();
                comp += count;
                
                System.out.println("Test "+j+" "+timer.getElapsedTimeSecs()+" "+count);
            }
            
            System.out.println("Average number of comparisons: "+comp/numOfTests);
        }
 
        /**
         * Calculates and returns the difference matrix for the given two strings
         * HINT: http://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance
         * @param str1
         * @param str2
         * @return 
         */
        public static int[][] diffMatrix(String str1,String str2) {
                
                int m=str1.length();
                int n=str2.length();
                
                int[][] distance = new int[m+1][n+1];
 
                //fill in columns
                for (int i = 0; i <= m; i++)
                    distance[i][0] = i;
                
                //fill in rows
                for (int j = 1; j <= n; j++)
                    distance[0][j] = j;
 
                for (int i = 1; i <= m; i++)
                {
                    for (int j = 1; j <= n; j++)
                        {
                            int cost = ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1);//the cost of op based on whether we need to change a character
                            distance[i][j] = minimum(
                                            distance[i - 1][j] + 1,// deletion
                                            distance[i][j - 1] + 1,// insertion
                                            distance[i - 1][j - 1]+ cost);// substitution
                            
                             //transposition
                            if(i > 2 && j > 2)
                            {
                                if(str1.charAt(i-1) == str2.charAt(j-2) && str1.charAt(i-2) == str2.charAt(j-1))
                                    distance[i][j] = Math.min(distance[i][j],distance[i-2][j-2] + cost);   
                            }
                            
                        }
                }
                 
                return distance;
        }
        
        /**
         * Helper to calculate the minimal value of the given three integers
         * @param a
         * @param b
         * @param c
         * @return 
         */
        private static int minimum(int a, int b, int c) {
                return Math.min(Math.min(a, b), c);
        }
        
        
        /**
         * Helper to print the given difference matrix
         * @param d
         * @param s
         * @param t 
         */
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
        
        /**
         * Generates random string
         * HINT: http://syntx.co/languages-frameworks/how-to-generate-a-random-string-in-java/
         * @param length
         * @return 
         */
        public static String genString(int length) {

		StringBuffer buffer = new StringBuffer();
		String characters = "abcdefghijklmnopqrstuvwxyz";

		int charactersLength = characters.length();

		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		return buffer.toString();
	}
        
        /**
        * A small helper for generating random integers
        * HINT: http://stackoverflow.com/questions/363681/generating-random-numbers-in-a-range-with-java
        * @param min
        * @param max
        * @return 
        */
       public static int randInt(int min, int max)
       {
           Random rand = new Random();
           return rand.nextInt((max - min) + 1) + min;
       }
}
