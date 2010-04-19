package edu.udel.cis.cisc475.rex.output;

import java.io.PrintWriter;

import edu.udel.cis.cisc475.rex.exam.IF.BlockIF;
import edu.udel.cis.cisc475.rex.exam.IF.ExamElementIF;
import edu.udel.cis.cisc475.rex.exam.IF.ExamIF;
import edu.udel.cis.cisc475.rex.exam.IF.FigureIF;
import edu.udel.cis.cisc475.rex.exam.impl.Problem;
import edu.udel.cis.cisc475.rex.output.IF.ExamWriterIF;
/**
 * @author team 5
 * The ExamWriter class utilizes a print writer and an precreated Exam
 * class to output parts of the exam class to the print writer.
 *  
 */
public class ExamWriter implements ExamWriterIF {

	/*
	 * Default constructor for ExamWriter
	 * 	
	 * @param E created with the Exam module
	 */
	public ExamWriter(ExamIF exam) {

		this.E = exam;

	}//end of constructor


	/*
	 * Writes the Exam class to a specified PrintWriter object
	 * 	
	 * @param out any writer used to output data
	 */
	public void write(PrintWriter out){
		// output beginning of exam file
		out.printf("%s\n", E.preamble());
		out.printf("%s\n", E.frontMatter());
		
		// output problems with respective answers
		for(int i=0; i< E.elements().toArray().length; i++){
			ExamElementIF temp = (ExamElementIF) E.elements().toArray()[i];

			// the following checks what type the current element is
			// and prints accordingly
			if(temp instanceof BlockIF){
				BlockIF tempBlockIF  = (BlockIF)temp;
				out.printf("%s\n", tempBlockIF.source().text());
			}
			else if(temp instanceof FigureIF){
				FigureIF tempFigureIF = (FigureIF)temp;
				out.printf("%s\n",tempFigureIF.source().text());
			}
			else if(temp instanceof Problem){
				Problem tempProblem = (Problem)temp;
				
				//prints the required block if it does not equal null
				if(tempProblem.requiredBlock()!= null)
					out.printf("%s\n",tempProblem.requiredBlock());
				//prints the question
				out.printf("%s\n",tempProblem.question().text());
				
				//prints all the answers
				for(int index=0; index<tempProblem.answers().length; index++){
					out.printf("%s\n",tempProblem.answers()[index].source().text());
				}
			}
		}
		
		// output the end block for exam
		out.printf("%s\n", E.finalBlock());
		
	}//end of write(PrintWriter out)

	
	//private vars
	private ExamIF E;
}//end of class 
