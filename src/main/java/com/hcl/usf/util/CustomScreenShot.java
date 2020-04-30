package com.hcl.usf.util;

import java.nio.file.Paths;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

/***
 *
 * @author Intakhabalam.s@hcl.com
 *
 */
public class CustomScreenShot {

	/***
	 * @param driver {@link WebDriver}
	 * @return {@link String}
	 */
	public static String captureFullScreenImage(WebDriver driver) {
		try {
			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
					.takeScreenshot(driver);
			return imageConverter(screenshot);

		} catch (Exception e) {
			e.fillInStackTrace();
		}
		return null;
	}
    /***
     * 
     * @param driver {@link WebDriver}
     * @param element {@link WebElement}
     * @return {@link String}
     */
	public static String specificElementCaptureScreenImage(WebDriver driver, WebElement element) {
		try {

			// Screenshot screenshot = new
			// AShot().shootingStrategy(ShootingStrategies.simple()).takeScreenshot(driver,
			// element);
			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
					.takeScreenshot(driver, element);

			return imageConverter(screenshot);
		} catch (Exception e) {
			e.fillInStackTrace();
		}

		return null;

	}

	public static String imageConverter(Screenshot screenshot) {
		final String filePath = "screenshot/" + System.currentTimeMillis();
		try {
			ImageIO.write(screenshot.getImage(), "png", Paths.get(filePath).toFile());
			byte[] fileContent = FileUtils.readFileToByteArray(Paths.get(filePath).toFile());
			String encodedString = Base64.getEncoder().encodeToString(fileContent);
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
			}
			FileUtils.deleteQuietly(Paths.get(filePath).toFile());
			return encodedString;
		} catch (Exception e) {
			FileUtils.deleteQuietly(Paths.get(filePath).toFile());
		}
		return null;
	}
}
