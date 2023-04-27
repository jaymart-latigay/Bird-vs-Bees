package Game;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Scale;

/*
 * The GameTimer is a subclass of the AnimationTimer class. It must override the handle method.
 */

public class GameTimer extends AnimationTimer{

	private boolean right;
	private boolean left;
	private boolean up;
	private boolean down;
	private GraphicsContext gc;
	private Scene theScene;
	private PlayerBird bird;
	private ArrayList<Food> foods;
	public static final int MAX_NUM_FOODS = 50;
	private ArrayList<EnemyBee> bees;
	private static final int MOVEMENT = 3;
	private static final int CHANGER = 50;
	private double locationX = 0;
	private double locationY = 0;
	private static double edgeX = -1900;
	private static double edgeXNegative = 320;
	private static double edgeY = 300;
	private static double edgeYNegative = -1800;
	private Image bg = new Image("images/bg.gif",GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT,false,false);
	private Scale scale = new Scale(0.5,0.5);
	private Scale scale2 = new Scale(0.85,0.85);
	private Scale scale3 = new Scale(0.4,0.4);
	private Scale scale4 = new Scale(1,1);
	private int Food_count = 50;
	private int speedUp_count = 10;
	private int immunity_count = 10;
	private boolean scaledUp = false;
	private boolean scaledUp2 = false;
	private boolean scaledUp3 = false;
	private boolean collidedY = true;
	private boolean collidedX = true;
	private boolean faceChanged = false;
	private PauseSpawn pause;
	private GameDurationTimer timer;


	GameTimer(GraphicsContext gc, Scene theScene){
		this.gc = gc;
		this.gc.drawImage( bg, this.locationX, this.locationY );
		this.theScene = theScene;
		this.bird = new PlayerBird("Birdie",360,360);
		this.foods = new ArrayList<Food>();
		this.bees = new ArrayList<EnemyBee>();
		//call the spawnBees method
		this.spawnFoods();
		this.spawnBees();
		this.pause = new PauseSpawn(this.foods);
		this.pause.start();
		this.timer = new GameDurationTimer(this.bird);
		this.timer.start();
		//call method to handle mouse click event
		this.handleKeyPressEvent();


	}


	@Override
	public void handle(long currentNanoTime) {

		double width = bird.width;

		StayBg();
		this.bird.render(this.gc);
		this.bird.changeBirdImage();


		for(Food food : this.foods){
			if(!(food instanceof SuperSpeed) && !(food instanceof Immunity) && Food_count<50){
				spawnOneFood();
				Food_count+=1;
			}
		}

		for(Food food : this.foods){
			if(Food_count==0){
				Food_count+=10;
			}
		}


		for(Food food : this.foods){
			if(immunity_count == 0){
				immunity_count+=10;
				System.out.println("immunity spawned: "+immunity_count);
			}
		}

		for(Food food: this.foods) {

			food.render(this.gc);
			if(food.getVisible()){

				food.checkCollision(this.bird);

				for(EnemyBee bee: this.bees) {

					food.checkCollision(bee);
					if(this.bird.width>300 && !this.scaledUp){   //when size of bird reaches certain size the window will mimic a zom out effect
						gc.setTransform(scale2.getMxx(), scale2.getMyx(), scale2.getMxy(), scale2.getMyy(), scale2.getTx(), scale2.getTy());
						this.scaledUp = true;
						GameTimer.edgeYNegative = -1600;
					}
					if(this.bird.width>500 && !this.scaledUp2){
						gc.setTransform(scale.getMxx(), scale.getMyx(), scale.getMxy(), scale.getMyy(), scale.getTx(), scale.getTy());
						this.scaledUp2 = true;
						GameTimer.edgeYNegative = -1600;
					}
					if(this.bird.width>900 && !this.scaledUp3){
						gc.setTransform(scale3.getMxx(), scale3.getMyx(), scale3.getMxy(), scale3.getMyy(), scale3.getTx(), scale3.getTy());
						this.scaledUp2 = true;
						GameTimer.edgeYNegative = -1600;
					}
				}
			}

			else{
				if(!(food instanceof SuperSpeed) && !(food instanceof Immunity)){
					this.Food_count-=1;
				}
				else if(food instanceof SuperSpeed){
					this.speedUp_count-=1;
				}
				else if(food instanceof Immunity){
					this.immunity_count-=1;
				}
				this.foods.remove(food);

			}

		}

		for(EnemyBee bee: this.bees) {
			bee.render(this.gc);
			bee.move();
			if(bee.getVisible()){
				bee.checkCollision(this.bird);
				for(EnemyBee bee2:this.bees){
					bee.checkCollision2(bee2);
				}
				if(this.bird.width>300 && !this.scaledUp){
						gc.setTransform(scale2.getMxx(), scale2.getMyx(), scale2.getMxy(), scale2.getMyy(), scale2.getTx(), scale2.getTy());
						this.scaledUp = true;
						GameTimer.edgeYNegative = -1600;
				}
				if(this.bird.width>500 && !this.scaledUp2){
						gc.setTransform(scale.getMxx(), scale.getMyx(), scale.getMxy(), scale.getMyy(), scale.getTx(), scale.getTy());
						this.scaledUp2 = true;
						GameTimer.edgeYNegative = -1600;
				}
				if(this.bird.width>900 && !this.scaledUp3){
					gc.setTransform(scale3.getMxx(), scale3.getMyx(), scale3.getMxy(), scale3.getMyy(), scale3.getTx(), scale3.getTy());
					this.scaledUp2 = true;
					GameTimer.edgeYNegative = -1600;
				}
			}
			else{
				this.bees.remove(bee);
			}
		}

		this.drawScores();

		if(!bird.getAlive() && this.bird.width <= 1000){  //game ends when bird dies
			for(EnemyBee bee: this.bees) {
				bee.setAlive(false);
			}
			for(Food bFood: this.foods) {
				bFood.setExists(false);
			}
			gc.setTransform(scale4.getMxx(), scale4.getMyx(), scale4.getMxy(), scale4.getMyy(), scale4.getTx(),scale3.getTy());  //reverts back the window to initial scale
			gc.clearRect(0, 0, GameStage.WINDOW_HEIGHT, GameStage.WINDOW_HEIGHT);
			gc.setFill(Color.WHITE);
			Font theFont = Font.font("Times New Roman", FontWeight.BOLD,50);
			gc.setFont(theFont);
			gc.fillText("GAMEOVER!", 225, 250);
			gc.fillText("Bees eaten: "+bird.getKilled(), 240, 350);
			gc.fillText("Food eaten: "+bird.getFood(), 240, 400);
			gc.fillText("Final size: "+bird.width, 230, 450);
			gc.fillText("Seconds alive: "+(bird. getTimeAlive()), 215, 500);
		}
		if(this.bird.width > 1000){ // when size increases more than 1000, the player is hailed as winner
			gc.setTransform(scale4.getMxx(), scale4.getMyx(), scale4.getMxy(), scale4.getMyy(), scale4.getTx(),scale3.getTy()); //reverts back the window to initial scale
			bird.setAlive(false);
			for(EnemyBee bee: this.bees) {
				bee.setAlive(false);
			}
			for(Food bFood: this.foods) {
				bFood.setExists(false);
			}
			gc.clearRect(0, 0, GameStage.WINDOW_HEIGHT, GameStage.WINDOW_HEIGHT);
			gc.setFill(Color.WHITE);
			Font theFont = Font.font("Times New Roman", FontWeight.BOLD,50);
			gc.setFont(theFont);
			gc.fillText("CONGRATULATIONS!", 130, 250);
			gc.fillText("Bees eaten: "+bird.getKilled(), 240, 350);
			gc.fillText("Food eaten: "+bird.getFood(), 240, 400);
			gc.fillText("Final size: "+bird.width, 230, 450);
			gc.fillText("Seconds alive: "+(bird. getTimeAlive()), 215, 500);

		}


	}


	private void drawScores(){  //shows # of eaten food and enemy, current size of bird, and seconds of gameplay

		//Food Eaten
		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		this.gc.setFill(Color.YELLOW);
		this.gc.fillText("Food Eaten:", 40, 30);
		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		this.gc.setFill(Color.WHITE);
		this.gc.fillText(bird.getFoodEaten() +"", 180, 30);

		//Enemy Eaten
		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		this.gc.setFill(Color.BLACK);
		this.gc.fillText("Enemy Eaten:", 220, 30);
		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		this.gc.setFill(Color.WHITE);
		this.gc.fillText(bird.getEnemyEaten() +"", 380, 30);

		//Current size
		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		this.gc.setFill(Color.GREEN);
		this.gc.fillText("Size:", 420, 30);
		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		this.gc.setFill(Color.WHITE);
		this.gc.fillText(bird.width +"", 480, 30);

		//Timer
		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		this.gc.setFill(Color.BROWN);
		this.gc.fillText("Seconds Alive:", 560, 30);
		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		this.gc.setFill(Color.WHITE);
		this.gc.fillText(this.bird. getTimeAlive() +"", 730, 30);



	}


	private void StayBg(){   //for when background isn't moving

		  this.gc.clearRect(0, 0, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);   //clears the canvas
	      this.gc.drawImage( bg, this.locationX, this.locationY );   //redraws background again
	}

	private void moveBgX(int value){  //moves background left and right (X axis)
		this.gc.clearRect(0, 0, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);

        // redraw background image (moving effect)

	  this.gc.drawImage( bg, this.locationX, this.locationY );

	  if(this.bird.getSpeedUp()){

		  if(this.locationX >= GameTimer.edgeXNegative){

        		this.collidedX = false;
        		this.locationX = this.locationX-GameTimer.CHANGER;

        	}

        	else if(this.locationX <= GameTimer.edgeX){
        		this.collidedX = false;
        		this.locationX = this.locationX+GameTimer.CHANGER;

        	}

        	else{

        		this.collidedX = true;
        		this.locationX += 2*(120/bird.width)*value*-1;

        	}


		}
	  else{

		  if(this.locationX >= GameTimer.edgeXNegative){
			  	System.out.println("collided");
        		this.collidedX = false;
        		this.locationX = this.locationX-GameTimer.CHANGER;

        	}

        	else if(this.locationX <= GameTimer.edgeX){
        		this.collidedX = false;
        		this.locationX = this.locationX+GameTimer.CHANGER;

        	}

        	else{
        		this.collidedX = true;
        		this.locationX += (120/bird.width)*value*-1;

        	}


		}


        for(Food bFood: this.foods) {

        	if(this.bird.getSpeedUp()){

        		if(this.locationX<=GameTimer.edgeX){

					bFood.x = bFood.x+2*((120/bird.width)*value*-1)+GameTimer.CHANGER;
				}
				else if(this.locationX >= GameTimer.edgeXNegative){

					bFood.x = bFood.x+2*((120/bird.width)*value*-1)-GameTimer.CHANGER;
	        	}
				else if (collidedX){
					bFood.x = bFood.x+2*((120/bird.width)*value*-1);
				}

			}
			else{
				if(this.locationX<=GameTimer.edgeX){

					bFood.x = bFood.x+((120/bird.width)*value*-1)+GameTimer.CHANGER;
				}
				else if(this.locationX >= GameTimer.edgeXNegative){

					bFood.x = bFood.x+((120/bird.width)*value*-1)-GameTimer.CHANGER;
	        	}
				else if (collidedX){
					bFood.x = bFood.x+((120/bird.width)*value*-1);
				}
			}

			bFood.render(this.gc);

		}

		for(EnemyBee bee: this.bees) {

			if(this.bird.getSpeedUp()){

        		if(this.locationX<=GameTimer.edgeX){

					bee.x = bee.x+2*((120/bird.width)*value*-1)+GameTimer.CHANGER;
				}
				else if(this.locationX >= GameTimer.edgeXNegative){

					bee.x = bee.x+2*((120/bird.width)*value*-1)-GameTimer.CHANGER;
	        	}
				else if (collidedX){
					bee.x = bee.x+2*((120/bird.width)*value*-1);
				}

			}
			else{
				if(this.locationX<=GameTimer.edgeX){

					bee.x = bee.x+((120/bird.width)*value*-1)+GameTimer.CHANGER;
				}
				else if(this.locationX >= GameTimer.edgeXNegative){

					bee.x = bee.x+((120/bird.width)*value*-1)-GameTimer.CHANGER;
	        	}
				else if (collidedX){
					bee.x = bee.x+((120/bird.width)*value*-1);
				}
			}

			bee.render(this.gc);
		}
	}


	private void moveBgY(int Value){  //moves background,food, and enemy object upward and downward

		 this.gc.clearRect(0, 0, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		  this.gc.drawImage( bg, this.locationX, this.locationY );
	        // redraw background image (moving effect)
		  if(this.bird.getSpeedUp()){

	        	if(this.locationY >= GameTimer.edgeY){
	        		this.collidedY = false;
	        		this.locationY = this.locationY-GameTimer.CHANGER;
	        	}

	        	else if(this.locationY <= GameTimer.edgeYNegative){
	        		this.collidedY = false;
	        		this.locationY = this.locationY+GameTimer.CHANGER;
	        	}

	        	else{
	        		this.collidedY = true;
	        		this.locationY += 2*(120/bird.width)*Value;
	        		System.out.println("This is the bird loc:"+this.locationY);
	        	}


			}
		  else{

				if(this.locationY >= GameTimer.edgeY){
					this.collidedY = false;
					this.locationY = this.locationY-GameTimer.CHANGER;
	        		System.out.println("This is the bird loc:"+this.locationY);
	        	}

				else if(this.locationY <= GameTimer.edgeYNegative){
					this.collidedY = false;
	        		this.locationY = this.locationY+GameTimer.CHANGER;
	        	}

	        	else{
	        		this.collidedY = true;
	        		this.locationY += (120/bird.width)*Value;
	        		System.out.println("This is the bird loc:"+this.locationY);
	        	}
			}


	        for(Food bFood: this.foods) {

					if(this.bird.getSpeedUp()){
						if(this.locationY>=GameTimer.edgeY){
							bFood.y = bFood.y+2*((120/bird.width)*Value)-GameTimer.CHANGER;
						}
						else if(this.locationY <= GameTimer.edgeYNegative){
							bFood.y = bFood.y+2*((120/bird.width)*Value)+GameTimer.CHANGER;
			        	}
						else if (collidedY){
							bFood.y = bFood.y+2*((120/bird.width)*Value);
						}

					}
					else{
						if(this.locationY>=GameTimer.edgeY){
							bFood.y = bFood.y+((120/bird.width)*Value)-GameTimer.CHANGER;
						}
						else if(this.locationY <= GameTimer.edgeYNegative){
							bFood.y = bFood.y+((120/bird.width)*Value)+GameTimer.CHANGER;
			        	}
						else if (collidedY){
							bFood.y = bFood.y+((120/bird.width)*Value);
						}
					}

					bFood.render(this.gc);
			}

	        for(EnemyBee bee: this.bees) {

	        	if(this.locationY == GameTimer.edgeY || this.locationY <= GameTimer.edgeYNegative){
	        		bee.render(this.gc);

	        	}
	        	else{
					bee.render(this.gc);
					if(this.bird.getSpeedUp()){

						bee.y = bee.y+2*((120/bird.width)*Value);
					}else{
						bee.y = bee.y+(120/bird.width)*Value;
					}
	        	}
			}
	}



	private void spawnFoods(){


		Random r = new Random();

		for(int i=0;i<50;i++){  //for normal food

			int x = r.nextInt(GameStage.WINDOW_WIDTH-(int)this.locationX);
			int y = r.nextInt(GameStage.WINDOW_HEIGHT+(int)this.locationY+200);
			this.foods.add(new Food(x,y));
		}

		for(int i=0;i<10;i++){ //for flowers

			int x = r.nextInt(GameStage.WINDOW_WIDTH-(int)this.locationX);
			int y = r.nextInt(GameStage.WINDOW_HEIGHT+(int)this.locationY+200);
			this.foods.add(new SuperSpeed(x,y));
		}
		for(int i=0;i<10;i++){  //for grapes
			int x = r.nextInt(GameStage.WINDOW_WIDTH-(int)this.locationX);
			int y = r.nextInt(GameStage.WINDOW_HEIGHT+(int)this.locationY+200);
			this.foods.add(new Immunity(x,y));
		}
	}

	private void spawnBees(){
		Random r = new Random();
		for(int i=0;i<10;i++){
			int x = r.nextInt(GameStage.WINDOW_WIDTH-200);
			int y = r.nextInt(GameStage.WINDOW_HEIGHT-300);


			this.bees.add(new EnemyBee(x,y));
		}

	}

	private void spawnOneFood(){  //for when the food in the array list decreases by 1, spawn 1 food in map
		Random r = new Random();
		for(int i=0;i<1;i++){
			int x = r.nextInt(GameStage.WINDOW_WIDTH-500);
			int y = r.nextInt(GameStage.WINDOW_HEIGHT-500);

			this.foods.add(new Food(x,y));
		}

	}









	//method that will listen and handle the key press events
	private void handleKeyPressEvent() {
		this.theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
            	KeyCode code = e.getCode();
                movebird(code);

			}
		});

		this.theScene.setOnKeyReleased(new EventHandler<KeyEvent>(){
		            public void handle(KeyEvent e){
		            	KeyCode code = e.getCode();
		                stopbird(code);

		            }
		        });
    }

	//method that will move the ship depending on the key pressed
	private void movebird(KeyCode ke) {
		if(ke==KeyCode.W) {    //when W is pressed, background moves upward
			moveBgY(10);
			up = true;

		}

		if(ke==KeyCode.S) {   //when S is pressed, background moves downward
			moveBgY(-10);
			down = true;

		};


		if(ke==KeyCode.A) {  //when A is pressed, background moves leftward
			moveBgX(-10);
			left = true;
			bird.setFaceToRighOrig(bird.getFaceToRight());
			bird.setFaceToRight(false);
			this.faceChanged = true;

			if(faceChanged && bird.getFaceToRighOrig()!=bird.getFaceToRight()){
				faceChanged = !faceChanged;
				this.bird.changeFace(this.bird.getFaceToRight());
			}


		};

		if(ke==KeyCode.D) {  //when D is pressed, background moves rightward
			moveBgX(10);
			right = true;
			bird.setFaceToRighOrig(bird.getFaceToRight());
			bird.setFaceToRight(true);
			this.faceChanged = true;

			if(faceChanged && bird.getFaceToRighOrig()!=bird.getFaceToRight()){
				faceChanged = !faceChanged;
				this.bird.changeFace(this.bird.getFaceToRight());
			}

		};


		if(locationX==-1980){
			locationX = -1950;

		}

		if(locationX == 360){
			locationX = 330;

		}

		if(locationY==360){
			locationY = 330;
		}

		if(locationY==-1830){
			locationY = -1800;
		}
		System.out.println(ke+" key pressed.");
   	}


	private void stopbird(KeyCode ke){
		if(ke==KeyCode.UP) {
			up = false;
		};

		if(ke==KeyCode.LEFT) {
			left = false;
		};

		if(ke==KeyCode.DOWN) {
			down = false;
		};

		if(ke==KeyCode.RIGHT) {
			right = false;
		};

		System.out.println(ke+" key released.");
	}


	public double getPosX(){
		return this.locationX;
	}

	public double getPosY(){
		return this.locationY;
	}

}