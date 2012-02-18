package generics;

public class Rankings {
	public String [] bukauth=new String[2];
	public float rank;
	public String raters;
	public  Rankings(String a,String b,float c, String d ){
		bukauth[0]=a;
		bukauth[1]=b;
		rank=c;
		raters=d;		
	}
	public String toString()
	{
		return bukauth[0]+" "+bukauth[1]+" "+rank+ ": "+ raters;
		
	}
}
