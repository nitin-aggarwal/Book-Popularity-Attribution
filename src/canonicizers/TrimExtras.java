package canonicizers;

import mainDoc.MainFunc;
import generics.Canonicizer;

public class TrimExtras extends Canonicizer{

	@Override
	public void canon() {
		// TODO Auto-generated method stub
		MainFunc.docText.trimToSize();
	}

}
