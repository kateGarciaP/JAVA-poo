package platzi.play;
import platzi.play.contenido.*;
import platzi.play.exception.PeliculaExistenException;
import platzi.play.plataforma.Plataforma;
import platzi.play.utils.FileUtils;
import platzi.play.utils.ScannerUtils;
import java.util.List;

public class Main {
    public static final String NOMBRE_PLATAFORMA = "PLATZI PLAY";
    public static final String VERSION ="1.0.0";
    public static final int AGREGAR = 1;
    public static final int MOSTRAR = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int BUSCAR_POR_GENERO = 4;
    public static final int VER_POPULARES = 5;
    public static final int REPRODUCIR = 6;
    public static final int BUSCAR_POR_TIPO = 7;
    public static final int ELIMINAR = 8;
    public static final int SALIR = 9;

    //1. Agregar contenido
    //2. mostrar todo
    //3. Buscar por titulo
    //4. Eliminar
    //5. salir

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + " v" +  VERSION);

        cargarPeliculas(plataforma);

        System.out.println("Mas de " + plataforma.getDuracionTotal() + " minutos de contenido \n");

        plataforma.getContenidoPromocionable().forEach(promocionable -> System.out.println(promocionable.promocionar()));

        while(true){
            int opcionElegida = ScannerUtils.capturarNumero("""
                    Ingrese una de las opciones:
                    1. Agregar contenido
                    2. Mostrar contenido
                    3. Buscar por titulo
                    4. Buscar por genero
                    5. Ver mas populares 
                    6. Reproducir
                    7. Buscar por tipo de contenido
                    8. Eliminar
                    9. salir""");
            switch (opcionElegida){
                case AGREGAR -> {
                    int tipoDeContenido = ScannerUtils.capturarNumero("¿Que tipo de contenido quiere agregar? 1.Pelicula\n 2.Documental");
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
                    Genero genero = ScannerUtils.capturarGenero("Genero del contenido a buscar: ");
                    int duracion = ScannerUtils.capturarNumero("Duración del contenido");
                    double calificacion = ScannerUtils.capturarDecimal("Calificación del contenido");

                    try{
                        if(tipoDeContenido ==1){
                            plataforma.agregar(new Pelicula(nombre,duracion,genero,calificacion));
                        }else {
                            String narrador = ScannerUtils.capturarTexto("Narrador del documental: ");
                            plataforma.agregar(new Documental(nombre,duracion,genero,calificacion,narrador));
                        }

                    }catch (PeliculaExistenException e){
                        System.out.println(e.getMessage());
                    }
                }
                case MOSTRAR -> {
                    List<ResumenContenido> contenidosResumidos = plataforma.getResumenes();
                    contenidosResumidos.forEach(resumen -> System.out.println(resumen.toString()));
                    //titulos.forEach(System.out::println); metodo de referencia hace los mismo que titulo -> sout titulos
                }
                case BUSCAR_POR_TITULO -> {
                    String nombreBuscado = ScannerUtils.capturarTexto("Nombre del contenido a buscar: ");
                    Contenido contenido = plataforma.burcarPorTitulo(nombreBuscado);

                    if(contenido != null){
                        System.out.println(contenido.obtenerFichaTecnica());
                    }else {
                        System.out.println(nombreBuscado + " no existe dentro de " + plataforma.getNombre());
                    }
                }
                case BUSCAR_POR_GENERO -> {
                    Genero generoBuscado = ScannerUtils.capturarGenero("Genero del contenido a buscar: ");

                    List<Contenido> contenidoPorGenero = plataforma.burcarPorGenero(generoBuscado);
                    System.out.println(contenidoPorGenero.size() + " encontrados para el genero " + generoBuscado);
                    contenidoPorGenero.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica()
                            +"\n"));
                }
                case VER_POPULARES -> {
                    int cantidad = ScannerUtils.capturarNumero("Cantidad de resultados a mostrar");

                    List<Contenido> contenidoPopular = plataforma.getPopulares(cantidad);
                    contenidoPopular.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica()
                            + "\n"));
                }
                case REPRODUCIR -> {
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido a reproducir");
                    Contenido contenido = plataforma.burcarPorTitulo(nombre);

                    if(contenido != null){
                        plataforma.reproducir(contenido);
                    }else {
                        System.out.println(nombre + "no existe dentro de " + plataforma.getNombre());
                    }
                }
                case BUSCAR_POR_TIPO -> {
                    int tipoDeContenido = ScannerUtils.capturarNumero("¿Que tipo de contenido quiere ver?\n" +
                            " 1.Pelicula\n 2.Documental");
                    if(tipoDeContenido == 1 ){
                        List<Pelicula> peliculas = plataforma.getPeliculas();
                        peliculas.forEach(pelicula -> System.out.println(pelicula.obtenerFichaTecnica() + "\n"));
                    }else {
                        List<Documental> documentales = plataforma.getDcumentales();
                        documentales.forEach(documental -> System.out.println(documental.obtenerFichaTecnica() + "\n"));
                    }
                }
                case ELIMINAR -> {
                    String nombreAEliminar = ScannerUtils.capturarTexto("Nombre del contenido a buscar: ");
                    Contenido contenido = plataforma.burcarPorTitulo(nombreAEliminar);

                    if(contenido != null){
                        plataforma.delete(contenido);
                        System.out.println(nombreAEliminar + "eliminado");
                    }else {
                        System.out.println(nombreAEliminar + "no existe dentro de " + plataforma.getNombre());
                    }
                }
                case SALIR -> System.exit(0);
            }

        }

    }


     private static void cargarPeliculas(Plataforma plataforma){
        plataforma.getContenido().addAll(FileUtils.leerContenido());
     }

}