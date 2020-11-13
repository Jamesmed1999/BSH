package application;


public abstract class Player {
	private Ship[] playerShips = new Ship[5];
	private int totalHealth=0;
	
	/**
	 * Parametrized constructor
	 * @param shipArray, an array of 5 Ships that each player has
	 */
	public Player(Ship[] shipArray) {
		for (int i =0; i < shipArray.length; i++) {
			playerShips[i] = shipArray[i];
			totalHealth += shipArray[i].getNumSpots();
		}
	}
	
	public Ship getShip(int index) {
		return playerShips[index];
	}

	public int getHealth() {
		return totalHealth;
	}
	
	public int decrementHealth() {
		return totalHealth--;
	}
	
	
	
}
