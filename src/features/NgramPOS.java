package features;

import generics.BiGram;
import generics.Document;
import generics.Punctuation;
import generics.QuadGram;
import generics.TriGram;
import generics.UniGram;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import mainDoc.FeatureCalc;
import mainDoc.MainFunc;

public class NgramPOS {
	
	public Document uniGramPOS(Document doc,TreeMap<String, Integer> uniPOS) throws IOException
	{
		File f = new File("WordPOSTags/UniGramPOS/"+MainFunc.filename);
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		
		TreeMap<String, Integer> tempUniP = new TreeMap<String, Integer>(new FeatureCalc(uniPOS));
		tempUniP.putAll(uniPOS);
		Set<Entry<String, Integer>> set;
		set = tempUniP.entrySet();
		Iterator<Entry<String, Integer>> itr;
		itr = set.iterator();
		
		while(itr.hasNext())
		{
			Map.Entry<String, Integer> temp = (Map.Entry<String, Integer>)itr.next();
			UniGram u = new UniGram(temp.getKey(),temp.getValue());
			
			// Check for punctuations and numbers in a unigram		
			if(!Punctuation.checkPuncNums(temp.getKey()))
			{
				bw.write(temp.getKey()+" "+temp.getValue());
				bw.write("\n");
				
				// Tree for unique Uni-grams
				MainFunc.uniPD.add(temp.getKey());
				
				// Document Uni-grams
				doc.countUniPOS.put(u.words,u.count);
			}
			
			// Update Coarse Parts Of Speech counts
			if(temp.getKey().startsWith("NN"))				//Noun
				doc.coarsePOSNoun += temp.getValue();
			if(temp.getKey().startsWith("VB"))				//Verb
				doc.coarsePOSVerb += temp.getValue();
			if(temp.getKey().startsWith("RB"))				//Adverb
				doc.coarsePOSAdverb += temp.getValue();
			if(temp.getKey().startsWith("IN"))				//Preposition
				doc.coarsePOSPrep += temp.getValue();
			if(temp.getKey().startsWith("PRP"))				//Pronoun
				doc.coarsePOSPronoun += temp.getValue();
			if(temp.getKey().startsWith("JJ"))				//Adjective
				doc.coarsePOSAdjective += temp.getValue();
			
			// Update Foreign Words Counts
			if(temp.getKey().startsWith("FW"))				//Foreign Words
				doc.foreignWords += temp.getValue();
		}
		bw.flush();
		bw.close();
		return doc;
	}
	
	public Document biGramPOS(Document doc,TreeMap<String, Integer> biPOS) throws IOException
	{
		File f = new File("WordPOSTags/BiGramPOS/"+MainFunc.filename);
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		
		TreeMap<String, Integer> tempBiP = new TreeMap<String, Integer>(new FeatureCalc(biPOS));
		tempBiP.putAll(biPOS);
		Set<Entry<String, Integer>> set;
		Iterator<Entry<String, Integer>> itr;
		set = tempBiP.entrySet();
		itr = set.iterator();
		
		while(itr.hasNext())
		{
			Map.Entry<String, Integer> temp = (Map.Entry<String, Integer>)itr.next();
			String[] strings = temp.getKey().split(" ");
			
			//Eliminate sets which are inconsistent Bigrams
			if(strings.length == 2)
			{
				// Check for punctuations and numbers in a bigram		
				if(!Punctuation.checkPuncNums(temp.getKey()))
				{
					BiGram b = new BiGram(strings[0],strings[1],temp.getValue());
					doc.countBiPOS.put(b.words[0]+" "+b.words[1],b.count);
						
					bw.write(temp.getKey()+" "+temp.getValue());
					bw.write("\n");
					
					// Tree for unique Bigrams
					MainFunc.biPD.add(temp.getKey());
				}
			}
			
		}
		bw.flush();
		bw.close();
		return doc;
	}
	public Document triGramPOS(Document doc,TreeMap<String, Integer> triPOS) throws IOException
	{
		File f = new File("WordPOSTags/TriGramPOS/"+MainFunc.filename);
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		
		TreeMap<String, Integer> tempTriP = new TreeMap<String, Integer>(new FeatureCalc(triPOS));
		tempTriP.putAll(triPOS);
		Set<Entry<String, Integer>> set;
		Iterator<Entry<String, Integer>> itr;
		set = tempTriP.entrySet();
		itr = set.iterator();
		
		while(itr.hasNext())
		{
			Map.Entry<String, Integer> temp = (Map.Entry<String, Integer>)itr.next();
			String[] strings = temp.getKey().split(" ");
			
			//Eliminate sets which are inconsistent Trigrams
			if(strings.length == 3)
			{
				// Check for punctuation and numbers in a unigram		
				if(!Punctuation.checkPuncNums(temp.getKey()))
				{
					TriGram t = new TriGram(strings[0],strings[1],strings[2],temp.getValue());
					doc.countTriPOS.put(t.words[0]+" "+t.words[1]+" "+t.words[2],t.count);
					
					bw.write(temp.getKey()+" "+temp.getValue());
					bw.write("\n");
				
					// Tree for unique Trigrams
					MainFunc.triPD.add(temp.getKey());
				}
			}
			
		}
		bw.flush();
		bw.close();
		return doc;
	}
	public Document quadGramPOS(Document doc,TreeMap<String, Integer> quadPOS) throws IOException
	{
		File f = new File("WordPOSTags/QuadGramPOS/"+MainFunc.filename);
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		
		TreeMap<String, Integer> tempQuadP = new TreeMap<String, Integer>(new FeatureCalc(quadPOS));
		tempQuadP.putAll(quadPOS);
		Set<Entry<String, Integer>> set;
		Iterator<Entry<String, Integer>> itr;
		set = tempQuadP.entrySet();
		itr = set.iterator();
		
		while(itr.hasNext())
		{
			Map.Entry<String, Integer> temp = (Map.Entry<String, Integer>)itr.next();
			String[] strings = temp.getKey().split(" ");
			
			//Eliminate sets which are inconsistent Quadgrams
			if(strings.length == 4)
			{
				// Check for punctuations and numbers in a quadgram		
				if(!Punctuation.checkPuncNums(temp.getKey()))
				{
					QuadGram q = new QuadGram(strings[0],strings[1],strings[2],strings[3],temp.getValue());
					doc.countQuadPOS.put(q.words[0]+" "+q.words[1]+" "+q.words[2]+" "+q.words[3],q.count);
							
					bw.write(temp.getKey()+" "+temp.getValue());
					bw.write("\n");
				
					// Tree for unique Quadgrams
					MainFunc.quadPD.add(temp.getKey());
				}
			}
			
		}
		bw.flush();
		bw.close();
		return doc;
	}
}
