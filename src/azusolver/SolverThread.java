/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azusolver;

/**
 * This class has the CDCL solver which runs on a thread.
 * @author Jota
 */
public class SolverThread implements Runnable {

    /*
     * The model that the thread will use to solve the problem.
     */
    Model model;
    int decisionLevel;
    int conflictLevel;

    /**
     * Constructor of the class.
     * @param model The model that the solver thread will use.
     */
    public SolverThread(Model model) {
        this.model = model;
    }

    @Override
    public void run() {
        solve();
    }

    /**
     * Method that implements the CDCL algorithm and solves the problem.
     * @return returns "SAT" if the problem is satisfiable, "UNSAT" otherwise.
     */
    public String solve() {
        if (unitPropagation() == "CONFLICT") {
            return "UNSAT";
        }
        decisionLevel = 0;
        while (allVariablesAssigned() == false) {
            pickBranchingVariable();
            decisionLevel = decisionLevel + 1;
            if (unitPropagation() == "CONFLICT") {
                conflictLevel = conflictAnalysis();
                if (conflictLevel < 0) {
                    return "UNSAT";
                } else {
                    backtrack();
                    decisionLevel = conflictLevel;
                }
            }
        }
        return "SAT";
    }
    
    /**
     * This method propagates the literals through the clauses.
     * @return Returns "CONFLICT" if there is a conflict detected in the propagation.
     */
    public String unitPropagation(){
        return "NOT IMPLEMENTED";
    }
    
    /**
     * Method to check if all variables have been assigned in the model.
     * @return true if all variables have been assigned, false otherwise.
     */
    public boolean allVariablesAssigned(){
        return false;
    }
    
    /**
     * Method that sets a the next variable to branch the search.
     */
    public void pickBranchingVariable(){
        
    }
    
    /**
     * Method that analyses the conflict and detects the decision level responsible
     * @return The decision level that originated the conflict.
     */
    public int conflictAnalysis(){
        return 0;
    }
    
    /**
     * Method that backjumps the branching decision to the conflict level.
     */
    public void backtrack(){
        
    }
}
