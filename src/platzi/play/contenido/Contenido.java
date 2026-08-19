package platzi.play.contenido;
import java.time.LocalDate;

public abstract class Contenido {

    private String titulo;
    private String descripcion;
    private int duracion;
    private Genero genero;
    private LocalDate fechaDeEstreno;
    private double calificacion;
    private boolean disponible;
    private Idioma idioma;

    public Contenido(String titulo, int duracion, Genero genero){
        this.titulo = titulo;
        this.duracion= duracion;
        this.genero = genero;
        this.fechaDeEstreno=LocalDate.now();
        this.disponible=true;
    }

    public Contenido(String titulo, int duracion, Genero genero, double calificacion) {
        this(titulo,duracion,genero);
        this.calificar(calificacion);
    }

    public abstract void repoducir();

    public String obtenerFichaTecnica(){
        return titulo + "(" + fechaDeEstreno.getYear() + ") \n" +
                "Genero: " +genero+ "\n" +
                "Calificación: " +calificacion+ "/5";
    }

    public void calificar(double calificacion){
        if(calificacion >= 0 && calificacion <= 5){
            this.calificacion = calificacion;
        }
    }

    public boolean esPopular(){
        return calificacion >=4;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Genero getGenero() {
        return genero;
    }

    public LocalDate getFechaDeEstreno() {
        return fechaDeEstreno;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setFechaDeEstreno(LocalDate fechaDeEstreno) {
        this.fechaDeEstreno = fechaDeEstreno;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}