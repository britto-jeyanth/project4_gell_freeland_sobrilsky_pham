Project 3
CSCI 4370
Spring 2013
Group 1: Ryan Gell, Zachary Freeland, Nicholas Sobrilsky, Minh Pham

Project compiles as expected using the javac command on any .java file with a main method.
The commands:

javac MovieDB.java
java MovieDB

Will show off the cabailities of the relational algebra operators implemented in Table.java along with indexing implemented in ExtHash and BpTree.

To change index type, In the constructor of class table, You can choose either BpTree or ExtHash for index:
           
	index = new ExtHash<KeyType, Comparable[]> (KeyType.class, Comparable[].class, attribute.length);
or
	index = new BpTree <KeyType, Comparable[]> (KeyType.class, Comparable[].class);

Just comment out the one you wish to use.

