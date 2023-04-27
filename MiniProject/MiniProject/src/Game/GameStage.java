package Game;


import javafx.event.EventHandler;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;



public class GameStage {
	public static final int WINDOW_HEIGHT = 2400;
	public static final int WINDOW_WIDTH = 2400;
	private Scene scene1;
	private Scene scene2;
	private Scene scene3;
	private Scene scene4;
	private Stage stage;
	private Group root;
	private Group root3;
	private Group root4;
	private Canvas canvas;
	private GraphicsContext gc;
	ImageView view;
	private GameTimer gametimer;
	private GraphicsContext gc2;
	private GraphicsContext gc3;
	private Group root2;
	boolean clicked = false;

	//the class constructor
	public GameStage() {


		this.root2 = new Group();
		this.root = new Group();
		this.root3 = new Group();
		this.root4 = new Group();
		this.canvas = new Canvas(800 , 800);
		Canvas title_canvas = new Canvas(GameStage.WINDOW_WIDTH , GameStage.WINDOW_HEIGHT);
		Canvas about_canvas = new Canvas(GameStage.WINDOW_WIDTH , GameStage.WINDOW_HEIGHT);
		Canvas instructions_canvas = new Canvas(GameStage.WINDOW_WIDTH , GameStage.WINDOW_HEIGHT);
		GraphicsContext gc3 = about_canvas.getGraphicsContext2D();
		GraphicsContext gc4 = instructions_canvas.getGraphicsContext2D();
		Image bg2 = new Image("images/bg.gif",800, 800,false,false);
		Image border = new Image("images/border.png",2400, 2400,false,false);
		ImageView view2 = new ImageView();
		ImageView view3 = new ImageView();

		ImageView viewInstructions = new ImageView();
		viewInstructions.setImage(bg2);
		this.root3.getChildren().add(viewInstructions);

		ImageView viewAbout = new ImageView();
		viewAbout.setImage(bg2);
		this.root4.getChildren().add(viewAbout);


		view3.setImage(border);
		view2.setImage(bg2);
		this.gc2 = title_canvas.getGraphicsContext2D();


		Font theFont = Font.font("Times New Roman", FontWeight.BOLD,20);
		Font titleFont = Font.font("Times New Roman", FontWeight.BOLD,40);
		//About
		gc3.setFill(Color.BLACK);
		gc3.setFont(theFont);
		gc3.fillText("ABOUT THE AUTHORS" + "", 280, 210);
		gc3.fillText("The creators - Jaymart Latigay and James Albert Hans Duldulao, are both sophomore \n       students taking up a Bachelor of Science in Computer Science. They created \n             'Birdie vs Bees' for CMSC 22 as it is their final output for the course. ", 30, 270 );
		//instructions
		gc4.setFill(Color.BLACK);
		gc4.setFont(theFont);
		gc4.fillText("INSTRUCTIONS", 290, 140);
		gc4.fillText("You play as Birdie, if you have to ask, YES, you're the bird! \n\nTo win the game, get Birdie to have a size greater than 1000, Basic, Right? \n\n In order to eat the bees you must be larger in terms of size than them and \n be close enough to devour them! MWAHAHAHA! \n\n To get larger in size, eat food! In this world eat wheats, cuz you're a healthy Birdie. \n\n Ohhh! Also, you can get power ups by eating grapes (makes you UNEATABLE, \n if that's even a word) and flowers (doubles your normal speed). \n         - Use them efficiently, as their effects only lasts for 5 seconds\n           and respawning them back takes 10 seconds. \n\n One last piece of advice: DON'T GET EATEN! ", 50, 200);

		//title
		gc2.setFill(Color.BLACK);
		gc2.setFont(titleFont);
		gc2.fillText("Birdie vs Bees", 270, 270);

		this.gc = this.canvas.getGraphicsContext2D();
		Button startbtn = new Button("START GAME");
		Button aboutbtn = new Button("ABOUT");
		Button instructionsbtn = new Button("HOW TO PLAY");
		Button backbtn = new Button("Back to menu");
		Button backbtn2 = new Button("Back to menu");

		startbtn.setLayoutX(330);
		startbtn.setLayoutY(300);
		instructionsbtn.setLayoutX(325);
		instructionsbtn.setLayoutY(350);
		aboutbtn.setLayoutX(350);
		aboutbtn.setLayoutY(400);

		backbtn.setLayoutX(330);
		backbtn.setLayoutY(600);
		backbtn2.setLayoutX(330);
		backbtn2.setLayoutY(600);
		this.root.getChildren().add(view3);


		this.scene1 = new Scene(root, 800, 800, Color.WHITE);
		this.scene2 = new Scene(root2, 800, 800, Color.WHITE);
		this.scene3 = new Scene(root3, 800, 800, Color.WHITE);
		this.scene4 = new Scene(root4, 800, 800, Color.WHITE);
		this.root2.getChildren().add(view2);
		this.root2.getChildren().add(title_canvas);
		this.root2.getChildren().add(startbtn);
		this.root2.getChildren().add(aboutbtn);
		this.root2.getChildren().add(instructionsbtn);
		this.root3.getChildren().add(instructions_canvas);
		this.root3.getChildren().add(backbtn);
		this.root4.getChildren().add(about_canvas);
		this.root4.getChildren().add(backbtn2);

		addEventHandler(startbtn);
		addEventHandler2(instructionsbtn);
		addEventHandler3(aboutbtn);
		addEventHandler4(backbtn);
		addEventHandler4(backbtn2);





	}

	private void addEventHandler(Button btn) {  //event hander for when user clicks the exit button
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent e) {
				clicked = true;
				stage.setScene(scene1);
				System.out.println(clicked);
				if (clicked){
					gametimer = new GameTimer(gc,scene1);
					gametimer.start();
				}
			}
		});

	}

	private void addEventHandler2(Button btn) {  //event hander for when user clicks the exit button

		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent e) {
				clicked = true;
				stage.setScene(scene3);
				System.out.println(clicked);
				if (clicked){
					gametimer = new GameTimer(gc,scene1);

				}
			}
		});

	}

	private void addEventHandler3(Button btn) {  //event hander for when user clicks the exit button
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent e) {
				clicked = true;
				stage.setScene(scene4);
				System.out.println(clicked);
				if (clicked){
					gametimer = new GameTimer(gc,scene1);

				}
			}
		});

	}


	private void addEventHandler4(Button btn) {  //event hander for when user clicks the exit button
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent e) {
				clicked = true;
				stage.setScene(scene2);
				System.out.println(clicked);
				if (clicked){
					gametimer = new GameTimer(gc,scene1);

				}
			}
		});

	}

	//method to add the stage elements
	public void setStage(Stage stage) {

			this.stage = stage;

			this.root.getChildren().add(this.canvas);

			//set stage elements here

			this.stage.setTitle("Birdie vs Bees");
			this.stage.setScene(this.scene2);

			//invoke the start method of the animation timer


			this.stage.show();
	}

}



