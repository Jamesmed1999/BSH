package application;

import java.io.ObjectOutputStream;
import javafx.application.Application;
import java.io.*; 
import java.lang.*; 
import java.util.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

//old
public class Main extends Application {
	
	boolean [][] playerARR = new boolean[10][10];
	boolean [][] CPUARR = new boolean[10][10];
	boolean [][] hitormiss = new boolean[10][10];
	Rectangle [][] recARR = new Rectangle[10][10];
	int x3 = 5;
	boolean attack = false; //attack phase false
	boolean turn = true;
	
	
	//Fields
	private TextField rowTextField;
	private TextField columnTextField;
	private TextField orientationTextField;
	private Button set;
	private RadioButton battleshipSelection;
	private RadioButton submarineSelection;
	private RadioButton cruiserSelection;
	private RadioButton carrierSelection;
	private RadioButton destroyerSelection;
	private GridPane grid1;
	private GridPane grid2;
	private Button load = new Button("LOAD");
	private Button save = new Button("Save");
	private TextField saveTXT = new TextField();
	private TextField loadTXT = new TextField();

	
	//Ships for players
	Ship carrier1 = new Carrier(5);
	Ship battleship1 = new Battleship(4);
	Ship cruiser1 = new Cruiser(3);
	Ship submarine1 = new Submarine(3);
	Ship destroyer1 = new Destroyer(2);
	
	Ship carrier2 = new Carrier(5);
	Ship battleship2 = new Battleship(4);
	Ship cruiser2 = new Cruiser(3);
	Ship submarine2 = new Submarine(3);
	Ship destroyer2 = new Destroyer(2);
	
	//Arrays for players
	Ship hpShips[] = {carrier1, battleship1, cruiser1, submarine1, destroyer1};
	Ship cpShips[] = {carrier2, battleship2, cruiser2, submarine2, destroyer2};
	
	//HumanPlayer and ComputerPlayer initialization
	Player user1 = new HumanPlayer(hpShips);
	Player user2 = new ComputerPlayer(cpShips);
	
	String orientation;
	int orientationINT;
	
	//GLOBAL LABEL THAT WILL CHANGE DEPENDING ON GAME STATE
	Label gameinfo = new Label("");
	
	//gridspot object for atack phase yes
	GridSpot g4 = new GridSpot();
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
        try {
		//Initialize grids for both players
			grid1 = new GridPane();
			grid2 = new GridPane();
			
		//Create Labels for the Boards
			Label grid1Label = new Label("Human Player");
			Label grid2Label = new Label("Computer Player");
			grid1Label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20)); 
			grid2Label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
			gameinfo.setFont(Font.font("verdana", FontPosture.REGULAR, 18));
		//Initialize RadioButtons for ships
			battleshipSelection = new RadioButton("Battleship");
			submarineSelection = new RadioButton("Submarine");
			cruiserSelection = new RadioButton("Cruiser");
			carrierSelection = new RadioButton("Carrier");
			destroyerSelection = new RadioButton("Destroyer");
		//ToggleGroup so that more than one RadioButton cannot be selected at the same time
			ToggleGroup shipChoices = new ToggleGroup();
			battleshipSelection.setToggleGroup(shipChoices);
			submarineSelection.setToggleGroup(shipChoices);
			cruiserSelection.setToggleGroup(shipChoices);
			carrierSelection.setToggleGroup(shipChoices);
			destroyerSelection.setToggleGroup(shipChoices);
		//Select one of the RadioButton controls
			battleshipSelection.setSelected(true);
			
		//Create TextFields and Labels for ship placement
			Label rowLabel = new Label("Row");
			Label columnLabel = new Label("Column");
			Label orientationLabel = new Label("H or V");
			rowTextField = new TextField();
			rowTextField.setPrefWidth(35);
			columnTextField = new TextField();
			columnTextField.setPrefWidth(35);
			orientationTextField = new TextField();
			orientationTextField.setPrefWidth(35);

		System.out.println(user1.getHealth() + " " + user2.getHealth());
		//Create button to set the TextField ship placement information
			set = new Button("SET");
			set.setPrefWidth(60);
		//Register the event handler
			set.setOnAction((new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent event) {
					Integer column = Integer.valueOf(columnTextField.getText());
			        Integer row = Integer.valueOf(rowTextField.getText());
			        String orientation = orientationTextField.getText();
			        if(x3 != 0) 
			        {
					if(carrierSelection.isSelected()) {
						user1.getShip(0).humanPlaceShip(column, row, grid1, orientation, user1.getShip(0).getNumSpots(), playerARR,recARR); 
					}
					 if(battleshipSelection.isSelected()) {
						user1.getShip(1).humanPlaceShip(column, row, grid1, orientation, user1.getShip(1).getNumSpots(), playerARR,recARR);
					}
					 if(cruiserSelection.isSelected()) {
						user1.getShip(2).humanPlaceShip(column, row, grid1, orientation, user1.getShip(2).getNumSpots(), playerARR,recARR);
					}
					 if(submarineSelection.isSelected()) {
						user1.getShip(3).humanPlaceShip(column, row, grid1, orientation, user1.getShip(3).getNumSpots(), playerARR,recARR);
					}	
					 if(destroyerSelection.isSelected()) {
						user1.getShip(4).humanPlaceShip(column, row, grid1, orientation, user1.getShip(4).getNumSpots(), playerARR,recARR);
					}
			        
					 x3--;
					 gameinfo.setText("Remaining ships to place: " + x3);
			        }
			        
			        else
			        	gameinfo.setText("All ships placed");
					//gameinfo.setText("HumanPlayer health:" + user1.getHealth() + "ComputerPlayer health:" + user2.getHealth());
			        
				
			}})); 
			save.setOnAction((new EventHandler<ActionEvent>()
			{
				
				@Override
				public void handle(ActionEvent eve)  { 
					try {
						
					
					String outputLoc = "face";
					Save mydata = new Save(playerARR, CPUARR, hitormiss, recARR);
					ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(outputLoc)); //create the File
					output.writeObject(mydata);
					output.close();
					}
					catch (Exception e) 
			        { 
			            saveTXT.setText("An error has occurred when saving to the file!"); 
			        }     
				}
			
			}));
			
			load.setOnAction((new EventHandler<ActionEvent>()
			{

				@Override
				public void handle(ActionEvent eve)  { 
					try
					{
					String outputLoc = save.getText();
					Save mydata = new Save(playerARR, CPUARR, hitormiss, recARR);
					ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(outputLoc)); //create the File
					output.writeObject(mydata);
					output.close();
					}
					catch(Exception e2)
					{
						loadTXT.setText("An error has occurred when saving to the file!"); 
					}
				}
			
			}));
			
			
		//Create 10 rows and 10 columns and add rectangles to each cell for grid1
			for(int i=0; i<10; i++){
				for(int j=0; j<10; j++){
					Rectangle rec = new Rectangle(35,35);
					rec.setFill(Color.AQUA);
					rec.setStroke(Color.GREY);
					grid1.add(rec, i, j);
					recARR[i][j] = rec;
				}
			}
			
		//Create 10 rows and 10 columns and add rectangles to each cell for grid2
			for(int i=0; i<10; i++) {
				for(int j=0; j<10; j++) {
					Rectangle rec = new Rectangle(35,35);
					rec.setFill(Color.AQUA);
					rec.setStroke(Color.GREY);
					grid2.add(rec, i, j);
					
				//Mouse click handler - changes color of clicked cells
					rec.setOnMousePressed(new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {	
							int x = (int) ((Math.random() * 5));
							int y = (int) ((Math.random() * 5));	
							if(playerARR[x][y] == true)
		           			{
		           				g4.CPUattack(recARR,playerARR,CPUARR,hitormiss,user1);
		           				gameinfo.setText("HumanPlayer health: " + user1.getHealth());
		           				user1.decrementHealth();
		           				System.out.println(user1.getHealth());
		           			}
		           			else
		           			{
		           				g4.CPUattack(recARR,playerARR,CPUARR,hitormiss,user1);
		           				rec.setFill(Color.WHITE);
		           				//gameinfo.setText("Miss!!");
		           			}		           			
		           				
						}
					});
				}
			}

		
		//Set grid's grid lines and position
			grid1.setGridLinesVisible(true);
			grid2.setGridLinesVisible(true);
			grid1.setAlignment(Pos.CENTER);
			grid2.setAlignment(Pos.CENTER);
		
			
			
			//Create a HBox to add the RadioButtons
			HBox hbox1 = new HBox(5, battleshipSelection, submarineSelection, cruiserSelection, carrierSelection, destroyerSelection);
			hbox1.setAlignment(Pos.CENTER);
		
		//Create a HBox to add the TextFields and Labels
			HBox hbox2 = new HBox(8, rowLabel, rowTextField, columnLabel, columnTextField, orientationLabel, orientationTextField, set);
			hbox2.setAlignment(Pos.CENTER);
			
		//Create a HBox to add the gameinfo Label
			HBox hbox3 = new HBox(gameinfo);
			HBox HBox4 = new HBox(saveTXT,save,loadTXT,load);

			
		//Create VBox, add grids, labels, hbox's with 3 pixels spacing
			VBox vbox = new VBox(2, grid1Label, hbox1, hbox2, grid1,gameinfo, grid2Label, grid2, HBox4);
		
		//Position the VBox
			vbox.setAlignment(Pos.CENTER);

		//Set the title
			primaryStage.setTitle("BATTLESHIP");
			
		//Create a Scene with the VBox as its root node. The Scene is 400 pixels wide by 800 pixels high
			Scene scene = new Scene(vbox, 450, 900);
		
		//Add the scene to the stage
			primaryStage.setScene(scene);
		
		//Show the window
			primaryStage.show();
			
		//CPU placing ships
			for (int i =0; i<5; i++) {
				while(user2.getShip(i).shipPlaced() != true) {
					int x = (int) ((Math.random() * 5));
					int y = (int) ((Math.random() * 5));
				//determines whether horizontal or vertical
					switch (orientationINT = (int) ((Math.random() * 2) + 1)) {
					case 1:
						orientation="h";
						break;
					case 2:
						orientation="v";
						break;
					}	
					user2.getShip(i).cpuPlaceShip(x, y, grid2, orientation, user2.getShip(i).getNumSpots(), playerARR,CPUARR,hitormiss,recARR,gameinfo,user2);
				}				
			}
			
		} 
        catch(Exception e) {
			e.printStackTrace();
		} 
        
	}
}
	