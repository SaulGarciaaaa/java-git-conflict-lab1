package model;

public class Usuario {
    private int id;
    private String nombre;
    private String email; // opcional

    public Usuario(int id, String nombre) {
        this(id, nombre, null);
    }

    public Usuario(int id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
}
