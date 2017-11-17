package models;

import javax.persistence.Entity;
import play.db.jpa.Model;

@Entity
public class Sucursal extends Model{
	
	public String nombre;
	
	public Sucursal(String nombre)
	{
		this.nombre = nombre;
		this.save();
	}

}
