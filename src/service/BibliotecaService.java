package service;

import model.Libro;
import model.Reserva;
import model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaService {

    private List<Reserva> reservas = new ArrayList<>();
    private List<Libro> catalogo = new ArrayList<>();

    // Catalogo
    public void agregarLibro(Libro libro) {
        catalogo.add(libro);
    }

    public boolean eliminarLibro(String isbn) {
        return catalogo.removeIf(l -> l.getIsbn().equals(isbn));
    }

    public Libro buscarPorIsbn(String isbn) {
        return catalogo.stream()
                .filter(l -> l.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    public List<Libro> buscarPorTitulo(String texto) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro l : catalogo) {
            if (l.getTitulo().toLowerCase().contains(texto.toLowerCase())) {
                resultados.add(l);
            }
        }
        return resultados;
    }

    public List<Libro> listarCatalogo() {
        return catalogo;
    }

    // Reservas
    public boolean reservarLibro(Usuario usuario, Libro libro) {
        if (usuario == null || libro == null || !libro.isDisponible()) return false;
        libro.incrementarReservas();
        reservas.add(new Reserva(usuario, libro));
        return true;
    }

    public void listarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas");
            return;
        }
        reservas.forEach(System.out::println);
    }

    // Prestamos
    public boolean prestarLibro(Usuario usuario, String isbn) {
        Libro libro = buscarPorIsbn(isbn);
        if (libro == null || !libro.isDisponible()) return false;
        libro.setDisponible(false);
        reservas.add(new Reserva(usuario, libro));
        return true;
    }

    public boolean devolverLibro(String isbn) {
        for (int i = 0; i < reservas.size(); i++) {
            Reserva r = reservas.get(i);
            if (r.getLibro().getIsbn().equals(isbn)) {
                r.getLibro().setDisponible(true);
                reservas.remove(i);
                return true;
            }
        }
        return false;
    }
}
