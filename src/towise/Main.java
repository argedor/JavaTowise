package towise;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.json.simple.JSONObject;

public class Main {
	public static void main(String[] args) {
		Api api = new Api();
		api.AppId = "1";
		api.AppKey = "argedor123";
		int person_id = 338;
		try {
			BufferedImage originalImage = ImageIO.read(new File("/home/hakan/Desktop/download (1).jpeg"));
			JSONObject obj = api.FaceDetect(originalImage);
			System.out.println(obj);
			JSONObject data = (JSONObject) obj.get("data");
			int facecount = Integer.parseInt(String.valueOf(data.get("count")));
			System.out.println(facecount);
			
			if(facecount==1)
			{
				JSONObject obj1 = api.AddPerson("hakan", person_id);
				System.out.println(obj1);
				JSONObject data1 = (JSONObject) obj1.get("data");
				
				try {
					Boolean add = (Boolean) data1.get("acknowledged");
					System.out.println(add);
					if(add)
					{
						JSONObject obj2 = api.AddFace(originalImage,person_id);
						System.out.println(obj2);
					}
				}
				catch (Exception e) {
					// TODO: handle exception
				}
				
				
			}
		} catch (Exception e) {
			
		}
		
	}
}
