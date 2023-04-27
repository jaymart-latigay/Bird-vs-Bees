package Game;



import javafx.scene.image.Image;

public class Food extends Sprite {
	public final static int BASICFOOD_SIZE = 20;
	private final static Image BASICFOOD_IMAGE = new Image("images/wheat.png",  Food.BASICFOOD_SIZE, Food.BASICFOOD_SIZE,false,false);
	protected boolean exists;
	protected final static int GAIN = 1;

	//Constructor
	Food(int x, int y) {
		super(x,y);
		this.visible = true;
		this.exists = true;
		this.loadImage(Food.BASICFOOD_IMAGE);
	}

	public boolean doesExist() {
		return this.exists;
	}


	void checkCollision(PlayerBird bird){   //checks if bird collides with food

		if(this.collidesWith(bird)){

			this.vanish();   //changes the visibility of the food to false
			bird.gainFood(Food.GAIN);  //increments the number of food eaten

			//increments the width and height of bird per normal food eaten
			bird.setWidth(bird.width+10);
			bird.setHeight(bird.height+10);

			//for normal bird changing directions
			if(bird.getFaceToRight() && !bird.getImmunity()){
				bird.img = new Image("images/bird_right.gif",bird.width,bird.width,false,false);
			}
			else if (bird.getFaceToRight() && !bird.getImmunity()) {
				bird.img = new Image("images/bird_left.gif",bird.width,bird.width,false,false);
			}

			bird.changeBirdImage();   //changes the bird appearance - represents immunity

		}
	}



	void checkCollision(EnemyBee bee){ //checks if bee collides with food

		if(this.collidesWith(bee)){

			this.vanish();
			bee.setWidth(bee.width+10);  //bee's width and size is incremented by 10
			bee.setHeight(bee.height+10);
			bee.img = new Image("images/enemyBee_left.gif",bee.width,bee.width,false,false); //to show the increase in size, sets image to a larger version
		}
	}

	public void setExists(boolean doesExist) {
		this.exists = doesExist;
	}
}
