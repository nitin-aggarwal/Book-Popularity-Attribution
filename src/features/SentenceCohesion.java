package features;

import generics.Document;
import generics.Punctuation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class SentenceCohesion {
 
	public static void main(String args[]) throws IOException
	{
		Document doc = new Document();
		doc = computeCohesion(doc);
	}
	public static Document computeCohesion(Document doc) throws IOException
	{
		StringBuilder filePath = new StringBuilder("POSTagged/"+doc.title);
		File f = new File(filePath.toString());
		
		String tempStr = "";
		StringBuilder strPOS = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(f));
		while((tempStr = br.readLine()) != null)
		{
			strPOS.append(tempStr+"\n");
		}
		br.close();
		
		String[] sentences = strPOS.toString().replace("./. "+"\n","\n").replace("./. ","\n").trim().split("\n");
		System.out.println("length: "+sentences.length);
		
		long nPcohesion = 0;
		for(int k=0;k<sentences.length-1;k++)
		{
			long temp = sentNPCohesion(sentences[k],sentences[k+1]);
			nPcohesion += temp;
		}
		
		long wDcohesion = 0;
		for(int k=0;k<sentences.length-1/* && k < 10*/;k++)
		{
			long temp = sentWordCohesion(sentences[k],sentences[k+1]);
			wDcohesion += temp;
		}
		
		doc.countSentences1 = sentences.length;
		doc.countNPCohesion = nPcohesion;
		doc.countWordCohesion = wDcohesion;
		return doc;
	}
	
	/*
	 * Compute continuity in two sentences
	 * over proper nouns and pronouns
	 */
	public static long sentNPCohesion(String sent1, String sent2)
	{
		String[] temp1 = sent1.split(" ");
		String[] temp2 = sent2.split(" ");
		
		ArrayList<String> array1 = new ArrayList<String>();
		ArrayList<String> array2 = new ArrayList<String>();
		
		HashSet<String> h = new HashSet<String>();
		
		int k;
		boolean flag = false;
		
		// Extract proper nouns and pronouns from sentence 1
		for(String str: temp1)
		{
			if((k = str.indexOf("/NN")) != -1)
			{
				if(flag)
					System.out.print(str+" ");
				array1.add(str.substring(0, k));
			}
			else if((k = str.indexOf("/PP")) != -1)
			{
				if(flag)
					System.out.print(str+" ");
				array1.add(str.substring(0, k));
			}
			
		}
		if(flag)
			System.out.println();
		
		// Remove repeated elements from array1
		h.addAll(array1);
		array1.clear();
		array1.addAll(h);
		h.clear();
		
		// Extracts proper nouns and pronouns from sentence 2
		for(String str: temp2)
		{
			if((k = str.indexOf("/NN")) != -1)
			{
				if(flag)
					System.out.print(str+" ");
				array2.add(str.substring(0, k));
			}
			else if((k = str.indexOf("/PP")) != -1)
			{
				if(flag)
					System.out.print(str+" ");
				array2.add(str.substring(0, k));
			}
			
		}
		if(flag)
			System.out.println();
		
		// Remove repeated elements from array2
				h.addAll(array2);
				array2.clear();
				array2.addAll(h);
		
		/*
		 *  Compare elements of sentences
		 *  for overlapping elements
		 */
		Iterator<String> itr1 = array1.iterator();
		Iterator<String> itr2 = array2.iterator();
		long countNPCont = 0;
		while(itr1.hasNext())
		{
			String temp = itr1.next();
			while(itr2.hasNext())
			{
				String tag = itr2.next();
				if(temp.compareTo(tag) == 0)
					countNPCont++;
			}
		}
		return countNPCont;
	}
	
	/*
	 * Compute continuity in two sentences
	 * over words
	 */
	public static long sentWordCohesion(String sent1, String sent2)
	{
		String[] temp1 = sent1.split(" ");
		String[] temp2 = sent2.split(" ");
		
		ArrayList<String> array1 = new ArrayList<String>();
		ArrayList<String> array2 = new ArrayList<String>();
		
		HashSet<String> h = new HashSet<String>();
		
		int k;
		boolean flag = false;
		
		/*
		 * Extract all words apart from ones with
		 * special characters and punctuation from sentence 1
		 */
		for(String str: temp1)
		{
			if(!Punctuation.checkPuncNums(str))
			{
				k = str.indexOf("/");
				if(flag)
					System.out.print(str+" ");
				if(k>0)
					array1.add(str.substring(0, k));
			}
		}
		if(flag)
			System.out.println();
		
		// Remove repeated elements from array1
		h.addAll(array1);
		array1.clear();
		array1.addAll(h);
		h.clear();
		
		/*
		 * Extract all words apart from ones with
		 * special characters and punctuation from sentence 2
		 */
		for(String str: temp2)
		{
			if(!Punctuation.checkPuncNums(str))
			{
				k = str.indexOf("/");
				if(flag)
					System.out.print(str+" ");
				if(k>0)
					array2.add(str.substring(0, k));
			}
		}
		if(flag)
			System.out.println();
		
		// Remove repeated elements from array2
				h.addAll(array2);
				array2.clear();
				array2.addAll(h);
		
		/*
		 *  Compare elements of sentences
		 *  for overlapping elements
		 */
		Iterator<String> itr1 = array1.iterator();
		Iterator<String> itr2;
		long countWCont = 0;
		while(itr1.hasNext())
		{
			String temp = itr1.next();
			itr2 = array2.iterator();
			while(itr2.hasNext())
			{
				String tag = itr2.next();
				if(temp.compareToIgnoreCase(tag) == 0)
					countWCont++;
			}
		}
		return countWCont;
	}
}
