package towise;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.graphics.Bitmap;
/*
 * TOWISE API v1.0
 * 
 * https://www.towise.io
 * 
 * */
public class Api 
{
	public String AppKey = "1";
	public String AppId = "1";
	
	private String url = "https://towise.io/towise-api/v1";
	private static int IMG_WIDTH = 400;
	private static int IMG_HEIGHT = 400;
	ByteArrayOutputStream tmp=new ByteArrayOutputStream();
	
    public JSONObject FaceDetect(BufferedImage originalImage)
    {
    	try {
			MultipartUtility multiple = new MultipartUtility(this.url, "UTF-8");
			multiple.addFormField("getface", "1");
			multiple.addFormField("apitxt", "getface");
			multiple.addFormField("AppKey", this.AppKey);
			multiple.addFormField("AppId", this.AppId);
			File f = File.createTempFile("tmp","jpg",null);
			ImageIO.write(originalImage, "jpg", f);
			multiple.addFilePart("upload",f);
			String response = multiple.finish();
			f.delete();
			
			JSONParser parser = new JSONParser();
	        JSONObject json;
			
	        try 
	        {
				json = (JSONObject) parser.parse(response);
				return json;
			} 
	        catch (ParseException e) 
	        {
				System.out.println("Hata:"+response);
			}
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    
    public JSONObject FaceDetect(Bitmap originalImage)
    {
    	
    	try {
			MultipartUtility multiple = new MultipartUtility(this.url, "UTF-8");
			multiple.addFormField("getface", "1");
			multiple.addFormField("apitxt", "getface");
			multiple.addFormField("AppKey", this.AppKey);
			multiple.addFormField("AppId", this.AppId);
			File f = File.createTempFile("tmp","jpg",null);
			FileOutputStream out = new FileOutputStream(f);
			originalImage.compress(Bitmap.CompressFormat.JPEG, 90, out);
			multiple.addFilePart("upload",f);
			String response = multiple.finish();
			f.delete();
			
			JSONParser parser = new JSONParser();
	        JSONObject json;
			
	        try 
	        {
				json = (JSONObject) parser.parse(response);
				return json;
			} 
	        catch (ParseException e) 
	        {
				System.out.println("Hata:"+response);
			}
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    public JSONObject FaceDetect(String originalImage)
    {
    	try {
			MultipartUtility multiple = new MultipartUtility(this.url, "UTF-8");
			multiple.addFormField("getface", "1");
			multiple.addFormField("apitxt", "getface");
			multiple.addFormField("AppKey", this.AppKey);
			multiple.addFormField("AppId", this.AppId);
			multiple.addFormField("image_url",originalImage);
			String response = multiple.finish();
			JSONParser parser = new JSONParser();
	        JSONObject json;
			try {
				json = (JSONObject) parser.parse(response);
				return json;
			} catch (ParseException e) {
				System.out.println(response);
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
    }
    
    
    public JSONObject AddPerson(String namesurname, int id)
    {
    	try {
			MultipartUtility multiple = new MultipartUtility(this.url, "UTF-8");
			multiple.addFormField("AddPerson", "1");
			multiple.addFormField("id", String.valueOf(id));
			multiple.addFormField("name",namesurname);
			multiple.addFormField("AppKey", this.AppKey);
			multiple.addFormField("AppId", this.AppId);
			multiple.addFormField("apitxt", "AddPerson");
			String response = multiple.finish();
			
			JSONParser parser = new JSONParser();
	        JSONObject json;
	        try 
	        {
				json = (JSONObject) parser.parse(response);
				return json;
			} 
	        catch (ParseException e) 
	        {
				System.out.println("Hata:"+response);
			}
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    
    public JSONObject AddFace(BufferedImage originalImage, int person_id)
    {
    	try {
			MultipartUtility multiple = new MultipartUtility(this.url, "UTF-8");
			multiple.addFormField("AddFace", "1");
			multiple.addFormField("apitxt", "AddFace");
			multiple.addFormField("AppKey", this.AppKey);
			multiple.addFormField("AppId", this.AppId);
			multiple.addFormField("id", String.valueOf(person_id));
			File f = File.createTempFile("tmp","jpg",null);
			ImageIO.write(originalImage, "jpg", f);
			multiple.addFilePart("upload",f);
			String response = multiple.finish();
			f.delete();
			
			JSONParser parser = new JSONParser();
	        JSONObject json;
			
	        try 
	        {
				json = (JSONObject) parser.parse(response);
				return json;
			} 
	        catch (ParseException e) 
	        {
				System.out.println("Hata:"+response);
			}
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    
   
    
	private static BufferedImage resizeImage(BufferedImage originalImage, int type)
	{
		if (originalImage.getWidth() > IMG_WIDTH) 
		{
	        IMG_HEIGHT = (IMG_WIDTH * originalImage.getHeight()) / originalImage.getWidth();
	    }

	    // then check if we need to scale even with the new height
	    if (IMG_HEIGHT > originalImage.getHeight()) 
	    {
	    	IMG_WIDTH = (IMG_HEIGHT * originalImage.getWidth()) / originalImage.getHeight();
	    }

		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
		
		return resizedImage;
    }
	
}
