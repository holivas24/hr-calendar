package models;

import java.util.Calendar;

public class UserToRender {

	public Usuario usuario;
	public int dayOfBirth;
	public int dayOfInt;
	public int years;

	public UserToRender(Usuario usuario)
	{
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);		
		
		this.usuario = usuario;
		
		cal.setTime(this.usuario.fecNac);
		this.dayOfBirth = cal.get(Calendar.DAY_OF_MONTH);
		
		cal.setTime(this.usuario.fecIng);
		this.dayOfInt = cal.get(Calendar.DAY_OF_MONTH);
		this.years = year - (cal.get(Calendar.YEAR));		
	}
}
