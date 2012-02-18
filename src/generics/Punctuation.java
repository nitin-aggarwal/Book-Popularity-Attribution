package generics;

public class Punctuation {
	
	public long comma;
	public long doubleQuotes;
	public long singleQuotes;
	public long exclamation;
	public long questionM;
	public long ellipsis;
	public long colon;
	public long fullStop;
	public long semiColon;
	
	public static String[] punc = {
			"'", "''", ",", ".", "?", "!", ":", ";", "...", "`", "``"
	};
	
	public static String[] nums = {
		"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
	};
	
	public static String[] extras = {
		"-", "&", "|", "$", "@", "*"
	};
	
	public Punctuation()
	{
		comma = 0;
		doubleQuotes = 0;
		singleQuotes = 0;
		exclamation = 0;
		questionM = 0;
		ellipsis = 0;
		colon = 0;
		fullStop = 0;
		semiColon = 0;
	}
	
	public static boolean checkPuncNums(String key)
	{
		boolean flag = false;
		
		//Exclude punctuation related words
		for(String i: punc)
		{
			if(key.indexOf(i) != -1)
			{
				flag = true;
				break;
			}
		}
		
		//Exclude numeric values
		for(String i: nums)
		{
			if(key.indexOf(i) != -1)
			{
				flag = true;
				break;
			}
		}
		
		//Exclude special characters values
		for(String i: extras)
		{
			if(key.indexOf(i) != -1)
			{
				flag = true;
				break;
			}
		}		
		return flag;
	}
	
	public void print()
	{
		System.out.println("Count ',': "  + comma);
		System.out.println("Count '\"': " + doubleQuotes);
		System.out.println("Count ''': "  + singleQuotes);
		System.out.println("Count '!': "  + exclamation);
		System.out.println("Count '?': "  + questionM);
		System.out.println("Count '...': "+ ellipsis);
		System.out.println("Count ':': "  + colon);
		System.out.println("Count '.': "  + fullStop);
		System.out.println("Count ';': "  + semiColon);
	}
	
}
