package edu.udel.cis.cisc475.rex.output;
import java.util.Collection;
import edu.udel.cis.cisc475.rex.exam.IF.BlockIF;
import edu.udel.cis.cisc475.rex.exam.IF.ExamElementIF;
import edu.udel.cis.cisc475.rex.exam.IF.ExamFactoryIF;
import edu.udel.cis.cisc475.rex.exam.IF.ExamIF;
import edu.udel.cis.cisc475.rex.exam.IF.FigureIF;
import edu.udel.cis.cisc475.rex.exam.IF.ProblemIF;
import edu.udel.cis.cisc475.rex.exam.generatestubs.ExamFactoryStub;
import edu.udel.cis.cisc475.rex.source.IF.SourceFactoryIF;
import edu.udel.cis.cisc475.rex.source.IF.SourceIF;
import edu.udel.cis.cisc475.rex.source.impl.SourceFactory;


public class ExamStub implements ExamIF {
	SourceFactoryIF sourceFactory = new SourceFactory();
	ExamFactoryIF examFactory = new ExamFactoryStub();
	

	@Override
	public SourceIF preamble() {
		SourceIF preamble = sourceFactory.newSource("preamble");
		preamble.addText("\\documentclass[12pt]{article}"); 
		preamble.addText("\\usepackage{graphicx}");
		preamble.addText("\\usepackage[letterpaper,textheight=9.5in,left=1in,textwidth=6.5in,bottom=1in]{geometry}");
		preamble.addText("\\usepackage{amsmath}");
		preamble.addText("\\author{Dr T. Harvey}");
		preamble.addText("\\title{test TEST}");
		preamble.addText("\\date{April 08, 2010}");
		preamble.addText("\\begin{document}");
		preamble.addText("\\maketitle");
		preamble.addText("\\newpage");

		return preamble;
	}
	
	@Override
	public SourceIF frontMatter() {
		SourceIF frontMatter = sourceFactory.newSource("frontmatter.txt");
		frontMatter.addText("\\newpage");
		return frontMatter;
	}
	
	
	@Override
	public BlockIF finalBlock() {
		SourceIF finalSource = sourceFactory.newSource("finalBlock.txt");
		finalSource.addText("\\newpage");
		BlockIF finalBlock = examFactory.newBlock("Final topic", finalSource);
		return finalBlock;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public ExamElementIF element(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean isMaster() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Collection<String> labels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numElements() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public Collection<ProblemIF> problems() {
/*		SourceIF question1 = sourceFactory.newSource("testFile.txt");
		question1.addText("question1");
		
		AnswerIF[] answers1 = new Answer[2];
		//answers1[0] = examFactory.newAnswer(true, sourceFactory.newSource("option1"));
		//answers1[1] = examFactory.newAnswer(false, sourceFactory.newSource("option2"));
		
		answers1[0] = new Answer(true, sourceFactory.newSource("option1"));
		answers1[1] = new Answer(false, sourceFactory.newSource("option2"));
		
		ProblemIF prob1 = examFactory.newProblem("topic1", "label1", question1, answers1);
		
		
		SourceIF question2 = sourceFactory.newSource("testFile.txt");
		question1.addText("question2");
		
		AnswerIF[] answers2 = new AnswerStub[2];
		answers2[0] = examFactory.newAnswer(false, sourceFactory.newSource("option3"));
		answers2[1] = examFactory.newAnswer(true, sourceFactory.newSource("option4"));
		
		ProblemIF prob2 = examFactory.newProblem("topic1", "label1", question2, answers2);
		
		ArrayList<ProblemIF> list = new ArrayList<ProblemIF>();
		list.add(prob1);
		list.add(prob2);
		
		return list;
	*/return null;
		}

	@Override
	public Collection<ProblemIF> problemsWithTopic(String topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFinalBlock(BlockIF block) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFrontMatter(SourceIF frontMatter) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPreamble(SourceIF preamble) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<String> topics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addElementIF(ExamElementIF element) {
		return 0;
	}

	@Override
	public void declareUse(ExamElementIF user, ExamElementIF usee) {
		// TODO Auto-generated method stub

	}


	@Override
	public ExamElementIF elementWithLabel(String label) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ExamElementIF> elements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ExamElementIF> elementsUsingElement(ExamElementIF element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ExamElementIF> elementsWithTopic(String topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<FigureIF> figures() {
		// TODO Auto-generated method stub
		return null;
	}
}//end of class