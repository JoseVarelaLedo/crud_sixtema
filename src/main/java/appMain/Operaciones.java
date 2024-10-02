package appMain;

import modelos.Departamento;
import modelos.Empleado;
import modelos.Usuario;
import org.hibernate.Session;
import vistas.ConjuntoTablasVista;
import vistas.DepartamentoVista;
import vistas.EmpleadoVista;
import vistas.UsuarioVista;

import java.util.HashMap;
import java.util.Scanner;

public class Operaciones {
    private final static Scanner in = new Scanner(System.in);

    void creaRegistro(char tabla, Menu menu) {
        switch (tabla){
            case 'd':
                HashMap <Integer, String> datos = menu.creaDepartamentoMenu();
                int idDepartamento = datos.keySet().stream().findFirst().orElse(null);
                String nombreDepartamento = datos.get(idDepartamento);
                new DepartamentoVista().crea(idDepartamento, nombreDepartamento);
                break;
            case 'e':
                Empleado empleado = menu.creaEmpleadoMenu();
                new EmpleadoVista().crea(empleado.getId(), empleado.getNombre(), empleado.getDepartamento(), empleado.getSueldo());
                break;
            case 'u':
                Usuario usuario = menu.creaUsuarioMenu();
                new UsuarioVista().crea(usuario.getId(), usuario.getNickname(), usuario.getPassword(), usuario.getEmail(), usuario.isAdmin());
                break;
            default:
                break;
        }
    }

    void consultaRegistro(char tabla, Menu menu) {
       switch (tabla) {
            case 'd':
                new DepartamentoVista().le(menu.consultaDepartamentoMenu());
                break;
           case 'e':
                new EmpleadoVista().lee(menu.consultaEmpleadoMenu());
                break;
           case 'u':
               new UsuarioVista().lee(menu.consultaUsuarioMenu());
               break;
           default:
               break;
        }
    }

    void modificaRegistro(char tabla, Menu menu) {
       switch (tabla ) {
           case 'd':
                Departamento dummy = menu.modificaDepartamentoMenu();
                new DepartamentoVista().actualiza(dummy.getId(), dummy.getNombre());
                break;
           case 'e':
               Session sessionEmpleado = HiberConfig.conectaEmpleado();

                int idEmpleado = menu.obtenerIdEmpleadoMenu();
                HashMap <String, Float> datosModificadosEmpleado = menu.modificarEmpleadoMenu(idEmpleado, sessionEmpleado);
                String nombre = datosModificadosEmpleado.keySet().stream().findFirst().orElse(null);
                float salario = datosModificadosEmpleado.get (nombre);
                new EmpleadoVista().actualiza(idEmpleado, nombre, 0, salario);
                break;
           case 'u':
              Session sessionUsuario = HiberConfig.conectaUsuario();

               int idUsuario = menu.obtenerIdUsuarioMenu();
               HashMap <String, Boolean> datosModificadosUsuario = menu.modificarUsuarioMenu(idUsuario, sessionUsuario);
               String password = datosModificadosUsuario.keySet().stream().findFirst().orElse(null);
               boolean isAdmin = datosModificadosUsuario.get (password);
               new UsuarioVista().actualiza(idUsuario, null, password, null, isAdmin);
               break;
        }
    }

    void borraRegistro(char tabla, Menu menu) {
        switch (tabla){
            case 'd':
                new DepartamentoVista().elimina(menu.borraDepartamentoMenu());
                break;
            case 'e':
                new EmpleadoVista().elimina(menu.borraEmpleadoMenu());
                break;
            case 'u':
                new UsuarioVista().elimina(menu.borraUsuarioMenu());
                break;
            default:
                break;
        }
    }

    void realizaListado(char opcion) {
        switch (opcion) {
            case 'd':
                listaTabla("departamentos");
                break;
            case 'e':
                listaTabla("empleados");
                break;
            case 'u':
                listaTabla("usuarios");
                break;
            case 'c':
                listaConjunta();
                break;
            default:
                break;
        }
    }

    void listaTabla(String tabla) {
        switch (tabla){
            case "departamentos":
                new DepartamentoVista().consulta();
                break;
            case "empleados":
                new EmpleadoVista().consulta();
                break;
            case "usuarios":
                new UsuarioVista().consulta();
                break;
            default:
                break;
        }
    }

    void listaConjunta() {
        new ConjuntoTablasVista().consulta();
    }

    //método a llamar en caso de implementar consultas adicionales
    void consultaHQL() {
		/*System.out.println("Qué tabla vas a consultar?"
						   + "\n [D]epartamento"
						   + "\n [E]mpleado"
						   + "\n [A]mbas");
		char respuesta = in.next().toLowerCase().charAt(0);
		System.out.println ("Escribe tu consulta HQL");
		in.nextLine();
		String consulta = in.nextLine();
		switch (respuesta) {
			case 'd':
				session = sessionFactoryDepartamento.openSession();
				Query<Departamento> consultaDepartamento = session.createQuery(consulta, Departamento.class);
				//aquí iría la lógica a implementar con la consulta
				break;
			case 'e':
				session = sessionFactoryEmpleado.openSession();
				Query<Empleado> consultaEmpleado = session.createQuery(consulta, Empleado.class);
				//aquí iría la lógica a implementar con la consulta
				break;
			case 'a':
				session = sessionFactoryAmbas.openSession();
				break;
		}	*/
    }
}


