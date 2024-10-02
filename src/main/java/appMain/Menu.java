package appMain;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import modelos.Departamento;
import modelos.Empleado;
import modelos.Usuario;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class Menu {
    private final static Scanner in = new Scanner (System.in);
    private final Operaciones ops = new Operaciones();

    void start(){
        boolean isAdmin = login();
        while (true) {
            System.out.println ("Deseas hacer alguna operación?" +
                                "\n (Nota: sólo los usuarios con permisos de administrador podrán hacer operaciones CRUD)" +
                            " \n(n) para salir \n(s) para realizar operaciones");

            if (in.next().toLowerCase().charAt(0)=='n') {
                System.out.println ("FIN DE LA EJECUCIÓN");

                //app.cerrar(); //aquí había un método para el cierre de los SessionFactory cuando estaban en esta clase

                //salida del programa
                System.exit(0);
            }else {
                this.menuSeleccion(isAdmin);
            }
        }
    }
    boolean login(){
        System.out.println ("Introduce tu nickname de usuario:");
        String nickName = in.next();
        Session session = HiberConfig.conectaUsuario();
        Query<String> consulta = session.createQuery("SELECT u.nickname FROM Usuario u", String.class);
        List <String> listaUsuarios = consulta.getResultList();
        if (!listaUsuarios.contains(nickName)){
            System.out.println ("Usuario no existe.");
            start();
        }
        //la idea es que se oculte la entrada de la contraseña, pero IntelliJ no permite el uso de la clase Console
        //y las únicas alternativas que se me ocurren son utilizando interfaz gráfico
        System.out.println ("Introduce tu contraseña de usuario:");
        String passwordIntroducido = in.next();
        consulta = session.createQuery("SELECT u.password FROM Usuario u WHERE u.nickname= :nickname", String.class);
        consulta.setParameter("nickname", nickName);

        //se verifica que la contraseña introducida sea correcta
        String passwordExistente = consulta.uniqueResult();
        if (!passwordIntroducido.equals(passwordExistente)){
            System.out.println ("Password introducido incorrecto");
            start();
        }
        //averiguamos si es o no administrador
        Query <Boolean> consultaAdmin = session.createQuery("SELECT u.isAdmin FROM Usuario u WHERE u.nickname = :nickname", Boolean.class);
        consultaAdmin.setParameter("nickname", nickName);
        Boolean isAdmin = consultaAdmin.uniqueResult();
        return isAdmin;
    }

    void menuSeleccion(boolean isAdmin) {
        System.out.println("Qué operación quieres realizar?");
        System.out.println("1.- Operaciones CRUD sobre la tabla seleccionada"
                +"\n2.- Otro tipo de operaciones o consultas");
        int seleccion = in.nextInt();
        if (seleccion == 1) {
            if (isAdmin) {
                System.out.println("Quieres hacer operaciones sobre [U]suarios, [D]epartamentos o [E]mpleados?");
                char seleccionDeTabla = in.next().toLowerCase().charAt(0);
                this.ejecutaOperacionesCrud(seleccionDeTabla);
            }else{
                System.out.println("No eres administrador, no puedes realizar operaciones CRUD");
            }
        } else  {
            ops.realizaListado(this.eligeTabla());
        }
    }

    void ejecutaOperacionesCrud(char seleccionDeTabla) {
        System.out.println("Selecciona operación a realizar:"
                + "\n[A]lta de un nuevo registro"
                + "\n[C]onsulta de un registro existente"
                + "\n[M]odificación de un registro existente"
                + "\n[E]liminación de un registro existente");
        char seleccion = in.next().toLowerCase().charAt(0);
        switch (seleccion) {
            case 'a':
                ops.creaRegistro(seleccionDeTabla, this);
                break;
            case 'c':
                ops.consultaRegistro(seleccionDeTabla, this);
                break;
            case 'm':
                ops.modificaRegistro(seleccionDeTabla, this);
                break;
            case 'e':
                ops.borraRegistro(seleccionDeTabla, this);
                break;
            default:
                System.out.println("No es una opción válida");
                break;
        }
    }

    HashMap <Integer, String> creaDepartamentoMenu(){
        HashMap <Integer, String> datos = new HashMap<>();
        System.out.println("Introduce id de departamento a crear:");
        int idDepartamento = in.nextInt();
        System.out.println("Introduce nombre do departamento a crear:");
        in.nextLine();
        String nomeDepartamento = in.nextLine();
        datos.put(idDepartamento, nomeDepartamento);
        return datos;
    }

    Empleado creaEmpleadoMenu(){
        System.out.println("Introduce id del empleado a crear:");
        int idEmpleado = in.nextInt();
        System.out.println("Introduce nombre del empleado a crear:");
        in.nextLine();
        String nombreEmpleado = in.nextLine();
        System.out.println("Introduce la id del departamento en el que va a estar el empleado:");
        int idDepartamento = in.nextInt();
        System.out.println("Introduce el salario del empleado, puedes usar decimales");
        float salario = in.nextFloat();
        return new Empleado(idEmpleado, nombreEmpleado, idDepartamento, salario);
    }

    Usuario creaUsuarioMenu(){
        System.out.println("Introduce id del usuario a crear:");
        int idUsuario = in.nextInt();
        System.out.println("Introduce nickname del usuario a crear:");
        in.nextLine();
        String nickNameUsuario = in.nextLine();
        System.out.println("Introduce contraseña para el usuario:");
        String password = in.next();
        System.out.println("Introduce email del usuario:");
        String email = in.next();
        System.out.println("Va a ser [a]dministrador o [n]o?");
        char opcion = in.next().charAt(0);
        boolean isAdmin = opcion == 'a';
        return new Usuario(idUsuario, nickNameUsuario, password, email, isAdmin);
    }

    int consultaDepartamentoMenu(){
        System.out.println("Introduce id de departamento a consultar:");
        return in.nextInt();
    }

    int consultaEmpleadoMenu(){
        System.out.println("Introduce id de empleado a consultar:");
        return in.nextInt();
    }

    int consultaUsuarioMenu(){
        System.out.println("Introduce id de usuario a consultar:");
        return in.nextInt();
    }

    int borraDepartamentoMenu(){
        System.out.println("Introduce id de departamento a borrar:");
        return in.nextInt();
    }

    int borraEmpleadoMenu(){
        System.out.println("Introduce id de empleado a borrar:");
        return in.nextInt();
    }

    int borraUsuarioMenu(){
        System.out.println("Introduce id de usuario a borrar:");
        return in.nextInt();
    }

    Departamento modificaDepartamentoMenu(){
        System.out.println("Introduce id de departamento a modificar:");
        int idDepartamento = in.nextInt();
        in.nextLine();
        System.out.println("Introduce el nuevo nombre del Departamento");
        String nombreDepartamento = in.nextLine();
        return new Departamento(idDepartamento, nombreDepartamento);
    }

    int obtenerIdEmpleadoMenu(){
        System.out.println("Introduce id del empleado a modificar:");
        return in.nextInt();
    }

    int obtenerIdUsuarioMenu(){
        System.out.println("Introduce id del usuario a modificar:");
        return in.nextInt();
    }

    HashMap <String, Float> modificarEmpleadoMenu(int idEmpleado, Session session){
        HashMap <String, Float> datos = new HashMap<>();
        Empleado dummy = session.get(Empleado.class, idEmpleado);
        String nombre = dummy.getNombre();
        float salario = dummy.getSueldo();
        System.out.println("Puedes modificar Nombre o Salario.\nQué quieres modificar?\n ([N]ombre | [S]alario | [T]odo)");
        char respuesta = in.next().toLowerCase().charAt(0);
        switch (respuesta) {
            case 'n':
                System.out.println("Introduce el nuevo nombre del empleado");
                in.nextLine();
                nombre = in.nextLine();
                break;
            case 's':
                System.out.println("Introduce el nuevo salario");
                salario = in.nextFloat();
                break;
            case 't':
                System.out.println("Introduce el nuevo salario");
                salario = in.nextFloat();
                in.nextLine();
                System.out.println("Introduce el nuevo nombre del empleado");
                nombre = in.nextLine();
                break;
            default:
                System.out.println("No es una opción válida");
                break;
        }
        datos.put(nombre, salario);
        return datos;
    }
    HashMap <String, Boolean> modificarUsuarioMenu(int idUsuario, Session session){
        HashMap <String, Boolean> datos = new HashMap<>();
        Usuario dummy = session.get(Usuario.class, idUsuario);
        String password = dummy.getPassword();
        boolean isAdmin = dummy.isAdmin();
        System.out.println("Puedes modificar password o condición de administrador.\nQué quieres modificar?\n ([P]assword | [A]dministrador | [T]odo)");
        char respuesta = in.next().toLowerCase().charAt(0);
        switch (respuesta) {
            case 'p':
                System.out.println("Introduce el nuevo password");
                password = in.next();
                break;
            case 'a':
                System.out.println("Introduce [a]dmin o [u]suario sin privilegios");
                char opcion = in.next().charAt(0);
                isAdmin = opcion =='a';
                break;
            case 't':
                System.out.println("Introduce el nuevo password");
                password = in.next();
                System.out.println("Introduce [a]dmin o [u]suario sin privilegios");
                char opcionBoth = in.next().charAt(0);
                isAdmin = opcionBoth =='a';
                break;
            default:
                System.out.println("No es una opción válida");
                break;
        }
        datos.put(password, isAdmin);
        return datos;
    }

    char eligeTabla (){
        System.out.println("Selecciona operación: \nListar datos de la tabla [D]epartamentos"
                + "\nListar datos de tabla [E]mpleados"
                + "\nListar datos de tabla [U]suarios"
                + "\nListado [C]onjunto de tablas Empleados y Departamentos");
        char respuesta = in.next().toLowerCase().charAt(0);
        if (!(respuesta == 'c' || respuesta == 'd' || respuesta == 'e' || respuesta == 'u')){
            System.out.println ("Opción no válida");
            eligeTabla();
        }
        return respuesta;
    }
}
