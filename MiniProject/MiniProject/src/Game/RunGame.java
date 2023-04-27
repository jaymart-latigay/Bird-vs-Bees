/*************************************************************************************************************************
 *
 * CMSC 22 Object-Oriented Programming
 * Exercise 8: GUI I
 *
 *
 * (c) Institute of Computer Science, CAS, UPLB
 *@author: Jaymart Latigay and James Duldulao
 *@date: 2023/01/09
 *
 *
 *MiniProJect in CMSC 22 YZ2L
 *
 *This is a game application that resembles the known game Agar.io
 *Where instead of circular blobs the player (user) is represented by a flying bird, enemies as bees, and wheat, grapes, and flowers as consumable foods
 *
 *Flower provides the bird object twice the current speed of the bird object at a certain time when consumed.
 *Grapes provide the bird object immunity against larger Bee objects for a certain amount of time
 *
 *The main goal of the game is to consume all the enemy bees at the shortest amount of time
 *
 *The game ends with player losing when a larger bee object consumes the bird.
 *
 *
 *
 *Resources (Images):
 *Averina, A. (n.d.). Fruit Icons. Shutterstock. https://www.shutterstock.com/image-vector/set-pixel-fruit-icons-apple-600w-349737026.jpg?fbclid=IwAR0XUDfPwXXsRjTtIuTOiTAlnAp5BplQRIyMzBAsjT18eTT4olKaLtUgydE
*Crop Wheat. (n.d.). PNG EGG. https://www.pngegg.com/en/png-dyihi
*derGriza. (n.d.). Flowers icon set. Shutterstock. https://www.shutterstock.com/image-vector/flowers-icon-set-pixel-art-600w-1030573114.jpg?fbclid=IwAR2VrD9S0t0WLvnNZVTZbRvUTdx6Nd5vfUIZ8mQulxWwxg81xrmKp_6hPH8
*iSohei. (2012, April 26). Pixel Waterfall BG. DEVIANT ART. https://i.pinimg.com/originals/46/ac/9e/46ac9e282d3c303934a72d941845785b.gif?fbclid=IwAR3RJOEjrdrLsbyL53i09jM8dXy94W5Ndjcl3-78hnRxLDbBlMw21Cr3TIk
*man_darinka. (n.d.). Bunch Grapes. VectorStock. https://cdn.vectorstock.com/i/1000x1000/09/49/pixel-art-bunch-grapes-icon-32x32-vector-30730949.webp?fbclid=IwAR3MKjcVC_brGfoMPBbI_UbepeVZe2431EDTRnZfWIdWJUHBQ-DZnpIFxP8
*Robinson, A. (2017, November 19). Bird Flap Animation. VALKRYSA. https://blog.valkrysa.com/content/images/2017/11/bird-flap-animation.gif
*Vumbaca, A. (ca. 2018). Forbidden Forest. ARTSTATION. https://cdna.artstation.com/p/assets/images/images/012/648/858/original/andrea-vumbaca-bee.gif?1535912616
 *
 *
 *Source Codes:
 *https://drive.google.com/drive/u/1/folders/16NaEEJg1WnfWXk5gueePbkRrmV1puwzh
 *
 *
 *************************************************************************************************************************/



package Game;

import javafx.application.Application;
import javafx.stage.Stage;

public class RunGame extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage){
		GameStage theGameStage = new GameStage();
		theGameStage.setStage(stage);
	}

}