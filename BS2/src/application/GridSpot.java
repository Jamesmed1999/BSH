package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

	

public class GridSpot {
	

	
	public void CPUattack (Rectangle[][] rec,boolean[][] pARR,boolean[][] cARR,boolean[][] hitormiss, Player user) 
	{
		int x = (int) ((Math.random() * 10));
		int y = (int) ((Math.random() * 10));
		if(hitormiss[x][y] == true)
		{
			System.out.println("Already gussed that spot buddy");
			CPUattack(rec,pARR,cARR,hitormiss,user); //call again if they guess the same spot
		}
		else
		{
			if(pARR[x][y] == true)
			{
				rec[x][y].setFill(Color.RED); //sets player ship red if it's hit
				hitormiss[x][y] = true; //i hit
				//System.out.println("HIT");
				user.decrementHealth(); //decrements health if get a HIT
				System.out.println(user.getHealth());
					
			}
			else if(pARR[x][y] == false)
			{
				rec[x][y].setFill(Color.WHITE);
				hitormiss[x][y] = true;//i miss
				System.out.println("MISS");
				
			}
		}
	} //end gridSpot
	
	
}

