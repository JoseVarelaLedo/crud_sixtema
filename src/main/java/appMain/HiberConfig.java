package appMain;

import modelos.Empleado;
import modelos.Departamento;
import modelos.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/*
*clase para establecer conexiones Hibernate, compuesta de métodos estáticos
* utilizados para llamar directamente a la clase cada vez que necesitemos
* una conexión para una consulta.
* Tiene un método por cada tabla existentente en la base de Datos.
 */
public class HiberConfig {
    public static Session conectaEmpleado(){
        Session session;
       SessionFactory sessionFactoryEmpleado = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Empleado.class)
                .buildSessionFactory();
            session = sessionFactoryEmpleado.openSession();

        return session;
    }
    public static Session conectaUsuario(){
        Session session;
        SessionFactory sessionFactoryUsuario = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Usuario.class)
                .buildSessionFactory();
            session = sessionFactoryUsuario.openSession();

        return session;
    }

   public static Session conectaDepartamento(){
        SessionFactory sessionFactoryDepartamento = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Departamento.class)
                .buildSessionFactory();
        Session session = sessionFactoryDepartamento.openSession();
        return session;
   }

   public static Session conectaDepartamentoJoinEmpleado(){
       SessionFactory sessionFactoryAmbas = new Configuration().configure("hibernate.cfg.xml")
               .addAnnotatedClass(Empleado.class) //tenemos que añadir ambas clases para poder hacer INNER JOIN
               .addAnnotatedClass(Departamento.class)
               .buildSessionFactory();
       Session session = sessionFactoryAmbas.openSession();
       return session;
   }
}
