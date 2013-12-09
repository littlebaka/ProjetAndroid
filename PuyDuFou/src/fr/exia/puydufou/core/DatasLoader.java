package fr.exia.puydufou.core;

import java.io.IOException;
import java.net.UnknownHostException;

import fr.exia.puydufou.webservice.WebServiceManager;

public class DatasLoader implements DatasLoadable {

	
	private WebServiceManager webServiceManager;
	
	public DatasLoader() {
		this.webServiceManager = new WebServiceManager();
	}
	
	@Override
	public boolean isReachableWebservice() {
		try {
			if(this.webServiceManager.isReachable())
			{
				return true;
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
