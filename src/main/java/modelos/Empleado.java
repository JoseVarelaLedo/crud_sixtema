package modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "empleado")
public class Empleado {
	@Id
	@Column(name ="id")
	private int id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name="departamento")
	private int departamento;
	@Column(name="sueldo")
	private float sueldo;
	
	public Empleado() {
		//hay que generar (definir explícitamente) este constructor por defecto, vacío, para que Hibernate no dé errores
	}
	
	public Empleado(int id, String nombre, int departamento, float sueldo) {		
		this.id = id;
		this.nombre = nombre;
		this.departamento = departamento;
		this.sueldo = sueldo;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDepartamento() {
		return departamento;
	}

	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}

	public float getSueldo() {
		return sueldo;
	}

	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}
	
	public Empleado getInstance (int id) {
		return this;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", departamento=" + departamento + ", sueldo=" + sueldo
				+ "]";
	}	
}
