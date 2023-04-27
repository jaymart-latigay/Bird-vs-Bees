package Game;

public class SpeedTimer extends Thread {

	private PlayerBird bird;
	private int time;
	private final static int EFFECT_DURATION = 5;

	SpeedTimer(PlayerBird bird){
		this.bird = bird;
		this.time = SpeedTimer.EFFECT_DURATION;
	}

	/*
	void refresh(){
		this.time = SpeedTimer.UPGRADED_TIME;
	}*/
	private synchronized void countDown(){

		while(this.time!=0){
			try{
				Thread.sleep(1000);  //delays by 1 second
				this.time--;   //so time decrements by 1 after every 1 second
			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
		}

		this.bird.setSpeedUp(false);
	}

	public void run(){ //when start(), this occurs automatically

			countDown();

	}



}

