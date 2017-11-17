package models;

import javax.persistence.Entity;
import play.db.jpa.Model;

@Entity
public class Nivel extends Model{
	public String nombre;
	public int numero;
	
	public Nivel(String nombre, int numero)
	{
		this.nombre = nombre;
		this.numero = numero;
		this.save();
	}
}
