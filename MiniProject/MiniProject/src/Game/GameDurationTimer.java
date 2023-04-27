package Game;

public class GameDurationTimer extends Thread {


	PlayerBird bird;

	public GameDurationTimer(PlayerBird bird) {
		this.bird = bird;
	}


	private synchronized void countDown(){

		while(this.bird.getAlive()){
			try{
				Thread.sleep(1000);
				this.bird.setTimeAlive(1);
			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
		}this.bird.setTimeAlive(-1);



	}

	public void run(){

			countDown();

	}



}
