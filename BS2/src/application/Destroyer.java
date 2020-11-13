package application;

public class Destroyer extends Ship {

	public Destroyer(int ns) 
	{
		super("Destroyer", ns);
	}

	@Override
	public void sink() 
	{
		System.out.println("THE DESTROYER HAS SUNK");
	}

}
