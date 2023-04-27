package Game;

public class ImmunityTimer extends Thread {  //counts the seconds where immunity's effect occurs

	private PlayerBird bird;
	private int time;
	private final static int EFFECT_DURATION = 5;

	public ImmunityTimer(PlayerBird bird){
		this.bird = bird;
		this.time = ImmunityTimer.EFFECT_DURATION;

	}

	/*
	void refresh(){
		this.time = ImmunityTimer.UPGRADE_TIME;
	}*/

	private synchronized void countDown(){
		while(this.time!=0){
			try{
				Thread.sleep(1000);  //delays loop by 1 second
				this.time--;   //so time decrements every after 1 second
			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
		}

		this.bird.setImmunity(false);
	}

	public void run(){

			countDown();

	}



}
