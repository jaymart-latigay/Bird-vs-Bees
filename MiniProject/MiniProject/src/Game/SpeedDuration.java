package Game;

public class SpeedDuration extends Thread {

	private SuperSpeed speed;
	private int time;
	private final static int DURATION = 5;


	SpeedDuration(SuperSpeed speed){
		this.speed = speed;
		this.time = 5;
	}

	private void countDown(){

		while(this.time!=0){
			try{
				Thread.sleep(1000);
				this.time--;

			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
		}
		speed.vanish();


	}

	public void run(){

			countDown();

	}



}

