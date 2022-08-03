/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author harle
 */
public class Domiciliario extends Empleado{
    private String tipoTransporte;

    public Domiciliario(String tipoTransporte, String puestoTrabajo, float salario, String nombre, String documento, String correo) {
        super(puestoTrabajo, salario, nombre, documento, correo);
        this.tipoTransporte = tipoTransporte;
    }

    public String getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(String tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    @Override
    public String toString() {
        return "Person{" + "nombre=" + nombre + ", documento=" + documento + ", correo=" + correo + "puestoTrabajo=" + puestoTrabajo + ", salario=" + salario + "tipoTransporte=" + tipoTransporte + '}';
    }
    
}
