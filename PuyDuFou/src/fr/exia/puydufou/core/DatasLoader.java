package fr.exia.puydufou.core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentResolver;
import android.content.ContentValues;

import fr.exia.puydufou.provider.HoraireContentProvider;
import fr.exia.puydufou.provider.MenuContentProvider;
import fr.exia.puydufou.provider.NoteContentProvider;
import fr.exia.puydufou.provider.NoteServiceContentProvider;
import fr.exia.puydufou.provider.NoteSpectacleContentProvider;
import fr.exia.puydufou.provider.ParcContentProvider;
import fr.exia.puydufou.provider.ServiceContentProvider;
import fr.exia.puydufou.provider.Service_MenuContentProvider;
import fr.exia.puydufou.provider.SharedInformation.Menu;
import fr.exia.puydufou.provider.SharedInformation.Note;
import fr.exia.puydufou.provider.SharedInformation.NoteService;
import fr.exia.puydufou.provider.SharedInformation.NoteSpectacle;
import fr.exia.puydufou.provider.SharedInformation.Parc;
import fr.exia.puydufou.provider.SharedInformation.Horaire;
import fr.exia.puydufou.provider.SharedInformation.Service;
import fr.exia.puydufou.provider.SharedInformation.Service_Menu;
import fr.exia.puydufou.provider.SharedInformation.Spectacle;
import fr.exia.puydufou.provider.SharedInformation.Spectacle_Horaire;
import fr.exia.puydufou.provider.SharedInformation.TypeService;
import fr.exia.puydufou.provider.SpectacleContentProvider;
import fr.exia.puydufou.provider.Spectacle_HoraireContentProvider;
import fr.exia.puydufou.provider.TypeServiceContentProvider;
import fr.exia.puydufou.webservice.WebServiceManager;

public class DatasLoader implements DatasLoadable {

	
	private WebServiceManager webServiceManager;
	
	private final String TAG_PARC = "parc";
	private final String TAG_HORAIRE = "horaire";
	private final String TAG_MENU ="menus";
	private final String TAG_SERVICE ="service";
	private final String TAG_SERVICE_MENU ="service_menus";
	private final String TAG_SPECTACLE ="spectacle";
	private final String TAG_SPECTACLE_HORAIRE ="spectacle_horaire";
	private final String TAG_TYPE_SERVICE ="type_service";
	private final String TAG_NOTE_SERVICE ="note service";
	private final String TAG_NOTE_SPECTACLE ="note spectacle";
	private final String TAG_NOTE ="note";
	
	
	public DatasLoader() {
		this.webServiceManager = new WebServiceManager();
	}
	
	@Override
	public void getAndStoreParcInformation(ContentResolver contentResolver) {
		
		try{
			JSONArray jsonArray = this.webServiceManager.getParcInformation().getJSONArray(TAG_PARC);
			
			
			for(int i = 0; i < jsonArray.length(); i++){
			
				JSONObject p = jsonArray.getJSONObject(i);
				
				ContentValues parc = new ContentValues();
				parc.put(Parc.ID_PARC, p.getString(Parc.ID_PARC));
				parc.put(Parc.NOM_PARC, p.getString(Parc.NOM_PARC));
				parc.put(Parc.INFO_PARC, p.getString(Parc.INFO_PARC));
				parc.put(Parc.VERSION_PARC, p.getString(Parc.VERSION_PARC));
				contentResolver.insert(ParcContentProvider.CONTENT_URI, parc);
				parc.clear();
			}
			
		}catch(JSONException e){
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void getAndStoreHoraireInformation(ContentResolver contentResolver) {
		try{
			JSONArray jsonArray = this.webServiceManager.getHoraireInformation().getJSONArray(TAG_HORAIRE);
			
			for(int i = 0; i < jsonArray.length(); i++){
			
				JSONObject p = jsonArray.getJSONObject(i);
				
				ContentValues schedule = new ContentValues();
				schedule.put(Horaire.ID_HORAIRE, p.getString(Horaire.ID_HORAIRE));
				schedule.put(Horaire.HORAIRE, p.getString(Horaire.HORAIRE));
				contentResolver.insert(HoraireContentProvider.CONTENT_URI, schedule);
				schedule.clear();
			}
			
		}catch(JSONException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void getAndStoreMenuInformation(ContentResolver contentResolver) {
		try{
			JSONArray jsonArray = this.webServiceManager.getMenuInformation().getJSONArray(TAG_MENU);
			
			for(int i = 0; i < jsonArray.length(); i++){
				
				JSONObject p = jsonArray.getJSONObject(i);
				
				ContentValues menu = new ContentValues();
				menu.put(Menu.ID_MENU, p.getString(Menu.ID_MENU));
				menu.put(Menu.NOM_MENU, p.getString(Menu.NOM_MENU));
				menu.put(Menu.DESCRIPTION_MENU, p.getString(Menu.DESCRIPTION_MENU));
				menu.put(Menu.PRIX_MENU, p.getString(Menu.PRIX_MENU));
				contentResolver.insert(MenuContentProvider.CONTENT_URI, menu);
			}
			
		}catch(JSONException e){
			e.printStackTrace();
		}
	}

	@Override
	public void getAndStoreServiceInformation(ContentResolver contentResolver) {
		try{
			JSONArray jsonArray = this.webServiceManager.getServiceInformation().getJSONArray(TAG_SERVICE);
			
			for(int i = 0; i < jsonArray.length(); i++){
				
				JSONObject p = jsonArray.getJSONObject(i);
				
				ContentValues menu = new ContentValues();
				menu.put(Service.ID_SERVICE, p.getString(Service.ID_SERVICE));
				menu.put(Service.ID_TYPE_SERVICE, p.getString(Service.ID_TYPE_SERVICE));
				menu.put(Service.ID_PARC, p.getString(Service.ID_PARC));
				menu.put(Service.NOM_SERVICE, p.getString(Service.NOM_SERVICE));
				menu.put(Service.INFORMATION, p.getString(Service.INFORMATION));
				menu.put(Service.LONGITUDE, p.getString(Service.LONGITUDE));
				menu.put(Service.LATITUDE, p.getString(Service.LATITUDE));
				contentResolver.insert(ServiceContentProvider.CONTENT_URI, menu);
			}
			
		}catch(JSONException e){
			e.printStackTrace();
		}		
	}

	@Override
	public void getAndStoreServiceMenuInformation(
			ContentResolver contentResolver) {
		try{
			JSONArray jsonArray = this.webServiceManager.getServiceMenuInformation().getJSONArray(TAG_SERVICE_MENU);
			
			for(int i = 0; i < jsonArray.length(); i++){
				
				JSONObject p = jsonArray.getJSONObject(i);
				
				ContentValues service_menu = new ContentValues();
				service_menu.put(Service_Menu.ID_SERVICE, p.getString(Service_Menu.ID_SERVICE));
				service_menu.put(Service_Menu.ID_MENU, p.getString(Service_Menu.ID_MENU));
				contentResolver.insert(Service_MenuContentProvider.CONTENT_URI, service_menu);
			}
			
		}catch(JSONException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void getAndStoreSpectacleInformation(ContentResolver contentResolver) {
		try{
			JSONArray jsonArray = this.webServiceManager.getSpectacleInformation().getJSONArray(TAG_SPECTACLE);
			
			for(int i = 0; i < jsonArray.length(); i++){
				
				JSONObject p = jsonArray.getJSONObject(i);
				
				ContentValues spectacle = new ContentValues();
				spectacle.put(Spectacle.ID_SPECTACLE, p.getString(Spectacle.ID_SPECTACLE));
				spectacle.put(Spectacle.ID_PARC, p.getString(Spectacle.ID_PARC));
				spectacle.put(Spectacle.DUREE, p.getString(Spectacle.DUREE));
				spectacle.put(Spectacle.DATE_CREATION, p.getString(Spectacle.DATE_CREATION));
				spectacle.put(Spectacle.NB_ACTEUR, p.getString(Spectacle.NB_ACTEUR));
				spectacle.put(Spectacle.EVEN_HIST, p.getString(Spectacle.EVEN_HIST));
				spectacle.put(Spectacle.INFORMATION_SPECTACLE, p.getString(Spectacle.INFORMATION_SPECTACLE));
				spectacle.put(Spectacle.NOM_SPECTACLE, p.getString(Spectacle.NOM_SPECTACLE));
				spectacle.put(Spectacle.LONGITUDE, p.getString(Spectacle.LONGITUDE));
				spectacle.put(Spectacle.LATITUDE, p.getString(Spectacle.LATITUDE));
				contentResolver.insert(SpectacleContentProvider.CONTENT_URI, spectacle);
			}
			
		}catch(JSONException e){
			e.printStackTrace();
		}
	}

	@Override
	public void getAndStoreSpectacleHoraireInformation(
			ContentResolver contentResolver) {
		try{
			JSONArray jsonArray = this.webServiceManager.getSpectacleHoraireInformation().getJSONArray(TAG_SPECTACLE_HORAIRE);
			
			for(int i = 0; i < jsonArray.length(); i++){
				
				JSONObject p = jsonArray.getJSONObject(i);
				
				ContentValues spectacle_horaire = new ContentValues();
				spectacle_horaire.put(Spectacle_Horaire.ID_SPECTACLE, p.getString(Spectacle_Horaire.ID_SPECTACLE));
				spectacle_horaire.put(Spectacle_Horaire.ID_HORAIRE, p.getString(Spectacle_Horaire.ID_HORAIRE));
				contentResolver.insert(Spectacle_HoraireContentProvider.CONTENT_URI, spectacle_horaire);
			}
			
		}catch(JSONException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void getAndStoreTypeServiceInformation(
			ContentResolver contentResolver) {
		try{
			JSONArray jsonArray = this.webServiceManager.getTypeServiceInformation().getJSONArray(TAG_TYPE_SERVICE);
			
			for(int i = 0; i < jsonArray.length(); i++){
				
				JSONObject p = jsonArray.getJSONObject(i);
				
				ContentValues typeService = new ContentValues();
				typeService.put(TypeService.ID_TYPE_SERVICE, p.getString(TypeService.ID_TYPE_SERVICE));
				typeService.put(TypeService.TYPE_SERVICE, p.getString(TypeService.TYPE_SERVICE));
				contentResolver.insert(TypeServiceContentProvider.CONTENT_URI, typeService);
			}
			
		}catch(JSONException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void getAndStoreNoteServiceInformation(
			ContentResolver contentResolver) {
		try{
			JSONArray jsonArray = this.webServiceManager.getNoteServiceInformation().getJSONArray(TAG_NOTE_SERVICE);
			
			for(int i = 0; i < jsonArray.length(); i++){
				
				JSONObject p = jsonArray.getJSONObject(i);
				
				ContentValues noteService = new ContentValues();
				noteService.put(NoteService.ID_NOTE, p.getString(NoteService.ID_NOTE));
				noteService.put(NoteService.ID_SERVICE, p.getString(NoteService.ID_SERVICE));
				contentResolver.insert(NoteServiceContentProvider.CONTENT_URI, noteService);
			}
			
		}catch(JSONException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void getAndStoreNoteSpectacleInformation(
			ContentResolver contentResolver) {
		try{
			JSONArray jsonArray = this.webServiceManager.getNoteSpectacleInformation().getJSONArray(TAG_NOTE_SPECTACLE);
			
			for(int i = 0; i < jsonArray.length(); i++){
				
				JSONObject p = jsonArray.getJSONObject(i);
				
				ContentValues noteSpectacle = new ContentValues();
				noteSpectacle.put(NoteSpectacle.ID_SPECTACLE, p.getString(NoteSpectacle.ID_SPECTACLE));
				noteSpectacle.put(NoteSpectacle.ID_NOTE, p.getString(NoteSpectacle.ID_NOTE));
				contentResolver.insert(NoteSpectacleContentProvider.CONTENT_URI, noteSpectacle);
			}
			
		}catch(JSONException e){
			e.printStackTrace();
		}
	}

	@Override
	public void getAndStoreNoteInformation(ContentResolver contentResolver) {
		try{
			JSONArray jsonArray = this.webServiceManager.getNoteInformation().getJSONArray(TAG_NOTE);
			
			for(int i = 0; i < jsonArray.length(); i++){
				
				JSONObject p = jsonArray.getJSONObject(i);
				
				ContentValues menu = new ContentValues();
				menu.put(Note.ID_NOTE, p.getString(Note.ID_NOTE));
				menu.put(Note.NOTE, p.getString(Note.NOTE));
				contentResolver.insert(NoteContentProvider.CONTENT_URI, menu);
			}
			
		}catch(JSONException e){
			e.printStackTrace();
		}
	}
	
	

}
