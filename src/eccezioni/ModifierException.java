package eccezioni;

public class ModifierException extends Exception{
	private String msg;
	
	public ModifierException(){
		msg = "Jobstream o Workstation mancante";
	}
	
	@Override
	public String toString(){
		return msg;
	}
}
