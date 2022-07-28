package Principal;
//Importar los Packages
import Views.*;
import Model.*;

public class Main {

    public static void main(String[] args) {
        //Crear instancia de la clase conexion
        Conexion conexion = new Conexion();
        conexion.getConnection();
        
        // Crear instancia del JFrame Login
        Login login = new Login();
        
        //Hacer que el proyecto se cargue automaticamente
        login.setVisible(true);
    }
    
}
