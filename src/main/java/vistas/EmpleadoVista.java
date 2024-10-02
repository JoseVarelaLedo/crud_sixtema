package vistas;

import controladores.EmpleadoController;

public class EmpleadoVista {
	public void crea(int id, String nombre, int departamento, float sueldo) {
		//creamos empleado
		System.out.println (new EmpleadoController(id, nombre, departamento, sueldo).crea ());
	}	
	
	public void actualiza(int id, String nombre, int departamento, float sueldo) {
		//actualizamos empleado
		System.out.println (new EmpleadoController(id, nombre, departamento, sueldo).actualiza());
	}
	
	public void lee(int id) {
		//leemos empleado
		System.out.println(new EmpleadoController(id, null, 0, 0).lee());
	}
	
	public void elimina(int id) {
		//borramos empleado
		System.out.println (new EmpleadoController(id, null, 0, 0).borra());		
	}
	
	public void consulta() {
		new EmpleadoController().consultaTabla();
	}
}
