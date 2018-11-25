package example;

import java.io.IOException;

import org.json.simple.JSONObject;

import towise.Api;

public class Main {
	public static void main(String[] args) {
		Api api = new Api();
		api.AppId = "1";
		api.AppKey = "1";
		try {
			JSONObject obj = api.FaceDetect("https://www.cutislaserclinics.com/wp-content/uploads/2018/02/Achieve-a-Youthful-V-Shape-Face.jpg");
			System.out.println(obj);
			
			//JSONObject obj = api.EmotionDetect("https://www.cutislaserclinics.com/wp-content/uploads/2018/02/Achieve-a-Youthful-V-Shape-Face.jpg");
			//System.out.println(obj);
			
			//JSONObject obj = api.BodyDetect("https://www.cutislaserclinics.com/wp-content/uploads/2018/02/Achieve-a-Youthful-V-Shape-Face.jpg");
			//System.out.println(obj);
			
			//JSONObject obj = api.FaceComparing("https://www.cutislaserclinics.com/wp-content/uploads/2018/02/Achieve-a-Youthful-V-Shape-Face.jpg");
			//System.out.println(obj);

		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
