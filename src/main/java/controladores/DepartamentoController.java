package controladores;

import java.util.List;

import appMain.HiberConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import modelos.Departamento;

/*
 * clase controladora para operaciones CRUD de usuario ([C]reate, [R]ead, [U]pdate,[D]elete)
 */

public class DepartamentoController implements Controller{
	
	private int id;
	private String nombre;
	
	public DepartamentoController() {
		//
	}
	
	public DepartamentoController (int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	private String createDepartamento(int id, String nombre) {
		Session session = HiberConfig.conectaDepartamento();

		try {
			Departamento departamentoACrear = new Departamento(id, nombre);
			session.beginTransaction();

			// session.save(departamentoACrear);
			session.persist(departamentoACrear); // usamos persist(Object) porque save(Object) está deprecated

			session.getTransaction().commit();
			return "Departamento creado exitosamente";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error al crear departamento";
	}
	
	private String readDepartamento(int id) {
		Session session = HiberConfig.conectaDepartamento();
		try {			
			session.beginTransaction(); // iniciamos transacción

			//el método get(), que usamos para obtener el usuario con la id que pasamos por argumento
			//al método readDepartamento, tiene varias versiones sobrecargadas, en una de ellas
			//admite la clase y un entero que sea la id del objeto a leer
			Departamento departamentoALeer = session.get(Departamento.class, id);

			session.getTransaction().commit();
			return departamentoALeer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Departamento no existe";
	}
	
	//cambiamos nombre según la id que pasemos como primer argumento
	private String updateDepartamento(int id, String nombre) {
		Session session = HiberConfig.conectaDepartamento();
		try {
			session.beginTransaction(); // iniciamos transacción

			//el método get(), que usamos para obtener el usuario con la id que pasamos por argumento
			//al método updateDepartamento, tiene varias versiones sobrecargadas, en una de ellas
			//admite la clase y un entero que sea la id del objeto a actualizar
			Departamento departamentoAActualizar = session.get(Departamento.class, id);
			
			departamentoAActualizar.setNombre(nombre);

			//session.update(usuarioAActualizar);
			session.merge(departamentoAActualizar); // usamos merge(Object) porque update(Object) está deprecated

			session.getTransaction().commit();
			return "Departamento actualizado correctamente";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error al actualizar departamento";
	}	

	private String deleteDepartamento(int id) {
		Session session = HiberConfig.conectaDepartamento();
		try {			
			session.beginTransaction();

			//el método get(), que usamos para obtener el usuario con la id que pasamos por argumento
			//al método deleteDepartamento, tiene varias versiones sobrecargadas, en una de ellas
			//admite la clase y un entero que sea la id del objeto a borrar
			Departamento departamentoABorrar = session.get(Departamento.class, id);

			//session.delete(departamentoBorrar);
			session.remove(departamentoABorrar); // usamos remove(Object) porque delete(Object) está deprecated

			session.getTransaction().commit();

			return "Departamento eliminado correctamente";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error al borrar departamento";
	}
	
	private void consulta() {
		try {
			Session session = HiberConfig.conectaDepartamento();
			Query<Departamento> consulta = session.createQuery("SELECT d FROM Departamento d", Departamento.class);
			List <Departamento> lista = consulta.list();
	
			System.out.println ("--------------------\nPRINCIPIO DEL LISTADO\n--------------------");
			int i = 0;
			//lista.forEach(System.out::println);
			for (Departamento d: lista) {
				System.out.println ("Departamento #" + (++i) + ": " + d);
			}		
			System.out.println ("--------------\nFIN DEL LISTADO\n--------------");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String crea() {
		return createDepartamento (this.id, this.nombre);		
	}

	@Override
	public String lee() {
		return readDepartamento (this.id);		
	}

	@Override
	public String actualiza() {
		return updateDepartamento (this.id, this.nombre);
	}

	@Override
	public String borra() {		
		return deleteDepartamento (this.id);
	}
	
	@Override
	public void consultaTabla() {
		consulta();
	}
}