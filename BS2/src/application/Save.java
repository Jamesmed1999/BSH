package application;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import javafx.scene.shape.Rectangle;
public class Save implements Serializable {
	
	private boolean[][] playerArray;
	private boolean[][] computerArray;
	private boolean[][] HorM;
	private Rectangle[][] square;
	
	public Save(boolean[][] par,boolean[][] car,boolean[][] hv,Rectangle[][] rec)
	{
		
			playerArray = par;
			computerArray = car;
			HorM = hv;
			square = rec;
		
		
	}
	public boolean[][] getPar()
	{
		return playerArray;
	}
	
	public boolean[][] getCar()
	{
		return computerArray;
	}
	public boolean[][] getHorM()
	{
		return HorM;
	}
	
	public Rectangle[][] getRec()
	{
		return square;
	}
	
	
}