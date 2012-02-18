package mainDoc;
import features.NgramPOS;
import features.NgramWords;
import features.NlpParser;
import features.SentenceCohesion;
import generics.Document;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;


public class FeatureCalc implements Comparator<Object>{

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	
	public TreeMap<String, Integer> uniW;
	
	public TreeMap<String, Integer> uniPOS;
	public TreeMap<String, Integer> biPOS;
	public TreeMap<String, Integer> triPOS;
	public TreeMap<String, Integer> quadPOS;
	
	public Map<String, Integer> m;
	
	public FeatureCalc(Map<String,Integer> map)
	{
		this.m = map;
	}
	
	public void feature() throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Document doc=new Document();
		doc.title = MainFunc.filename;
		
		NgramWords nWObj = new NgramWords();
		NgramPOS nPObj = new NgramPOS();
		NlpParser nObj = new NlpParser();
		
		uniW = new TreeMap<String, Integer>();
		
		uniPOS = new TreeMap<String, Integer>();
		biPOS = new TreeMap<String, Integer>();
		triPOS = new TreeMap<String, Integer>();
		quadPOS = new TreeMap<String, Integer>();
		
		//Calculating number of paragraphs
		StringBuilder docStr = new StringBuilder(MainFunc.docText);
		for(int i=0;i<docStr.length();i++)
		{
			if(docStr.charAt(i) == '\n')
				doc.countParagraphs++;				
		}
		doc.countParagraphs++;
		
		doc = calculate(doc);
		
		//N-gram Word and POS Features
		doc = nWObj.uniGramWord(doc, uniW);	
		doc = nPObj.uniGramPOS(doc, uniPOS);
		doc = nPObj.biGramPOS(doc, biPOS);
		doc = nPObj.triGramPOS(doc, triPOS);
		doc = nPObj.quadGramPOS(doc, quadPOS);
		
		//Lexical Cohesion
		doc = SentenceCohesion.computeCohesion(doc);
		
		//Semantic
		doc = nObj.calPhrases(doc);
		
		doc.flesch=206.835-(1.1015*(doc.countWords/doc.countSentences))-(84.6*(doc.countSyllable/doc.countWords));
		MainFunc.doc = doc;	
	}
	public Document calculate(Document d)
	{		
		String syllable="aeiouyAEIOUY";
		StringBuilder strPOS = new StringBuilder(MainFunc.parseText);
		String[] pair = strPOS.toString().split(" ");
		
		String[] words = new String[pair.length];
		String[] POS = new String[pair.length];
		
		// Unigram Words
		for(int i=0;i<pair.length;i++)
		{
			String[] temp = pair[i].split("/");
			if(temp.length < 2)
				continue;
			if(pair[i].length() > 3)
			{
				d.countCharacters += temp[0].length();
				d.countWords++;
			}
			if(pair[i].compareTo("./.") == 0)
			{
				d.countSentences++;
			}
			
			words[i] = temp[0];
			POS[i] = temp[1];
			addTreeElement(uniW, temp[0]);
			addTreeElement(uniPOS, temp[1]);
			String word=(String)words[i];
			for(int k=0;k<words[i].length();k++)
			{
				if((syllable.indexOf(word.charAt(k))!=-1))
				{
					if(!(k==words[i].length()-1 && word.charAt(k)=='e'))
				    	d.countSyllable++;
					k++;
				}
			}
		}
		
		
		// Bigram POS
		for(int i=0;i<pair.length -1;i++)
		{
			addTreeElement(biPOS, POS[i]+" "+POS[i+1]);
		}
		
		// Trigram POS
		for(int i=0;i<pair.length -2;i++)
		{
			addTreeElement(triPOS, POS[i]+" "+POS[i+1]+" "+POS[i+2]);
		}
		
		// Quadgram POS
		for(int i=0;i<pair.length -3;i++)
		{
			addTreeElement(quadPOS, POS[i]+" "+POS[i+1]+" "+POS[i+2]+" "+POS[i+3]);
		}
		return d;
	}
	
	public void addTreeElement(TreeMap<String, Integer> tm, String str)
	{
		Integer value = tm.get(str);
		if(value != null)
		{
			tm.put(str, new Integer(value+1));
		}
		else
			tm.put(str, 1);
	}
	
	@Override
	public int compare(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		
		String a = (String)arg0;
		String b = (String)arg1;
		
		Integer val1 = this.m.get(a);
		Integer val2 = this.m.get(b);
		
		if(val2 > val1)
			return 1;
		else 
			return -1;
	}	
}
