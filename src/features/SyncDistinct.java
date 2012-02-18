package features;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

public class SyncDistinct {
	public void syncUnigramWord(TreeSet<String> uniW) throws IOException
	{
		TreeSet<String> temp = new TreeSet<String>();
		File f = new File("WordPOSTags/UnigramWord.txt");
		if(!f.exists())
			f.createNewFile();
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		String str="";
		while((str= br.readLine()) != null)
		{
			temp.add(str);
		}
		br.close();
		
		Iterator<String> itr = uniW.iterator();
		while(itr.hasNext())
		{
			temp.add(itr.next());
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		itr = temp.iterator();
		while(itr.hasNext())
		{
			bw.write(itr.next());
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}
	public void syncUnigramPOS(TreeSet<String> uniP) throws IOException
	{
		TreeSet<String> temp = new TreeSet<String>();
		File f = new File("WordPOSTags/UnigramPOS.txt");
		if(!f.exists())
			f.createNewFile();
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		String str="";
		while((str= br.readLine()) != null)
		{
			temp.add(str);
		}
		br.close();
		
		Iterator<String> itr = uniP.iterator();
		while(itr.hasNext())
		{
			temp.add(itr.next());
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		itr = temp.iterator();
		while(itr.hasNext())
		{
			bw.write(itr.next());
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}
	public void syncBigramPOS(TreeSet<String> biP) throws IOException
	{
		TreeSet<String> temp = new TreeSet<String>();
		File f = new File("WordPOSTags/BigramPOS.txt");
		if(!f.exists())
			f.createNewFile();
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		String str="";
		while((str= br.readLine()) != null)
		{
			temp.add(str);
		}
		br.close();
		
		Iterator<String> itr = biP.iterator();
		while(itr.hasNext())
		{
			temp.add(itr.next());
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		itr = temp.iterator();
		while(itr.hasNext())
		{
			bw.write(itr.next());
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}
	public void syncTrigramPOS(TreeSet<String> triP) throws IOException
	{
		TreeSet<String> temp = new TreeSet<String>();
		File f = new File("WordPOSTags/TrigramPOS.txt");
		if(!f.exists())
			f.createNewFile();
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		String str="";
		while((str= br.readLine()) != null)
		{
			temp.add(str);
		}
		br.close();
		
		Iterator<String> itr = triP.iterator();
		while(itr.hasNext())
		{
			temp.add(itr.next());
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		itr = temp.iterator();
		while(itr.hasNext())
		{
			bw.write(itr.next());
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}
	public void syncQuadgramPOS(TreeSet<String> quadP) throws IOException
	{
		TreeSet<String> temp = new TreeSet<String>();
		File f = new File("WordPOSTags/QuadgramPOS.txt");
		if(!f.exists())
			f.createNewFile();
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		String str="";
		while((str= br.readLine()) != null)
		{
			temp.add(str);
		}
		br.close();
		
		Iterator<String> itr = quadP.iterator();
		while(itr.hasNext())
		{
			temp.add(itr.next());
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		itr = temp.iterator();
		while(itr.hasNext())
		{
			bw.write(itr.next());
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}
}
