package features;
import java.util.Iterator;
import java.util.List;

import mainDoc.MainFunc;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.trees.Tree;
import generics.Document;


public class NlpParser 
{

	/**
	 * @param args
	 */

	LexicalizedParser lp;
	int limit =1000;
	public NlpParser()
	{
		lp = new LexicalizedParser("taggers/englishPCFG.ser.gz");
		lp.setOptionFlags("-maxLength", "1000", "-retainTmpSubcategories");
	}
	public Document calPhrases(Document doc) 
	{
		// TODO Auto-generated method stub

		long countNP = 0;		//Noun
		long countVP = 0;		//verb
		long countAdjP = 0;		//Adjective
		long countCP = 0;		//Conjunction
		long countDepth = 0;		//Depth
		double countScore = 0;  //Tree Grammar Score
		long countS = 0;			//Sentences
		
		long countFinal = 0;		//For sentences greater than 1000
		
		System.out.println("Parse start");
		
	    DocumentPreprocessor dp = new DocumentPreprocessor("Documents/"+MainFunc.filename);
	    		
	    int k = 0;
	    		
		System.out.println("Total Memory: "+Runtime.getRuntime().totalMemory());
		System.out.println("Max Memory: "+Runtime.getRuntime().maxMemory());
		 
		Tree parse;
		
		int tempCount = 0;
		
	    for (List<HasWord> sentence : dp)
	  	{
	    	  tempCount++;
	    	  if(tempCount> limit)
	    		  break;
	    	  
	  	   	  System.out.print(++k + " ");
	  	   	  if(k%50 == 0)
	  	   	  {
	  	    	  System.out.println();
	  	    	  System.out.println("Before gc Free Memory: "+Runtime.getRuntime().freeMemory());
	  	    	  Runtime.getRuntime().gc();
	  	    	  //System.gc();
	  	    	  System.out.println("After gc Free Memory:  "+Runtime.getRuntime().freeMemory());
	  	   	  }
	  	   	  
	  	   	  parse = lp.apply(sentence);
	  	   	  
	  	   	  countDepth += parse.depth();
	  	   	  countScore += parse.score();
	          
	  	   	  //parse.pennPrint();
	  	   	  
	  	   	  Iterator<Tree> itr = parse.iterator(); 
	          //parse.toStringBuilder(splitter1);
	         
	  	   	  String tempStr = "";
	  	   	  while(itr.hasNext())
	  	   	  {
	  	   		  Tree obj = itr.next();
	  	   		  String str = obj.nodeString().toString();
	  	   		  String[] splitter = str.split(" ");
	  	   		  
	  	   		  tempStr = splitter[0];
	  	   		  
	  	   		  if(tempStr.toString().compareTo("NP") == 0)
	        		  countNP++;
	        	  else if(tempStr.toString().compareTo("VP") == 0)
	        		  countVP++;
	        	  else if(tempStr.toString().compareTo("ADJP") == 0)
	        		  countAdjP++;
	        	  else if(tempStr.toString().compareTo("CONJP") == 0)
	        		  countCP++;
	        	  
	          }
	  	}
	    if(countS > limit)
	    	countFinal = limit;
	    else
	    	countFinal = countS;
	  	System.out.println("Parse terminates");
	  	
	  	doc.countSentences = countS;
	  	doc.countNP = (double)countNP/countFinal;
	  	doc.countVP = (double)countVP/countFinal;
	  	doc.countAdjP = (double)countAdjP/countFinal;
	  	doc.countCP = (double)countCP/countFinal;
	  	doc.countDepth = (double)countDepth/countFinal;
	  	doc.avgScore = (double)countScore/countFinal;
	  	
	  	return doc;
	}

}
