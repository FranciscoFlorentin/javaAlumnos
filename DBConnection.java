package ejercicioAlumnos;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
	
//	1) Lista de todos los alumnos de una escuela y grado en particular 
//	(Escuela, GradoDescripcion, GradoTurno, AlumnoNombre, AlumnoApellido, AlumnoDNI, AlumnoCuota)
// 	Lista de Escuelas
	
	public  List<Escuela> obtenerListaEscuelas() {
		List<Escuela> escuelas=new ArrayList<Escuela>();
		
		Connection conn = null;
		try {
			System.out.println("BASE DE DATOS CONECTADA");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/alumnos", "root", "12345");			
			PreparedStatement ps = conn.prepareStatement("SELECT esc.ID_ESCUELA idEscuela, esc.NOMBRE Escuela,esc.presupuesto, gr.id_grado, gr.DESCRIPCION GradoDescripcion, gr.Turno GradoTurno, \r\n"
					+ "al.ID_ALUMNO, al.NOMBRE AlumnoNombre, al.APELLIDO AlumnoApellido, al.cuota \r\n"
					+ "FROM alumno al INNER JOIN grado gr ON (al.ID_GRADO=gr.ID_GRADO) INNER JOIN escuela esc ON (gr.ID_ESCUELA=esc.ID_ESCUELA)");
			ResultSet result = ps.executeQuery();
			while(result.next()) {
				Alumno alumno=new Alumno();
				Grado grado1=new Grado();
				Escuela escuela=new Escuela();
				
				alumno.setIdAlumno(result.getLong("ID_ALUMNO"));
				alumno.setNombre(result.getString("AlumnoNombre"));
				alumno.setApellido(result.getString("AlumnoApellido"));
				alumno.setCuota(result.getBigDecimal("al.cuota"));
				escuela.setIdEscuela(result.getLong("idEscuela"));			
				escuela.setNombre(result.getString("Escuela"));
				escuela.setPresupuesto(result.getBigDecimal("esc.presupuesto"));
				grado1.setIdGrado(result.getLong("id_grado"));
				grado1.setDescripcion(result.getString("GRADODESCRIPCION"));
				grado1.setTurno(result.getString("GRADOTURNO"));
				
				if(escuelas.contains(escuela)) {
					System.out.println("Escuela repetida: "+escuela.getIdEscuela());
					int indexEscuela=escuelas.indexOf(escuela);
					
					if(escuelas.get(indexEscuela).getGrados().contains(grado1)) {
						System.out.println("La escuela: "+ escuelas.get(indexEscuela).getIdEscuela()+" ya tiene el grado "+grado1.getIdGrado());
						int indexGrado=escuelas.get(indexEscuela).getGrados().indexOf(grado1);
						escuelas.get(indexEscuela).getGrados().get(indexGrado).getAlumnos().add(alumno);
						
					}else {
						System.out.println("Agregando el grado "+grado1.getIdGrado()+" a la escuela "+escuelas.get(indexEscuela).getIdEscuela());
						grado1.getAlumnos().add(alumno);
						escuelas.get(indexEscuela).getGrados().add(grado1);
					}
				}else {
					System.out.println("Agregando el grado "+grado1.getIdGrado()+" a la escuela "+escuela.getIdEscuela());
					grado1.getAlumnos().add(alumno);
					escuela.getGrados().add(grado1);
					System.out.println("Agregando nueva escuela: "+escuela.getIdEscuela());
					escuelas.add(escuela);
				}
			}
		}catch (SQLException e) {
			System.out.println("ERROR EN CONEXION");
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
				System.out.println("CONEXION CERRADA");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return escuelas;
	}
	
//	2) Lista de todos los alumnos y las excursiones de las que participaron ordenado por apellido/nombre del alumno 
// 	 y fecha de excursión (AlumnoNombre, AlumnoApellido, AlumnoDNI, AlumnoCuota, ExcursionDescripción, ExcursiónFecha)
//	 Lista de excursiones	
	
	public List<Excursion> obtenerListaExcursiones(){
		List<Excursion> excursiones=new ArrayList<Excursion>();
		Connection conn = null;
		try {
			System.out.println("BASE DE DATOS CONECTADA");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/alumnos", "root", "12345");			
			PreparedStatement ps = conn.prepareStatement("SELECT al.NOMBRE AlumnoNombre, al.APELLIDO AlumnoApellido, al.DNI AlumnoDNI, al.CUOTA AlumnoCuota,\r\n"
					+ "exc.DESCRIPCION ExcursionDescripción,exc.id_excursion idExcursion, exc.FECHA ExcursiónFecha, gr.id_grado \r\n"
					+ "FROM excursion exc INNER JOIN grado gr ON (exc.ID_GRADO = gr.ID_GRADO) INNER JOIN alumno al ON (gr.ID_GRADO = al.ID_GRADO)\r\n"
					+ "ORDER BY al.APELLIDO, al.NOMBRE , exc.FECHA;");
			ResultSet result = ps.executeQuery();
			while(result.next()) {
				Alumno alumno=new Alumno();
				Grado grado=new Grado();
				Excursion excursion=new Excursion();
				
				alumno.setDni(result.getInt("AlumnoDNI"));
				alumno.setNombre(result.getString("AlumnoNombre"));
				alumno.setApellido(result.getString("AlumnoApellido"));
				excursion.setIdExcursion(result.getLong("idExcursion"));			
				excursion.setDescripcion(result.getString("ExcursionDescripción"));
				excursion.setFecha(result.getDate("ExcursiónFecha"));
				grado.setIdGrado(result.getLong("gr.id_grado"));
				
				if(excursiones.contains(excursion)) {
					System.out.println("Excursion repetida: "+excursion.getDescripcion());
					int indexExcursion=excursiones.indexOf(excursion);
					
					if(excursiones.get(indexExcursion).getGrados().contains(grado)) {
						System.out.println("La Excursion: "+ excursiones.get(indexExcursion).getDescripcion()+" ya tiene el grado "+grado.getIdGrado());
						int indexGrado=excursiones.get(indexExcursion).getGrados().indexOf(grado);
						excursiones.get(indexExcursion).getGrados().get(indexGrado).getAlumnos().add(alumno);
					}else {
						System.out.println("Agregando el grado "+grado.getIdGrado()+" a la excursion "+excursiones.get(indexExcursion).getDescripcion());
						grado.getAlumnos().add(alumno);
						excursiones.get(indexExcursion).getGrados().add(grado);
					}
				}else {
					System.out.println("Agregando el grado "+grado.getIdGrado()+" a la excursion "+excursion.getDescripcion());
					grado.getAlumnos().add(alumno);
					excursion.getGrados().add(grado);
					System.out.println("Agregando nueva excursion: "+excursion.getDescripcion());
					excursiones.add(excursion);
				}
			}
		}catch (SQLException e) {
			System.out.println("ERROR EN CONEXION");
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
				System.out.println("CONEXION CERRADA");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return excursiones;
	}
	
// 3) Lista de los alumnos de un grado de una escuela en particular ordenado por grado ascendente y 
//   apellido/nombre del alumno(Escuela, GradoDescripcion, GradoTurno, AlumnoNombre, AlumnoApellido, AlumnoDNI)
// 	Lista de Grados
	
	public List<Grado> obtenerListaGrados(Escuela escuela){
		List<Grado> grados=new ArrayList<Grado>();
		Connection conn = null;
		try {
			System.out.println("BASE DE DATOS CONECTADA");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/alumnos", "root", "12345");			
			PreparedStatement ps = conn.prepareStatement("SELECT es.NOMBRE Escuela,gr.id_grado, gr.DESCRIPCION GradoDescripcion, gr.TURNO GradoTurno,\r\n"
					+ "al.NOMBRE AlumnoNombre, al.APELLIDO AlumnoApellido, al.DNI AlumnoDNI \r\n"
					+ "FROM alumno al INNER JOIN grado gr ON (al.ID_GRADO = gr.ID_GRADO)\r\n"
					+ "INNER JOIN escuela es ON (gr.ID_ESCUELA= es.ID_ESCUELA) WHERE (es.ID_ESCUELA= ?)\r\n"
					+ "ORDER BY gr.DESCRIPCION, al.APELLIDO, al.NOMBRE;");
			ps.setLong(1, escuela.getIdEscuela());
			ResultSet result = ps.executeQuery();
			while(result.next()) {
				Alumno alumno=new Alumno();
				Grado grado=new Grado();
				
				alumno.setDni(result.getInt("AlumnoDNI"));
				alumno.setNombre(result.getString("AlumnoNombre"));
				alumno.setApellido(result.getString("AlumnoApellido"));
				grado.setIdGrado(result.getLong("gr.id_grado"));
				grado.setDescripcion(result.getString("GradoDescripcion"));
				grado.setTurno(result.getString("GradoTurno"));
				
				if(grados.contains(grado)) {
					System.out.println("Grado: "+grado.getDescripcion()+" Turno: "+grado.getTurno()+ " ya existe");
					System.out.println("__Añadiendo alumno  "+alumno.getApellido()+" "+alumno.getNombre()+" al grado: "+grado.getDescripcion()+" Turno: "+grado.getTurno());
					int indexGrado=grados.indexOf(grado);
					grados.get(indexGrado).getAlumnos().add(alumno);
				}else if(!grados.contains(grado)) {
					System.out.println("Añadiendo grado: "+grado.getDescripcion()+" Turno: "+grado.getTurno());
					System.out.println("__Añadiendo alumno "+alumno.getApellido()+" "+alumno.getNombre()+" al grado: "+grado.getDescripcion()+" Turno: "+grado.getTurno());
					grado.getAlumnos().add(alumno);
					grados.add(grado);
				}
				
			}
		}catch (SQLException e) {
			System.out.println("ERROR EN CONEXION");
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
				System.out.println("CONEXION CERRADA");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return grados;
	}
	//Funciones de salida
	public void mostrarListaEscuelas(List<Escuela> escuelas) {
		System.out.println("\n***********************1) LISTA DE ESCUELAS ***********************\n ");
		for (Escuela escuela : escuelas) {
			System.out.println("idEscuela: "+escuela.getIdEscuela()+ " Nombre: "+escuela.getNombre());
			for(Grado grado: escuela.getGrados()) {
				System.out.println("----idGrado: "+grado.getIdGrado()+" Grado: "+grado.getDescripcion()+ " Turno: "+grado.getTurno());
				for(Alumno alumno:grado.getAlumnos()) {
					System.out.println("____________Nombre: "+alumno.getNombre()+ " Apellido: "+alumno.getApellido());
				}
			}
		}
		System.out.println("\n\n ");
	}
	public void mostrarListaExcursiones(List<Excursion> excursiones) {
		System.out.println("\n ***********************2) LISTA DE EXCURSIONES ***********************\n");
		for (Excursion excursion: excursiones) {
			System.out.println("Excursion: "+excursion.getDescripcion()+"// Fecha: "+excursion.getFecha());
			for(Grado grado: excursion.getGrados()) {
				for(Alumno alumno:grado.getAlumnos()) {
					System.out.println("________Nombre: "+alumno.getNombre()+ " Apellido: "+alumno.getApellido());
				}
			}
		}	
	}
	public void mostrarListaGrados(List<Grado> grados) {
		System.out.println("\n ***********************3) LISTA DE GRADOS ***********************\n");
		for (Grado grado : grados) {
			System.out.println("Grado: "+grado.getDescripcion()+" Turno: "+grado.getTurno());
			for(Alumno alumno:grado.getAlumnos()) {
				System.out.println("--------- Nombre: "+alumno.getNombre()+" "+alumno.getApellido()+" DNI: "+alumno.getDni());
			}
		}
	}
	public void mostrarListaEscuelaYcuotas(List<Escuela> escuelas) {
		System.out.println("\n******************4) LISTA DE ESCUELAS Y CUOTAS ***********************\n ");
		for (Escuela escuela : escuelas) {
			BigDecimal sumaCuotaAlumnos=new BigDecimal(0);
			for(Grado grado: escuela.getGrados()) {
				for(Alumno alumno:grado.getAlumnos()) {
//					System.out.println("__Nombre: "+alumno.getNombre()+ " "+alumno.getApellido()+" Cuota: "+alumno.getCuota());
					sumaCuotaAlumnos = sumaCuotaAlumnos.add(alumno.getCuota());
				}
			}
			if(sumaCuotaAlumnos.compareTo(escuela.getPresupuesto())==1) {
				System.out.println(" Nombre: "+escuela.getNombre()+" Presupuesto: "+escuela.getPresupuesto()
				+" Suma Cuota de alumnos: "+ sumaCuotaAlumnos);
			}
		}
		System.out.println("\n\n ");
	}
	
	public static void main(String[] args) {
		
// 1)		
		DBConnection db=new DBConnection();
 		List<Escuela> escuelas=db.obtenerListaEscuelas();
		db.mostrarListaEscuelas(escuelas);
	
//	2)	
 		List<Excursion> excursiones=db.obtenerListaExcursiones();
		db.mostrarListaExcursiones(excursiones);

// 3)
		Escuela escuela=new Escuela();
		escuela.setIdEscuela(Long.valueOf(30));
		List<Grado> grados=db.obtenerListaGrados(escuela);
		db.mostrarListaGrados(grados);

// 4)
		db.mostrarListaEscuelaYcuotas(escuelas);
	}
}

