package Model;

public class Person {
    protected String nombre;
    protected String documento;
    protected String correo;

    public Person() {
    }

    public Person(String nombre, String documento, String correo) {
        this.nombre = nombre;
        this.documento = documento;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Person{" + "nombre=" + nombre + ", documento=" + documento + ", correo=" + correo + '}';
    }
    
}
