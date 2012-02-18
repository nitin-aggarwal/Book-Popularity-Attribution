package analyzers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadDistinct {
	
	public void readUnigramPOS() throws NumberFormatException, IOException
	{
		// Writing back to a file in another directory
		String filePath = "WordPOSTags/UnigramPOS.txt";
		File f = new File(filePath);
		
		String temp;
		BufferedReader br = new BufferedReader(new FileReader(f));
		while((temp = br.readLine()) != null)
		{ 
			ComputeCounts.distinctUniPOS.add(temp);
		}
		br.close();
	}
	
	public void readBigramPOS() throws NumberFormatException, IOException
	{
		// Writing back to a file in another directory
		String filePath = "WordPOSTags/BigramPOS.txt";
		File f = new File(filePath);
		
		String temp;
		BufferedReader br = new BufferedReader(new FileReader(f));
		while((temp = br.readLine()) != null)
		{ 
			ComputeCounts.distinctBiPOS.add(temp);
		}
		br.close();
	}
	
	public void readTrigramPOS() throws NumberFormatException, IOException
	{
		// Writing back to a file in another directory
		String filePath = "WordPOSTags/TrigramPOS.txt";
		File f = new File(filePath);
		
		String temp;
		BufferedReader br = new BufferedReader(new FileReader(f));
		while((temp = br.readLine()) != null)
		{ 
			ComputeCounts.distinctTriPOS.add(temp);
		}
		br.close();
	}
	
	public void readQuadgramPOS() throws NumberFormatException, IOException
	{
		// Writing back to a file in another directory
		String filePath = "WordPOSTags/QuadgramPOS.txt";
		File f = new File(filePath);
		
		String temp;
		BufferedReader br = new BufferedReader(new FileReader(f));
		while((temp = br.readLine()) != null)
		{ 
			ComputeCounts.distinctQuadPOS.add(temp);
		}
		br.close();
	}
}
