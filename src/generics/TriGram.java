package generics;

public class TriGram {
	public String[] words = new String[3];
	public Integer count;

	public TriGram()
	{
		
	}
	public TriGram(String a, String b, String c, Integer i)
	{
		words[0] = a;
		words[1] = b;
		words[2] = c;
		count = i;
	}
	public String toString()
	{
		return words[0]+" "+words[1]+" "+words[2]+ ": "+ count;
		
	}
}
