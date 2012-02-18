package canonicizers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import mainDoc.MainFunc;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class PosTagger {
	
	MaxentTagger tagger;
	public PosTagger() throws IOException, ClassNotFoundException
	{
		tagger = new MaxentTagger("taggers/bidirectional-distsim-wsj-0-18.tagger");
	}
	public void tagString() throws IOException, ClassNotFoundException
	{
		String temp[];
		StringBuilder strPOS = new StringBuilder();
		
		String filePath = "POSTagged/"+MainFunc.filename;
		System.out.println("POS File: "+filePath);
		
		System.out.println("POS Tagging started");
		File f = new File(filePath);
		if(!f.exists())
		{
			System.gc();
			System.out.println("Available Memory: "+Runtime.getRuntime().freeMemory());
			System.out.println("Total Memory: "+Runtime.getRuntime().totalMemory());
			System.out.println("Max memory: "+Runtime.getRuntime().maxMemory());
			
			temp = MainFunc.docText.toString().split("\n");
			System.out.println("Sentences: "+temp.length);
			for(String sentence: temp)
				strPOS.append(tagger.tagString(sentence) +"\n");
					
			strPOS.setLength(strPOS.length()-1);
			
			System.out.println("POS tagging completed");
			
			System.out.println("POS file writing started");
			f.createNewFile();
			
			BufferedWriter posWrite = new BufferedWriter(new FileWriter(f));
			posWrite.write(strPOS.toString());
			posWrite.close();
		}
		else
		{
			String tempStr;
			BufferedReader br = new BufferedReader(new FileReader(f));
			while((tempStr = br.readLine()) != null)
				strPOS.append(tempStr);
			br.close();
		}
		
		System.out.println("POS file writing completed");
		
		MainFunc.parseText = strPOS;
	}
}
