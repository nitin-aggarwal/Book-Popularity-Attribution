package generics;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class Document {
		
	public float rating;
	public StringBuilder title = new StringBuilder();
	
	//Lexical Features
	public long countCharacters;
	public long countWords;
	public long countSentences;
	public long countSentences1;
	public long countParagraphs;
	public long countSyllable;
	
	public long stopWords;
	public double flesch;
	
	public TreeMap<String,Integer> countUniWords;
	
	public Punctuation pun;
	
	//Syntactic Features
	public long coarsePOSNoun;
	public long coarsePOSPronoun;
	public long coarsePOSVerb;
	public long coarsePOSPrep;
	public long coarsePOSAdverb;
	public long coarsePOSAdjective;
	
	public long countPunc;
	public long foreignWords;
	
	public TreeMap<String,Integer> countUniPOS;
	public TreeMap<String,Integer> countBiPOS;
	public TreeMap<String,Integer> countTriPOS;
	public TreeMap<String,Integer> countQuadPOS;
	
	//Semantic Features
	public double countNP;
	public double countVP;
	public double countAdjP;
	public double countCP;
	public double countDepth;
	public double avgScore;
	
	//Lexical Cohesion Features
	public long countNPCohesion;
	public long countWordCohesion;
	
	public Document()
	{
		rating = 0;
		
		countCharacters = 0;
		countWords = 0;
		countSentences = 0;
		countSentences1 = 0;
		countParagraphs = 0;
		countSyllable=0;
				
		countUniWords = new TreeMap<String,Integer>();
		
		pun = new Punctuation();
		
		coarsePOSNoun = 0;
		coarsePOSAdjective = 0;
		coarsePOSAdverb = 0;
		coarsePOSPrep = 0;
		coarsePOSVerb = 0;
		coarsePOSPronoun = 0;
		
		foreignWords = 0;
		stopWords = 0;
		countPunc = 0;
		
		countUniPOS = new TreeMap<String,Integer>();
		countBiPOS = new TreeMap<String,Integer>();
		countTriPOS = new TreeMap<String,Integer>();
		countQuadPOS = new TreeMap<String,Integer>();
		
		countNP=0;
		countVP=0;
		countAdjP=0;
		countCP=0;
		countDepth=0;
		avgScore=0;
		
		countNPCohesion = 0;
		countWordCohesion = 0;
	}
	public void print()
	{
		boolean flag = false;
		
		System.out.println("Title: "+title);
		System.out.println("Rating: "+rating);
		
		System.out.println("Character Count: "+countCharacters);
		System.out.println("Word Count: "+countWords);
		System.out.println("Sentence Count: "+countSentences);
		System.out.println("Sentence Count1: "+countSentences1);
		System.out.println("Paragraph Count: "+countParagraphs);
		System.out.println("Syllable Count: "+countSyllable);
			
		System.out.println("Flesch Readability:"+flesch);
		
		Set<Entry<String, Integer>> set;
		Iterator<Entry<String, Integer>> itr;
		
		set = countUniWords.entrySet();
		itr = set.iterator();
		System.out.println("\nCount Unigram Words:");
		while(itr.hasNext())
		{
			Map.Entry<String, Integer> temp = (Map.Entry<String, Integer>)itr.next();
			if(flag)
				System.out.println(temp.getKey()+" "+temp.getValue());
		}
				
		System.out.println("\nCoarse Noun Count: "+coarsePOSNoun);
		System.out.println("Coarse Pronoun Count: "+coarsePOSPronoun);
		System.out.println("Coarse Verb Count: "+coarsePOSVerb);
		System.out.println("Coarse Adverb Count: "+coarsePOSAdverb);
		System.out.println("Coarse Preposition Count: "+coarsePOSPrep);
		System.out.println("Coarse Adjective Count: "+coarsePOSAdjective);
		
		System.out.println("\nForeign Word Count: "+foreignWords);
		System.out.println("Stop Words Count: "+stopWords);
		
		System.out.println("Punctuations Count: "+countPunc);
		System.out.println("\nPunctuations:");
		pun.print();
		
		System.out.println("Count Noun Phrases: "+countNP);
		System.out.println("Count Verb Phrases: "+countVP);
		System.out.println("Count Adjective Phrases: "+countAdjP);
		System.out.println("Count Conjunction Phrases: "+countCP);
		System.out.println("Count Tree Depth: "+countDepth);
		System.out.println("Average Score: "+avgScore);
		
		System.out.println("Count NP Lexical Cohesion: "+countNPCohesion);
		System.out.println("Count Word Cohesion: "+countWordCohesion);
		
		set = countUniPOS.entrySet();
		itr = set.iterator();
		System.out.println("\nCount Unigram POS:");
		while(itr.hasNext())
		{
			Map.Entry<String, Integer> temp = (Map.Entry<String, Integer>)itr.next();
			if(flag)
				System.out.println(temp.getKey()+" "+temp.getValue());
		}
		
		set = countBiPOS.entrySet();
		itr = set.iterator();
		System.out.println("\nCount Bigram POS:");
		while(itr.hasNext())
		{
			Map.Entry<String, Integer> temp = (Map.Entry<String, Integer>)itr.next();
			if(flag)
				System.out.println(temp.getKey()+" "+temp.getValue());
		}
		
		set = countTriPOS.entrySet();
		itr = set.iterator();
		System.out.println("\nCount Trigram POS:");
		while(itr.hasNext())
		{
			Map.Entry<String, Integer> temp = (Map.Entry<String, Integer>)itr.next();
			if(flag)
				System.out.println(temp.getKey()+" "+temp.getValue());
		}
		
		set = countQuadPOS.entrySet();
		itr = set.iterator();
		System.out.println("\nCount Quadgram POS:");
		while(itr.hasNext())
		{
			Map.Entry<String, Integer> temp = (Map.Entry<String, Integer>)itr.next();
			if(flag)
				System.out.println(temp.getKey()+" "+temp.getValue());
		}
		
		
	}
}
