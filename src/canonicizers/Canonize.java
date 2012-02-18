package canonicizers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import mainDoc.MainFunc;

public class Canonize {
	public void canonizersFun() throws IOException
	{
		StringBuilder str = new StringBuilder();
		System.out.println("File Processing");
						
		// Writing back to a file in another directory
		String filePath = "Documents/"+MainFunc.filename;
		System.out.println("DOC File: "+filePath);
		File f = new File(filePath);
		if(!f.exists())
		{
			//Call different canonicizers
			new HTMLParser().canon();
			new TrimExtras().canon();
			
			str = MainFunc.docText;
			
			f.createNewFile();
			System.out.println("Writing Word Document");
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(str.toString());
			bw.close();
		}
		else
		{
			String temp ="";
			BufferedReader br = new BufferedReader(new FileReader(f));
			while((temp = br.readLine()) != null)
				str.append(temp+"\n");
			br.close();
			
			MainFunc.docText = str;
		}
		System.out.println("Word Document Written");
	}
}
