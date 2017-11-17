package models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import play.db.jpa.Model;
import play.libs.Codec;

@Entity
public class Usuario extends Model{
	public String nombre;
	public String apellido;
	public String username;
	public String password;
	@OneToOne
	public Sucursal sucursal;
	@OneToOne
	public Nivel nivel;
	
	public Usuario() {}
	
	
	public Usuario(String nombre, String apellido, String password, int nivel, Long sucursal)
	{
		this.nombre = nombre;
		this.apellido = apellido;
		this.nivel= Nivel.find("numero = ?1",nivel).first();
		this.password = Codec.hexMD5(password);
		String user = (this.nombre.substring(0, 2)+this.apellido.substring(0, 3)).toLowerCase()+((int)(Math.random()*100));
		this.username = user.replace(" ","");
		this.sucursal = Sucursal.findById(sucursal); 
		this.save();
	}
	
	public void cambiarContrasena(String newPass)
	{
		this.password = Codec.hexMD5(newPass);
		this.save();		
	}
	
}
