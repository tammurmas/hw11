/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tamm.aa.hw11;

/**
 *
 * @author Dell
 */
public class MinMax {
    public static void main(String[] args)
    {
        String sequence = "2 1 2 1 1 3 6 10 1 2 2 1 6 1 2 2 1 7 2 1 1 3 2 1 5 1 2 1 1 1 2 3 1 4 4 5 2 1 2 2 2 1 1 1 2 3";
        calculate(sequence, true);
        calculate(sequence, false);
        
    }
    
    /**
     * Calculates the min/max value of the given sequence
     * Prints out the result formula and its value
     * @param sequence - sequence as String
     * @param max - boolean whether we have to minimize or maximize
     */
    public static void calculate(String sequence, boolean max)
    {
        String[] seqParts = sequence.split("\\s+");
        
        int result = Integer.parseInt(seqParts[0]);
        String strRes = seqParts[0];
        String prevOp = "";//the previously applied arithmetic operation
        
        for (int i=1; i<seqParts.length; i++)
        {
            int val = Integer.parseInt(seqParts[i]);
            
            if(max == true)
            {
                if(result*val >= result+val)
                {
                    result = result*val;
                    
                    if(prevOp == "+")
                        strRes = "("+strRes+")"+"*"+val;//add parentheses if addition is followed by multiplication
                    else
                        strRes = strRes+"*"+val;
                        
                    prevOp = "*";
                }
                else
                {
                    result = result+val;
                    strRes = strRes+"+"+val;
                    prevOp = "+";
                }
            }
            else
            {
                if(result*val <= result+val)
                {
                    result = result*val;
                    
                    if(prevOp == "+")
                        strRes = "("+strRes+")"+"*"+val;//add parentheses if addition is followed by multiplication
                    else
                        strRes = strRes+"*"+val;
                        
                    prevOp = "*";
                }
                else
                {
                    result = result+val;
                    strRes = strRes+"+"+val;
                    prevOp = "+";
                }
            }
                       
        }
        
        System.out.println(strRes);
        System.out.println(result);
    }
}
