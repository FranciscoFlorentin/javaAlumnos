package ejercicioAlumnos;

import java.math.BigDecimal;
import java.util.Date;

public class Alumno {
	private Long idAlumno;
	private Grado grado;
	private String nombre,apellido;
	private Integer dni;
	private BigDecimal cuota;
	private Date fecha_nacimiento;
	public Long getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(Long idAlumno) {
		this.idAlumno = idAlumno;
	}
	public Grado getGrado() {
		return grado;
	}
	public void setGrado(Grado grado) {
		this.grado = grado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public BigDecimal getCuota() {
		return cuota;
	}
	public void setCuota(BigDecimal cuota) {
		this.cuota = cuota;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	
}
