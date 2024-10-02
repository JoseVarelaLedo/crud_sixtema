package controladores;

import java.util.List;

import appMain.HiberConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import modelos.Empleado;

/*
 * clase controladora para operaciones CRUD sobre la tabla de empleados ([C]reate, [R]ead, [U]pdate,[D]elete)
 */

public class EmpleadoController implements Controller{

	private int id;
	private String nombre;
	private int departamento;
	private float sueldo;

	public EmpleadoController() {
		//
	}

	public EmpleadoController (int id, String nombre, int departamento, float sueldo) {
		this.id = id;
		this.nombre = nombre;
		this.departamento = departamento;
		this.sueldo = sueldo;
	}

	private String createEmpleado(int id, String nombre, int departamento, float sueldo) {
		Session session = HiberConfig.conectaEmpleado();
		try {
			Empleado empleadoACrear = new Empleado(id, nombre, departamento, sueldo); // creamos nuevo objeto empleado
			session.beginTransaction(); // iniciamos transacción

			// session.save(empleadoACrear);
			session.persist(empleadoACrear); // usamos persist(Object) porque save(Object) está deprecated

			session.getTransaction().commit(); //obtenemos estado de la transacción hasta ese momento y "comiteamos" las acciones

			return "Empleado creado exitosamente";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error al crear empleado";
	}

	private String readEmpleado(int id) {
		Session session = HiberConfig.conectaEmpleado();
		try {
			session.beginTransaction();

			Empleado empleadoALeer = session.get(Empleado.class, id);

			session.getTransaction().commit();

			return empleadoALeer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Empleado no existe";
	}

	//cambiamos nombre o salario, únicos cambios modificables, según la id que pasemos como primer argumento
	private String updateEmpleado(int id, String nombre, int departamento, float sueldo) {
		Session session = HiberConfig.conectaEmpleado();
		try {
			session.beginTransaction(); // iniciamos transacción

			//el método get(), que usamos para obtener el usuario con la id que pasamos por argumento
			//al método updateEmpleado, tiene varias versiones sobrecargadas, en una de ellas
			//admite la clase y un entero que sea la id del objeto a actualizar
			Empleado empleadoAActualizar = session.get(Empleado.class, id);

			empleadoAActualizar.setNombre(nombre);
			empleadoAActualizar.setSueldo(sueldo);

			//session.update(usuarioAActualizar);
			session.merge(empleadoAActualizar); // usamos merge(Object) porque update(Object) está deprecated

			session.getTransaction().commit();
			return "Empleado actualizado correctamente";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error al actualizar empleado";
	}

	private String deleteEmpleado(int id) {
		Session session = HiberConfig.conectaEmpleado();
		try {
			session.beginTransaction();

			//el método get(), que usamos para obtener el usuario con la id que pasamos por argumento
			//al método deleteEmpleado, tiene varias versiones sobrecargadas, en una de ellas
			//admite la clase y un entero que sea la id del objeto a borrar
			Empleado empleadoABorrar = session.get(Empleado.class, id);


			//session.delete(empleadoABorrar);
			session.remove(empleadoABorrar); // usamos remove(Object) porque delete(Object) está deprecated

			session.getTransaction().commit();

			return "Empleado eliminado correctamente";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error al borrar empleado";
	}

	private void consulta() {
		try {
			Session session = HiberConfig.conectaEmpleado();
			Query<Empleado> consulta = session.createQuery("SELECT e FROM Empleado e", Empleado.class);
			List <Empleado> lista = consulta.list();

			int i = 0;
			System.out.println ("--------------------\nPRINCIPIO DEL LISTADO\n--------------------");
			for (Empleado e: lista) {
				System.out.println ("Empleado #" + (++i) + ": " + e);
			}
			System.out.println ("--------------\nFIN DEL LISTADO\n--------------");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String crea() {
		return createEmpleado (this.id, this.nombre, this.departamento, this.sueldo);
	}

	@Override
	public String lee() {
		return readEmpleado (this.id);
	}

	@Override
	public String actualiza() {
		return updateEmpleado (this.id, this.nombre, this.departamento, this.sueldo);
	}

	@Override
	public String borra() {
		return deleteEmpleado (this.id);
	}

	@Override
	public void consultaTabla() {
		consulta();
	}
}