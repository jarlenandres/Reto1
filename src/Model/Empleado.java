package Model;

public class Empleado extends Person{
    protected String puestoTrabajo;
    protected float salario;

    public Empleado(String puestoTrabajo, float salario, String nombre, String documento, String correo) {
        super(nombre, documento, correo);
        this.puestoTrabajo = puestoTrabajo;
        this.salario = salario;
    }
    
    public String getPuestoTrabajo() {
        return puestoTrabajo;
    }

    public void setPuestoTrabajo(String puestoTrabajo) {
        this.puestoTrabajo = puestoTrabajo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Person{" + "nombre=" + nombre + ", documento=" + documento + ", correo=" + correo + "puestoTrabajo=" + puestoTrabajo + ", salario=" + salario + '}';
        
    }
    
}
