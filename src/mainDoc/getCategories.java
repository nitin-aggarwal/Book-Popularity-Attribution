package mainDoc;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class getCategories {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		int files = 101;
		int cat =0;
		String mainURL="http://www.gutenberg.org/ebooks/search.html/?default_prefix=bookshelves&sort_order=downloads";
		for(int page = 1;page<2;page++)
		{
			String URL = mainURL;
			files += 25;
			if(page > 0)
				URL += "&start_index="+files;
			System.out.println(URL);
			Document doc = null;
			doc=Jsoup.connect(URL).timeout(10000).get();
			
			Elements el=doc.select("table").select("tr");
			int links = el.size();
			System.out.println("\nPage: "+page+ "Links: "+links);
			
			if(el.size() == 3)
			{
				System.out.println("No results");
				break;
			}
			
			//get topmost result
			for(int k=0;k<el.size()-1;k++)
			{
				System.out.println("Hi");
				Document temp = Jsoup.parse(el.get(k).html());
				//System.out.println(el.get(k).html());
				
				
				if(el.get(k).getElementsByClass("subtitle").text().compareTo("") == 0)
					continue;
				cat++;
				String e;
				if(cat>40)
					continue;
				e = temp.select("a").first().attr("href");
				
				String url = "http://www.gutenberg.org"+e;
				String title = el.get(k).getElementsByClass("title").text();
				System.out.println("Category: "+cat);
				
				System.out.println("URL: "+url);
				System.out.println("Title: "+title);
				
				GetDocs.getDoc(title, url);
				
				
			}
		}
	}
}