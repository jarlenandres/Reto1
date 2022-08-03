/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author harle
 */
public class Operario extends Empleado{
    private boolean manejoMaquinaria;

    public Operario(boolean manejoMaquinaria, String puestoTrabajo, float salario, String nombre, String documento, String correo) {
        super(puestoTrabajo, salario, nombre, documento, correo);
        this.manejoMaquinaria = manejoMaquinaria;
    }

    public boolean isManejoMaquinaria() {
        return manejoMaquinaria;
    }

    public void setManejoMaquinaria(boolean manejoMaquinaria) {
        this.manejoMaquinaria = manejoMaquinaria;
    }

    @Override
    public String toString() {
        return "Person{" + "nombre=" + nombre + ", documento=" + documento + ", correo=" + correo + "puestoTrabajo=" + puestoTrabajo + "manejoMaquinaria=" + manejoMaquinaria + '}';
    }
    
}
