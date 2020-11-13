package application;

public class Cruiser extends Ship {

	public Cruiser(int ns) 
	{
		super("Cruiser", ns);
	}

	@Override
	public void sink() 
	{
		System.out.println("THE CRUISER HAS SUNK");
	}

}
