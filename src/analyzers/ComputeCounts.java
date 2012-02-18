package analyzers;

import features.SentenceCohesion;
import generics.Document;
import generics.MoodWords;
import generics.StopWords;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;


public class ComputeCounts {
	
	public static ArrayList<String> distinctUniPOS = new ArrayList<String>();
	public static ArrayList<String> distinctBiPOS = new ArrayList<String>();
	public static ArrayList<String> distinctTriPOS = new ArrayList<String>();
	public static ArrayList<String> distinctQuadPOS = new ArrayList<String>();
	
	public void compute() throws NumberFormatException, IOException, SQLException, ClassNotFoundException
	{
		Document doc = new Document();
		
		ReadDatabase rdbObj = new ReadDatabase();
		ReadDistinct rdiObj = new ReadDistinct();
		ReadDocument rdObj = new ReadDocument();
		
		
		File dir = new File("Documents/");
		String[] htmlFiles = dir.list();
		if(htmlFiles != null)
		{
			
			/*
			 *  Read all set of distinct values
			 *  for N-gram words and POS
			 */
			rdiObj.readUnigramPOS();
			rdiObj.readBigramPOS();
			rdiObj.readTrigramPOS();
			rdiObj.readQuadgramPOS();
			
			System.out.println("@relation lexical");
			for(int z=1;z<6;z++)
				System.out.println("@attribute attr"+z+" numeric");
			System.out.println("@attribute ranking {A,B,C,D,E,F}");
			
			System.out.println();
			System.out.println("@data");
					
			for(int i=0;i<htmlFiles.length;i++)
			{
				doc.title = new StringBuilder(htmlFiles[i]);
				SentenceCohesion.computeCohesion(doc);
				
				/*
				 *  Read all N-gram words and POS 
				 *  sequences for the document
				 *  in the object
				 */
				doc = rdObj.readUnigramWord(doc);
				doc = rdObj.readUnigramPOS(doc);
				doc = rdObj.readBigramPOS(doc);
				doc = rdObj.readTrigramPOS(doc);
				doc = rdObj.readQuadgramPOS(doc);
				
				/*
				 *  Read all feature values stored in database
				 *  for the document in object
				 */
				doc = rdbObj.readLexical(doc);
				doc = rdbObj.readSyntactic(doc);
				doc = rdbObj.readSemantic(doc);
				doc = rdbObj.readPunc(doc);
				doc = rdbObj.readRating(doc);
				
				String classRating="Z";
				
				// 3-way classification
				
				/*if(doc.rating >= 3.0 && doc.rating < 3.5)
					classRating = "A";
				else if(doc.rating >= 3.5 && doc.rating < 4.0)
					classRating = "B";
				else if(doc.rating >= 4.0 && doc.rating < 4.5)
					classRating = "C";*/
				
				// 6-way classification
				if(doc.rating >= 3.0 && doc.rating < 3.25)
					classRating = "A";
				else if(doc.rating >= 3.25 && doc.rating < 3.5)
					classRating = "B";
				else if(doc.rating >= 3.5 && doc.rating < 3.75)
					classRating = "C";
				else if(doc.rating >= 3.75 && doc.rating < 4.0)
					classRating = "D";
				else if(doc.rating >= 4.0 && doc.rating < 4.25)
					classRating = "E";
				else if(doc.rating >= 4.25 && doc.rating < 4.5)
					classRating = "F";
					
				if(classRating.compareToIgnoreCase("Z") == 0)
					continue;
				StringBuilder str;
				
				str = computeLexical(doc);
				System.out.println("Lexical:");
				System.out.println(str+","+classRating);
				
				str = computeSyntactic(doc);
				System.out.println("Syntactic:");
				System.out.println(str+","+classRating);
				
				str = computeSemantic(doc);
				System.out.println("Semantic:");
				System.out.println(str+","+classRating);
				
				str = computeLexicalCohesion(doc);
				System.out.println("Lexical Cohesion:");
				System.out.println(str+","+classRating);
				
				str = computePunc(doc);
				System.out.println("Punctuation:");
				System.out.println(str+","+classRating);
				
				str = computeStopWord(doc);
				System.out.println("Stop Words:");
				System.out.println(str+","+classRating);
				
				str = computeMoodWord(doc);
				System.out.println("Mood Words:");
				System.out.println(str+","+classRating);
				
				str = computeUnigramPOS(doc);
				System.out.println("Unigram POS:");
				System.out.println(str+","+classRating);
				
				str = computeBigramPOS(doc);
				System.out.println("Bigram POS:");
				System.out.println(str+","+classRating);
				
				str = computeTrigramPOS(doc);
				System.out.println("Trigram POS:");
				System.out.println(str+","+classRating);
								
				str = computeQuadgramPOS(doc);
				System.out.println("Quadgram POS:");
				System.out.println(str+","+classRating);
				
			}
		}
	}
	
	private StringBuilder computeLexical(Document doc)
	{
		float avgWordLength = (float)doc.countWords/doc.countCharacters;
		float avgSentenceLength = (float)doc.countSentences/doc.countWords;
		float avgParagraphLength =(float)doc.countSentences/doc.countParagraphs;
		float avgStopWord = (float)doc.stopWords/doc.countSentences;
		
		return (new StringBuilder(avgWordLength+","+avgSentenceLength+","+avgParagraphLength+","+
			avgStopWord+","+doc.flesch));
	}
	
	private StringBuilder computeSyntactic(Document doc)
	{
		float avgNoun = (float)doc.coarsePOSNoun/doc.countSentences;
		float avgPronoun = (float)doc.coarsePOSPronoun/doc.countSentences;
		float avgVerb =(float)doc.coarsePOSVerb/doc.countSentences;
		float avgPrep = (float)doc.coarsePOSPrep/doc.countSentences;
		float avgAdverb = (float)doc.coarsePOSAdverb/doc.countSentences;
		float avgAdjective = (float)doc.coarsePOSAdjective/doc.countSentences;
		
		float avgPunc = (float)doc.countPunc/doc.countSentences;
		float avgFW = (float)doc.foreignWords/doc.countSentences;
		
		return (new StringBuilder(avgNoun+","+avgPronoun+","+avgVerb+","+avgPrep+","+
				avgAdverb+","+avgAdjective+","+avgPunc+","+avgFW));
	}
	
	private StringBuilder computeSemantic(Document doc)
	{
		double avgNP = doc.countNP;
		double avgVP = doc.countVP;
		double avgAdjP = doc.countAdjP;
		double avgCP = doc.countCP;
		double avgScore = doc.avgScore;
		double avgDepth = doc.countDepth;
		
		return (new StringBuilder(avgNP+","+avgVP+","+avgAdjP+","+avgCP+","+avgScore+","+avgDepth));
	}
	
	private StringBuilder computeLexicalCohesion(Document doc)
	{
		float avgNPCohesion = (float)doc.countNPCohesion/doc.countSentences;
		float avgWordCohesion = (float)doc.countWordCohesion/doc.countSentences;
		
		return (new StringBuilder(avgNPCohesion+","+avgWordCohesion));
	}
	
	private StringBuilder computePunc(Document doc)
	{
		float avgComma = (float)doc.pun.comma/doc.countSentences;
		float avgDQ = (float)doc.pun.doubleQuotes/doc.countSentences;
		float avgSQ = (float)doc.pun.singleQuotes/doc.countSentences;
		float avgExc = (float)doc.pun.exclamation/doc.countSentences;
		float avgQ = (float)doc.pun.questionM/doc.countSentences;
		float avgEl = (float)doc.pun.ellipsis/doc.countSentences;
		float avgColon = (float)doc.pun.colon/doc.countSentences;
		float avgFS = (float)doc.pun.fullStop/doc.countSentences;
		float avgSemiColon = (float)doc.pun.semiColon/doc.countSentences;
		
		return (new StringBuilder(avgComma+","+avgDQ+","+avgSQ+","+avgExc+","+avgQ+","+avgEl+","+avgColon
					+","+avgFS+","+avgSemiColon));
	}
	
	private StringBuilder computeStopWord(Document doc)
	{
		StringBuilder temp = new StringBuilder();
		for(String stop: StopWords.stopWords)
		{
			Integer value = doc.countUniWords.get(stop);
			if(value != null)
				temp.append((float)value/doc.countWords + ",");
			else
				temp.append("0,");
		}
		temp.setLength(temp.length()-1);
		return temp;
	}
	
	private StringBuilder computeMoodWord(Document doc)
	{
		StringBuilder temp = new StringBuilder();
		for(String mood: MoodWords.moodWords)
		{
			Integer value = doc.countUniWords.get(mood);
			if(value != null)
				temp.append(value + ",");
			else
				temp.append("0,");
		}
		temp.setLength(temp.length()-1);
		return temp;
	}
	
	private StringBuilder computeUnigramPOS(Document doc)
	{
		StringBuilder temp = new StringBuilder();
		Iterator<String> itr = distinctUniPOS.iterator();
		while(itr.hasNext())
		{
			Integer value = doc.countUniPOS.get(itr.next());
			if(value != null)
				temp.append(value + ",");
			else
				temp.append("0,");
		}
		temp.setLength(temp.length()-1);
		return temp;
	}
	
	private StringBuilder computeBigramPOS(Document doc)
	{
		StringBuilder temp = new StringBuilder();
		Iterator<String> itr = distinctBiPOS.iterator();
		while(itr.hasNext())
		{
			Integer value = doc.countBiPOS.get(itr.next());
			if(value != null)
				temp.append(value + ",");
			else
				temp.append("0,");
		}
		temp.setLength(temp.length()-1);
		return temp;
	}
	
	private StringBuilder computeTrigramPOS(Document doc)
	{
		StringBuilder temp = new StringBuilder();
		Iterator<String> itr = distinctTriPOS.iterator();
		while(itr.hasNext())
		{
			String str = itr.next();
			Integer value = doc.countTriPOS.get(str);
			if(value != null)
				temp.append(value + ",");
			else
				temp.append("0,");
		}
		temp.setLength(temp.length()-1);
		return temp;
	}
	
	private StringBuilder computeQuadgramPOS(Document doc)
	{
		StringBuilder temp = new StringBuilder();
		Iterator<String> itr = distinctQuadPOS.iterator();
		while(itr.hasNext())
		{String str = itr.next();
		Integer value = doc.countTriPOS.get(str);
			if(value != null)
				temp.append(value + ",");
			else
				temp.append("0,");
			
			if(value != null && value > 100)
				System.out.println(str);
		}
		temp.setLength(temp.length()-1);
		return temp;
	}
}
