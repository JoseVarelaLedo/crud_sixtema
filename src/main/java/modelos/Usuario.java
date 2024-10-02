package modelos;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @Column(name ="id")
    private int id;
    @Column(name = "nickname")
    private String nickname;
    @Column(name="password")
    private String password;
    @Column(name="email")
    private String email;
    @Column(name="is_admin")
    private boolean isAdmin;

    public Usuario() {
        //hay que generar (definir explícitamente) este constructor por defecto, vacío, para que Hibernate no dé errores
    }

    public Usuario(int id, String nickname, String password, String email, boolean isAdmin) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Usuario getInstance (int id) {
        return this;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nickname=" + nickname + ", email=" + email + ", ADMINISTRADOR?=" + isAdmin
                + "]";
    }
}
