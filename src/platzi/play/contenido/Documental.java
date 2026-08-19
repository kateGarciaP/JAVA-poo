package platzi.play.contenido;

public class Documental extends Contenido implements Promocionable{
    private String narrador;

    public Documental(String titulo, int duracion, Genero genero) {
        super(titulo, duracion, genero);
    }

    public Documental(String titulo, int duracion, Genero genero, double calificacion, String narrador) {
        super(titulo, duracion, genero, calificacion);
        this.narrador = narrador;
    }


    @Override
    public void repoducir() {
        System.out.println("Reproduciendo el documental: " + getTitulo() + " narrado por: " + getNarrador());
    }

    @Override
    public String promocionar() {
        return "Descubre el documental " + this.getTitulo() + " narador por: " + narrador +
                ". ¡Ahora en Platzi Play!";
    }

    public String getNarrador() {
        return narrador;
    }
}
