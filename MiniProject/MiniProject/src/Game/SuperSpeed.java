package Game;

import javafx.scene.image.Image;

public class SuperSpeed extends Food {

	private final static Image SPEED_UP_FLOWER = new Image("images/speedUp_flower.png",Food.BASICFOOD_SIZE,Food.BASICFOOD_SIZE,false,false);
	SpeedDuration duration;

	SuperSpeed(int x, int y){
		super(x,y);
		this.visible = true;
		this.exists = true;
		this.loadImage(SuperSpeed.SPEED_UP_FLOWER);
		this.duration = new SpeedDuration(this);  //creates timer for the duration of when flower appears in the map, before respawning
		this.duration.start();   //starts timer
	}

	@Override
	void checkCollision(PlayerBird bird){

		if(this.collidesWith(bird)){
			if(!bird.getSpeedUp()){
				bird.setSpeedUp(true);
				this.vanish();
				bird.speed_stopWatch = new SpeedTimer(bird);   //starts timer for the effects of super speed
				bird.speed_stopWatch.start();  //start timer
			}
			else{
				//bird.power.refresh();
				this.vanish(); //removes the flower from the map due to consumption
			}
		}
	}


	void checkCollision(EnemyBee bee){ //when bee collides with flower no consumption nor effects happen

		if(this.collidesWith(bee)){

		}
	}
}
