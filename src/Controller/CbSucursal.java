/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.Connection;
import Model.Conexion;
import Model.Sucursal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author harle
 */
public class CbSucursal {

    Connection connection;
    Conexion conexion = new Conexion();
    Statement st;
    ResultSet rs;

    public CbSucursal() {
    }

    public ArrayList getListSucursales() {
        ArrayList mListaSucursales = new ArrayList();
        Sucursal sucursal = null;
        String querySucursales = "SELECT idSucursal, nombreSucursal FROM sucursal;";
        try {
            connection = conexion.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(querySucursales);
            while(rs.next()){
                sucursal = new Sucursal();
                //Cunsulta que nos devuelve la BD
                int idSucursal = rs.getInt("idSucursal");
                String nombreSucursal = rs.getString("nombreSucuesal");
                //Asignar los valores que nos da la BD a los atributos de la clase sucursal
                sucursal.setIdSucursal(idSucursal);
                sucursal.setNameSucursal(nombreSucursal);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println(mListaSucursales);
        return mListaSucursales;
    }
}
