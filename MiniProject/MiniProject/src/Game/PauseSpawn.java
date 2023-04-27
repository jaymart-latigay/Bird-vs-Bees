package Game;

import java.util.ArrayList;
import java.util.Random;

public class PauseSpawn extends Thread {


	private int time;
	private ArrayList<Food> foods;

	PauseSpawn(ArrayList<Food> foods){
		this.foods = foods;
		this.time = 15;
	}

	private synchronized void spawnSpeedUp(){

		Random r = new Random();
		for(int i=0;i<10;i++){
			int x = r.nextInt(GameStage.WINDOW_WIDTH-500);
			int y = r.nextInt(GameStage.WINDOW_HEIGHT-500);

			this.foods.add(new SuperSpeed(x,y));
		}

	}

	private  void spawnImmunity(){
		Random r = new Random();
		for(int i=0;i<10;i++){
			int x = r.nextInt(GameStage.WINDOW_WIDTH);
			int y = r.nextInt(GameStage.WINDOW_HEIGHT);


			this.foods.add(new Immunity(x,y));
		}

	}

	private void countDown(){

		System.out.println("started spawn");

		while(this.time!=0){
			try{
				Thread.sleep(1000);
				this.time--;

			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
		}

		spawnImmunity();
		spawnSpeedUp();
		this.time = 15;
		System.out.println("spawned Power Up");

	}

	public void run(){
		while(true){
			countDown();
		}

	}



}
