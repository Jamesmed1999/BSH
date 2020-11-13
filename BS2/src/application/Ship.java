package application;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;

public abstract class Ship implements ISinkable {
	
	//Private fields
	private String name;
	private int numSpots;
	private boolean shipPlaced = false;
	GridSpot g4 = new GridSpot();


	public Ship(String n, int ns) 
	{
		name = n;
		numSpots = ns;
	}

	/**The name of the ship
	 * @param none
	 * @return a String that is the name of the ship
	 */
	public String getName()
	{
		return name;
	}
	
	/**The amount of spots on the board that the ship is occupying
	 * @param none
	 * @return an int representing the number of spots
	 */
	public int getNumSpots()
	{
		return numSpots;
	}
	
	public void attacked() {
		numSpots--;
	}
	
	/**Determines whether or not the ship has sunk
	 * @param none
	 * @return a boolean that is true if numSpots <= 0, false otherwise
	 */
	public boolean hasSunk()
	{
		if(numSpots <= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean shipPlaced() {
		return shipPlaced;
	}
	
	/**Function that takes parameters to place the ship on the board if and only if the spot is free
	 * @param x, an int which represents the row
	 * @param y, an int which represents the column
	 * @param g1, GridPane which represents the grid for ship to be placed
	 * @param p, String which represents the orientation of the ship - vertical or horizontal
	 * @param size, an int which represents the size of the ship
	 */
	public void cpuPlaceShip(int x, int y, GridPane g, String p, int size, boolean[][] pARR,boolean[][] cARR,boolean[][] hitormiss, Rectangle[][] rec,Label L, Player user) {
				int it = 0;
			    int tx = x;
			    int ty = y;
			    boolean valid = false;
			    //For loop checks to see if location is taken or not on board
			    for(int r = 0;r <size;r++) {
			    	if(p.equalsIgnoreCase("h")) {
			    		if(cARR[tx][y] == false) {
			    			tx++;
			                valid = true;
			            }
			            else {
			                System.out.println("Location Taken!"); 
			                valid = false;
			                break;
			            }
			             } //end if
			    	else if (p.equalsIgnoreCase("v")) {
			        	if(cARR[x][ty] == false) {
			        		ty++;  
			                valid = true;
			            }
			            else {
			                System.out.println("Location Taken!"); 
			                valid = false;
			                break;
			            } 
			        }
			    	else {
			    		System.out.println("Wrong entry - Please enter h or v");
			    	}
			    } //end for loop
			    
			    //If location is not taken and entire ship can fit, then place ship on board in color BLUE.
			    if(valid == true) {
			    	for(int i = 0; i < size; i++) {
			    		Rectangle r = new Rectangle(35,35);
			           	r.setFill(Color.AQUA);
			           	r.setStroke(Color.BLACK);
			           	//gridSpot(r);
			           	r.setOnMousePressed(new EventHandler<MouseEvent>() {
			           		@Override
							public void handle(MouseEvent event) {
			           			if(cARR[x][y] == true)
			           			{
			           				r.setFill(Color.RED);
			           				g4.CPUattack(rec,pARR,cARR,hitormiss,user);
			           				user.decrementHealth();
			           				System.out.println(user.getHealth());
			           				L.setText("ComputerPlayer health " + user.getHealth());
			           			}
			           			else
			           			{
			           				r.setFill(Color.WHITE);
			           				
			           			}
							}
						}); 
			           	if (p.equalsIgnoreCase("h")) {
			           		g.add(r,x+it,y);
			           		
			           		cARR[x+it][y] = true;
			           		it++;
			           	}
			           	else if (p.equalsIgnoreCase("v")) {
			           		g.add(r,x,y+it);
			           		
			           		cARR[x][y+it] = true;
			           		it++;
			           	}
			           	else {
				    		System.out.println("Wrong entry - Please enter h or v");
				    	}
			        } //end for   
			   } //end if
			    if (valid == true)
			    	shipPlaced = true;
			    else 
			    	shipPlaced = false;
			} //end placeShip

	
	void humanPlaceShip(int x, int y, GridPane g, String p, int size, boolean[][] ARR,Rectangle[][] mad)
	{
		int it = 0;
	    int tx = x;
	    int ty = y;
	    boolean valid = false;
	    //For loop checks to see if location is taken or not on board
	    for(int r = 0;r <size;r++) {
	    	if(p.equalsIgnoreCase("h")) {
	    		if(ARR[tx][y] == false) {
	    			tx++;
	                valid = true;
	            }
	            else {
	                System.out.println("Location Taken!"); 
	                valid = false;
	                break;
	            }
	             } //end if
	    	else if (p.equalsIgnoreCase("v")) {
	        	if(ARR[x][ty] == false) {
	        		ty++;  
	                valid = true;
	            }
	            else {
	                System.out.println("Location Taken!"); 
	                valid = false;
	                break;
	            } 
	        }
	    	else {
	    		System.out.println("Wrong entry - Please enter h or v");
	    	}
	    } //end for loop
	    
	    //If location is not taken and entire ship can fit, then place ship on board in color BLUE.
	    if(valid == true) {
	    	for(int i = 0; i < size; i++) {
	    		Rectangle r = new Rectangle(35,35);
	           	r.setFill(Color.BLUE);
	           	//gridSpot(r);
	           	r.setOnMousePressed(new EventHandler<MouseEvent>() {
	           		@Override
					public void handle(MouseEvent event) {
	           			//SHOULD CALL A FUNCTION HERE GRIDSPOT WHICH CHANGES COLOR OF RECTANGLE BASED ON HIT OR MISS
		                //r.setFill(Color.RED);
	           			//gridSpot(r);
					}
				}); 
	           	if (p.equalsIgnoreCase("h")) {
	           		g.add(r,x+it,y);
	           		mad[x+it][y] = r;
	           		ARR[x+it][y] = true;
	           		it++;
	           	}
	           	else if (p.equalsIgnoreCase("v")) {
	           		g.add(r,x,y+it);
	           		mad[x][y+it] = r;
	           		ARR[x][y+it] = true;
	           		it++;
	           	}
	           	else {
		    		System.out.println("Wrong entry - Please enter h or v");
		    	}
	        } //end for   
	   } //end if
	    if (valid == true)
	    	shipPlaced = true;
	    else 
	    	shipPlaced = false;
	} //end placeShip
	
	abstract public void sink();
}
