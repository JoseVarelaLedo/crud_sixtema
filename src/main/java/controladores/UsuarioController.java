package controladores;

import java.util.List;

import appMain.HiberConfig;
import jakarta.persistence.Column;
import modelos.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import modelos.Empleado;

/*
 * clase controladora para operaciones CRUD sobre la tabla de empleados ([C]reate, [R]ead, [U]pdate,[D]elete)
 */

public class UsuarioController implements Controller{

    private int id;
    private String nickname;
    private String password;
    private String email;
    private boolean isAdmin;

    public UsuarioController() {
        //
    }

    public UsuarioController(int id, String nickname, String password, String email, boolean isAdmin) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    private String createUsuario(int id, String nickname, String password, String email, boolean isAdmin) {
        Session session = HiberConfig.conectaUsuario();
        try {
            Usuario usuarioACrear = new Usuario(id, nickname, password, email, isAdmin); // creamos nuevo objeto usuario
            session.beginTransaction(); // iniciamos transacción

            // session.save(usuarioACrear);
            session.persist(usuarioACrear); // usamos persist(Object) porque save(Object) está deprecated

            session.getTransaction().commit(); //obtenemos estado de la transacción hasta ese momento y "comiteamos" las acciones

         return "Usuario creado exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error al crear usuario";
    }

    private String readUsuario(int id) {
        Session session = HiberConfig.conectaUsuario();
        try {
            session.beginTransaction();

            Usuario usuarioALeer = session.get(Usuario.class, id);

            session.getTransaction().commit();

            //sessionFactory.close();

            return usuarioALeer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Usuario no existe";
    }

    //cambiamos contraseña, o rol de administrador, únicos cambios modificables, según la id que pasemos como primer argumento
    private String updateUsuario(int id, String nickname, String password, String email, boolean isAdmin) {
        Session session = HiberConfig.conectaUsuario();
        try {
            session.beginTransaction(); // iniciamos transacción

            //el método get(), que usamos para obtener el usuario con la id que pasamos por argumento
            //al método updateUsuario, tiene varias versiones sobrecargadas, en una de ellas
            //admite la clase y un entero que sea la id del objeto a actualizar
            Usuario usuarioAActualizar = session.get(Usuario.class, id);

            usuarioAActualizar.setPassword(password);
            usuarioAActualizar.setAdmin(isAdmin);

            //session.update(usuarioAActualizar);
            session.merge(usuarioAActualizar); // usamos merge(Object) porque update(Object) está deprecated

            session.getTransaction().commit();

            //sessionFactory.close();

            return "Usuario actualizado correctamente";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error al actualizar usuario";
    }

    private String deleteUsuario(int id) {
        Session session = HiberConfig.conectaUsuario();
        try {
            session.beginTransaction();

            //el método get(), que usamos para obtener el usuario con la id que pasamos por argumento
            //al método deleteUsuario, tiene varias versiones sobrecargadas, en una de ellas
            //admite la clase y un entero que sea la id del objeto a borrar
            Empleado usuarioABorrar = session.get(Empleado.class, id);


            //session.delete(usuarioABorrar);
            session.remove(usuarioABorrar); // usamos remove(Object) porque delete(Object) está deprecated

            session.getTransaction().commit();

            //sessionFactory.close();

            return "Usuario eliminado correctamente";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error al borrar usuario";
    }

    private void consulta() {
        try {
            Session session = HiberConfig.conectaUsuario();
            Query<Usuario> consulta = session.createQuery("SELECT u FROM Usuario u", Usuario.class);
            List <Usuario> lista = consulta.list();

            int i = 0;
            System.out.println ("--------------------\nPRINCIPIO DEL LISTADO\n--------------------");
            for (Usuario e: lista) {
                System.out.println ("Usuario #" + (++i) + ": " + e);
            }
            System.out.println ("--------------\nFIN DEL LISTADO\n--------------");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String crea() {
        return createUsuario(this.id, this.nickname, this.password, this.email, this.isAdmin);
    }

    @Override
    public String lee() {
        return readUsuario (this.id);
    }

    @Override
    public String actualiza() {
        return updateUsuario (this.id, this.nickname, this.password, this.email, this.isAdmin);
    }

    @Override
    public String borra() {
        return deleteUsuario (this.id);
    }

    @Override
    public void consultaTabla() {
        consulta();
    }
}