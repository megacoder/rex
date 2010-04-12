package edu.udel.cis.cisc475.rex.source.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.udel.cis.cisc475.rex.source.IF.SourceIF;

/**
 * @author Jim Cardona
 * @author Justin Johnson
 * @author Jack Song
 *
 */
public class Source implements SourceIF {

//constructor
public Source(String filename) {
	 //init private vars
     startline = startcolumn = lastline = lastcolumn = 0;	
	 Text = " "; //using this as temp string buffer only

    // create File object based on user input
	 fileName = filename;	
	 name = new File( fileName );
	
	 //array list object that will contain all of our lines of imported text
	 text_lines = new ArrayList<String>();
	 
	 
	 /*	check if the filename is an existing file (need try - catch statements) 
	  * 
	 	this instance of source is being used to read in
	 	an ECF or UEF file and we will 
	 	read in the whole file 
	 	into our 
	 	array list 
	 	and 
	 	set the markers along the way	 	
	 */
     if ( name.exists() ) // check if exists
     { if ( name.isFile() ) // check if is file and not directory
     { 

    	 try
         {
            input = new Scanner( name );
         } // end try
         catch ( FileNotFoundException fileNotFoundException )
         {
            System.err.println( "Error opening file." );
            System.exit( 1 );
         } // end catch
	 
	 
         while ( input.hasNext() )
         {
        	 Text = input.nextLine(); // read line
        	 addText(Text); //add line to our string array
        	 lastline++; //increment # of lines

         } // end while
         lastline--;
	 
	 
     }//end of if ( name.isFile() )
     else {}
     	// exception is a directory, not file
     
     
     }//end of if ( name.exists() ) 
     	else {}
     	// exception file not found
     
}//end of constructor



//getters
public String filename() {return fileName;}
public String text()	 {return Text;}
public int startLine() 	 {return startline;} 
public int startColumn() {return startcolumn;}
public int lastLine() 	 {return lastline;}
public int lastColumn()	 {return lastcolumn;} 

//setters
public void setStartLine(int line) 		{startline=line;} 
public void setStartColumn(int column)  {startcolumn=column;}
public void setLastLine(int line) 		{lastline=line;}
public void setLastColumn(int column) 	{lastcolumn=column;}

//output of our array list string object
public void write(PrintWriter out) {
	// loop through the array list and dump
	//	the contents to out
	// use delimiters set by requesting program of
	//start and end rows
	
	String tempString = "";
	if(startcolumn < text_lines.get(startline).length())
	{
		for(int i = startcolumn; i<text_lines.get(startline).length(); i++)
			tempString += text_lines.get(startline).charAt(i);
		out.printf("%s\n", tempString);
	}
	
	for (int i = startline + 1; (i < text_lines.size()) && (i < lastline - 1); i++)
		out.printf("%s\n", text_lines.get(i)); 
	tempString = "";
	
	if(lastcolumn == 0)
		out.printf("%s\n", text_lines.get(lastline));
	else
	{
		for(int i = 0; (i<text_lines.get(lastline).length() && i < lastcolumn); i++)
			tempString += text_lines.get(lastline).charAt(i);
		out.printf("%s\n", tempString);
	}
} 

// add text to our structure
public void addText(String text) {
	text_lines.add(text);
}

	
//private vars
private String fileName;
private File name;		//our file object handle
private Scanner input;  //our input object handle 
private String Text;	//using this as temp string buffer only
private int startline;
private int startcolumn;
private int lastline;
private int lastcolumn;	
		List<String> text_lines; //our string array
}//end of class SourceFactoryIF 
