package platzi.play.plataforma;
import platzi.play.contenido.Contenido;

import java.time.LocalDateTime;

public class Usuario {

    private String nombre;
    private String email;
    private int edad;
    private LocalDateTime fechaDeRegistro;

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.fechaDeRegistro = LocalDateTime.now();
    }

    public void ver(Contenido contenido){
        System.out.println(nombre + " esta viendo...");
        contenido.repoducir();
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public int getEdad() {
        return edad;
    }

    public LocalDateTime getFechaDeRegistro() {
        return fechaDeRegistro;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

}
