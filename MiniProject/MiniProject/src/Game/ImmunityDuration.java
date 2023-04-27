package Game;

public class ImmunityDuration extends Thread {   //counts the seconds before Immunity-grapes vanishes from the map --for respawning purposes

	private Immunity immunity;
	private int time;
	private final static int DURATION = 10;


	ImmunityDuration(Immunity immunity){
		this.immunity = immunity;
		this.time = 10;
	}

	private void countDown(){

		while(this.time!=0){
			try{
				Thread.sleep(1000);  //delays by 1 second
				this.time--;        //so, time decrements after every second

			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
		}

		immunity.vanish();  //when time == 0; immunity-grape's visibility in map is false

	}

	public void run(){   //when start(), this occurs automatically
		countDown();
	}

}

