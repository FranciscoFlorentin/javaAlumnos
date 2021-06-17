package ejercicioAlumnos;

import java.util.ArrayList;
import java.util.List;

public class Grado {
	private Long idGrado;
	private Escuela escuela;
	private String descripcion,turno;
	private List<Alumno> alumnos=new ArrayList<Alumno>();
	public Long getIdGrado() {
		return idGrado;
	}
	public void setIdGrado(Long idGrado) {
		this.idGrado = idGrado;
	}
	public Escuela getEscuela() {
		return escuela;
	}
	public void setEscuela(Escuela escuela) {
		this.escuela = escuela;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public List<Alumno> getAlumnos() {
		return alumnos;
	}
	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	@Override
	public boolean equals(Object obj) {
		return (this.idGrado.equals(((Grado) obj).idGrado));
	}
	

}
