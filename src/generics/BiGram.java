package generics;

public class BiGram {
	public String[] words = new String[2];
	public Integer count;

	public BiGram()
	{
		
	}
	public BiGram(String a, String b, Integer i)
	{
		words[0] = a;
		words[1] = b;
		count = i;
	}
	public String toString()
	{
		return words[0]+" "+words[1]+ ": "+ count;
		
	}
}
