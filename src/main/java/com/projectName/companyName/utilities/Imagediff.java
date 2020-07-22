package com.projectName.companyName.utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;


/**
 * 
 * @author Sasi Vinod Akula
 *
 */
public class Imagediff {

	
	// private Logger log = LoggerHelper.getLogger(WaitHelper.class);
	private static Logger log = Logger.getLogger(Imagediff.class);

	

	public static void diff(BufferedImage expectedImg, BufferedImage actualImg) {
		ImageDiffer imgDiff = new ImageDiffer();
		log.info("Verifing the the image");
		ImageDiff dif = imgDiff.makeDiff(expectedImg, actualImg);
		if (dif.hasDiff()) {
			log.info("Both images are different");			
		} else {
			log.info("Both images are same");			
		}		
	}
	
	public static BufferedImage readFile(String ImageFileName) throws IOException {		
		File file = new File(System.getProperty("user.dir")+"\\screenshots\\"+ImageFileName+".png");
	      BufferedImage Img = ImageIO.read(file);	
	      return Img ;
	}
	
	public static void check(String expectedImageFileName,String actualImageFileName ) throws Exception {
		BufferedImage expectedImg = readFile(expectedImageFileName);
		BufferedImage actualImg = readFile(actualImageFileName);
		diff(expectedImg, actualImg);		
	}
	
	

}
