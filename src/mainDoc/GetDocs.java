package mainDoc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GetDocs {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void getDoc(String classTitle,String classUrl) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		int files = -24;
		int count = 1;
		String mainURL=classUrl;
		for(int page = 0;;page++)
		{
			String URL = mainURL;
			files += 25;
			//System.out.println("files: "+files);
			if(page > 0)
				URL += "&start_index="+files;
			System.out.println(URL);
			Document doc = null;
			try{
			doc=Jsoup.connect(URL).timeout(10000).get();
			}
			catch(IOException ij)
			{
				System.out.println("Exception: "+ij);
				Thread.sleep(60*1000);
				page--;
				files -= 25;
				continue;
			}
			Elements el=doc.select("table").select("tr");
			int links = el.size();
			System.out.println("\nPage: "+page+ "Links: "+links);
			
			if(el.size() == 3)
			{
				System.out.println("No results");
				break;
			}
			
			if(classTitle.compareTo("Pirates, Buccaneers, Corsairs, etc.")==0 && page < 3)
			{
				continue;
			}
			//get topmost result
			for(int k=0;k<el.size()-1;k++)
			{
				Document temp = Jsoup.parse(el.get(k).html());
				
				String e;
				if(el.get(k).getElementsByClass("extra").text().compareTo("") == 0)
					continue;
				e = temp.select("a").first().attr("href");
				
				int index = e.lastIndexOf("/");
				String filenum = e.substring(index + 1);
				String url = "http://www.gutenberg.org/files/"+filenum+"/"+filenum+"-h/"+filenum+"-h.htm";
				String title = el.get(k).getElementsByClass("title").text();
				title = title.replace("?", "").replaceAll("\"", "").replace(":", "").replace("/", "");
				String author = el.get(k).getElementsByClass("subtitle").text();
				if(author.length() > 150)
					author = author.substring(0, 150);
				if(title.length() > 150)
					title = title.substring(0, 150);
				Document file=null;
				try
				{
					file=Jsoup.connect(url).timeout(10000).get();
				}
				catch(IOException exp)
				{
					url = "http://www.gutenberg.org/cache/epub/"+filenum+"/pg"+filenum+".html";
					continue;
				}
				
				System.out.println("count: " +count++ +" "+k);
				System.out.println("URL: "+url);
				System.out.println("Title: "+title);
				System.out.println("Author: "+author);
				
				createDoc("Books/"+classTitle,url,title,author,file);
				
			}
		}
	}
	private static void createDoc(String query, String url, String title, String author, Document f) throws IOException
	{
		// Main Folder
		File dir = new File(query);
		if(dir.mkdir())
		{
			System.out.println("Directory Created");
		}
		else
			System.out.println("No creation");
		
		// Sub-Folder
		File subdir = new File(query+"/"+author);
		if(subdir.mkdir())
		{
			System.out.println("Subdirectory Created");
		}
		else
			System.out.println("Subdir No creation");
		
		// Text-File
		File file = new File(query+"/"+author+"/"+title);
		if(!file.exists())
		{
			file.createNewFile();
			System.out.println("File Created");
			BufferedWriter br = new BufferedWriter(new FileWriter(file));
			br.write(f.html());
			br.flush();
			br.close();
			
		}
		else
			System.out.println("Already exists");
	}

}

