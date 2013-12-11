package fr.exia.puydufou.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class WebServiceManager {

	private String baseUrl = "http://10.33.126.7/ProjectAndroid/WebService.php?req=";
	
	public JSONObject getParcInformation()
	{
		return this.getJsonObject(this.openHttpConnection("[{\"req\":\"getParc\"}]"));
	}
	
	public JSONObject getHoraireInformation()
	{
		return this.getJsonObject(this.openHttpConnection("[{\"req\":\"getHoraire\"}]"));
	}
	
	public JSONObject getMenuInformation()
	{
		return this.getJsonObject(this.openHttpConnection("[{\"req\":\"getMenus\"}]"));
	}
	
	public JSONObject getServiceInformation()
	{
		return this.getJsonObject(this.openHttpConnection("[{\"req\":\"getService\"}]"));
	}
	
	public JSONObject getServiceMenuInformation()
	{
		return this.getJsonObject(this.openHttpConnection("[{\"req\":\"getService_menus\"}]"));
	}
	
	public JSONObject getSpectacleInformation()
	{
		return this.getJsonObject(this.openHttpConnection("[{\"req\":\"getSpectacle\"}]"));
	}
	
	public JSONObject getSpectacleHoraireInformation()
	{
		return this.getJsonObject(this.openHttpConnection("[{\"req\":\"getSpectacle_horaire\"}]"));
	}
	
	public JSONObject getTypeServiceInformation()
	{
		return this.getJsonObject(this.openHttpConnection("[{\"req\":\"getType_service\"}]"));
	}
	
	public JSONObject getNoteServiceInformation()
	{
		return this.getJsonObject(this.openHttpConnection("[{\"req\":\"getNoteService\"}]"));
	}
	
	public JSONObject getNoteSpectacleInformation()
	{
		return this.getJsonObject(this.openHttpConnection("[{\"req\":\"getNoteSpectacle\"}]"));
	}
	
	public JSONObject getNoteInformation()
	{
		return this.getJsonObject(this.openHttpConnection("[{\"req\":\"getNote\"}]"));
	}
	
	public JSONObject setNoteService(int idNote, int idService)
	{
		return this.getJsonObject(this.openHttpConnection("[{\"req\":\"setNoteService\"},{\"id_note\":"+idNote+"},{\"id_service\":"+idService+"}]"));
	}
	
	public JSONObject setNoteSpectacle(int idNote, int idSpectacle)
	{
		return this.getJsonObject(this.openHttpConnection("[{\"req\":\"setNoteSpectacle\"},{\"id_note\":"+idNote+"},{\"id_spectacle\":"+idSpectacle+"}]"));
	}

 	private InputStream openHttpConnection(String urlParam)
	{
 		InputStream inputStream = null;
		try
		{
			HttpClient httpClient = new DefaultHttpClient();
			URI uri = null;
			
			if(!urlParam.isEmpty())
			{
				uri = new URI(this.baseUrl + URLEncoder.encode(urlParam, "UTF-8"));
			}
			else
			{
				uri = new URI(this.baseUrl);
			}
			
			HttpPost httpPost = new HttpPost(uri);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			inputStream = httpEntity.getContent();
		}catch(Exception e){
			e.printStackTrace();
		}
		return inputStream;
	}
	
	private JSONObject getJsonObject(InputStream inputStream)
	{
		JSONObject jsonObject = null;
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			
			while((line = reader.readLine()) != null)
			{
				stringBuilder.append(line + "\n");
			}
			
			inputStream.close();
			String jsonString = stringBuilder.toString();
			
			jsonObject = new JSONObject(jsonString);
			
		}catch(JSONException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
}
