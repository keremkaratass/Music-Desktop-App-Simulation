
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MusicManager {
    Connection con;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet rs;
    Boolean iscatched=false;

    public MusicManager() {
        String url="jdbc:mysql://" + Database.host + ":" + Database.port + "/" + Database.db_ismi;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadı...");
        }
        try {
            con=DriverManager.getConnection(url,Database.kullanici_adi , Database.parola);
            System.out.println("Bağlantı başarılı...");
        } catch (SQLException ex) {
            System.out.println("Bağlantı başarısız...");
            ex.printStackTrace();
        }
    }
    
    public boolean girisYap(String kullaniciAd,String parola){
        String sorgu="Select * From kullanici where kullanici_ad = ? and kullanici_sifre = ?";
        
        try {
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1, kullaniciAd);
            preparedStatement.setString(2, parola);
            
            rs=preparedStatement.executeQuery();
            
           return rs.next();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MusicManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
       
    }
    public void kullaniciEkle(JFrame frame,int id,String kullaniciAd,String parola,String ulke,String email,String uyelikTip){
        iscatched=false;
        int calma_liste_id=0;
        String sorgu="Insert Into kullanici (id,kullanici_ad,kullanici_sifre,ulke_ad,kullanici_email,uyelik_tip_ad,odemeli_mi) VALUES (?,?,?,?,?,?,?)";
        try {
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, kullaniciAd);
            preparedStatement.setString(3, parola);
            preparedStatement.setString(4, ulke);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, uyelikTip);
            
            if(uyelikTip.equalsIgnoreCase("premium")){
                preparedStatement.setString(7, "odemeli");
                
            }else{
                preparedStatement.setString(7, "odemesiz");
            }
            preparedStatement.executeUpdate();
            
  
            preparedStatement = con.prepareStatement("select id\n"
                   + " from calma_liste as o1 \n"
                   + " where id>all(select id from calma_liste as o2\n"
                   + "						where o1.id<>o2.id);");
           
            rs=preparedStatement.executeQuery();
            while (rs.next()) {
                calma_liste_id = (rs.getInt(1) + 1);
            }
            
            preparedStatement=con.prepareStatement("insert into calma_liste(id,calma_liste_ad,kullanici_id) values(?,?,?),(?,?,?),(?,?,?);");
            preparedStatement.setInt(1, calma_liste_id);
            preparedStatement.setString(2, "pop");
            preparedStatement.setInt(3, id);
            preparedStatement.setInt(4, calma_liste_id+1);
            preparedStatement.setString(5, "jazz");
            preparedStatement.setInt(6, id);
            preparedStatement.setInt(7, calma_liste_id+2);
            preparedStatement.setString(8, "klasik");
            preparedStatement.setInt(9, id);
            preparedStatement.execute();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame,"hatalı giriş yaptınız!");
            Logger.getLogger(MusicManager.class.getName()).log(Level.SEVERE, null, ex);
            iscatched=true;
        }
        
        if(!iscatched){
            JOptionPane.showMessageDialog(frame,"kayıt başarılı!");
            frame.setVisible(false);
        }
        
        
        
        
    }
    
    
    
    
    
}
