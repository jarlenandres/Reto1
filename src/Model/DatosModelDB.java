package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatosModelDB {
    Conexion conexion = new Conexion();
    Connection connection;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    SucursalPuestoTrabajo datosDB;
    
    public ArrayList<SucursalPuestoTrabajo> getPuestoTrabajo(int idSucursal){
        ArrayList listaPuestoTrabajo = new ArrayList();
        String query = "SELECT `idPuestoTrabajo`, `nombrePuestoTrabajo`, `salario`, `FK_idSucursal` FROM `puestotrabajo` INNER JOIN sucursal ON (idSucursal = FK_idSucursal) WHERE idSucursal = ?;";
        try{
            connection = conexion.getConnection();
            pst = connection.prepareStatement(query);
            pst.setInt(1, idSucursal);
            rs = pst.executeQuery();
            while(rs.next()){
                datosDB = new SucursalPuestoTrabajo();
                int idPuestoTrabajo = rs.getInt("idPuestoTrabajo");
                String nombrePuestoTrabajo = rs.getString("nombrePuestoTrabajo");
                float salario = rs.getFloat("salario");
                datosDB.setIdPuestoTrabajo(idPuestoTrabajo);
                datosDB.setNombrePuestoTrabajo(nombrePuestoTrabajo);
                datosDB.setSalario(salario);
                listaPuestoTrabajo.add(datosDB);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        System.out.println(listaPuestoTrabajo);
        return listaPuestoTrabajo;
    }
    
    public ArrayList<SucursalPuestoTrabajo> getSucursales(){
        ArrayList listaSucursales = new ArrayList();
        String query = "SELECT `idSucursal`, `nombreSucursal` FROM `sucursal`;";
        try{
            connection = conexion.getConnection();
            pst = connection.prepareCall(query);
            rs = pst.executeQuery();
            while(rs.next()){
                datosDB = new SucursalPuestoTrabajo();
                int idSucursal = rs.getInt("idSucursal");
                String nombreSucursal = rs.getString("nombreSucursal");
                datosDB.setIdSucursal(idSucursal);
                datosDB.setNombreSucursal(nombreSucursal);
                listaSucursales.add(datosDB);
            }
            }catch(SQLException e){
            System.out.println(e);
        }
        System.out.println(listaSucursales);
        return listaSucursales;
    }
}
