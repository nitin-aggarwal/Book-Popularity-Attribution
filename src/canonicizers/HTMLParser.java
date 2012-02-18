package canonicizers;

import generics.Canonicizer;

import mainDoc.MainFunc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class HTMLParser extends Canonicizer{
	
	@Override
	public void canon() {
		// TODO Auto-generated method stub
		
		Document doc = Jsoup.parse(MainFunc.docText.toString());
		Elements e1 = doc.select("p").not("p[class]");
		StringBuilder temp = new StringBuilder(e1.html().replaceAll("</p>", "</p>\n").replaceAll("&quot;", "\"").replaceAll("\\<.*?\\>", ""));
		MainFunc.docText = temp;
	}
}
