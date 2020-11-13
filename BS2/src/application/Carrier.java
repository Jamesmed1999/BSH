package application;

public class Carrier extends Ship {

	public Carrier(int ns) 
	{
		super("Carrier", ns);
	}

	@Override
	public void sink() 
	{
		System.out.println("THE CARRIER HAS SUNK");
	}

}