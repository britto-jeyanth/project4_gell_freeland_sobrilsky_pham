import static java.lang.System.out;
import java.io.*;
public class TimeTester
{
/*
	private static UnindexedTable makeTable(int d){
		TupleGenerator test = new TupleGeneratorImpl ();
        	test.addRelSchema ("Student",
       		                   "id name address gpa",
        	                   "Integer String String Double",
        	                   "id",
        	                   null);

        	String [] tables = { "Student"};
	
        	int[] tups = new int[1];
		tups[0] = d;
    	   	Comparable [][][] resultTest = test.generate (tups);

		UnindexedTable [] tableList = {
		 			new UnindexedTable("Student",
        	        		"id name address gpa",
        	        	        "Integer String String Double",
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
		return tableList[0];
	}
*/
	private static UnindexedTable[] makeTables(){
		TupleGenerator test = new TupleGeneratorImpl ();
        	
        	test.addRelSchema ("Student",
                           "id name address status",
                           "Integer String String String",
                           "id",
                           null);
        
        	test.addRelSchema ("Professor",
                           "id name deptId",
                           "Integer String String",
                           "id",
                           null);
        
        	test.addRelSchema ("Course",
                           "crsCode deptId crsName descr",
                           "String String String String",
                           "crsCode",
                           null);
        
        	test.addRelSchema ("Teaching",
                           "crsCode semester profId",
                           "String String Integer",
                           "crsCode semester",
                           new String [][] {{ "profId", "Professor", "id" },
                                            { "crsCode", "Course", "crsCode" }});
		test.addRelSchema ("Transcript",
                           "studId crsCode semester grade",
                           "Integer String String String",
                           "studId crsCode semester",
                           new String [][] {{ "studId", "Student", "id"},
                                            { "crsCode", "Course", "crsCode" },
                                            { "crsCode semester", "Teaching", "crsCode semester" }});
		String [] tables = { "Student", "Professor", "Course", "Teaching", "Transcript" };

	
        	int[] tups = new int [] { 10000, 1000, 2000, 50000, 5000 };

    	   	Comparable [][][] resultTest = test.generate (tups);

		
		UnindexedTable [] tableList = {
			new UnindexedTable("Student",
                           "id name address status",
                           "Integer String String String",
			   "id"),
			new UnindexedTable("Professor",
                           "id name deptId",
                           "Integer String String",
                           "id"),
			new UnindexedTable("Course",
                           "crsCode deptId crsName descr",
                           "String String String String",
                           "crsCode"),
			new UnindexedTable("Teaching",
                           "crsCode semester profId",
                           "String String Integer",
                           "crsCode semester"),
			new UnindexedTable("Transcript",
                           "studId crsCode semester grade",
                           "Integer String String String",
                           "studId crsCode semester") };

        	for (int i = 0; i < resultTest.length; i++) {
		    	Comparable [] tuple = new Comparable[resultTest[i][0].length];
        	    	for (int j = 0; j < resultTest [i].length; j++) {
        	        	for (int k = 0; k < resultTest [i][j].length; k++) {
        	        	   	tuple[k] = resultTest [i][j][k];
        	        	} // for
				tableList[i].insert(tuple);
        	    	} // for
        	} // for
		return tableList;
	}
	public static void main(String[] args){
		try{
			FileWriter fstream = new FileWriter("UnindexedArrayPointSelect.txt");
			BufferedWriter out = new BufferedWriter(fstream);
				for(int i = 0; i < 40; i++){
					UnindexedTable t = makeTables()[0];
					long startTime = System.nanoTime();
					t.select("id == 111111");
					long endTime = System.nanoTime();
					out.write(i + "	"+ (endTime-startTime)+'\n');
				
				}
			out.close();
			fstream = new FileWriter("UnindexdArrayRangeSelect.txt");
			out = new BufferedWriter(fstream);
				for(int i = 0; i < 40; i++){
					UnindexedTable t = makeTables()[0];
					long startTime = System.nanoTime();
					t.select("id >= 500000");
					long endTime = System.nanoTime();
					out.write( i + "	"+ (endTime-startTime)+'\n');
				
				}
			out.close();
			fstream = new FileWriter("UnindexArrayJoin.txt");
			out = new BufferedWriter(fstream);
				for(int i = 0; i < 40; i++){
					UnindexedTable[] t = makeTables();
					long startTime = System.nanoTime();
					t[0].join("id == studId",t[4]);
					long endTime = System.nanoTime();
					out.write(i + "	"+ (endTime-startTime)+'\n');
				
				}
			out.close();
		}
		catch(Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}		
} // main
