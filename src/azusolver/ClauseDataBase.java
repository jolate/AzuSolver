/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azusolver;

import java.util.ArrayList;

/**
 *
 * @author Jota
 */
public class ClauseDataBase {
  
    /*
     * The nary clause data base (nCDB)
     */
    public static ArrayList[] nClauseDataBase = new ArrayList[AzuSolver.numVars*2+1];
    
    public static ArrayList[] bClauseDataBase = new ArrayList[AzuSolver.numVars*2+1];
    
    public 
    
    /*
     * The input clause which is read from the file, before insertion to the CDB
     */
    public static NClause inputClause = new NClause(true);

    public ClauseDataBase() {
        
    }
    
    public void addClause(NClause clause){
        nClauseDataBase.add(clause);
    }
    
    public void addInputLiteral(int literal){
        if(literal!=0){
            inputClause.getLiterals().add(literal);
        } else {
            addClause(inputClause);
            inputClause = new NClause(true);
        }
    }
}
