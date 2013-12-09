package fr.exia.puydufou.webservice;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class WebServiceManager {

	private String baseUrl = "http://10.33.127.83/ProjectAndroid/WebService.php?req=";
	
	private final String GET_PARC = "[{\"req\":\"getParc\"}]";
	
	public JSONObject getParcInformation()
	{
		return this.getJsonObject(this.openHttpConnection(GET_PARC));
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
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonObject;
	}
}
