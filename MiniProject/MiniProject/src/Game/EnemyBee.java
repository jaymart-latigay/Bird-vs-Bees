package Game;


import javafx.scene.image.Image;



public class EnemyBee extends Sprite {

	public final static Image FISH_IMAGE = new Image("images/enemyBee_left.gif",EnemyBee.FISH_WIDTH,EnemyBee.FISH_WIDTH,false,false);
	public final static int FISH_WIDTH = 40;
	private boolean alive;

	//attribute that will determine if a bird will initially move to the right
	private boolean moveRight;
	private boolean moveUp;
	private double speed = 1;
	private static double edgeX = -1980;
	private static double edgeXNegative = 360;
	private static double edgeY = 360;
	private static double edgeYNegative = -1700;

	public double trueLocX;
	public double trueLocY;
	int random;
	private BeeMovementTimer move;




	EnemyBee(int x, int y){

		super(x,y);
		this.move = new BeeMovementTimer(this);
		this.move.start();
		this.alive = true;
		this.loadImage(EnemyBee.FISH_IMAGE);
		this.trueLocX = this.x-400;
		this.trueLocX = this.trueLocX*-1;
		this.trueLocY = this.y-400;
		this.trueLocY = this.trueLocY*-1;


		int z = this.random;



		if( z == 0){
			this.moveRight = true;
		}
		else if(z == 1){
			this.moveRight = false;
		}

		else if( z == 2){
			this.moveUp = true;
		}
		else if(z == 3){
			this.moveUp = false;
		}
	}

	//method that changes the x position of the bee
	void move(){

		speed = (120/this.width);  //bee's speed

		int z = this.random;

		if( z == 0){
			this.moveRight = true;
		}
		else if(z == 1){
			this.moveRight = false;
		}

		else if( z == 2){
			this.moveUp = true;
		}
		else if(z == 3){
			this.moveUp = false;
		}

		if(this.trueLocX >= this.edgeXNegative){
			this.moveRight = true;
		}

		else if(this.trueLocX <= this.edgeX){
			this.moveRight = false;
		}

		if(this.moveRight == true){
			this.x += speed;
			this.trueLocX -= speed;
		}
		else{
			this.x -= speed;
			this.trueLocX += speed ;
		}

		if(this.moveUp == true){
			this.y -= speed;
			this.trueLocY += speed;
		}
		else{
			this.y += speed;
			this.trueLocY -= speed ;
		}

		if(this.trueLocY >= this.edgeY){
			this.moveUp = false;
		}

		if(this.trueLocY <= this.edgeYNegative){
			this.moveUp = true;
		}
	}


	//getter
	public boolean isAlive() {
		return this.alive;
	}

	double getTrueLocX(){
		return(this.trueLocX);
	}


	void checkCollision(PlayerBird bird){   //checks if bee collides with bird

		if(this.collidesWith(bird)){

			if(bird.width>this.width){   //bird is larger than bee

				//add the size of the bee to the current size of the bird
				bird.setWidth(bird.width + this.width);
				bird.setHeight(bird.height + this.height);

				if(bird.getFaceToRight()) { //bird faces right
					bird.img = new Image("images/bird_right.gif",bird.width,bird.width,false,false);
				} else { //bird faces left
					bird.img = new Image("images/bird_left.gif",bird.width,bird.width,false,false);
				}

				this.alive = false; //the bee dies
				this.vanish(); // changes visibility to false
				bird.setKilled(1);  //increments the number of bees the bird consumed

			}
			else if(!bird.getImmunity() && bird.width < this.width) {   //when bird has no immunity and smaller than bee
				bird.setAlive(false);
				System.out.println("Birdie dies :(");
			}
		}
	}


		void checkCollision2(EnemyBee bee){  //when bee collides with other bees

			if(this.collidesWith(bee)){

				if(bee.width>this.width){  //this bee is larger than other bee

					//adds the size of the other bee to the current size of this bee
					bee.setWidth(bee.width + this.width);
					bee.setHeight(bee.height + this.height);

					bee.img = new Image("images/enemyBee_right.gif",bee.width,bee.width,false,false);  //purposefully only did this - for random purposes
					this.alive = false;  //other bee dies
					this.vanish(); // set visibility to false

				}
				else if(bee.width<this.width){ // when other bee is larger than the
					bee.vanish(); //visibility of this bee is set to false
					bee.alive = false; //this bee dies
				}
			}
	}


	public void setAlive(boolean isAlive) {
		this.alive = isAlive;
	}
}


