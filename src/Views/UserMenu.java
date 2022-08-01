/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import Controller.*;
import java.sql.Connection;
import Model.Conexion;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author harle
 */
public class UserMenu extends javax.swing.JFrame {

    //Crear una instancia de la clase conexión
    Conexion conexion = new Conexion();
    //Crear una instancia de la clase connection que trae nuestro jar
    Connection connection;
    Statement st;
    ResultSet rs;
    DefaultTableModel contenidoTablaEmpleados, contenidoTablaSucursales;
    ComboBoxModel emunDepartaments, enumZones, enumTypeStreets;

    /**
     * Creates new form UserMenu
     */
    //Contructor
    public UserMenu() {
        emunDepartaments = new DefaultComboBoxModel(EnumDepartament.values());
        enumZones = new DefaultComboBoxModel(EnumZone.values());
        enumTypeStreets = new DefaultComboBoxModel(EnumTypeStreet.values());
        initComponents();
        //Centrar la interfaz
        this.setLocationRelativeTo(this);
        listarEmpleados();
        listarSucursales();
    }

    //Crear método listar empleados
    private void listarEmpleados() {
        String searchUser = txtSearchUser.getText();
        if (searchUser.isEmpty()) {
            String query = "SELECT `nombreEmp`, `apellidos`, `tipoDocumento`, `documento`, `correo`,  `nombreSucursal` FROM `empleado` INNER JOIN sucursal ON idSucursal = FK_idSucursal;";
            try {
                connection = conexion.getConnection();
                //Crear la consula query para la BD
                st = connection.createStatement();
                rs = st.executeQuery(query);
                //Asignar en un objeto los datos que devuelve
                Object[] employee = new Object[6];
                contenidoTablaEmpleados = (DefaultTableModel) tblEmployee.getModel();

                while (rs.next()) {
                    employee[0] = rs.getString("nombreEmp");
                    employee[1] = rs.getString("apellidos");
                    employee[2] = rs.getString("tipoDocumento");
                    employee[3] = rs.getString("documento");
                    employee[4] = rs.getString("correo");
                    employee[5] = rs.getString("nombreSucursal");
                    //en la tabla creamos una nueva fila con los 6 atributos del empleado
                    contenidoTablaEmpleados.addRow(employee);
                    tblEmployee.setModel(contenidoTablaEmpleados);
                }

            } catch (SQLException e) {
                System.out.println("Nooo se pudo cargar información de los empleados");
            }
        } else {
            String query = "SELECT `nombreEmp`, `apellidos`, `tipoDocumento`, `documento`, `correo`,  `nombreSucursal` FROM `empleado` INNER JOIN sucursal WHERE idSucursal = FK_idSucursal AND nombreEmp like '%" + searchUser + "%' OR apellidos like '%" + searchUser + "%' OR documento like '%" + searchUser + "%';";
            try {
                connection = conexion.getConnection();
                //Crear la consula query para la BD
                st = connection.createStatement();
                rs = st.executeQuery(query);
                //Asignar en un objeto los datos que devuelve
                Object[] employee = new Object[7];
                contenidoTablaEmpleados = (DefaultTableModel) tblEmployee.getModel();

                while (rs.next()) {
                    employee[0] = rs.getString("nombreEmp");
                    employee[1] = rs.getString("apellidos");
                    employee[2] = rs.getString("tipoDocumento");
                    employee[3] = rs.getString("documento");
                    employee[4] = rs.getString("correo");
                    employee[5] = rs.getString("nombreSucursal");
                    //en la tabla creamos una nueva fila con los 6 atributos del empleado
                    contenidoTablaEmpleados.addRow(employee);
                    tblEmployee.setModel(contenidoTablaEmpleados);

                    System.out.println("idEmp: " + employee[0] + ", nombre: "
                            + employee[1] + ", documento: " + employee[2] + ", tipoDocumento: " + employee[3]
                            + ", correo" + employee[4]);
                }
            } catch (SQLException e) {
                System.out.println("No se pudo cargar información de los empleados");
            }
        }
    }

    //Método listar sucursales
    public void listarSucursales() {
        String filtroSucursal = txtSearchBranch.getText();
        if (filtroSucursal.isEmpty()) {
            String querySucursal = "SELECT nombreSucursal, nombreDepartamento FROM `sucursal` INNER JOIN `direccion` WHERE idDireccion = FK_idDireccion GROUP BY nombreDepartamento, nombreSucursal;";
            try {
                connection = conexion.getConnection();
                st = connection.createStatement();
                rs = st.executeQuery(querySucursal);
                Object[] sucursal = new Object[2];
                contenidoTablaSucursales = (DefaultTableModel) tblBranchs.getModel();
                while (rs.next()) {
                    sucursal[0] = rs.getString("nombreSucursal");
                    sucursal[1] = rs.getString("nombreDepartamento");
                    contenidoTablaSucursales.addRow(sucursal);
                    tblBranchs.setModel(contenidoTablaSucursales);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        } else {
            String filtroBranch = "SELECT `nombreSucursal`, `nombreDepartamento` FROM `sucursal` INNER JOIN `direccion` ON idDireccion = FK_idDireccion AND nombreDepartamento LIKE '%" + filtroSucursal + "%';";
            try {
                connection = conexion.getConnection();
                st = connection.createStatement();
                rs = st.executeQuery(filtroBranch);
                Object[] sucursal = new Object[2];
                contenidoTablaSucursales = (DefaultTableModel) tblBranchs.getModel();
                while (rs.next()) {
                    sucursal[0] = rs.getString("nombreSucursal");
                    sucursal[1] = rs.getString("nombreDepartamento");
                    System.out.println("sucursal " + sucursal[0] + " departamento " + sucursal[1]);
                    contenidoTablaSucursales.addRow(sucursal);
                    tblBranchs.setModel(contenidoTablaSucursales);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    //Método borrarDatosTablaDepartamentos
    public void borrarDatosTablaSucursales() {
        for (int i = 0; i < tblBranchs.getRowCount(); i++) {
            contenidoTablaSucursales.removeRow(i);
            i = i - 1;
        }
        cbDepartament.setSelectedIndex(0);
        cbStreet.setSelectedIndex(0);
        cbZone.setSelectedIndex(0);
        txtNum1.setText("");
        txtNum2.setText("");
        txtNum3.setText("");
    }

    //Crear método borrar registro para limpiar la tabla
    private void borrarRegistroTabla() {
        //getRowCount devuelve la cantidad de filas que tiene la tabla
        for (int i = 0; i < tblEmployee.getRowCount(); i++) {
            contenidoTablaEmpleados.removeRow(i);
            i = i - 1;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbpEmpresa = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cbDepartament = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbZone = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cbStreet = new javax.swing.JComboBox<>();
        txtNum1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNum2 = new javax.swing.JTextField();
        txtNum3 = new javax.swing.JTextField();
        btnSaveBranch = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBranchs = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtSearchBranch = new javax.swing.JTextField();
        btnListBranch = new javax.swing.JButton();
        btnAddEmployee = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmployee = new javax.swing.JTable();
        btnAddUser = new javax.swing.JButton();
        btnShow = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSearchUser = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbpEmpresa.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(219, 233, 233));
        jPanel4.setForeground(new java.awt.Color(201, 235, 235));

        jLabel5.setText("Departamento");

        cbDepartament.setModel(emunDepartaments);

        jLabel6.setText("Zona");

        cbZone.setModel(enumZones);

        jLabel7.setText("Tipo calle");

        cbStreet.setModel(enumTypeStreets);

        jLabel8.setText("#");

        txtNum3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNum3ActionPerformed(evt);
            }
        });

        btnSaveBranch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/comprobado.png"))); // NOI18N
        btnSaveBranch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveBranchActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Registro dirección");

        jLabel11.setText("-");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbDepartament, 0, 128, Short.MAX_VALUE)
                    .addComponent(cbStreet, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtNum1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNum2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(txtNum3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSaveBranch))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbZone, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(168, 168, 168))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbDepartament, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cbZone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSaveBranch))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNum3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNum2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtNum1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbStreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        tblBranchs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sucursal", "Departamento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBranchs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBranchsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblBranchs);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo.png"))); // NOI18N

        btnListBranch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/showUser.png"))); // NOI18N
        btnListBranch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListBranchActionPerformed(evt);
            }
        });

        btnAddEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/avatar.png"))); // NOI18N
        btnAddEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEmployeeActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Departamento");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddEmployee))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearchBranch, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnListBranch, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnListBranch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearchBranch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(btnAddEmployee)))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbpEmpresa.addTab("Sucursales", jPanel3);

        jPanel2.setBackground(new java.awt.Color(219, 233, 233));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo.png"))); // NOI18N

        tblEmployee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellidos", "Tipo documento", "Documento", "Correo", "Sucursal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmployeeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmployee);

        btnAddUser.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/avatar.png"))); // NOI18N
        btnAddUser.setText("Nuevo");
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });

        btnShow.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/showUser.png"))); // NOI18N
        btnShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Lista de empleados");
        jLabel2.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Misión TIC 2022");

        txtSearchUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchUserActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Usuario");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(177, 177, 177)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtSearchUser, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnShow, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)))
                        .addComponent(btnAddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 819, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSearchUser, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addComponent(btnShow))))
                    .addComponent(jLabel1))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tbpEmpresa.addTab("Empleados", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpEmpresa)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpEmpresa)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        //Generar actualización de la información
        borrarRegistroTabla();
        listarEmpleados();
    }//GEN-LAST:event_btnShowActionPerformed

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        AddUserForm addUserForm = new AddUserForm(this, true);
        addUserForm.setVisible(true);
        //Generar actualización de la información
        borrarRegistroTabla();
        listarEmpleados();
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void tblEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmployeeMouseClicked
        // Capturar el número de la fila seleccionada
        int filaSeleccionada = tblEmployee.getSelectedRow();
        //Validar si el usuario selecciona una fila
        if (filaSeleccionada <= -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un empleado.", "", JOptionPane.WARNING_MESSAGE);
        } else {
            //Indicamos los datos de la fila seleccionada
            String name = (String) tblEmployee.getValueAt(filaSeleccionada, 0).toString();
            String surname = (String) tblEmployee.getValueAt(filaSeleccionada, 1).toString();
            String tipoDoc = (String) tblEmployee.getValueAt(filaSeleccionada, 2).toString();
            String document = (String) tblEmployee.getValueAt(filaSeleccionada, 3).toString();
            String email = (String) tblEmployee.getValueAt(filaSeleccionada, 4).toString();
            String sucursal = (String)(tblEmployee.getValueAt(filaSeleccionada, 5).toString());

            //Instanciar el JDialg para mostar la info del empleado seleccionado
            ShowUserForm showUserForm = new ShowUserForm(this, true);
            showUserForm.recibeDatos(name, surname, tipoDoc, document, email, sucursal);
            showUserForm.setVisible(true);
            //Actualizamos la info de la tabla en caso que se hubiese editado
            borrarRegistroTabla();
            listarEmpleados();
        }
    }//GEN-LAST:event_tblEmployeeMouseClicked

    private void txtNum3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNum3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNum3ActionPerformed

    private void btnSaveBranchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveBranchActionPerformed
        String departament = cbDepartament.getSelectedItem().toString();
        String zone = cbZone.getSelectedItem().toString();
        String typeStreet = cbStreet.getSelectedItem().toString();
        String num1 = txtNum1.getText();
        String num2 = txtNum2.getText();
        String num3 = txtNum3.getText();
        if (num1.isEmpty() || num2.isEmpty() || num3.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Faltan campos por diligenciar", "", JOptionPane.ERROR_MESSAGE);
        }else{
            String queryDireccion = "INSERT INTO `direccion`(`zona`, `tipoCalle`, `numero1`, `numero2`, `numero3`, `nombreDepartamento`) VALUES ('"
                + zone + "','" + typeStreet + "','" + num1 + "','" + num2 + "','" + num3 + "','" + departament + "');";
        try {
            connection = conexion.getConnection();
            st = connection.createStatement();
            st.executeUpdate(queryDireccion);

            String queryIdDireccion = "SELECT idDireccion FROM `direccion` WHERE nombreDepartamento = '"
                    + departament + "' AND zona = '" + zone + "' AND tipoCalle = '" + typeStreet
                    + "' AND numero1 = '" + num1 + "' AND numero2 = '" + num2 + "' AND numero3 = '" + num3 + "';";
            try {
                rs = st.executeQuery(queryIdDireccion);
                while (rs.next()) {
                    int idDireccion = rs.getInt("idDireccion");
                    //Instancia de la vista sucursal
                    BranchForm branchForm = new BranchForm(this, true);
                    branchForm.setVisible(true);
                    branchForm.recibeDatosDireccion(idDireccion);
                    JOptionPane.showMessageDialog(this, "Nueva sucursal creada correctamente");
                }
                borrarDatosTablaSucursales();
                listarSucursales();
            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "No se pudo crear la sucursal", "", JOptionPane.ERROR_MESSAGE);
        }
        }
        
    }//GEN-LAST:event_btnSaveBranchActionPerformed

    private void btnAddEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEmployeeActionPerformed
        int nroFila = tblBranchs.getSelectedRow();
        String sucursal = tblBranchs.getValueAt(nroFila, 0).toString();
        String querySucursal = "SELECT idSucursal FROM `sucursal` WHERE nombreSucursal = '" + sucursal + "'";
        System.out.println(querySucursal);
        try {
            connection = conexion.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(querySucursal);
            while (rs.next()) {
                int idSucursal = rs.getInt("idSucursal");
                ListEmployee empLista = new ListEmployee(this, true);
                empLista.setVisible(true);
                empLista.recibirIdSucursal(idSucursal);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_btnAddEmployeeActionPerformed

    private void btnListBranchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListBranchActionPerformed
        borrarDatosTablaSucursales();
        listarSucursales();
    }//GEN-LAST:event_btnListBranchActionPerformed

    private void txtSearchUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchUserActionPerformed

    private void tblBranchsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBranchsMouseClicked
        // Capturar el número de la fila seleccionada
        int filaSelect = tblBranchs.getSelectedRow();
        //Validar si se selecciona una sucursal
        if (filaSelect == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una sucursal", "", JOptionPane.ERROR_MESSAGE);
        } else {
            //Indicar los datos seleccionados
            String sucursal = (String) tblBranchs.getValueAt(filaSelect, 0).toString();
            String departament = (String) tblBranchs.getValueAt(filaSelect, 1).toString();
            String queryIdSucursal = "SELECT idSucursal FROM `sucursal` WHERE nombreSucursal = '" + sucursal + "';";
            System.out.println(queryIdSucursal);
            try {
                connection = conexion.getConnection();
                st = connection.createStatement();
                rs = st.executeQuery(queryIdSucursal);
                while (rs.next()) {
                    int idSucursal = rs.getInt("idSucursal");
                    //Instanciar el JDialog para mostar la info de la sucursal
                    ShowBranchForm showBranchForm = new ShowBranchForm(this, true);
                    //showBranchForm.recibeDatos(departament, zone, typeStreet, num1, num2, num3);
                    showBranchForm.setVisible(true);
                    showBranchForm.recibeIdSucursal(idSucursal);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            //Actualizar la info de la tabla en caso de ser editado
            borrarDatosTablaSucursales();
            listarSucursales();
        }
    }//GEN-LAST:event_tblBranchsMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddEmployee;
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnListBranch;
    private javax.swing.JButton btnSaveBranch;
    private javax.swing.JButton btnShow;
    private javax.swing.JComboBox<String> cbDepartament;
    private javax.swing.JComboBox<String> cbStreet;
    private javax.swing.JComboBox<String> cbZone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblBranchs;
    private javax.swing.JTable tblEmployee;
    private javax.swing.JTabbedPane tbpEmpresa;
    private javax.swing.JTextField txtNum1;
    private javax.swing.JTextField txtNum2;
    private javax.swing.JTextField txtNum3;
    private javax.swing.JTextField txtSearchBranch;
    private javax.swing.JTextField txtSearchUser;
    // End of variables declaration//GEN-END:variables
}
