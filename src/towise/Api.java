package towise;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
	private String postString = "";
	
	/*Api bağlantı sağlar.*/
	private HttpURLConnection httpCo() throws MalformedURLException, IOException
	{
		HttpURLConnection httpcon = (HttpURLConnection) ((new URL(url).openConnection()));
		byte[] postData = this.postString.getBytes( StandardCharsets.UTF_8 );
		httpcon.setDoOutput(true);
		httpcon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		httpcon.setRequestProperty("Content-Length", Integer.toString( postData.length ));
		httpcon.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		httpcon.setRequestMethod("POST");
		try( DataOutputStream wr = new DataOutputStream( httpcon.getOutputStream())) {
			wr.write( postData );
		}
		return httpcon;
	}
	
	private JSONObject httpResponse(HttpURLConnection httpcon) throws IOException
	{
		int status = httpcon.getResponseCode();
		System.out.println("Status:"+status);
		switch (status) 
		{
	        case 200:
	           BufferedReader br = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
	           StringBuilder sb = new StringBuilder();
	           String line;
	           while ((line = br.readLine()) != null) {
	                sb.append(line+"\n");
	           }
	           br.close();
	           System.out.println(sb.toString());
	           JSONParser parser = new JSONParser();
	           JSONObject json;
			try {
				json = (JSONObject) parser.parse(sb.toString());
				return json;
			} catch (ParseException e) {
				e.printStackTrace();
			}
	           return null;
	        case 403:
	        	System.out.println("403 error");
	        	System.out.println(httpcon.getErrorStream());
	        	return null;
		}
		return null;
	}
	
	/*Verilen resimdeki yüzleri algılar.*/
	public JSONObject FaceDetect(String imageurl) throws IOException
	{
		this.postString = "imgurl="+imageurl+"&apitxt=getface";
		HttpURLConnection httpcon = this.httpCo();
		httpcon.connect();
		return httpResponse(httpcon);
	}
	
	/*Verilen resimdeki yüzleri ve duygularını algılar.*/
	public JSONObject EmotionDetect(String imageurl) throws IOException
	{
		this.postString = "imgurl="+imageurl+"&apitxt=age3";
		HttpURLConnection httpcon = this.httpCo();
		httpcon.connect();
		return httpResponse(httpcon);
	}
	
	/*Verilen resimdeki vücutları algılar.*/
	public JSONObject BodyDetect(String imageurl) throws IOException
	{
		this.postString = "imgurl="+imageurl+"&apitxt=bodies";
		HttpURLConnection httpcon = this.httpCo();
		httpcon.connect();
		return httpResponse(httpcon);
	}
	
	/*Verilen resimdeki yüzlerin benzerlerini verir.*/
	public JSONObject FaceComparing(String imageurl) throws IOException
	{
		this.postString = "imgurl="+imageurl+"&apitxt=comparing";
		HttpURLConnection httpcon = this.httpCo();
		httpcon.connect();
		return httpResponse(httpcon);
	}
	
	
	/*Veritabanına kişileri listeler*/
	public JSONObject GetAllPerson() throws IOException
	{
		this.postString = "apitxt=getallperson";
		HttpURLConnection httpcon = this.httpCo();
		httpcon.connect();
		return httpResponse(httpcon);
	}
	
	/*Veritabanına belli kişiyi getirir*/
	public JSONObject GetPerson() throws IOException
	{
		this.postString = "apitxt=getperson";
		HttpURLConnection httpcon = this.httpCo();
		httpcon.connect();
		return httpResponse(httpcon);
	}
	
	
	/*Veritabanına kişi ekler*/
	public JSONObject AddPerson(int person_id, String person_name) throws IOException
	{
		this.postString = "person_id="+person_id+"&persone_name="+person_name+"&apitxt=addperson";
		HttpURLConnection httpcon = this.httpCo();
		httpcon.connect();
		return httpResponse(httpcon);
	}
	
	
	/*Veritabanına kişi siler*/
	public JSONObject RemovePerson(int person_id, String person_name) throws IOException
	{
		this.postString = "person_id="+person_id+"&persone_name="+person_name+"&apitxt=addperson";
		HttpURLConnection httpcon = this.httpCo();
		httpcon.connect();
		return httpResponse(httpcon);
	}
	
	
	/*Veritabanına kişiye bir resim tanımlar*/
	public JSONObject AddFace(int person_id, String imgurl) throws IOException
	{
		this.postString = "person_id="+person_id+"&url="+imgurl+"&apitxt=addface";
		HttpURLConnection httpcon = this.httpCo();
		httpcon.connect();
		return httpResponse(httpcon);
	}
	
	/*Persone tanımlanmış tüm yüzleri siler*/
	public JSONObject RemoveAllFace(int person_id) throws IOException
	{
		this.postString = "person_id="+person_id+"&apitxt=addface";
		HttpURLConnection httpcon = this.httpCo();
		httpcon.connect();
		return httpResponse(httpcon);
	}
	
	/*Persone tanımlanmış face siler*/
	public JSONObject RemoveFace(int face_id) throws IOException
	{
		this.postString = "person_id="+face_id+"&apitxt=addface";
		HttpURLConnection httpcon = this.httpCo();
		httpcon.connect();
		return httpResponse(httpcon);
	}
	
}
