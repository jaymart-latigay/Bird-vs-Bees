package Game;


import javafx.scene.image.Image;



public class PlayerBird extends Sprite{
	private String name;
	private boolean alive;
	private int food;
	private int killed;
	private int timeAlive;
	private int enemy;
	public static  int SHIP_WIDTH = 40;
	public final static Image BIRD_IMAGE_RIGHT = new Image("images/bird_right.gif",SHIP_WIDTH,SHIP_WIDTH,false,false);
	public final static Image BIRD_IMAGE_LEFT = new Image("images/bird_left.gif",SHIP_WIDTH,SHIP_WIDTH,false,false);
	private boolean speedUp = false;
	private boolean immunity = false;
	SpeedTimer speed_stopWatch;
	ImmunityTimer immune_stopWatch;
	private boolean faceToRight = true;
	private boolean faceToRighOrig = false;

	//constructor
	public PlayerBird(String name, int x, int y){
		super(x,y);
		this.speed_stopWatch = new SpeedTimer(this);
		this.immune_stopWatch = new ImmunityTimer(this);
		this.name = name;
		this.alive = true;
		this.loadImage(PlayerBird.BIRD_IMAGE_RIGHT);

	}

	public boolean isAlive(){
		if(this.alive) return true;
		return false;
	}


	public void die(){
    	this.alive = false;
    }


	public void move() {
		/*
		 *TODO: 		Only change the x and y position of the ship if the current x,y position
		 *				is within the gamestage width and height so that the ship won't exit the screen
		 */
		this.x += this.dx;
		this.y += this.dy;
	}

	void gainFood(int increase){
    	this.food+=increase;
    	System.out.println("Score: "+food);
    }

	void gainEnemy(double increase){
    	this.enemy+=increase;
    	System.out.println("Score: "+enemy);
    }

	public int getFoodEaten() {
		return this.food;
	}

	public int getEnemyEaten() {
		return this.killed;
	}

	void changeFace(Boolean bool){  //changes image depending on where user whats bird to face to
		if (bool && !this.immunity){
			this.img = new Image("images/bird_right.gif",this.width,this.width,false,false);
		}
		else if(!bool && !this.immunity){
			this.img = new Image("images/bird_left.gif",this.width,this.width,false,false);
		}
	}

	void changeBirdImage() {   //changes the bird image for immunity effect
		if(this.immunity && this.faceToRight) {
			this.img = new Image("images/immunity_bird_right.gif",this.width,this.width,false,false);
		}
		else if(this.immunity && !this.faceToRight) {
			this.img = new Image("images/immunity_bird_left.gif",this.width,this.width,false,false);
		}
	}

	//setters
	public void setAlive(boolean isAlive) {
		this.alive = isAlive;
	}

	public void setKilled(int killCount) {
		this.killed = this.killed  + killCount;
	}

	public void setTimeAlive(int second) {
		this.timeAlive = this.timeAlive + second;
	}

	public void setSpeedUp(boolean toSpeedUp) {
		this.speedUp = toSpeedUp;
	}

	public void setImmunity(boolean isImmune) {
		this.immunity = isImmune;
	}

	public void setFaceToRight(boolean facingRight) {
		this.faceToRight = facingRight;
	}

	public void setFaceToRighOrig(boolean facingRight) {
		this.faceToRighOrig = facingRight;
	}


	//getters
	public boolean getAlive() {
		return this.alive;
	}

	public int getFood() {
		return this.food;
	}

	public int getKilled() {
		return this.killed;
	}

	public int getTimeAlive() {
		return this.timeAlive;
	}

	public boolean getSpeedUp() {
		return this.speedUp;
	}

	public boolean getImmunity() {
		return this.immunity;
	}

	public boolean getFaceToRight() {
		return this.faceToRight;
	}

	public boolean getFaceToRighOrig() {
		return this.faceToRighOrig;
	}
}
