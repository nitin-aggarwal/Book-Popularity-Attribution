package generics;

public class QuadGram {
	public String[] words = new String[4];
	public Integer count;

	public QuadGram()
	{
		
	}
	public QuadGram(String a, String b, String c,String d, Integer i)
	{
		words[0] = a;
		words[1] = b;
		words[2] = c;
		words[3] = d;
		count = i;
	}
	public String toString()
	{
		return words[0]+" "+words[1]+" "+words[2]+" "+words[3]+ ": "+ count;
		
	}
}
