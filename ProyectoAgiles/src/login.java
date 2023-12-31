/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class login extends javax.swing.JFrame {

    /**
     * Creates new form login
     */
    FondoPanel fondo = new FondoPanel();

    public login() {
        this.setContentPane(fondo);
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    public void ingresar() {
        if (jtxtUsuario.getText().equals("") || jpwdPass.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Por favor llene todos los campos.");
        } else {
            try {
                Conexion cc = new Conexion();
                Connection con = cc.conectar();
                String sql = "select cedula, contrasenia from login where cedula='" + jtxtUsuario.getText() + "' and contrasenia ='" + jpwdPass.getText() + "'";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    //credenciales correctas
                    //abrir ventana principal
                    JOptionPane.showMessageDialog(this, "Datos correctos. Ingresando al sistema...");
                        Principal pr= new Principal();
                        pr.setVisible(true);
                        dispose();
                } else if (validaciones() == false) {
                    JOptionPane.showMessageDialog(this, "Datos incorrectos. Vuelva a intentarlo.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Datos no encontrados.\nPor favor comuníquese con el administrador.");
            }
        }
    }

    public boolean validaciones() {
        if (controlIngresoLetrasYNumerosUsuario(jtxtUsuario) == false) {
            JOptionPane.showMessageDialog(this, "Error. Para el ingreso de usuario solo se permiten números y letras, no espacios o caracteres especiales");
        } else {
            validarContrasena();
        }

        return true;
    }

    public boolean controlIngresoLetrasYNumerosUsuario(javax.swing.JTextField jtxtUsuario) {
        String us = jtxtUsuario.getText();
        if (!((us.toUpperCase().matches("[A-Z]*")) || (us.toUpperCase().matches("[0-9]*")))) {
            return false;
        }
        return true;
    }

    public boolean validarUsuario() {
        boolean resultado = false;
        String user = jtxtUsuario.getText();
        String sql = "select * from login where cedula ='" + user + "'";
        try {
            Conexion cc = new Conexion();
            Connection con = cc.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                resultado = true;
            } else {
                resultado = false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error. " + e.getMessage());
        }

        return resultado;
    }

    public boolean validarContrasena() {
        boolean resultado = false;
        String user = jtxtUsuario.getText();
        String pass = String.valueOf(jpwdPass.getPassword());
        String sql = "select cedula, contrasenia from login where cedula='" + user + "' and contrasenia ='" + pass + "'";
        try {
            Conexion cc = new Conexion();
            Connection con = cc.conectar();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                resultado = true;
                //usuario correcto
            } else if (validarUsuario() == true && resultado == false) {
                JOptionPane.showMessageDialog(this, "Error en el ingreso. Contraseña incorrecta");
            } else if ((validarUsuario() == false && resultado == false)) {
                JOptionPane.showMessageDialog(this, "Error en el ingreso. Usuario inexistente");
            } else {
                JOptionPane.showMessageDialog(this, "acá");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error. " + e.getMessage());

        }
        return resultado;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new FondoPanel();
        jLabel2 = new javax.swing.JLabel();
        jbtnIniciarSesion = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtxtUsuario = new javax.swing.JTextField();
        jpwdPass = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jbtnRegistrarse = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(208, 184, 168));
        jPanel1.setForeground(new java.awt.Color(28, 2, 1));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setInheritsPopupMenu(true);

        jLabel2.setFont(new java.awt.Font("Ink Free", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Bienvenid@! ");

        jbtnIniciarSesion.setBackground(new java.awt.Color(248, 237, 227));
        jbtnIniciarSesion.setFont(new java.awt.Font("Ink Free", 1, 12)); // NOI18N
        jbtnIniciarSesion.setText("Iniciar sesión");
        jbtnIniciarSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jbtnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIniciarSesionActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/login.png")));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/clave.png")));

        jtxtUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtxtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtUsuarioKeyTyped(evt);
            }
        });

        jpwdPass.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jpwdPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jpwdPassKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Ink Free", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Ingrese con sus credenciales");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Logoempresa.jpeg")));

        jbtnRegistrarse.setBackground(new java.awt.Color(248, 237, 227));
        jbtnRegistrarse.setFont(new java.awt.Font("Ink Free", 1, 12)); // NOI18N
        jbtnRegistrarse.setText("Registrarse");
        jbtnRegistrarse.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jbtnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRegistrarseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jbtnIniciarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnRegistrarse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(250, 250, 250))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(184, 184, 184)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(149, 149, 149)
                                .addComponent(jLabel5)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(72, 72, 72)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(30, 30, 30)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jtxtUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jpwdPass, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addComponent(jLabel2)))
                .addContainerGap(191, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(51, 51, 51)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(75, 75, 75)
                        .addComponent(jpwdPass, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jbtnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(224, Short.MAX_VALUE))
        );

        jtxtUsuario.getAccessibleContext().setAccessibleParent(jLabel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jpwdPassKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jpwdPassKeyTyped
        // TODO add your handling code here:
        if (jpwdPass.getText().length() == 30) {
            evt.consume();
        }
    }//GEN-LAST:event_jpwdPassKeyTyped

    private void jtxtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtUsuarioKeyTyped
        // TODO add your handling code here:
        if (jtxtUsuario.getText().length() == 15) {
            evt.consume();
        }
    }//GEN-LAST:event_jtxtUsuarioKeyTyped

    private void jbtnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIniciarSesionActionPerformed
        // TODO add your handling code here:
        ingresar();

    }//GEN-LAST:event_jbtnIniciarSesionActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//        // TODO add your handling code here:
//        Iniciar();
//        start();
//        EstadoHuellas();
//        jlblImgHuella.setIcon(new ImageIcon(getClass().getResource("/imagenes/fingerprint1.png")));
    }//GEN-LAST:event_formWindowOpened

    private void jbtnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRegistrarseActionPerformed
        RegistroUsuarios ru=new RegistroUsuarios();
        ru.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jbtnRegistrarseActionPerformed

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbtnIniciarSesion;
    private javax.swing.JButton jbtnRegistrarse;
    private javax.swing.JPasswordField jpwdPass;
    private javax.swing.JTextField jtxtUsuario;
    // End of variables declaration//GEN-END:variables
}
