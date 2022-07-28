package Model;
//Importar paquetes de conexion a la bd

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    Connection connection;
    
    //Crear variables de conexión con la bd
    String driver = "com.mysql.cj.jdbc.Driver";
    String cadenaConexion = "jdbc:mysql://localhost:3306/reto_mtic";
    String user = "root";
    String password = "";
    
    //Crear constructor de la clase sin atrs

    public Conexion() {
        //Validar si nos podemos conectar a la bd
        try {
            Class.forName(driver);
            //Cadena de conexión
            connection = DriverManager.getConnection(cadenaConexion, user, password);
            if (connection != null) {
                System.out.println("Conexión exitosa con la BD");
            }
        }catch (ClassNotFoundException | SQLException e){
            System.out.println("No se puede establecer conexión con la BD");
        }
    }
        
        public Connection getConnection(){
            return connection;
        }
    }
    
