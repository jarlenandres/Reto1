/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author harle
 */
public class Gerente extends Empleado{
    protected int catSubordinados;

    public Gerente(int catSubordinados, String puestoTrabajo, float salario, String nombre, String documento, String correo) {
        super(puestoTrabajo, salario, nombre, documento, correo);
        this.catSubordinados = catSubordinados;
    }

    public int getCatSubordinados() {
        return catSubordinados;
    }

    public void setCatSubordinados(int catSubordinados) {
        this.catSubordinados = catSubordinados;
    }

    @Override
    public String toString() {
        return "Person{" + "nombre=" + nombre + ", documento=" + documento + ", correo=" + correo + "puestoTrabajo=" + puestoTrabajo + ", salario=" + salario + "catSubordinados=" + catSubordinados + '}';
    }
}
