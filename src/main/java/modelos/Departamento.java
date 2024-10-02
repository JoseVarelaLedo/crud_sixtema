package modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "departamento")
public class Departamento {
	@Id
	@Column(name ="id")
	private int id;
	@Column(name = "nombre")
	private String nombre;
	
	public Departamento() {
		//hay que generar (definir explícitamente) este constructor por defecto, vacío, para que Hibernate no dé errores
	}
	
	public Departamento(int id, String nombre) {		
		this.id = id;
		this.nombre = nombre;
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

	@Override
	public String toString() {
		return "Departamento [id=" + id + ", nombre=" + nombre + "]";
	}	
}
