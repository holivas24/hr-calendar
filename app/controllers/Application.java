package controllers;


import models.*;
import play.mvc.Controller;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Application extends Controller{
	
	public static void index()
	{
		List<UserToRender> entrance = new ArrayList<UserToRender>();
		List<UserToRender> birth = new ArrayList<UserToRender>();
				
		//get current date time with Calendar()
		Calendar cal = Calendar.getInstance();
		int mes = cal.get(Calendar.MONTH);
		int dia = cal.getMaximum(Calendar.DAY_OF_MONTH);
		List<Usuario> usuarios = Usuario.find("month(fecIng) = ?1 and nivel.numero > ?2", mes,1).fetch();
		
		
		for(Usuario ing:usuarios){
			UserToRender temp = new UserToRender(ing);
			if(temp.years > 0)
				entrance.add(temp);
		}
		usuarios = Usuario.find("month(fecNac) = ?1 and nivel.numero > ?2", mes,1).fetch();
		
		for(Usuario bd:usuarios)
			birth.add(new UserToRender(bd));
		
		
		render(entrance, birth, mes,dia);
	}
	
	public static void imagenes()
	{
		List<String> imagenes = new ArrayList<String>();
		File folder = new File("/home/holivas/workspace/hr-calendar/public/slides");
		File[] listOfFiles = folder.listFiles();

		    for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		        imagenes.add(listOfFiles[i].getName());
		      } else if (listOfFiles[i].isDirectory()) {
		        
		      }
		    }
		    
	    renderJSON(imagenes);
	}
}
