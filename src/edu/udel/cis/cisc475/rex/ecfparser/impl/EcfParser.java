package edu.udel.cis.cisc475.rex.ecfparser.impl;

import edu.udel.cis.cisc475.rex.ecfparser.IF.EcfParserIF;
import edu.udel.cis.cisc475.rex.ecfparser.impl.err.EcfParserHackException;
import edu.udel.cis.cisc475.rex.ecfparser.impl.parser.EcfAntlrLexer;
import edu.udel.cis.cisc475.rex.ecfparser.impl.parser.EcfAntlrParser;
import edu.udel.cis.cisc475.rex.config.IF.ConfigIF;
import edu.udel.cis.cisc475.rex.config.IF.ConfigFactoryIF;
import edu.udel.cis.cisc475.rex.config.impl.ConfigFactory;
import edu.udel.cis.cisc475.rex.source.IF.SourceIF;
import edu.udel.cis.cisc475.rex.source.IF.SourceFactoryIF;
import edu.udel.cis.cisc475.rex.source.impl.SourceFactory;

import edu.udel.cis.cisc475.rex.err.RexUnsatisfiableException;
import edu.udel.cis.cisc475.rex.err.RexParseException;

import java.io.File;
import java.io.IOException;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;



/**
 * 
 * @author cates
 * wrapper for the parser generated by ANTLR3
 */
 

 
public class EcfParser implements EcfParserIF {

  private long seed;
  private boolean pdfOption;
  private int numGeneratedExams;

  public EcfParser(int numGeneratedExams) {
    this.numGeneratedExams = numGeneratedExams;
  }

  public ConfigIF parse(File file) throws IOException, RexUnsatisfiableException, RexParseException {
    return this.genericInputParse(new ANTLRFileStream(file.toString()), file.toString());
  }
  
  public ConfigIF parseString(String ecfString) throws RexUnsatisfiableException, RexParseException {
    return this.genericInputParse(new ANTLRStringStream(ecfString), "passedAsString");
  }
  
  public ConfigIF genericInputParse(CharStream stream, String filename) throws RexUnsatisfiableException, RexParseException {
    ConfigFactoryIF configFactory = new ConfigFactory();
    ConfigIF config = configFactory.newConfig(pdfOption, numGeneratedExams);

    EcfAntlrLexer lexer = new EcfAntlrLexer(stream);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    EcfAntlrParser g = new EcfAntlrParser(tokens);

    
    try {
      g.ecf(config, filename);
    } catch (EcfParserHackException e) {
			throw new RexUnsatisfiableException(e.getMessage());
    } catch (RecognitionException e) {
			// create a source obect to stick into the new exception
			SourceFactoryIF sourceFactory = new SourceFactory();
			SourceIF errorSource = sourceFactory.newSource(filename);
			errorSource.setStartLine(e.line);
			errorSource.setStartColumn(e.charPositionInLine);
			errorSource.addText(e.token.getText());
			
			System.err.println("this isnt happening :[");
			throw new RexParseException(e.getMessage(), errorSource);
    }
    config.setSeed(seed);
    return config;
  }

  public void setPdfOption(boolean pdfOption) {
    this.pdfOption = pdfOption;
  }

  public void setSeed(long seed) {
    this.seed = seed;
  }

}

