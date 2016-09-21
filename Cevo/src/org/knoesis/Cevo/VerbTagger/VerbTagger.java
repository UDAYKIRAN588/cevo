package org.knoesis.Cevo.VerbTagger;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;



import java.io.*;
import java.util.*;

import javax.swing.DefaultListModel;

import org.knoesis.Cevo.Result.Result;
import org.knoesis.Cevo.jenna.Jenna;
import edu.stanford.nlp.hcoref.data.CorefChain;
import edu.stanford.nlp.hcoref.CorefCoreAnnotations;
import edu.stanford.nlp.io.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.ling.CoreAnnotations.CharacterOffsetBeginAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.CharacterOffsetEndAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokenBeginAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.util.*;
import edu.stanford.nlp.util.TypesafeMap.Key;

/** This class demonstrates building and using a Stanford CoreNLP pipeline. */
public class VerbTagger {
  List <Result> results;
  Result r;
  public List<Result> pos(String input) throws IOException {
    // set up optional output files
    
	  PrintWriter out = new PrintWriter(System.out);

    // Create a CoreNLP pipeline. To build the default pipeline, you can just use:
    //   StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    // Here's a more complex setup example:
    //   Properties props = new Properties();
    //   props.put("annotators", "tokenize, ssplit, pos, lemma, ner, depparse");
    //   props.put("ner.model", "edu/stanford/nlp/models/ner/english.all.3class.distsim.crf.ser.gz");
    //   props.put("ner.applyNumericClassifiers", "false");
    //   StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

    // Add in sentiment
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize, ssplit, pos, lemma");
    //props.put("pos.model","WebContent\\WEB-INF\\lib\\tagger\\english-bidirectional-distsim.tagger");

    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    

    // Initialize an Annotation with some text to be annotated. The text is the argument to the constructor.
	Annotation annotation = new Annotation(input);
    
    // run all the selected Annotators on this text
    pipeline.annotate(annotation);

    // this prints out the results of sentence analysis to file(s) in good formats
    //pipeline.prettyPrint(annotation, out);
    
    List<CoreMap> sentences = annotation.get(SentencesAnnotation.class);
    
    results = new ArrayList<Result>();
    String[] tokenAnnotations = {
            "Text", "PartOfSpeech", "Lemma", "Answer", "NamedEntityTag",
            "CharacterOffsetBegin", "CharacterOffsetEnd", "NormalizedNamedEntityTag",
            "Timex", "TrueCase", "TrueCaseText", "SentimentClass" };
    Jenna j = new Jenna();
     
    for (CoreMap sentence: sentences) {
    	 String text = sentence.get(CoreAnnotations.TextAnnotation.class);
      // traversing the words in the current sentence
      // a CoreLabel is a CoreMap with additional token-specific methods
      for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
    	r = new Result();
    	r.setWord(token.get(TextAnnotation.class));
      	r.setPartOfSpeechAnnotation(token.get(PartOfSpeechAnnotation.class));
      	r.setOffsetBegin(token.get(CharacterOffsetBeginAnnotation.class));
      	r.setOffsetEnd(token.get(CharacterOffsetEndAnnotation.class));
      	r.setLemma(token.get(LemmaAnnotation.class));
      	String verbClass = j.getClass(token.get(LemmaAnnotation.class));
      	
      	if (verbClass !=null) {
      	r.setVerbClass(verbClass.substring(0, verbClass.indexOf(" ")));
    	r.setVebUri(verbClass.substring(verbClass.indexOf(" ")));
      	} else {
      		r.setVerbClass(null);
      		r.setVebUri(null);
      	}
      	results.add(r);
      }
      
    
    }
	
return results;
  }
  }

/*	below code useful for individual data values
 * 
 */




//if (token.toShorterString(tokenAnnotations).contains("PartOfSpeech=V")) {
//    System.out.println(token.toShorterString(tokenAnnotations));
//    model.addElement(token.toShorterString(tokenAnnotations));
//    System.out.println();
//}
  	


