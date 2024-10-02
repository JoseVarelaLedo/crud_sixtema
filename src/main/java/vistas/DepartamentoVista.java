package vistas;

import controladores.DepartamentoController;

public class DepartamentoVista {

	public void crea(int id, String nombre) {
		//creamos departamento
		System.out.println (new DepartamentoController(id, nombre).crea());
	}	
	
	public void actualiza(int id, String nombre) {
		//actualizamos departamento
		System.out.println (new DepartamentoController(id, nombre).actualiza());
	}
	
	public void le(int id) {
		//leemos departamento
		System.out.println(new DepartamentoController(id, null).lee());
	}
	
	public void elimina(int id) {
		//borramos departamento
		System.out.println (new DepartamentoController(id, null).borra());		
	}
	
	public void consulta() {
		new DepartamentoController().consultaTabla();
	}
}
