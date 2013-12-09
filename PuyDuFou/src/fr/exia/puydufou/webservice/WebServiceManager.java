package fr.exia.puydufou.webservice;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class WebServiceManager {

	private String baseUrl = "http://dbcorp.fr/webservice/poi.json";

	public boolean isReachable() throws UnknownHostException, IOException
	{
		if(InetAddress.getByName(this.baseUrl).isReachable(1000))
		{
			return true;
		}
		return false;
	}
}
