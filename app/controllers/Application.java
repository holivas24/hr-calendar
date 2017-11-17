package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class Application extends Controller {
	
	
	@Before
	static void verificar()
	{
		String usuario = session.get("usuario");
		if(usuario == null)
		{
			Login.index();
		}
		if(getUser().nivel.numero!=1)
			Login.redirect();
	}
	
	public static Usuario getUser()
	{
		Long id = Long.parseLong(session.get("usuario"));
    	Usuario user = Usuario.findById(id);
    	return user;
	}

    public static void index() {
    	//List<Usuario> usuarios = Usuario.find("nivel.numero > ?1",getUser().nivel.numero).fetch();//Solo menores
    	List<Usuario> usuarios = Usuario.find("nivel.numero > 1").fetch();//todos
    	render(usuarios);
    }
    
    public static void crearUsuario()
    {
    	List<Sucursal> sucursales = Sucursal.findAll();
    	List<Nivel> niveles = Nivel.findAll();
    	render(sucursales,niveles);
    }
    
    public static void usuariosPuntos()
    {
    	List<Usuario> usuarios = Usuario.find("nivel.numero = ?1",5).fetch();
    	render(usuarios);    	
    }
        
    
    public static void newUser(String nombre, String apellido, String password, int nivel, Long sucursal, String nac, String ing)
    {
    	java.sql.Date fechaNac = java.sql.Date.valueOf(nac);
    	java.sql.Date fechaIng = java.sql.Date.valueOf(ing);
    	Usuario user = new Usuario(nombre,apellido,password,nivel,sucursal,fechaNac,fechaIng);    	
    	System.out.println(user.toString());
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
    
    public static void newsucursal(String nombre)
    {
    	Sucursal sucursal = new Sucursal(nombre);
    	renderJSON(sucursal);
    }
    
    public static void newLevel(String nombre, int numero)
    {
    	Nivel nivel = new Nivel(nombre, numero);
    	renderJSON(nivel);
    }
    
    public static void editarUsuario(Long id)
    {
    	Usuario user = Usuario.findById(id);
    	render(user);
    }
    
        
    public static void modificarUsuario(Long id,String nombre, String apellido, String nivel, String sucursal,String nac, String ing)
    {
    	java.sql.Date fechaNac = java.sql.Date.valueOf(nac);
    	java.sql.Date fechaIng = java.sql.Date.valueOf(ing);
    	Usuario user = Usuario.findById(id);
    	user.nombre = nombre;
    	user.apellido = apellido;
    	user.nivel = Nivel.find("nombre = ?1", nivel).first();
    	user.sucursal = Sucursal.find("nombre = ?1",sucursal).first();
    	user.fecNac = fechaNac;
    	user.fecIng = fechaIng;
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
    
    public static void registros()
    {
    	List<Registros> registros = Registros.find("activo = ?1",true).fetch();;
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
    
    public static void autoEditar()
    {
    	
    	editarUsuario(getUser().id);
    }
}