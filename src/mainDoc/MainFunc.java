package mainDoc;
import features.SyncDistinct;
import generics.Document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeSet;

import analyzers.ComputeCounts;

import canonicizers.Canonize;
import canonicizers.PosTagger;


public class MainFunc {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 * @throws SQLException 
	 */
	
	public static StringBuilder filename;
	
	public static StringBuilder htmlText;
	public static StringBuilder docText;
	public static StringBuilder parseText;
	public static Document doc;
	
	public static TreeSet<String> uniPD = new TreeSet<String>();
	public static TreeSet<String> biPD = new TreeSet<String>();
	public static TreeSet<String> triPD = new TreeSet<String>();
	public static TreeSet<String> quadPD = new TreeSet<String>();
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Map<String,Integer> m = null;
		System.out.println("Started");
		Canonize canObj = new Canonize();
		PosTagger posObj = new PosTagger();
		FeatureCalc fObj = new FeatureCalc(m);
		SyncDistinct synObj = new SyncDistinct();
		
		ComputeCounts ccObj = new ComputeCounts();
		
		boolean flagFeatures = false; 
		boolean flagDb = false;
		boolean flagSync = false;
		boolean flagComputations = true;
		
		File dir = new File("HTMLDocs/");
		
		String[] htmlFiles = dir.list();
		
		if(htmlFiles != null)
		{
			System.out.println("Total files: "+htmlFiles.length);
			for(int i=200;i<htmlFiles.length;i++)
			{
				htmlText = new StringBuilder();
				docText = new StringBuilder();
				parseText = new StringBuilder();
				filename = new StringBuilder(htmlFiles[i]);
				doc = new Document();
				
				System.gc();
				System.out.println("Available Memory: "+Runtime.getRuntime().freeMemory());
				System.out.println("Total Memory: "+Runtime.getRuntime().totalMemory());
				System.out.println("Max memory: "+Runtime.getRuntime().maxMemory());
				
				System.out.println("Processing File: "+ (i+1));
				// Reads the content of HTML file
				String text ="";
				String path = "HTMLDocs/" + filename;
				BufferedReader br = new BufferedReader(new FileReader(path)); 
				while((text = br.readLine()) != null)
				{
					htmlText.append(text);
				}
				br.close();
				
				//Canonized
				canObj.canonizersFun();
				
				//POS Tagging
				posObj.tagString();
				
				//Calculating Features
				if(flagFeatures)
					fObj.feature();
								
				/*
				 *  Storing the value of different features 
				 *  in a database
				 */
				if(flagDb)
				{
					try
					{
						DBop.insert_lex(Integer.valueOf(filename.toString()), doc);
						DBop.insert_syn(Integer.valueOf(filename.toString()), doc);
						DBop.insert_sem(Integer.valueOf(filename.toString()), doc);
						DBop.insert_punc(Integer.valueOf(filename.toString()), doc);
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
				}
								
				// Calculating the unique set of Unigram Words
				if(flagSync)
				{
					//synObj.syncUnigramWord(uniWD);
					synObj.syncUnigramPOS(uniPD);
					synObj.syncBigramPOS(biPD);
					synObj.syncTrigramPOS(triPD);
					synObj.syncQuadgramPOS(quadPD);
				}
			}
			
		}
		if(flagComputations)
			ccObj.compute();
	}

}
