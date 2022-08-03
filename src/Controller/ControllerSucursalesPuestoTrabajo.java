package Controller;

import Model.Conexion;
import Model.DatosModelDB;
import Model.Sucursal;
import Model.SucursalPuestoTrabajo;
import Views.AddUserForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class ControllerSucursalesPuestoTrabajo implements ActionListener{
    private final AddUserForm view;
    Conexion conexion = new Conexion();
    Connection connection;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    DatosModelDB model = new DatosModelDB();
    ArrayList<SucursalPuestoTrabajo> list;

    public ControllerSucursalesPuestoTrabajo(AddUserForm view) {
        this.view = view;
        this.getListaSucursales();
        Sucursal sucursal = (Sucursal)view.cbBranch.getSelectedItem();
        getListaPuestoTrabajos(sucursal.getIdSucursal());
        events();
    }
    public final void getListaSucursales(){
        list = model.getSucursales();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                int idSucursal = list.get(i).getIdSucursal();
                String nombreSucursal = list.get(i).getNombreSucursal();
                view.cbBranch.addItem(new Sucursal(idSucursal, nombreSucursal));
            }
        }else{
            JOptionPane.showMessageDialog(null, "No se encontraron sucursales", "Sucursales", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public final void getListaPuestoTrabajos(int idSucursal){
        list = model.getPuestoTrabajo(idSucursal);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                DefaultComboBoxModel model = (DefaultComboBoxModel)view.cbPuestroTrabajo.getModel();
                Object [] puestoTrabajo = new Object [2];
                puestoTrabajo[0] = list.get(i).getIdPuestoTrabajo();
                puestoTrabajo[1] = list.get(i).getNombrePuestoTrabajo();
                model.addElement(puestoTrabajo[1]);
                view.cbPuestroTrabajo.setModel(model);
                System.out.println(model);
            }
        }
    }
    
    public final void events(){
        view.cbBranch.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object eventos = ae.getSource();
        if (eventos.equals(view.cbBranch)) {
            view.cbPuestroTrabajo.removeAllItems();
            Sucursal sucursal = (Sucursal)view.cbBranch.getSelectedItem();
            getListaPuestoTrabajos(sucursal.getIdSucursal());
        }
    }
    
    
}
