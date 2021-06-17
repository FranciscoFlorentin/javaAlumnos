package ejercicioAlumnos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Excursion {
	private Long idExcursion;
	private List<Grado> grados=new ArrayList<Grado>();
	private String descripcion;
	private Date fecha;
	public Long getIdExcursion() {
		return idExcursion;
	}
	public void setIdExcursion(Long idExcursion) {
		this.idExcursion = idExcursion;
	}
	public List<Grado> getGrados() {
		return grados;
	}
	public void setGrados(List<Grado> grados) {
		this.grados = grados;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Excursion) {
			return (this.idExcursion.equals(((Excursion) obj).idExcursion)); 			
		}
		return false;
	}
	
}
