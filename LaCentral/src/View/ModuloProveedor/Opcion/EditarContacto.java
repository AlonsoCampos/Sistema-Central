/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View.ModuloProveedor.Opcion;

import Model.Contactos;
import Model.DisplayItem;
import Model.Domicilios;
import Model.Empresasproveedoras;
import Model.Personas;
import static View.ModuloCliente.Opcion.AñadirCliente.claveDoc;
import View.SeleccionarDireccion;
import View.SeleccionarDireccionContacto;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alonso
 */
public class EditarContacto extends javax.swing.JFrame {

    /**
     * Creates new form EditarContacto
     */
    public EditarContacto() {
        initComponents();
        actualizar();
        Mostrar();
        LoadEmpresas();
    }
    boolean validarContacto()
    {
        if(nombrejTextField.getText().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(null, "No puedes dejar vacio el nombre del contacto","Sistema Central", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if(nombrejTextField.getText().length()>=45)
        {
            JOptionPane.showMessageDialog(null, "No puedes sobrepasar los 45 caracteres para el nombre","Sistema Central", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if(paternojTextField.getText().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(null, "No puedes dejar vacio el apellido paterno del contacto","Sistema Central", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if(paternojTextField.getText().length()>=45)
        {
            JOptionPane.showMessageDialog(null, "No puedes sobrepasar los 45 caracteres para el apeklido paterno","Sistema Central", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if(maternojTextField.getText().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(null, "No puedes dejar vacio el apellido materno del contacto","Sistema Central", JOptionPane.ERROR_MESSAGE);
            return false;    
        }
        else if(maternojTextField.getText().length()>=45)
        {
            JOptionPane.showMessageDialog(null, "No puedes sobrepasar los 45 caracteres para el apellido materno","Sistema Central", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if(telefonojTextField.getText().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(null, "No puedes dejar vacio el telefono del contacto","Sistema Central", JOptionPane.ERROR_MESSAGE);
            return false;    
        }
        else if(telefonojTextField.getText().length()<12)
        {
            JOptionPane.showMessageDialog(null, "Tienes que ingresar 12 caracteres al menos para el telefono","Sistema Central", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if(telefonojTextField.getText().length()<12)
        {
            JOptionPane.showMessageDialog(null, "Tienes que ingresar al menos 12 caracteres para el telefono","Sistema Central", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        else if(direccionjTextArea.getText().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(null, "No puedes dejar vacio la dirección del contacto","Sistema Central", JOptionPane.ERROR_MESSAGE);
            return false;            
        }
        else
        {
            return true;
        }
    }
         void actualizar(){
        buscarjTextField.getDocument().addDocumentListener(new DocumentListener() {
            
            public void insertUpdate(DocumentEvent e) {
                limpiarTabla();
                buscarEmpresa();
                buscarContacto();
            }

            public void removeUpdate(DocumentEvent e) {
               limpiarTabla();
            }

            public void changedUpdate(DocumentEvent e) {
             limpiarTabla();
            }

        });
    }
     void Mostrar()
    {  
            for(Contactos oo: Controller.ProveedorController.Lista2())
            {
                        DefaultTableModel datos=(DefaultTableModel)contactojTable.getModel();
                        Vector resultado=new Vector();
                        if(oo.isActivo()==true)
                        {
                            resultado.add(oo.getClaveContacto());                        
                            resultado.add(oo.getPersonas().getNombre()+" "+ oo.getPersonas().getApellidoPaterno()
                                    +" "+oo.getPersonas().getApellidoMaterno());
                            resultado.add(oo.getEmpresasproveedoras().getNombre());
                            resultado.add("Habilitado");
                            datos.addRow(resultado);
                        }
            }
        

    }
    void buscarEmpresa()
    {
           for(Empresasproveedoras o: Controller.ProveedorController.Lista(buscarjTextField.getText()))
        {
            for(Contactos oo: Controller.ProveedorController.Lista2())
            {
                String s=String.valueOf(o.getClaveEmpresa());
                String ss= String.valueOf(oo.getEmpresasproveedoras().getClaveEmpresa());
                if(s.equalsIgnoreCase(ss))
                {

                        DefaultTableModel datos=(DefaultTableModel)contactojTable.getModel();
                        Vector resultado=new Vector();
                        if(oo.isActivo()==true)
                        {
                            resultado.add(oo.getClaveContacto());                        
                            resultado.add(oo.getPersonas().getNombre()+" "+ oo.getPersonas().getApellidoPaterno()
                                    +" "+oo.getPersonas().getApellidoMaterno());
                            resultado.add(oo.getEmpresasproveedoras().getNombre());
                            resultado.add("Habilitado");
                            datos.addRow(resultado);
                        }
                }

            }
        }

    }
    
    void buscarContacto()
    {
          for(Personas o: Controller.ProveedorController.ListaContacto(buscarjTextField.getText()))
        {
            for(Contactos oo: Controller.ProveedorController.Lista2())
            {
                String s=String.valueOf(o.getIdPersona());
                String ss= String.valueOf(oo.getPersonas().getIdPersona());
                if(s.equalsIgnoreCase(ss))
                {

                        DefaultTableModel datos=(DefaultTableModel)contactojTable.getModel();
                        Vector resultado=new Vector();                        
                        if(oo.isActivo()==true)
                            {    
                            resultado.add(oo.getClaveContacto());
                            resultado.add(oo.getPersonas().getNombre()+" "+ oo.getPersonas().getApellidoPaterno()
                                    +" "+oo.getPersonas().getApellidoMaterno());
                            resultado.add(oo.getEmpresasproveedoras().getNombre());
                                resultado.add("Habilitado");
                            datos.addRow(resultado);
                            }
                   
                }

            }
        }
     
    }
        public  void LoadEmpresas()
    {
        DefaultComboBoxModel datos=new DefaultComboBoxModel();
        List<Empresasproveedoras> resultado=Controller.EmpresaProveedoraController.showEmpresa();
        /*Se crea un objeto de tipo RecuperacionContraseña para
        * poder acceder a todos los objetos de la clase RecuperacionContraseña
        */  
        for(Empresasproveedoras o: resultado){
           DisplayItem d = new DisplayItem(o.getNombre(), o.getClaveEmpresa());
            datos.addElement(d);
        
        } 
        empresajComboBox.setModel(datos);
    }
    
    void limpiarTabla()
    {
        while (contactojTable.getRowCount()!=0)
        {
         ((DefaultTableModel)contactojTable.getModel()).removeRow(0);
        }
    }
    void CargarInfo()
    {
            int selected = contactojTable.getSelectedRow(); 
            String clave=contactojTable.getValueAt(selected, 0).toString();
            Contactos mostrar = Controller.ContactosController.BusquedaId(Integer.parseInt(clave));
            nombrejTextField.setText(mostrar.getPersonas().getNombre());
            paternojTextField.setText(mostrar.getPersonas().getApellidoPaterno());
            maternojTextField.setText(mostrar.getPersonas().getApellidoMaterno());
            telefonojTextField.setText(mostrar.getPersonas().getTelefono());
            telefono2jTextField.setText(mostrar.getTelefono());
            direccionjTextArea.setText(mostrar.getPersonas().getDomicilios().getDomicilio());
            empresajComboBox.setSelectedItem(mostrar.getEmpresasproveedoras().getNombre());
        
    }
    void limpiar(){
        nombrejTextField.setText("");
        paternojTextField.setText("");
        maternojTextField.setText("");
        direccionjTextArea.setText("");
        telefonojTextField.setText("");
        telefono2jTextField.setText("");
    }
    public static Domicilios domicilioContacto;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new org.edisoncor.gui.panel.Panel();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nombrejTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        paternojTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        maternojTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        telefonojTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        telefono2jTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        empresajComboBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        direccionjTextArea = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        contactojTable = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        buscarjTextField = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Editar Contacto");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(163, 220, 236));

        jLabel1.setText("Nombre:");

        nombrejTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombrejTextFieldKeyTyped(evt);
            }
        });

        jLabel3.setText("Apellido Paterno:");

        paternojTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                paternojTextFieldKeyTyped(evt);
            }
        });

        jLabel4.setText("Apellido Materno:");

        maternojTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                maternojTextFieldKeyTyped(evt);
            }
        });

        jLabel5.setText("Telefono 1:");

        telefonojTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefonojTextFieldKeyTyped(evt);
            }
        });

        jLabel6.setText("Telefono Opcional: ");

        telefono2jTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefono2jTextFieldKeyTyped(evt);
            }
        });

        jLabel7.setText("Empresa:");

        direccionjTextArea.setEditable(false);
        direccionjTextArea.setColumns(20);
        direccionjTextArea.setRows(5);
        jScrollPane1.setViewportView(direccionjTextArea);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/guardar.png"))); // NOI18N
        jButton1.setText("Editar");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        contactojTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Contacto", "Empresa", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        contactojTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contactojTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(contactojTable);
        contactojTable.getColumnModel().getColumn(0).setResizable(false);
        contactojTable.getColumnModel().getColumn(1).setResizable(false);
        contactojTable.getColumnModel().getColumn(2).setResizable(false);
        contactojTable.getColumnModel().getColumn(3).setResizable(false);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/Search.png"))); // NOI18N
        jLabel8.setText("Buscar:");

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/Add.png"))); // NOI18N
        jToggleButton1.setText("Dirección");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscarjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(paternojTextField)
                        .addComponent(maternojTextField)
                        .addComponent(telefonojTextField)
                        .addComponent(telefono2jTextField)
                        .addComponent(empresajComboBox, 0, 175, Short.MAX_VALUE))
                    .addComponent(nombrejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(jToggleButton1)))
                .addContainerGap(398, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(nombrejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(paternojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(maternojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(telefonojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(telefono2jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(empresajComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(buscarjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE))
        );

        jPanel1.setBounds(300, 0, 1210, 1080);
        jLayeredPane2.add(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Ima/Fondo.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setBounds(0, 0, 1510, 1080);
        jLayeredPane2.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1510, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1079, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/agregar.png"))); // NOI18N
        jMenu1.setText("Añadir");

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/user_48.png"))); // NOI18N
        jMenuItem4.setText("Añadir Empresa o Proveedor");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/usuarios.png"))); // NOI18N
        jMenuItem6.setText("Añador Contacto");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/Herramientas.png"))); // NOI18N
        jMenu2.setText("Mantenimiento");

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/actualizar.png"))); // NOI18N
        jMenu3.setText("Editar");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/user_48.png"))); // NOI18N
        jMenuItem1.setText("Empresa o Proveedor");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/usuarios.png"))); // NOI18N
        jMenuItem7.setText("Contacto");
        jMenuItem7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem7MouseClicked(evt);
            }
        });
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenu2.add(jMenu3);

        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/Eliminar.png"))); // NOI18N
        jMenu8.setText("Dar de Baja");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/user_48.png"))); // NOI18N
        jMenuItem2.setText("Empresa o Proveedor");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem2MouseClicked(evt);
            }
        });
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/usuarios.png"))); // NOI18N
        jMenuItem3.setText("Contacto");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem3MouseClicked(evt);
            }
        });
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem3);

        jMenu2.add(jMenu8);

        jMenu9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/Cancelar.png"))); // NOI18N
        jMenu9.setText("Dar de Alta");

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/user_48.png"))); // NOI18N
        jMenuItem8.setText("Empresa o Proveedor");
        jMenuItem8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem8MouseClicked(evt);
            }
        });
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem8);

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/usuarios.png"))); // NOI18N
        jMenuItem9.setText("Contacto");
        jMenuItem9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem9MouseClicked(evt);
            }
        });
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem9);

        jMenu2.add(jMenu9);

        jMenuBar1.add(jMenu2);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/paper_content_48.png"))); // NOI18N
        jMenu4.setText("Reportes");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/inicio.png"))); // NOI18N
        jMenu7.setText("Volver a Menu Principal");
        jMenu7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu7MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu7);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ima/ayuda.png"))); // NOI18N
        jMenu5.setText("Ayuda");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        SeleccionarDireccionContacto contacto = Clases.Singleton.getDireccionContacto();
        contacto.seleccionarjButton.setVisible(true);
        contacto.jButton1.setVisible(false);
        contacto.setVisible(true);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void contactojTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contactojTableMouseClicked
        CargarInfo();
    }//GEN-LAST:event_contactojTableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(contactojTable.getSelectedRow()>=0)
        {           
            int selected = contactojTable.getSelectedRow(); 
            String clave=contactojTable.getValueAt(selected, 0).toString();
            Contactos mostrar = Controller.ContactosController.BusquedaId(Integer.parseInt(clave));
            //Aqui falta recibir la direccion
            if(domicilioContacto==null)
            {
                Domicilios dom = Controller.DomiciliosController.BusquedaClave(Controller.DomiciliosController.IdLastDomicilio());
                domicilioContacto= dom;
            }
            
            Empresasproveedoras empresa = Controller.EmpresaProveedoraController.busquedaEmpresa(empresajComboBox.getSelectedItem().toString());
            Personas persona = Controller.PersonasController.BusquedaClave(mostrar.getPersonas().getIdPersona());
            if(validarContacto()==true)
            {
                if(Controller.PersonasController.UpdatePersona(mostrar.getPersonas().getIdPersona(),nombrejTextField.getText()
                        ,paternojTextField.getText(), maternojTextField.getText(), telefonojTextField.getText()
                        ,domicilioContacto)==true)
                {
                        if(Controller.ContactosController.UpdateContacto(mostrar.getClaveContacto(),
                                mostrar.getFechaRegistro(), mostrar.isActivo(), telefono2jTextField.getText()
                                , empresa,persona))
                        {
                            JOptionPane.showMessageDialog(null, "Se ha dado de actualizado correctamente","Sistema Central", JOptionPane.INFORMATION_MESSAGE);
                            limpiarTabla();
                            Mostrar();
                            limpiar();
                        }
                        else
                        {
                               JOptionPane.showMessageDialog(null, "A ocurrido un error intente mas tarde","Sistema Central", JOptionPane.ERROR_MESSAGE);
                        }

                }else{
                    JOptionPane.showMessageDialog(null, "A ocurrido un error intente mas tarde","Sistema Central", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Tienes que seleccionar un registro primero","Sistema Central",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void nombrejTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombrejTextFieldKeyTyped
char car = evt.getKeyChar();
if((car<'a' || car>'z') && (car<'A' || car>'Z')) evt.consume();
    }//GEN-LAST:event_nombrejTextFieldKeyTyped

    private void paternojTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paternojTextFieldKeyTyped
char car = evt.getKeyChar();
if((car<'a' || car>'z') && (car<'A' || car>'Z')) evt.consume();
    }//GEN-LAST:event_paternojTextFieldKeyTyped

    private void maternojTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_maternojTextFieldKeyTyped
char car = evt.getKeyChar();
if((car<'a' || car>'z') && (car<'A' || car>'Z')) evt.consume();
    }//GEN-LAST:event_maternojTextFieldKeyTyped

    private void telefonojTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonojTextFieldKeyTyped
char car = evt.getKeyChar();
if(telefonojTextField.getText().length()>=12) evt.consume();
if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_telefonojTextFieldKeyTyped

    private void telefono2jTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefono2jTextFieldKeyTyped
char car = evt.getKeyChar();
if(telefono2jTextField.getText().length()>=12) evt.consume();
if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_telefono2jTextFieldKeyTyped

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        new AñadirProveedor().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        new AñadirContacto().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
        new EditarProveedor().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new EditarProveedor().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem7MouseClicked
        new EditarContacto().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem7MouseClicked

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        new EditarContacto().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MouseClicked
        new BajaProveedor().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem2MouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new BajaProveedor().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MouseClicked
        new BajaContacto().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem3MouseClicked

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new BajaContacto().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem8MouseClicked
        new AltaProveedor().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem8MouseClicked

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        new AltaProveedor().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem9MouseClicked
        new AltaContacto().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem9MouseClicked

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        new AltaContacto().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        new ReporteProveedor().setVisible(true);
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenu7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu7MouseClicked
        Clases.VistasController.RegresarPrincipalProveedor();
        dispose();
    }//GEN-LAST:event_jMenu7MouseClicked

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        new AyudaProveedor().setVisible(true);
    }//GEN-LAST:event_jMenu5MouseClicked

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
            java.util.logging.Logger.getLogger(EditarContacto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarContacto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarContacto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarContacto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditarContacto().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscarjTextField;
    private javax.swing.JTable contactojTable;
    public static javax.swing.JTextArea direccionjTextArea;
    private javax.swing.JComboBox empresajComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextField maternojTextField;
    private javax.swing.JTextField nombrejTextField;
    private org.edisoncor.gui.panel.Panel panel1;
    private javax.swing.JTextField paternojTextField;
    private javax.swing.JTextField telefono2jTextField;
    private javax.swing.JTextField telefonojTextField;
    // End of variables declaration//GEN-END:variables
}
