package generics;

public class UniGram {
	public String words;
	public Integer count;

	public UniGram()
	{
		words = "";
		count = 0;
	}
	public UniGram(String u, Integer i)
	{
		words = u;
		count = i;
	}
	public String toString()
	{
		return words+ ": "+ count;
		
	}
}
