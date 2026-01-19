package model;

public class Libro {
    private String isbn;
    private String titulo;
    private boolean disponible;
    private int reservasActivas;

    public Libro(String isbn, String titulo) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.disponible = true;
        this.reservasActivas = 0;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean isDisponible() {
        return reservasActivas == 0 && disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void incrementarReservas() {
        reservasActivas++;
        disponible = false;
    }

    public void decrementarReservas() {
        if (reservasActivas > 0) reservasActivas--;
        if (reservasActivas == 0) disponible = true;
    }

    @Override
    public String toString() {
        return titulo + " (" + isbn + ") - " +
                (isDisponible() ? "Disponible" : "No disponible");
    }
}
