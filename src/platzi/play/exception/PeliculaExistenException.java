package platzi.play.exception;

public class PeliculaExistenException extends RuntimeException{
    public PeliculaExistenException(String titulo){
        super("El contenido " + titulo + " ya existe.");
    }

}
