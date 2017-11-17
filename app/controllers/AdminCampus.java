package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;


public class AdminCampus extends Controller{
	
	@Before
	static void verificar()
	{
		String usuario = session.get("usuario");
		if(usuario == null)
		{
			Login.index();
		}
		if(getUser().nivel.numero!=3)
			Login.redirect();
	}
	
	public static Usuario getUser()
	{
		Long id = Long.parseLong(session.get("usuario"));
    	Usuario user = Usuario.findById(id);
    	return user;
	}

    public static void index() {
    	Usuario user = getUser();
    	//Solo heroes del mismo campus
    	List<Usuario> usuarios = Usuario.find("nivel.numero = ?1 and campus.id = ?2",5,user.campus.id).fetch();
    	render(usuarios);
    }
    
    public static void crearUsuario()
    {
    	List<Campus> campuses = Campus.find("id = ?1",getUser().campus.id).fetch();
    	List<Nivel> niveles = Nivel.find("numero >= ?1",5).fetch();
    	render(campuses,niveles);
    }
    
    public static void usuariosPuntos()
    {
    	Usuario user = getUser();
    	//Solo heroes del mismo campus
    	List<Usuario> usuarios = Usuario.find("nivel.numero = ?1 and campus.id = ?2",5,user.campus.id).fetch();
    	render(usuarios);    	
    }
        
    
    public static void newUser(String nombre, String apellido, String password, int nivel, Long campus)
    {
    	Usuario user = new Usuario(nombre,apellido,password,nivel,campus);    	
    	render(user);
    }
    
    public static void removeUser(Long id)
    {
    	Usuario user = Usuario.findById(id);
    	render(user);
    	
    }    
    
    public static void eliminarUsuario(Long id)
    {
    	Usuario user = Usuario.findById(id);
    	List<Registros> reg = Registros.find("hero.id = ?1",id).fetch();
    	for(Registros r:reg)
    	{
    		r.delete();
    	}
    	user.delete();
    	renderJSON(user);
    }
    
    public static void editarUsuario(Long id)
    {
    	Usuario user = Usuario.findById(id);
    	render(user);
    }
    
        
    public static void modificarUsuario(Long id,String nombre, String apellido)
    {
    	Usuario user = Usuario.findById(id);
    	user.nombre = nombre;
    	user.apellido = apellido;
    	user.save();
    	renderJSON(user);    	
    }
    
    public static void cambiarPass(Long id, String newpass)
    {
    	Usuario user = Usuario.findById(id);
    	user.cambiarContrasena(newpass);    	
    	
    }
    
    public static void editarPuntos(Long id)
    {
    	Usuario user = Usuario.findById(id);
    	render(user);
    }
    
    public static void sumarPuntos(Long id)
    {
    	Usuario heroe = Usuario.findById(id);
    	Usuario admin = getUser();
    	heroe.sumarPuntos(10, "Asignacion regular", admin);
    	renderJSON(heroe);    	
    }
    
    public static void registros()
    {
    	List<Registros> registros = Registros.find("activo = ?1 and admin.id = ?2",true,getUser().id).fetch();;
    	render(registros);
    	
    }
    
    public static void detalleRegistro(Long id)
    {
    	Registros registro = Registros.findById(id);
    	render (registro);
    }
    
    public static void removeRegister(Long id)
    {
    	Registros registro = Registros.findById(id);
    	render(registro);
    }
    
    public static void eliminarRegistro(Long id)
    {
    	Registros registro = Registros.findById(id);
    	Usuario user = Usuario.findById(registro.hero.id);
    	user.restarPuntos(registro.cantidad, registro.id);
    	renderJSON(registro);
    	
    }
    
    public static void autoEditar()
    {
    	
    	editarUsuario(getUser().id);
    }

}
