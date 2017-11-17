package models;

import javax.persistence.Entity;
import play.db.jpa.Model;

@Entity
public class Campus extends Model{
	
	public String nombre;
	
	public Campus(String nombre)
	{
		this.nombre = nombre;
		this.save();
	}

}
