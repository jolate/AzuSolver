/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azusolver;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jota
 */
public class AzuSolver {

    /**
     * @param args the command line arguments
     */
    public static int numberOfThreads=1;
    public static int numVars;
    public static int numInputClauses;
    public static ClauseDataBase cdb = new ClauseDataBase();
    
    public static void main(String[] args) {
        /*
         * Opening the input file with the CNF data.
         */
        String inputFile = args[0];
        FileInputStream in = null;
        try {
            in = new FileInputStream(inputFile);
        } catch (IOException ex) {
            Logger.getLogger(AzuSolver.class.getName()).log(Level.SEVERE, null, ex);
        }
        FileParser.parseFile(in);
        for(int i=0; i<ClauseDataBase.clauseDataBase.size();i++){
            for(int j=0; j<ClauseDataBase.clauseDataBase.get(i).getLiterals().size(); j++){
                System.out.print(ClauseDataBase.clauseDataBase.get(i).getLiterals().get(j)+" ");
            }
            System.out.println("");
        }
    }
}
