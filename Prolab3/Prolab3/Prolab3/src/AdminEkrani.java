
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
public class AdminEkrani extends javax.swing.JFrame {
    
    MusicManager mm=new MusicManager();
    String sarki_ad,sarki_tur,sarki_tarih,sarki_sure,sarki_dinlenme,album_id; int sanatci1,sanatci2,sanatci3;
        int sarki_id,sarki_sanatci_id;
    
    public void sarkiListeDoldur(){
        
        
        try {
            mm.preparedStatement=mm.con.prepareStatement("select * from sarki");
            mm.rs=mm.preparedStatement.executeQuery();
            while(mm.rs.next()){
                sarki_id=mm.rs.getInt("id");
                album_id=mm.rs.getString("album_id");
                sarki_tur=mm.rs.getString("tur_ad");
                sarki_ad=mm.rs.getString("sarki_ad");
                sarki_tarih=mm.rs.getString("sarki_tarih");
                sarki_dinlenme=mm.rs.getString("sarki_dinlenme_sayisi");
                sarki_sure=mm.rs.getString("sarki_sure");
                SarkiListeTextArea.setText(SarkiListeTextArea.getText()+sarki_id+"    "+album_id+"    "+sarki_tur+"    "+sarki_ad+"    "+sarki_tarih+"    "+sarki_dinlenme+"    "+sarki_sure+"\n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminEkrani.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void sarkiEkle(){
        try {
            
            album_id=SEalbumidTextField.getText();
            sarki_tur=SEsarkiturTextField.getText();
            sarki_ad=SEsarkiadTextField.getText();
            sarki_tarih=SEsarkitarihTextField.getText();
            sarki_dinlenme=SEdinlenmeTextField.getText();
            sarki_sure=SEsureTextField.getText();
            sanatci1=Integer.parseInt(SEsanatci1TextField.getText());
            sanatci2=Integer.parseInt(SEsanatci2TextField.getText());
            sanatci3=Integer.parseInt(SEsanatci3TextField.getText());
            
            mm.preparedStatement = mm.con.prepareStatement("select id\n"
                    + " from sarki as o1 \n"
                    + " where id>all(select id from sarki as o2\n"
                    + "						where o1.id<>o2.id);");
            mm.rs=mm.preparedStatement.executeQuery();
            while (mm.rs.next()) {
                sarki_id = (mm.rs.getInt(1) + 1);
            }
            
            mm.preparedStatement = mm.con.prepareStatement("select id\n"
                    + " from sarki_sanatci as o1 \n"
                    + " where id>all(select id from sarki_sanatci as o2\n"
                    + "						where o1.id<>o2.id);");
            mm.rs=mm.preparedStatement.executeQuery();
            while (mm.rs.next()) {
                sarki_sanatci_id = (mm.rs.getInt(1) + 1);
            }
            
            mm.preparedStatement=mm.con.prepareStatement("insert into sarki(id,album_id,tur_ad,sarki_ad,sarki_tarih,sarki_dinlenme_sayisi,sarki_sure) values(?,?,?,?,?,?,?);");
            mm.preparedStatement.setInt(1, sarki_id);
            mm.preparedStatement.setString(2, album_id);
            mm.preparedStatement.setString(3, sarki_tur);
            mm.preparedStatement.setString(4, sarki_ad);
            mm.preparedStatement.setString(5, sarki_tarih);
            mm.preparedStatement.setString(6, sarki_dinlenme);
            mm.preparedStatement.setString(7, sarki_sure);
            mm.preparedStatement.execute();
            
            mm.preparedStatement=mm.con.prepareStatement("insert into sarki_sanatci(id,sarki_id,sanatci_id) values(?,?,?);");
            mm.preparedStatement.setInt(1,sarki_sanatci_id);
            mm.preparedStatement.setInt(2, sarki_id);
            mm.preparedStatement.setInt(3, sanatci1);
            mm.preparedStatement.execute();
            sarki_sanatci_id++;
            
            if(sanatci2!=-1){
                mm.preparedStatement=mm.con.prepareStatement("insert into sarki_sanatci(id,sarki_id,sanatci_id) values(?,?,?);");
                mm.preparedStatement.setInt(1,sarki_sanatci_id);
                mm.preparedStatement.setInt(2, sarki_id);
                mm.preparedStatement.setInt(3, sanatci2);
                mm.preparedStatement.execute();
                sarki_sanatci_id++;
            }
            
            if(sanatci3!=-1){
                mm.preparedStatement=mm.con.prepareStatement("insert into sarki_sanatci(id,sarki_id,sanatci_id) values(?,?,?);");
                mm.preparedStatement.setInt(1,sarki_sanatci_id);
                mm.preparedStatement.setInt(2, sarki_id);
                mm.preparedStatement.setInt(3, sanatci3);
                mm.preparedStatement.execute();
                
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,"Hatalı Değer Girişi!");
        }
           
          
    }
    
    public void sarkiSil(){
        
        
        try {
            sarki_id=Integer.parseInt(SSsarkiidTextField.getText());
            mm.preparedStatement=mm.con.prepareStatement("delete from sarki where id="+sarki_id+";");
            mm.preparedStatement.execute();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Hatalı Değer Girişi!");
        }
        
    }

    /**
     * Creates new form AdminEkrani
     */
    public AdminEkrani() {
        initComponents();
        sarkiListeDoldur();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AdminPanel = new javax.swing.JPanel();
        SEPanel = new javax.swing.JPanel();
        SEalbumidLabel = new javax.swing.JLabel();
        SEsarkiturLabel = new javax.swing.JLabel();
        SEsarkiadLabel = new javax.swing.JLabel();
        SEsarkitarihLabel = new javax.swing.JLabel();
        SEdinlenmeLabel = new javax.swing.JLabel();
        SEsureLabel = new javax.swing.JLabel();
        SEalbumidTextField = new javax.swing.JTextField();
        SEsarkiturTextField = new javax.swing.JTextField();
        SEsarkiadTextField = new javax.swing.JTextField();
        SEsarkitarihTextField = new javax.swing.JTextField();
        SEdinlenmeTextField = new javax.swing.JTextField();
        SEsureTextField = new javax.swing.JTextField();
        SEButton = new javax.swing.JButton();
        SELabel = new javax.swing.JLabel();
        SEsanatci1Label = new javax.swing.JLabel();
        SEsanatci2Label = new javax.swing.JLabel();
        SEsanatci3Label = new javax.swing.JLabel();
        SEsanatci1TextField = new javax.swing.JTextField();
        SEsanatci2TextField = new javax.swing.JTextField();
        SEsanatci3TextField = new javax.swing.JTextField();
        SSPanel = new javax.swing.JPanel();
        SSsarkiidLabel = new javax.swing.JLabel();
        SSsarkiidTextField = new javax.swing.JTextField();
        SSButton = new javax.swing.JButton();
        SSLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        SarkiListeTextArea = new javax.swing.JTextArea();
        AdminPanelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        AdminPanel.setBackground(new java.awt.Color(96, 96, 147));

        SEPanel.setBackground(new java.awt.Color(82, 51, 103));
        SEPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 102), 4));

        SEalbumidLabel.setText("album id");

        SEsarkiturLabel.setText("sarki türü");

        SEsarkiadLabel.setText("sarki adı");

        SEsarkitarihLabel.setText("sarki tarihi");

        SEdinlenmeLabel.setText("dinlenme");

        SEsureLabel.setText("süre");

        SEalbumidTextField.setBackground(new java.awt.Color(82, 51, 103));
        SEalbumidTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEalbumidTextFieldActionPerformed(evt);
            }
        });

        SEsarkiturTextField.setBackground(new java.awt.Color(82, 51, 103));
        SEsarkiturTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEsarkiturTextFieldActionPerformed(evt);
            }
        });

        SEsarkiadTextField.setBackground(new java.awt.Color(82, 51, 103));

        SEsarkitarihTextField.setBackground(new java.awt.Color(82, 51, 103));

        SEdinlenmeTextField.setBackground(new java.awt.Color(82, 51, 103));

        SEsureTextField.setBackground(new java.awt.Color(82, 51, 103));

        SEButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        SEButton.setForeground(new java.awt.Color(204, 102, 255));
        SEButton.setText("ekle");
        SEButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEButtonActionPerformed(evt);
            }
        });

        SELabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        SELabel.setText("sarki ekle");

        SEsanatci1Label.setText("sanatci 1");

        SEsanatci2Label.setText("sanatci 2");

        SEsanatci3Label.setText("sanatci 3");

        SEsanatci1TextField.setBackground(new java.awt.Color(82, 51, 103));
        SEsanatci1TextField.setText("-1");

        SEsanatci2TextField.setBackground(new java.awt.Color(82, 51, 103));
        SEsanatci2TextField.setText("-1");

        SEsanatci3TextField.setBackground(new java.awt.Color(82, 51, 103));
        SEsanatci3TextField.setText("-1");

        javax.swing.GroupLayout SEPanelLayout = new javax.swing.GroupLayout(SEPanel);
        SEPanel.setLayout(SEPanelLayout);
        SEPanelLayout.setHorizontalGroup(
            SEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SEPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(SEsanatci3Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SEsanatci2Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SEsanatci1Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SEsureLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SEdinlenmeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SEsarkitarihLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SEsarkiadLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SEsarkiturLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SEalbumidLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SELabel, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(SEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SEButton, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(SEalbumidTextField)
                    .addComponent(SEsarkiturTextField)
                    .addComponent(SEsarkiadTextField)
                    .addComponent(SEsarkitarihTextField)
                    .addComponent(SEdinlenmeTextField)
                    .addComponent(SEsureTextField)
                    .addComponent(SEsanatci1TextField)
                    .addComponent(SEsanatci2TextField)
                    .addComponent(SEsanatci3TextField))
                .addContainerGap())
        );
        SEPanelLayout.setVerticalGroup(
            SEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SEPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(SEalbumidLabel)
                    .addComponent(SEalbumidTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SEsarkiturLabel)
                    .addComponent(SEsarkiturTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SEsarkiadLabel)
                    .addComponent(SEsarkiadTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SEsarkitarihLabel)
                    .addComponent(SEsarkitarihTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SEdinlenmeLabel)
                    .addComponent(SEdinlenmeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SEsureLabel)
                    .addComponent(SEsureTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SEsanatci1Label)
                    .addComponent(SEsanatci1TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SEsanatci2Label)
                    .addComponent(SEsanatci2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(SEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SEsanatci3Label)
                    .addComponent(SEsanatci3TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(SEPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SEButton, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(SELabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        SSPanel.setBackground(new java.awt.Color(127, 103, 140));
        SSPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(74, 46, 103), 4));

        SSsarkiidLabel.setText("sarki id");

        SSsarkiidTextField.setBackground(new java.awt.Color(127, 103, 140));
        SSsarkiidTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SSsarkiidTextFieldActionPerformed(evt);
            }
        });

        SSButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        SSButton.setText("sil");
        SSButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SSButtonActionPerformed(evt);
            }
        });

        SSLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        SSLabel.setText("sarki sil");

        javax.swing.GroupLayout SSPanelLayout = new javax.swing.GroupLayout(SSPanel);
        SSPanel.setLayout(SSPanelLayout);
        SSPanelLayout.setHorizontalGroup(
            SSPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SSPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SSPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SSPanelLayout.createSequentialGroup()
                        .addComponent(SSsarkiidLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SSsarkiidTextField)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SSPanelLayout.createSequentialGroup()
                        .addComponent(SSLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                        .addGap(8, 8, 8)
                        .addComponent(SSButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))))
        );
        SSPanelLayout.setVerticalGroup(
            SSPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SSPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SSPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SSsarkiidLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SSsarkiidTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(SSPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SSButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SSLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        SarkiListeTextArea.setEditable(false);
        SarkiListeTextArea.setBackground(new java.awt.Color(111, 111, 150));
        SarkiListeTextArea.setColumns(20);
        SarkiListeTextArea.setRows(5);
        SarkiListeTextArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 204), 4));
        jScrollPane1.setViewportView(SarkiListeTextArea);

        AdminPanelButton.setBackground(new java.awt.Color(51, 0, 51));
        AdminPanelButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        AdminPanelButton.setText(">>");
        AdminPanelButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        AdminPanelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminPanelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AdminPanelLayout = new javax.swing.GroupLayout(AdminPanel);
        AdminPanel.setLayout(AdminPanelLayout);
        AdminPanelLayout.setHorizontalGroup(
            AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(SSPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SEPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AdminPanelLayout.createSequentialGroup()
                        .addGap(395, 395, 395)
                        .addComponent(AdminPanelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        AdminPanelLayout.setVerticalGroup(
            AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SEPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SSPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdminPanelLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(AdminPanelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AdminPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AdminPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SEalbumidTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEalbumidTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SEalbumidTextFieldActionPerformed

    private void SEsarkiturTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEsarkiturTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SEsarkiturTextFieldActionPerformed

    private void SSButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SSButtonActionPerformed
        // TODO add your handling code here:
        sarkiSil();
        SarkiListeTextArea.setText("");
        sarkiListeDoldur();
    }//GEN-LAST:event_SSButtonActionPerformed

    private void SEButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEButtonActionPerformed
        // TODO add your handling code here:
        sarkiEkle();
        SarkiListeTextArea.setText("");
        sarkiListeDoldur();
        
    }//GEN-LAST:event_SEButtonActionPerformed

    private void AdminPanelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminPanelButtonActionPerformed
        // TODO add your handling code here:
        
        new AdminEkrani2().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_AdminPanelButtonActionPerformed

    private void SSsarkiidTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SSsarkiidTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SSsarkiidTextFieldActionPerformed

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
            java.util.logging.Logger.getLogger(AdminEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminEkrani().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AdminPanel;
    private javax.swing.JButton AdminPanelButton;
    private javax.swing.JButton SEButton;
    private javax.swing.JLabel SELabel;
    private javax.swing.JPanel SEPanel;
    private javax.swing.JLabel SEalbumidLabel;
    private javax.swing.JTextField SEalbumidTextField;
    private javax.swing.JLabel SEdinlenmeLabel;
    private javax.swing.JTextField SEdinlenmeTextField;
    private javax.swing.JLabel SEsanatci1Label;
    private javax.swing.JTextField SEsanatci1TextField;
    private javax.swing.JLabel SEsanatci2Label;
    private javax.swing.JTextField SEsanatci2TextField;
    private javax.swing.JLabel SEsanatci3Label;
    private javax.swing.JTextField SEsanatci3TextField;
    private javax.swing.JLabel SEsarkiadLabel;
    private javax.swing.JTextField SEsarkiadTextField;
    private javax.swing.JLabel SEsarkitarihLabel;
    private javax.swing.JTextField SEsarkitarihTextField;
    private javax.swing.JLabel SEsarkiturLabel;
    private javax.swing.JTextField SEsarkiturTextField;
    private javax.swing.JLabel SEsureLabel;
    private javax.swing.JTextField SEsureTextField;
    private javax.swing.JButton SSButton;
    private javax.swing.JLabel SSLabel;
    private javax.swing.JPanel SSPanel;
    private javax.swing.JLabel SSsarkiidLabel;
    private javax.swing.JTextField SSsarkiidTextField;
    private javax.swing.JTextArea SarkiListeTextArea;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
