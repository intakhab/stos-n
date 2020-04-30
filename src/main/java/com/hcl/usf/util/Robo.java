package com.hcl.usf.util;

import java.awt.Robot;
import java.awt.event.KeyEvent;
public class Robo {
   
	public static void main(String[] args) throws Exception {

		Robot robot = new Robot();
		while (true) {

			try {
				//Runtime.getRuntime().exec("notepad");
				// Robot robot = new Robot();
				robot.delay(1000);
				robot.keyPress(KeyEvent.VK_A);
				robot.delay(1000);
				robot.keyPress(KeyEvent.VK_B);
				robot.delay(1000);
				robot.keyPress(KeyEvent.VK_C);
				robot.delay(1000);
				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.delay(1000);

				robot.keyPress(KeyEvent.VK_BACK_SPACE);
				robot.delay(1000);

				robot.keyPress(KeyEvent.VK_BACK_SPACE);

				robot.delay(1000);
				for(int i=0;i<10;i++) {
					robot.keyPress(KeyEvent.VK_A);
					robot.delay(2000);
					robot.keyPress(KeyEvent.VK_B);
					robot.delay(2000);
					robot.keyPress(KeyEvent.VK_C);
					Thread.sleep(5000);
					//robot.keyPress(KeyEvent.VK_BACK_SPACE);
					robot.delay(1000);

					//robot.keyPress(KeyEvent.VK_BACK_SPACE);
					robot.delay(5000);

					//robot.keyPress(KeyEvent.VK_BACK_SPACE);
					//robot.keyPress(KeyEvent.VK_ENTER);

				}
				System.out.println("b " ) ;
				robot.delay(100 * 30);
			} catch (Exception ex) {
				
			}
		}
	

	}

}
