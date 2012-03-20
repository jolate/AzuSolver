/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package azusolver;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class parses the CNF file into an internal clause database.
 * @author Jota
 */
public class FileParser {

    private static int i = 0;
    private static byte[] buffer = new byte[4096];
    private static int size = 0;
    private static char c;

    /**
     * Method to get the next character in the input stream.
     * @param in The input stream to read the character from.
     * @return The character read from the input stream.
     */
    public static void getNext(InputStream in) {
        i++;
        if (i >= size) {
            i = 0;
            try {
                size = in.read(buffer);
            } catch (IOException ex) {
                Logger.getLogger(FileParser.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(size==-1){ 
                c=0;
            }else {
                c=(char)buffer[i];
            }            
        } else {
            c=(char)buffer[i];
        }
    }

    /**
     * Static method to parse a file into the class database.
     * @param in This is the input stream from the file.
     */
    public static void parseFile(InputStream in) {
        c = 0;
        int n = 0;
        int variable;
        boolean isPositive;

        try {
            c = (char) in.read();
        } catch (IOException ex) {
            Logger.getLogger(FileParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
         * Skip comments in file
         */
        while (c == 'c') {
            while (c != '\n') {
                getNext(in);
            }
            getNext(in);
        }
        if (c != 'p') {
            System.out.println("No line starting with p. \n");
            System.exit(3);
        }
        getNext(in);
        /*
         * Skip white space
         */
        while ((c >= 9 && c <= 13) || c == 32) {
            getNext(in);
        }
        /*
         * Skip "CNF"
         */
        getNext(in);
        getNext(in);
        getNext(in);
        /*
         * Skip white space
         */
        while ((c >= 9 && c <= 13) || c == 32) {
            getNext(in);
        }

        while (c >= '0' && c <= '9') {
            n = n * 10 + (c - '0');
            getNext(in);
        }
        AzuSolver.numVars = n;
        System.out.println("Number of variables: "+AzuSolver.numVars);
        n = 0;

        /*
         * Skip white space
         */
        while ((c >= 9 && c <= 13) || c == 32) {
            getNext(in);
        }

        while (c >= '0' && c <= '9') {
            n = n * 10 + (c - '0');
            getNext(in);
        }
        AzuSolver.numInputClauses = n;
        System.out.println("Number of input clauses: "+AzuSolver.numInputClauses);
        /*
         * Starting to read clauses
         */
        c = 'a';
        boolean fileEnd = false;
        while (!fileEnd) {
            getNext(in);
            /*
             * Skip white space
             */
            while ((c >= 9 && c <= 13) || c == 32) {
                getNext(in);
            }
            if (c == 0) {
                fileEnd = true;
            } else {
                isPositive = true;
                /*
                 * At this point 'c' is either a digit or '-'
                 */
                if (c == '-') {
                    isPositive = false;
                    getNext(in);
                }
                /*
                 * At this point, 'c' is a digit for sure
                 */
                variable = 0;
                while (c >= '0' && c <= '9') {
                    variable = variable * 10 + (c - '0');
                    getNext(in);
                }
                if (isPositive) {
                    AzuSolver.cdb.addInputLiteral(variable);
                } else {
                    AzuSolver.cdb.addInputLiteral(-variable);
                }
            }
        }
        try {
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(FileParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
