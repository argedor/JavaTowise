package towise;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.json.simple.JSONObject;

public class Main {
	public static void main(String[] args) {
		Api api = new Api();
		api.AppId = "1";
		api.AppKey = "argedor123";
		int person_id = 345;
		
		File f = new File("/home/hakan/Desktop/Family-Support-745x400.jpg");
		JSONObject obj = api.EmotionDetect(f);
		System.out.println(obj);
	}
}
