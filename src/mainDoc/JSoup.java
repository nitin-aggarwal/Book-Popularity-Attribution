package mainDoc;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.Normalizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JSoup {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
	
		//define homedir
		File directory=new File("Books");
		String [] dir=directory.list();
		int dirlen=0;
		
		while(dirlen<dir.length)
		{
			System.out.println(dir[dirlen]);
			//get path of files in homedir
			String genrepath="Books"+"/"+dir[dirlen];
			String genre=dir[dirlen].replaceAll("\'","");

			int genre_id=DBop.create_uid("dir",genre);
			
			//read genre folders
			File authnamesdir=new File(genrepath);
			String[] authname=authnamesdir.list();
			
			for(int authindex=0;authindex<authname.length;authindex++)
			{
				//authdir name
				System.out.println(authname[authindex]);
				String authname_sntzd=Normalizer.normalize(authname[authindex], Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replaceAll(" ","+");
				//get path of all books by author
				String bookpath=genrepath+"/"+authname[authindex];
				File booknamesdir=new File(bookpath);
				String[] bookname=booknamesdir.list();
				
				for(int booknameindex=0;booknameindex<bookname.length;booknameindex++)
				{
					System.out.println(bookname[booknameindex]);
					String bookname_sntzd=Normalizer.normalize(bookname[booknameindex], Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replaceAll(" ", "+");
					//create URL from given book name and author name
					
					String URL="http://www.goodreads.com/search?query="+bookname_sntzd+"+"+authname_sntzd;
					System.out.println(URL);
					//connect to JSOUP
					Document doc=Jsoup.connect(URL).timeout(10000).ignoreHttpErrors(true).get();
					Elements el=doc.select("table").select("tr").select("td");
					print("\nLinks: (%d)", el.size());
					
					//get topmost result
					if(!el.isEmpty())
					{
						System.out.println(el.get(1).text());
						Element first=el.get(1);
						//store results
						String book=first.getElementsByAttributeValue("itemprop", "name").get(0).text();
						System.out.println("Book:"+book);
						String author=first.getElementsByAttributeValue("itemprop", "author").text();
						System.out.println("Author:"+author);
						String rating=first.getElementsByAttributeValue("class", "minirating").text();
						String[] splitter=rating.split(" ");
						float rank=Float.valueOf(splitter[0]);
						System.out.println("RAnk:"+rank);
						String raters=splitter[4].replaceAll(",","");
						int rankers=Integer.valueOf(raters);
						System.out.println("NO of Ratings:"+rankers);
						book=bookname[booknameindex].replaceAll("\'","");
						
						author=authname[authindex].replaceAll("\'","");
						
						//Rankings ranking=new Rankings(book,author,rank,rankers);
						//insert to DB
						DBop.ranking(genre_id,book, author, rank, rankers);
						
					
					}
				
				}
			}
			dirlen++;
		}	
	}
	private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

}


