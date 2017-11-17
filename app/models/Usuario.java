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
	public Campus campus;
	@OneToOne
	public Nivel nivel;
	public long puntos;
	
	public Usuario() {}
	
	
	public Usuario(String nombre, String apellido, String password, int nivel, Long campus)
	{
		this.nombre = nombre;
		this.apellido = apellido;
		this.nivel= Nivel.find("numero = ?1",nivel).first();
		this.password = Codec.hexMD5(password);
		this.puntos = 0;
		String user = (this.nombre.substring(0, 2)+this.apellido.substring(0, 3)).toLowerCase()+((int)(Math.random()*100));
		this.username = user.replace(" ","");
		this.campus = Campus.findById(campus); 
		this.save();
	}
	
	public void cambiarContrasena(String newPass)
	{
		this.password = Codec.hexMD5(newPass);
		this.save();		
	}
	
	public void sumarPuntos(long puntos,String nota, Usuario admin)
	{
		this.puntos += puntos;
		Registros registro = new Registros(nota, puntos, this, admin);
		registro.save();
		this.save();
	}
	
	public void restarPuntos(long puntos, Long registro)
	{
			this.puntos -=puntos;
			Registros reg = Registros.findById(registro);
			reg.borrarRegistro();
			this.save();
	}
}
