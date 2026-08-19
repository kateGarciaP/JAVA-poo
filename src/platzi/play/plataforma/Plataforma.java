package platzi.play.plataforma;
import platzi.play.contenido.*;
import platzi.play.exception.PeliculaExistenException;
import platzi.play.utils.FileUtils;
import java.util.*;

public class Plataforma {
    private String nombre;
    private List<Contenido> contenido;//Agregación
    private Map<Contenido,Integer> visualizaciones;

    public Plataforma(String nombre){
        this.nombre=nombre;
        this.contenido = new ArrayList<>();
        this.visualizaciones = new HashMap<>();
    }

    public void agregar(Contenido elemento){
        Contenido contenido = this.burcarPorTitulo(elemento.getTitulo());

        if(contenido != null){
            throw new PeliculaExistenException(elemento.getTitulo());
        }
        FileUtils.escribirContenido(elemento);
        this.contenido.add(elemento);
    }

    public void reproducir(Contenido contenido){
        this.contarVisualizaciones(contenido);
        contenido.repoducir();
    }

    private  void contarVisualizaciones(Contenido contenido){
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        System.out.println(contenido.getTitulo() + " ha sido reproduciodo " + conteoActual + " veces.");
        visualizaciones.put(contenido, conteoActual + 1);//agregar elemento
    }

    public List<String> getTitulos(){
        return contenido.stream()
                .map(Contenido::getTitulo)
                .toList();
    }

    public List<ResumenContenido> getResumenes(){
        return  contenido.stream()
                .map(c -> new ResumenContenido(c.getTitulo(), c.getDuracion(),c.getGenero()))
                .toList();
    }

    public void delete(Contenido elemento){
        this.contenido.remove(elemento);
    }

    public Contenido burcarPorTitulo(String titulo){
        //for(Contenido pelicula: contenido){
            //if(pelicula.getTitulo().equalsIgnoreCase(titulo)){
                //return pelicula;
            //}
        //}
        return contenido.stream()
                .filter(contenido -> contenido.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
        //excepcion de negocio va ahi
    }

    public List<Contenido> burcarPorGenero(Genero genero){
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equals(genero))
                .toList();
    }

    public List<Contenido> getPopulares(int cantidad){
        return contenido.stream()
                .sorted(Comparator.comparingDouble(Contenido::getCalificacion).reversed())
                .limit(cantidad)
                .toList();
    }

    public List<Pelicula> getPeliculas(){
        return contenido.stream()
                .filter(contenido -> contenido instanceof Pelicula)
                .map(contenidoFiltrado -> (Pelicula)contenidoFiltrado)
                .toList();
    }

    public List<Documental> getDcumentales(){
        return contenido.stream()
                .filter(contenido -> contenido instanceof Documental)
                .map(contenidoFiltrado -> (Documental)contenidoFiltrado)
                .toList();
    }
    public List<Promocionable> getContenidoPromocionable(){
        return contenido.stream()
                .filter(contenido -> contenido instanceof Promocionable)
                .map(contenidoPron ->(Promocionable) contenidoPron)
                .toList();
    }

    public List<Contenido> getMejorPelicula(){
        return contenido.stream()
                .filter(contenido -> contenido.getCalificacion() >= 5)
                .toList();
    }
    public int getDuracionTotal(){
        return contenido.stream()
                .mapToInt(Contenido::getDuracion)
                .sum();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Contenido> getContenido() {
        return contenido;
    }
}
