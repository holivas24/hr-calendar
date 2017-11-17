package controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import models.*;
import play.libs.Codec;
import play.mvc.Before;
import play.mvc.Controller;

public class Login extends Controller{
	
	public static void index()
	{	
		render();
	}
	
	public static void log(String username, String contrasenia)
	{
		Usuario user = Usuario.find("username = ?1 and password = ?2",username, Codec.hexMD5(contrasenia)).first();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		//superuser
		if(user != null)
		{
			session.put("usuario",user.id);
			System.out.println(dateFormat.format(cal.getTime()).toString()+" "+timeFormat.format(cal.getTime()).toString()+ " Usuario: "+user.username+" ha accedido");
			redirect();
		}
		
		else		
		{
			System.out.println(dateFormat.format(cal.getTime()).toString()+" "+timeFormat.format(cal.getTime()).toString()+ " Intento fallido con username: "+username);
			Login.index();
		}
	}
	
	public static void logout()
	{
		session.clear();
		Login.index();
	}
	
	public static void redirect()
	{
		switch(getUser().nivel.numero)
		{
		case 1://Superusuario
			Application.index();
			break;
		case 2://Administrador global
			AdminGlobal.index();
			break;
		case 3://Administrador de campus
			AdminCampus.index();
			break;
		case 4://Usuario
		case 5://Heroe
		default:
			Login.logout();
		}
	}
	
	public static Usuario getUser()
	{
		Long id = Long.parseLong(session.get("usuario"));
    	Usuario user = Usuario.findById(id);
    	return user;
	}

}
