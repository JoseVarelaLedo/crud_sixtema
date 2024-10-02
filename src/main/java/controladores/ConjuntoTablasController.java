package controladores;

import java.util.List;

import appMain.HiberConfig;
import org.hibernate.Session;
import jakarta.persistence.TypedQuery;


public class ConjuntoTablasController {

	private Session session;
	
	public void consultaConjunta() {
		try {
			session = HiberConfig.conectaDepartamentoJoinEmpleado();
			String consulta = "SELECT e.id, e.nombre, e.sueldo, d.nombre FROM Empleado e INNER JOIN Departamento d ON e.departamento = d.id";
			//usamos TypedQuery porque el método createQuery (String) está deprecated.
			TypedQuery<Object[]> typedQuery = session.createQuery(consulta, Object[].class);
			List <Object[]> lista  = typedQuery.getResultList();
			int i = 0;
			System.out.println ("--------------------\nPRINCIPIO DEL LISTADO\n--------------------");
			for (Object[] fila : lista) {
				System.out.print ("Resultado #" + (++i) + ": ");
				int idEmpleado = (int) fila[0];
			    String nombreEmpleado = (String) fila[1];
			    float salarioEmpregado = (float) fila[2];
			    String nombreDepartamento = (String) fila[3];
			    System.out.println("ID Empleado: " + idEmpleado +
			                       ", Nombre Empleado: " + nombreEmpleado +
			                       ", Salario: " + salarioEmpregado +
			                       ", Nombre Departamento: " + nombreDepartamento);
			}		
			System.out.println ("--------------\nFIN DEL LISTADO\n--------------");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
