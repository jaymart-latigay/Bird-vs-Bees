package Game;

import javafx.scene.image.Image;

public class Immunity extends Food {
	private final static Image IMMUNITY_GRAPES = new Image("images/immunity_grapes.png",Food.BASICFOOD_SIZE,Food.BASICFOOD_SIZE,false,false);

	public ImmunityDuration duration;

	//constructor
	Immunity(int x, int y){
		super(x,y);
		this.visible = true;
		this.exists = true;
		this.loadImage(Immunity.IMMUNITY_GRAPES);
		this.duration = new ImmunityDuration(this);  //creates duration timer for before grapes respawns
		this.duration.start(); //starts the timer

	}

	void checkCollision(PlayerBird bird){  //when bird collides with immunity-grapes

		if(this.collidesWith(bird)){
			if(!bird.getImmunity()){  //when bird is not immune already
				bird.setImmunity(true);  //bird's immunity is set to True
				this.vanish();     //grapes visibility is false
				bird.immune_stopWatch = new ImmunityTimer(bird);  //creates a timer for the seconds the effect of immunity lasts
				bird.immune_stopWatch.start();  //starts timer

				if(bird.getImmunity()){  // when bird is immune
					bird.changeBirdImage();  //changes the appearance of bird
				}
			}
			else{
				//bird.immune.refresh();  //turns back time to 5 seconds in Immunity timer
				this.vanish(); //removes the flower from the map due to consumption
			}
		}

	}

	void checkCollision(EnemyBee bee){

		if(this.collidesWith(bee)){  //does nothing when bee collides with Immunity-grapes

		}
	}
}

