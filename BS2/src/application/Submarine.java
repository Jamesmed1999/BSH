package application;

public class Submarine extends Ship {

	public Submarine(int ns) 
	{
		super("Submarine", ns);
	}

	@Override
	public void sink() 
	{
		System.out.println("THE SUBMARINE HAS SUNK");
	}

}
