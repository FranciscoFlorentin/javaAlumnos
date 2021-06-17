package ejercicioAlumnos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Escuela {
	private Long idEscuela;
	private String nombre,direccion;
	private BigDecimal presupuesto;
	List<Grado> grados=new ArrayList<Grado>();
	
	public Long getIdEscuela() {
		return idEscuela;
	}
	public void setIdEscuela(Long idEscuela) {
		this.idEscuela = idEscuela;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public BigDecimal getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(BigDecimal presupuesto) {
		this.presupuesto = presupuesto;
	}
	public List<Grado> getGrados() {
		return grados;
	}
	public void setGrados(List<Grado> grados) {
		this.grados = grados;
	}
	@Override
	public boolean equals(Object obj) {
		return (this.idEscuela.equals(((Escuela) obj).idEscuela));
	}
	

}
