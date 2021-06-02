
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fatih
 */
public class AdminEkrani2 extends javax.swing.JFrame {

    /**
     * Creates new form AdminEkrani2
     */
    MusicManager mm = new MusicManager();
    int album_id,album_sanatci_id,sanatci1,sanatci2,sanatci3;
    String album_turu,album_ad,album_tarih;
    
    public void albumListeDoldur(){
        
        
        try {
            mm.preparedStatement=mm.con.prepareStatement("select * from album");
            mm.rs=mm.preparedStatement.executeQuery();
            while(mm.rs.next()){
                album_id=mm.rs.getInt("id");
                album_ad=mm.rs.getString("album_ad");
                album_turu=mm.rs.getString("album_turu");
                album_tarih=mm.rs.getString("album_tarih");
                
                AlbumListeTextArea.setText(AlbumListeTextArea.getText()+album_id+"    "+album_ad+"    "+album_turu+"    "+album_tarih+"\n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminEkrani.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void albumEkle(){
        try {
            
            
            album_turu=AEalbumturTextField.getText();
            album_ad=AEalbumadTextField.getText();
            album_tarih=AEalbumtarihTextField.getText();
            sanatci1=Integer.parseInt(AEsanatci1TextField.getText());
            sanatci2=Integer.parseInt(AEsanatci2TextField.getText());
            sanatci3=Integer.parseInt(AEsanatci3TextField.getText());
            
            
            mm.preparedStatement = mm.con.prepareStatement("select id\n"
                    + " from album as o1 \n"
                    + " where id>all(select id from album as o2\n"
                    + "						where o1.id<>o2.id);");
            mm.rs=mm.preparedStatement.executeQuery();
            while (mm.rs.next()) {
                album_id = (mm.rs.getInt(1) + 1);
            }
            
            
            mm.preparedStatement = mm.con.prepareStatement("select id\n"
                    + " from album_sanatci as o1 \n"
                    + " where id>all(select id from album_sanatci as o2\n"
                    + "						where o1.id<>o2.id);");
            mm.rs=mm.preparedStatement.executeQuery();
            while (mm.rs.next()) {
                album_sanatci_id = (mm.rs.getInt(1) + 1);
            }
            
            mm.preparedStatement=mm.con.prepareStatement("insert into album(id,album_ad,album_turu,album_tarih) values(?,?,?,?);");
            mm.preparedStatement.setInt(1, album_id);
            mm.preparedStatement.setString(2, album_ad);
            mm.preparedStatement.setString(3, album_turu);
            mm.preparedStatement.setString(4, album_tarih);
            
            mm.preparedStatement.execute();
            
            mm.preparedStatement=mm.con.prepareStatement("insert into album_sanatci(id,album_id,sanatci_id) values(?,?,?);");
            mm.preparedStatement.setInt(1,album_sanatci_id);
            mm.preparedStatement.setInt(2, album_id);
            mm.preparedStatement.setInt(3, sanatci1);
            mm.preparedStatement.execute();
            album_sanatci_id++;
            
            if(sanatci2!=-1){
                mm.preparedStatement=mm.con.prepareStatement("insert into album_sanatci(id,album_id,sanatci_id) values(?,?,?);");
                mm.preparedStatement.setInt(1,album_sanatci_id);
                mm.preparedStatement.setInt(2, album_id);
                mm.preparedStatement.setInt(3, sanatci2);
                mm.preparedStatement.execute();
                album_sanatci_id++;
            }
            
            if(sanatci3!=-1){
                mm.preparedStatement=mm.con.prepareStatement("insert into album_sanatci(id,album_id,sanatci_id) values(?,?,?);");
                mm.preparedStatement.setInt(1,album_sanatci_id);
                mm.preparedStatement.setInt(2, album_id);
                mm.preparedStatement.setInt(3, sanatci3);
                mm.preparedStatement.execute();
                
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Hatalı Değer Girişi!");
        }
           
          
    }
    
    public void albumSil(){
        
        
        try {
            album_id=Integer.parseInt(ASalbumidTextField.getText());
            mm.preparedStatement=mm.con.prepareStatement("delete from album where id="+album_id+";");
            mm.preparedStatement.execute();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Hatalı Değer Girişi!");
        }
        
    }
    public AdminEkrani2() {
        initComponents();
        albumListeDoldur();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AdminPanel2 = new javax.swing.JPanel();
        AEPanel = new javax.swing.JPanel();
        AEalbumadLabel = new javax.swing.JLabel();
        AEalbumturLabel = new javax.swing.JLabel();
        AEalbumtarihLabel = new javax.swing.JLabel();
        AEalbumadTextField = new javax.swing.JTextField();
        AEalbumturTextField = new javax.swing.JTextField();
        AEalbumtarihTextField = new javax.swing.JTextField();
        AEButton = new javax.swing.JButton();
        AELabel = new javax.swing.JLabel();
        AEsanatci1Label = new javax.swing.JLabel();
        AEsanatci2Label = new javax.swing.JLabel();
        AEsanatci3Label = new javax.swing.JLabel();
        AEsanatci1TextField = new javax.swing.JTextField();
        AEsanatci2TextField = new javax.swing.JTextField();
        AEsanatci3TextField = new javax.swing.JTextField();
        ASPanel = new javax.swing.JPanel();
        ASalbumidLabel = new javax.swing.JLabel();
        ASalbumidTextField = new javax.swing.JTextField();
        ASLabel = new javax.swing.JLabel();
        ASButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        AlbumListeTextArea = new javax.swing.JTextArea();
        AdminPanel2Button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(787, 570));

        AdminPanel2.setBackground(new java.awt.Color(0, 102, 102));
        AdminPanel2.setPreferredSize(new java.awt.Dimension(787, 570));

        AEPanel.setBackground(new java.awt.Color(9, 70, 70));
        AEPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 4));

        AEalbumadLabel.setText("album ad");

        AEalbumturLabel.setText("album tur");

        AEalbumtarihLabel.setText(" tarih");

        AEalbumadTextField.setBackground(new java.awt.Color(9, 70, 70));
        AEalbumadTextField.setPreferredSize(new java.awt.Dimension(4, 21));
        AEalbumadTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AEalbumadTextFieldActionPerformed(evt);
            }
        });

        AEalbumturTextField.setBackground(new java.awt.Color(9, 70, 70));

        AEalbumtarihTextField.setBackground(new java.awt.Color(9, 70, 70));
        AEalbumtarihTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AEalbumtarihTextFieldActionPerformed(evt);
            }
        });

        AEButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        AEButton.setText("ekle");
        AEButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AEButtonActionPerformed(evt);
            }
        });

        AELabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        AELabel.setText("album ekle");

        AEsanatci1Label.setText("sanatci 1");

        AEsanatci2Label.setText("sanatci 2");

        AEsanatci3Label.setText("sanatci 3");

        AEsanatci1TextField.setBackground(new java.awt.Color(9, 70, 70));
        AEsanatci1TextField.setText("-1");

        AEsanatci2TextField.setBackground(new java.awt.Color(9, 70, 70));
        AEsanatci2TextField.setText("-1");
        AEsanatci2TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AEsanatci2TextFieldActionPerformed(evt);
            }
        });

        AEsanatci3TextField.setBackground(new java.awt.Color(9, 70, 70));
        AEsanatci3TextField.setText("-1");
        AEsanatci3TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AEsanatci3TextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AEPanelLayout = new javax.swing.GroupLayout(AEPanel);
        AEPanel.setLayout(AEPanelLayout);
        AEPanelLayout.setHorizontalGroup(
            AEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AEPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AEPanelLayout.createSequentialGroup()
                        .addGroup(AEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(AEalbumturLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(AEalbumtarihLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AEalbumadLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AEalbumtarihTextField)
                            .addComponent(AEalbumturTextField)
                            .addComponent(AEalbumadTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(AEPanelLayout.createSequentialGroup()
                        .addComponent(AELabel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AEButton)
                        .addGap(12, 12, 12))
                    .addGroup(AEPanelLayout.createSequentialGroup()
                        .addComponent(AEsanatci1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AEsanatci1TextField))
                    .addGroup(AEPanelLayout.createSequentialGroup()
                        .addComponent(AEsanatci3Label, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AEsanatci3TextField))
                    .addGroup(AEPanelLayout.createSequentialGroup()
                        .addComponent(AEsanatci2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AEsanatci2TextField)))
                .addContainerGap())
        );
        AEPanelLayout.setVerticalGroup(
            AEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AEPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(AEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AEalbumadLabel)
                    .addComponent(AEalbumadTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AEalbumturLabel)
                    .addComponent(AEalbumturTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AEalbumtarihLabel)
                    .addComponent(AEalbumtarihTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AEsanatci1Label)
                    .addComponent(AEsanatci1TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AEsanatci2Label)
                    .addComponent(AEsanatci2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AEsanatci3Label)
                    .addComponent(AEsanatci3TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(AEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AELabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AEButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        ASPanel.setBackground(new java.awt.Color(38, 126, 126));
        ASPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 4));

        ASalbumidLabel.setText("album id");

        ASalbumidTextField.setBackground(new java.awt.Color(38, 126, 126));

        ASLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ASLabel.setText("album sil");

        ASButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        ASButton.setText("sil");
        ASButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ASButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ASPanelLayout = new javax.swing.GroupLayout(ASPanel);
        ASPanel.setLayout(ASPanelLayout);
        ASPanelLayout.setHorizontalGroup(
            ASPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ASPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ASPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ASPanelLayout.createSequentialGroup()
                        .addComponent(ASLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addComponent(ASButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ASPanelLayout.createSequentialGroup()
                        .addComponent(ASalbumidLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ASalbumidTextField)))
                .addContainerGap())
        );
        ASPanelLayout.setVerticalGroup(
            ASPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ASPanelLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(ASPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ASalbumidLabel)
                    .addComponent(ASalbumidTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ASPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ASLabel)
                    .addComponent(ASButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        AlbumListeTextArea.setEditable(false);
        AlbumListeTextArea.setBackground(new java.awt.Color(0, 153, 153));
        AlbumListeTextArea.setColumns(20);
        AlbumListeTextArea.setRows(5);
        AlbumListeTextArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51), 4));
        jScrollPane1.setViewportView(AlbumListeTextArea);

        AdminPanel2Button.setBackground(new java.awt.Color(0, 51, 51));
        AdminPanel2Button.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        AdminPanel2Button.setText(">>");
        AdminPanel2Button.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        AdminPanel2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminPanel2ButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AdminPanel2Layout = new javax.swing.GroupLayout(AdminPanel2);
        AdminPanel2.setLayout(AdminPanel2Layout);
        AdminPanel2Layout.setHorizontalGroup(
            AdminPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AdminPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ASPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AEPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(AdminPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AdminPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(47, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdminPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AdminPanel2Button, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))))
        );
        AdminPanel2Layout.setVerticalGroup(
            AdminPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AEPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ASPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(AdminPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AdminPanel2Button, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AdminPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AdminPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void AEalbumadTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AEalbumadTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AEalbumadTextFieldActionPerformed

    private void AEButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AEButtonActionPerformed
        // TODO add your handling code here:
        AlbumListeTextArea.setText("");
        albumEkle();
        albumListeDoldur();
    }//GEN-LAST:event_AEButtonActionPerformed

    private void ASButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ASButtonActionPerformed
        // TODO add your handling code here:
        AlbumListeTextArea.setText("");
        albumSil();
        albumListeDoldur();
    }//GEN-LAST:event_ASButtonActionPerformed

    private void AEalbumtarihTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AEalbumtarihTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AEalbumtarihTextFieldActionPerformed

    private void AdminPanel2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminPanel2ButtonActionPerformed
        // TODO add your handling code here:
        
        new AdminEkrani3().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_AdminPanel2ButtonActionPerformed

    private void AEsanatci2TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AEsanatci2TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AEsanatci2TextFieldActionPerformed

    private void AEsanatci3TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AEsanatci3TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AEsanatci3TextFieldActionPerformed

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
            java.util.logging.Logger.getLogger(AdminEkrani2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminEkrani2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminEkrani2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminEkrani2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminEkrani2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AEButton;
    private javax.swing.JLabel AELabel;
    private javax.swing.JPanel AEPanel;
    private javax.swing.JLabel AEalbumadLabel;
    private javax.swing.JTextField AEalbumadTextField;
    private javax.swing.JLabel AEalbumtarihLabel;
    private javax.swing.JTextField AEalbumtarihTextField;
    private javax.swing.JLabel AEalbumturLabel;
    private javax.swing.JTextField AEalbumturTextField;
    private javax.swing.JLabel AEsanatci1Label;
    private javax.swing.JTextField AEsanatci1TextField;
    private javax.swing.JLabel AEsanatci2Label;
    private javax.swing.JTextField AEsanatci2TextField;
    private javax.swing.JLabel AEsanatci3Label;
    private javax.swing.JTextField AEsanatci3TextField;
    private javax.swing.JButton ASButton;
    private javax.swing.JLabel ASLabel;
    private javax.swing.JPanel ASPanel;
    private javax.swing.JLabel ASalbumidLabel;
    private javax.swing.JTextField ASalbumidTextField;
    private javax.swing.JPanel AdminPanel2;
    private javax.swing.JButton AdminPanel2Button;
    private javax.swing.JTextArea AlbumListeTextArea;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
