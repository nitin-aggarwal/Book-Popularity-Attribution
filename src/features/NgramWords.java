package features;

import generics.Document;
import generics.Punctuation;
import generics.StopWords;
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

public class NgramWords {
	
	int countD;
	
	public NgramWords()
	{
		countD = 0;
	}
	public boolean checkStopWord(Document doc,String key, long value)
	{
		boolean flag = false;
		
		for(String i: StopWords.stopWords)
		{
			if(key.compareToIgnoreCase(i) == 0)
			{
				flag = true;
				doc.stopWords += value;
				break;
			}
		}
		return flag;
	}
	
	public boolean checkPuncNums(Document doc,String key, long value)
	{
		boolean flag = false;
		
		//Exclude punctuation related words
		for(String i: Punctuation.punc)
		{
			if(key.indexOf(i) != -1)
			{
				doc.countPunc += value;
				flag = true;
				break;
			}
		}
		
				
		//Exclude numeric values
		for(String i: Punctuation.nums)
		{
			if(key.indexOf(i) != -1)
			{
				flag = true;
				break;
			}
		}
		
		//Exclude special characters values
		for(String i: Punctuation.extras)
		{
			if(key.indexOf(i) != -1)
			{
				flag = true;
				break;
			}
		}		
		return flag;
	}
	public Document uniGramWord(Document doc,TreeMap<String, Integer> uniW) throws IOException
	{
		File f = new File("WordPOSTags/UniGramWord/"+MainFunc.filename);
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		
		TreeMap<String, Integer> tempUni = new TreeMap<String, Integer>(new FeatureCalc(uniW));
		tempUni.putAll(uniW);
		Set<Entry<String, Integer>> set;
		set = tempUni.entrySet();
		Iterator<Entry<String, Integer>> itr;
		itr = set.iterator();
		
		while(itr.hasNext())
		{
			Map.Entry<String, Integer> temp = (Map.Entry<String, Integer>)itr.next();
			UniGram u = new UniGram(temp.getKey(),temp.getValue());
						
			if(temp.getKey().compareTo(",") == 0)
				doc.pun.comma = temp.getValue();
			if(temp.getKey().compareTo(":") == 0)
				doc.pun.colon = temp.getValue();
			if(temp.getKey().compareTo("''") == 0 || temp.getKey().compareTo("``") == 0)
				doc.pun.doubleQuotes += temp.getValue();
			if(temp.getKey().compareTo("'") == 0 || temp.getKey().compareTo("`") == 0)
				doc.pun.singleQuotes += temp.getValue();
			if(temp.getKey().compareTo("!") == 0)
				doc.pun.exclamation = temp.getValue();
			if(temp.getKey().compareTo("?") == 0)
				doc.pun.questionM = temp.getValue();
			if(temp.getKey().compareTo(".") == 0)
				doc.pun.fullStop = temp.getValue();
			if(temp.getKey().compareTo(";") == 0)
				doc.pun.semiColon = temp.getValue();
			if(temp.getKey().compareTo("...") == 0)
				doc.pun.ellipsis = temp.getValue();
			
			if(temp.getKey().compareTo("") == 0)
				continue;
			
			if(!checkPuncNums(doc,temp.getKey().toLowerCase(),temp.getValue()))
			{
				bw.write(temp.getKey().toLowerCase()+" "+temp.getValue());
				bw.write("\n");
				doc.countUniWords.put(u.words.toLowerCase(),u.count);
			}
		}
		bw.flush();
		bw.close();
		return doc;
	}
	
}
