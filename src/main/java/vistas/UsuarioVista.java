package vistas;

import controladores.UsuarioController;

public class UsuarioVista {
    public void crea(int id, String nickname, String password, String email, boolean isAdmin) {
        //creamos usuario
        System.out.println (new UsuarioController(id, nickname, password, email, isAdmin).crea ());
    }

    public void actualiza(int id, String nickname, String password, String email, boolean isAdmin) {
        //actualizamos usuario
        System.out.println (new UsuarioController(id, nickname, password, email, isAdmin).actualiza());
    }

    public void lee(int id) {
        //leemos usuario
        System.out.println(new UsuarioController(id, null, null, null,  true).lee());
    }

    public void elimina(int id) {
        //borramos usuario
        System.out.println (new UsuarioController(id, null, null, null,  true).borra());
    }

    public void consulta() {
        new UsuarioController().consultaTabla();
    }
}
