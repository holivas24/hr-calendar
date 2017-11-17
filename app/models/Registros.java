package models;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import play.db.jpa.Model;

@Entity
public class Registros extends Model{
	public Date fecha;
	public Time hora;
	public String log;
	public long cantidad;
	@OneToOne
	public Usuario hero;
	@OneToOne
	public Usuario admin;
	public boolean activo;
	
	public Registros(String log, long cantidad, Usuario hero, Usuario admin)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		Calendar cal = Calendar.getInstance();		   
		   
		this.fecha = java.sql.Date.valueOf(dateFormat.format(cal.getTime()).toString());
		this.hora = java.sql.Time.valueOf(timeFormat.format(cal.getTime()).toString());	
		this.log = log;
		this.cantidad = cantidad;
		this.hero = hero;	
		this.admin = admin;
		this.activo = true;
	}
	
	public void borrarRegistro()
	{
		this.activo = false;
		this.save();
	}
}
