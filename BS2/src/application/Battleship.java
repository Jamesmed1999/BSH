package application;

public class Battleship extends Ship {

	public Battleship(int ns) 
	{
		super("Battleship", ns);
	}

	@Override
	public void sink() 
	{
		System.out.println("THE BATTLESHIP HAS SUNK");
	}

}