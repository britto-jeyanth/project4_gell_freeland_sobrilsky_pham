 
/*******************************************************************************
 * @file  TestTupleGenerator.java
 *
 * @author   Sadiq Charaniya, John Miller
 */

import static java.lang.System.out;

/*******************************************************************************
 * This class tests the TupleGenerator on the Student Registration Database defined
 * in the Kifer, Bernstein and Lewis 2006 database textbook (see figure 3.6).
 * The primary keys (see figure 3.6) and foreign keys (see example 3.2.2) are as
 * given in the textbook.
 */
public class TestTupleGenerator
{
    /***************************************************************************
     * The main method is the driver for TestGenerator.
     * @param args  the command-line arguments
     */
    public static void main (String [] args)
    {
        TupleGenerator test = new TupleGeneratorImpl ();

        test.addRelSchema ("Student",
                           "id name address status",
                           "Integer String String String",
                           "id",
                           null);

        String [] tables = { "Student"};

        int[] tups = new int[args.length];

	for(int i = 0; i < args.length; i++){
	    tups[i] = new Integer(args[i]);
	    
	}
    
        Comparable [][][] resultTest = test.generate (tups);


	IndexTable [] tableList = {
	    new IndexTable("Student",
                           "id name address status",
                           "Integer String String String",
						  "id")};



        for (int i = 0; i < resultTest.length; i++) {
            out.println (tables [i]);
	    Comparable [] tuple = new Comparable[resultTest[i][0].length];
            for (int j = 0; j < resultTest [i].length; j++) {
                for (int k = 0; k < resultTest [i][j].length; k++) {
                    tuple[k] = resultTest [i][j][k];
                } // for
		tableList[i].insert(tuple);
            } // for
        } // for

	tableList[0].print();

        
	/*        for (int i = 0; i < resultTest.length; i++) {
            out.println (tables [i]);
            for (int j = 0; j < resultTest [i].length; j++) {
                for (int k = 0; k < resultTest [i][j].length; k++) {
                    out.print (resultTest [i][j][k] + ",");
                } // for
                out.println ();
            } // for
            out.println ();
	    } */// for
    } // main

} // TestTupleGenerator

