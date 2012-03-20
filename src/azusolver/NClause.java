/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azusolver;

import java.util.ArrayList;

/**
 * This class represents a clause.
 * @author Jota
 */
public class NClause {
    /*
     * The literals in the NClause.
     */
    private ArrayList<Integer> literals;
    
    /*
     * Array of integers for the 2 watched literals of each thread.
     */
    private int[] watchedLiterals;
    
    /*
     * Array of clauses for the next 2 watched clauses in each thread (1 for each watched literal).
     */
    private NClause[] nextWatchedClause;

    /**
     * Getter of the nextWatchedClause array.
     * @return the watched clauses of each thread.
     */
    public NClause[] getNextWatchedClause() {
        return nextWatchedClause;
    }

    /**
     * Getter for the watchedLiterals array.
     * @return The watched literals of each thread for this clause.
     */
    public int[] getWatchedLiterals() {
        return watchedLiterals;
    }
    
    /**
     * Literals getter
     * @return The ArrayList of integers, which are the clause literals.
     */
    public ArrayList<Integer> getLiterals() {
        return literals;
    }
    /*
     * To identify if the NClause is an input clause or a lemma.
     * True is an input clause, false is a lemma.
     */
    private boolean original;

    /**
     * Getter of original variable.
     * @return A boolean, true if it's an input clause, false if it's a lemma.
     */
    public boolean isOriginal() {
        return original;
    }
    
    /**
     * Constructor.
     * @param isOriginal Set if the clause is input or not.
     */
    public NClause(boolean isOriginal){
        literals = new ArrayList<Integer>();
        watchedLiterals = new int[AzuSolver.numberOfThreads*2];
        this.original = isOriginal;
    }
    
}
