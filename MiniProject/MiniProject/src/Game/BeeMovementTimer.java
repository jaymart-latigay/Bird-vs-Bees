package Game;

import java.util.Random;

public class BeeMovementTimer extends Thread {

	int time;
	int random;
	EnemyBee bee;




	BeeMovementTimer(EnemyBee bee){

		this.bee = bee;
		Random r = new Random();
		bee.random = r.nextInt(4);
		Random r2 = new Random();
		this.time = r2.nextInt(5);
	}




	public synchronized void countdown(){

		while(this.time!=0){
			try{
				Thread.sleep(1000);
				this.time--;
			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
		}

		Random r = new Random();
		Random r2 = new Random();
		bee.random = r.nextInt(2);
		this.time = r2.nextInt(5);



	}



	public void run(){

		while(true){
			countdown();
		}

	}

}
