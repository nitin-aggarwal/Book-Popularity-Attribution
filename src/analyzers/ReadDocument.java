package analyzers;

import generics.BiGram;
import generics.Document;
import generics.QuadGram;
import generics.TriGram;
import generics.UniGram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadDocument {


	public Document readUnigramWord(Document doc) throws NumberFormatException, IOException
	{
		// Writing back to a file in another directory
		String filePath = "WordPOSTags/UnigramWord/"+doc.title;
		File f = new File(filePath);
		
		String temp;
		UniGram u = new UniGram();
		BufferedReader br = new BufferedReader(new FileReader(f));
		while((temp = br.readLine()) != null)
		{ 
			String[] tags = temp.split(" ");
			u.words = tags[0];
			u.count = Integer.parseInt(tags[1]);
			doc.countUniWords.put(u.words,u.count);
		}
		br.close();
		return doc;
	}
	public Document readUnigramPOS(Document doc) throws NumberFormatException, IOException
	{
		// Writing back to a file in another directory
		String filePath = "WordPOSTags/UnigramPOS/"+doc.title;
		File f = new File(filePath);
		
		String temp;
		UniGram u = new UniGram();
		BufferedReader br = new BufferedReader(new FileReader(f));
		while((temp = br.readLine()) != null)
		{ 
			String[] tags = temp.split(" ");
			u.words = tags[0];
			u.count = Integer.parseInt(tags[1]);
			doc.countUniPOS.put(u.words,u.count);
		}
		br.close();
		return doc;
	}
	public Document readBigramPOS(Document doc) throws NumberFormatException, IOException
	{
		// Writing back to a file in another directory
		String filePath = "WordPOSTags/BigramPOS/"+doc.title;
		File f = new File(filePath);
		
		String temp;
		BiGram u = new BiGram();
		BufferedReader br = new BufferedReader(new FileReader(f));
		while((temp = br.readLine()) != null)
		{ 
			String[] tags = temp.split(" ");
			u.words[0] = tags[0];
			u.words[1] = tags[1];
			u.count = Integer.parseInt(tags[2]);
			doc.countBiPOS.put(u.words[0]+" "+u.words[1],u.count);
		}
		br.close();
		return doc;
	}
	public Document readTrigramPOS(Document doc) throws NumberFormatException, IOException
	{
		// Writing back to a file in another directory
		String filePath = "WordPOSTags/TrigramPOS/"+doc.title;
		File f = new File(filePath);
		
		String temp;
		TriGram u = new TriGram();
		BufferedReader br = new BufferedReader(new FileReader(f));
		while((temp = br.readLine()) != null)
		{ 
			String[] tags = temp.split(" ");
			u.words[0] = tags[0];
			u.words[1] = tags[1];
			u.words[2] = tags[2];
			u.count = Integer.parseInt(tags[3]);
			doc.countTriPOS.put(u.words[0]+" "+u.words[1]+" "+u.words[2],u.count);
		}
		br.close();
		return doc;
	}
	public Document readQuadgramPOS(Document doc) throws NumberFormatException, IOException
	{
		// Writing back to a file in another directory
		String filePath = "WordPOSTags/QuadgramPOS/"+doc.title;
		File f = new File(filePath);
		
		String temp;
		QuadGram u = new QuadGram();
		BufferedReader br = new BufferedReader(new FileReader(f));
		while((temp = br.readLine()) != null)
		{ 
			String[] tags = temp.split(" ");
			u.words[0] = tags[0];
			u.words[1] = tags[1];
			u.words[2] = tags[2];
			u.words[3] = tags[3];
			u.count = Integer.parseInt(tags[4]);
			doc.countQuadPOS.put(u.words[0]+" "+u.words[1]+" "+u.words[2]+" "+u.words[3],u.count);
		}
		br.close();
		return doc;
	}
}
