
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class AnaSayfa extends javax.swing.JFrame {

    //giris yapan kullanicinin bilgileri
    String kullanici_ad, uyelik_tip_ad, ulke_ad;
    int id = 1, kullanici_id,calma_liste_id=0;
    MusicManager musicManager2 = new MusicManager();
    String[] arama_tablo_ad = new String[100];
    int[] arama_tablo_id = new int[100];
    String s;
    DefaultListModel models = new DefaultListModel();
    int idd = 1;
    int[] arama_list_id = new int[100];
    int[] calma_liste1_id= new int[100];
    int[] calma_liste2_id= new int[100];
    int[] calma_liste3_id= new int[100];
    int[] calma_liste4_id= new int[100];
    int[] kullanici_takip_id= new int[100];
    int[] kullanici_calma_liste_id= new int[100];
    int[] ulke_sarki_id= new int[100];
    int[] genel_sarki_id= new int[100];
    int[] pop_sarki_id= new int[100];
    int[] jazz_sarki_id= new int[100];
    int[] klasik_sarki_id= new int[100];
    int[] sarki_id= new int[100];
    int index;
    int sarki_calma_liste_calmalisteid=0;
    
  
    String calmalistelerin;
    DefaultListModel<String> liste = new DefaultListModel<>() ;
    DefaultListModel<String> liste2 = new DefaultListModel<>() ;
    DefaultListModel<String> liste3 = new DefaultListModel<>() ;
    DefaultListModel<String> liste4 = new DefaultListModel<>() ;
    DefaultListModel<String> ulkeliste = new DefaultListModel<>() ;
    DefaultListModel<String> genelliste = new DefaultListModel<>() ;
    DefaultListModel<String> popliste = new DefaultListModel<>() ;
    DefaultListModel<String> jazzliste = new DefaultListModel<>() ;
    DefaultListModel<String> klasikliste = new DefaultListModel<>() ;

    public AnaSayfa(int kullanici_id,String ulke_ad) throws SQLException {
        initComponents();

        this.kullanici_id=kullanici_id;
        this.ulke_ad=ulke_ad;
        takip_listesi.setModel(models);
        sarkiPanel.setVisible(false);
        sanatciPanel.setVisible(false);
        albumPanel.setVisible(false);
        CalmaListeEklePanel.setVisible(false);
        CalmaListPanel.setVisible(false);
        KullaniciListeleriPanel.setVisible(false);
        musicManager2.preparedStatement = musicManager2.con.prepareStatement("delete from arama where id<100000 ;");
        musicManager2.preparedStatement.execute();
        KullaniciListeleriList.setModel(liste4);
        
        models.clear();
        String sorgu = "Select id,uyelik_tip_ad,kullanici_ad from kullanici where uyelik_tip_ad='premium'";
        int c = 0;
        try {
            musicManager2.preparedStatement = musicManager2.con.prepareStatement(sorgu);
            musicManager2.rs = musicManager2.preparedStatement.executeQuery();
            while (musicManager2.rs.next()) {
                models.addElement(musicManager2.rs.getString("kullanici_ad"));
                arama_list_id[c] = musicManager2.rs.getInt("id");
                c++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }

        //bütün gerekli tablolardan arama tablosuna veri aktarımı (satır 100e kadar sürer)
        try {
            musicManager2.preparedStatement = musicManager2.con.prepareStatement("select id\n"
                    + " from arama as o1 \n"
                    + " where id>all(select id from arama as o2\n"
                    + "						where o1.id<>o2.id);");

            musicManager2.rs = musicManager2.preparedStatement.executeQuery();
            while (musicManager2.rs.next()) {
                id = (musicManager2.rs.getInt(1) + 1);
            }

            musicManager2.preparedStatement = musicManager2.con.prepareStatement("select *\n"
                    + " from kullanici ;");

            musicManager2.rs = musicManager2.preparedStatement.executeQuery();
            while (musicManager2.rs.next()) {
                musicManager2.preparedStatement = musicManager2.con.prepareStatement("insert into arama(id,arama_id,arama_ad,arama_tablo) values(?,?,?,?)");
                musicManager2.preparedStatement.setInt(1, id++);
                musicManager2.preparedStatement.setString(2, musicManager2.rs.getString("id"));
                musicManager2.preparedStatement.setString(3, musicManager2.rs.getString("kullanici_ad"));
                musicManager2.preparedStatement.setString(4, "kullanici");
                musicManager2.preparedStatement.execute();
            }

            musicManager2.preparedStatement = musicManager2.con.prepareStatement("select *\n"
                    + " from sarki ;");

            musicManager2.rs = musicManager2.preparedStatement.executeQuery();
            while (musicManager2.rs.next()) {
                musicManager2.preparedStatement = musicManager2.con.prepareStatement("insert into arama(id,arama_id,arama_ad,arama_tablo) values(?,?,?,?)");
                musicManager2.preparedStatement.setInt(1, id++);
                musicManager2.preparedStatement.setString(2, musicManager2.rs.getString("id"));
                musicManager2.preparedStatement.setString(3, musicManager2.rs.getString("sarki_ad"));
                musicManager2.preparedStatement.setString(4, "sarki");
                musicManager2.preparedStatement.execute();
            }

            musicManager2.preparedStatement = musicManager2.con.prepareStatement("select *\n"
                    + " from sanatci ;");

            musicManager2.rs = musicManager2.preparedStatement.executeQuery();
            while (musicManager2.rs.next()) {
                musicManager2.preparedStatement = musicManager2.con.prepareStatement("insert into arama(id,arama_id,arama_ad,arama_tablo) values(?,?,?,?)");
                musicManager2.preparedStatement.setInt(1, id++);
                musicManager2.preparedStatement.setString(2, musicManager2.rs.getString("id"));
                musicManager2.preparedStatement.setString(3, musicManager2.rs.getString("sanatci_ad"));
                musicManager2.preparedStatement.setString(4, "sanatci");
                musicManager2.preparedStatement.execute();
            }

            musicManager2.preparedStatement = musicManager2.con.prepareStatement("select *\n"
                    + " from album ;");

            musicManager2.rs = musicManager2.preparedStatement.executeQuery();
            while (musicManager2.rs.next()) {
                musicManager2.preparedStatement = musicManager2.con.prepareStatement("insert into arama(id,arama_id,arama_ad,arama_tablo) values(?,?,?,?)");
                musicManager2.preparedStatement.setInt(1, id++);
                musicManager2.preparedStatement.setString(2, musicManager2.rs.getString("id"));
                musicManager2.preparedStatement.setString(3, musicManager2.rs.getString("album_ad"));
                musicManager2.preparedStatement.setString(4, "album");
                musicManager2.preparedStatement.execute();
            }

            musicManager2.preparedStatement = musicManager2.con.prepareStatement("select *\n"
                    + " from calma_liste ;");

            musicManager2.rs = musicManager2.preparedStatement.executeQuery();
            while (musicManager2.rs.next()) {
                musicManager2.preparedStatement = musicManager2.con.prepareStatement("insert into arama(id,arama_id,arama_ad,arama_tablo) values(?,?,?,?)");
                musicManager2.preparedStatement.setInt(1, id++);
                musicManager2.preparedStatement.setString(2, musicManager2.rs.getString("id"));
                musicManager2.preparedStatement.setString(3, musicManager2.rs.getString("calma_liste_ad"));
                musicManager2.preparedStatement.setString(4, "calma_liste");
                musicManager2.preparedStatement.execute();
            }
            
            CalmaListeList.setModel(liste);
            
            
            CalmaListeList2.setModel(liste2);
       
            int temp2=0,temp3=0,temp4=0,temp5=0,temp6=0;
            
            listeDoldur();
            
            liste2Doldur();
            
            //top 10 listeleri oluşturma
            
            //genel top10
            GenelTop10List.setModel(genelliste);
            musicManager2.preparedStatement = musicManager2.con.prepareStatement("SELECT * FROM spofity.sarki order by sarki_dinlenme_sayisi desc limit 10; ");
            musicManager2.rs=musicManager2.preparedStatement.executeQuery();
            while(musicManager2.rs.next()){
                
                genelliste.addElement(musicManager2.rs.getString("sarki_ad")+"     "+musicManager2.rs.getInt("sarki_dinlenme_sayisi"));
                genel_sarki_id[temp2]=musicManager2.rs.getInt("id");
                temp2++;
            }
            
            //ulke top10
            UlkeTop10Label.setText(ulke_ad+" top10");
            UlkeTop10List.setModel(ulkeliste);
            musicManager2.preparedStatement = musicManager2.con.prepareStatement("SELECT distinct sarki.id,sarki_ad,sarki_dinlenme_sayisi FROM sarki,sarki_sanatci,sanatci where sarki_sanatci.sarki_id=sarki.id and sanatci_id=sanatci.id and ulke_ad='"+ulke_ad+"' order by sarki_dinlenme_sayisi desc limit 10; ");
            musicManager2.rs=musicManager2.preparedStatement.executeQuery();
            while(musicManager2.rs.next()){
                
                ulkeliste.addElement(musicManager2.rs.getString("sarki_ad")+"     "+musicManager2.rs.getInt("sarki_dinlenme_sayisi"));
                ulke_sarki_id[temp3]=musicManager2.rs.getInt(1);
                temp3++;
            }
            
            //pop top10
            PopTop10List.setModel(popliste);
            musicManager2.preparedStatement = musicManager2.con.prepareStatement("SELECT id,sarki_ad,sarki_dinlenme_sayisi from sarki where tur_ad='pop' order by sarki_dinlenme_sayisi desc limit 10;");
            musicManager2.rs=musicManager2.preparedStatement.executeQuery();
            while(musicManager2.rs.next()){
                
                popliste.addElement(musicManager2.rs.getString("sarki_ad")+"     "+musicManager2.rs.getInt("sarki_dinlenme_sayisi"));
                pop_sarki_id[temp4]=musicManager2.rs.getInt(1);
                temp4++;
            }
            
            //jazz top10
            JazzTop10List.setModel(jazzliste);
            musicManager2.preparedStatement = musicManager2.con.prepareStatement("SELECT id,sarki_ad,sarki_dinlenme_sayisi from sarki where tur_ad='jazz' order by sarki_dinlenme_sayisi desc limit 10;");
            musicManager2.rs=musicManager2.preparedStatement.executeQuery();
            while(musicManager2.rs.next()){
                
                jazzliste.addElement(musicManager2.rs.getString("sarki_ad")+"     "+musicManager2.rs.getInt("sarki_dinlenme_sayisi"));
                jazz_sarki_id[temp5]=musicManager2.rs.getInt(1);
                temp5++;
            }
            
            //klasik top10
            KlasikTop10List.setModel(klasikliste);
            musicManager2.preparedStatement = musicManager2.con.prepareStatement("SELECT id,sarki_ad,sarki_dinlenme_sayisi from sarki where tur_ad='klasik' order by sarki_dinlenme_sayisi desc limit 10;");
            musicManager2.rs=musicManager2.preparedStatement.executeQuery();
            while(musicManager2.rs.next()){
                
                klasikliste.addElement(musicManager2.rs.getString("sarki_ad")+"     "+musicManager2.rs.getInt("sarki_dinlenme_sayisi"));
                klasik_sarki_id[temp6]=musicManager2.rs.getInt(1);
                temp6++;
            }
            

        } catch (Exception ex) {
            System.out.println("hata");
        }
        
        KullaniciTakipList.setModel(liste3);
        kullaniciTakipGoruntule();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        aramaButton = new javax.swing.JButton();
        aramaTextField = new javax.swing.JTextField();
        aramaListe = new javax.swing.JComboBox<>();
        sarkiPanel = new javax.swing.JPanel();
        sarkiLabel = new javax.swing.JLabel();
        sarkiBaslatLabel = new javax.swing.JLabel();
        sarkiTempoLabel = new javax.swing.JLabel();
        sarkiEkleLabel = new javax.swing.JLabel();
        sarkiDurdurma = new javax.swing.JLabel();
        sarkiKapamaPanel = new javax.swing.JLabel();
        gozatButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        sanatciPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        sanatciUlke = new javax.swing.JTextField();
        sanatciAd = new javax.swing.JTextField();
        sanatciKapamaPanel = new javax.swing.JLabel();
        albumPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        albumAd = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        albumTur = new javax.swing.JTextField();
        albumTarih = new javax.swing.JTextField();
        albumKapamaPanel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        takip_listesi = new javax.swing.JList<>();
        jLabel9 = new javax.swing.JLabel();
        listedekileri_takip_et = new javax.swing.JLabel();
        mesaj_yazisi = new javax.swing.JLabel();
        CalmaListelerinPanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        CalmaListeList = new javax.swing.JList<>();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        CalmaListeList2 = new javax.swing.JList<>();
        ListeSilButton = new javax.swing.JButton();
        TakiptenCikar = new javax.swing.JButton();
        CalmaListeEklePanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        CalmaListeEkleTextField = new javax.swing.JTextField();
        CalmaListeEklePanelButton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        KullaniciTakipList = new javax.swing.JList<>();
        jLabel15 = new javax.swing.JLabel();
        CalmaListPanel = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        CalmaListPanelLabel = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        CalmaListPanelList = new javax.swing.JList<>();
        HepsiniEkleButton = new javax.swing.JButton();
        Sarki_Calma_Liste_Sil = new javax.swing.JButton();
        KullaniciListeleriPanel = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        KullaniciListeleriList = new javax.swing.JList<>();
        KullaniciListe = new javax.swing.JButton();
        KullaniciListeleriKapat = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        GenelTop10Label = new javax.swing.JLabel();
        UlkeTop10Label = new javax.swing.JLabel();
        PopTop10Label = new javax.swing.JLabel();
        JazzTop10Label = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        GenelTop10List = new javax.swing.JList<>();
        jScrollPane8 = new javax.swing.JScrollPane();
        UlkeTop10List = new javax.swing.JList<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        PopTop10List = new javax.swing.JList<>();
        jScrollPane10 = new javax.swing.JScrollPane();
        JazzTop10List = new javax.swing.JList<>();
        KlasikTop10Label = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        KlasikTop10List = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(186, 79, 84));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        aramaButton.setBackground(new java.awt.Color(255, 255, 255));
        aramaButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        aramaButton.setText("ara");
        aramaButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        aramaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aramaButtonActionPerformed(evt);
            }
        });
        jPanel1.add(aramaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 129, 76, -1));

        aramaTextField.setBackground(new java.awt.Color(186, 79, 84));
        aramaTextField.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        aramaTextField.setText("                Şarkı,sanatçı veya albüm");
        aramaTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        aramaTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                aramaTextFieldFocusGained(evt);
            }
        });
        jPanel1.add(aramaTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 125, 249, 27));

        aramaListe.setBackground(new java.awt.Color(186, 79, 84));
        aramaListe.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        aramaListe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "                         liste" }));
        aramaListe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(aramaListe, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 176, 249, -1));

        sarkiPanel.setBackground(new java.awt.Color(186, 79, 84));
        sarkiPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        sarkiPanel.setForeground(new java.awt.Color(153, 0, 51));

        sarkiLabel.setFont(new java.awt.Font("Georgia", 1, 16)); // NOI18N
        sarkiLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sarkiLabel.setText("sarki");
        sarkiLabel.setToolTipText("");
        sarkiLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        sarkiBaslatLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/play.png"))); // NOI18N

        sarkiTempoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sestitresim.png"))); // NOI18N

        sarkiEkleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ekle.png"))); // NOI18N
        sarkiEkleLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sarkiEkleLabelMouseClicked(evt);
            }
        });

        sarkiDurdurma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pause.png"))); // NOI18N

        sarkiKapamaPanel.setBackground(new java.awt.Color(51, 51, 51));
        sarkiKapamaPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        sarkiKapamaPanel.setText("       X");
        sarkiKapamaPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sarkiKapamaPanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout sarkiPanelLayout = new javax.swing.GroupLayout(sarkiPanel);
        sarkiPanel.setLayout(sarkiPanelLayout);
        sarkiPanelLayout.setHorizontalGroup(
            sarkiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sarkiPanelLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(sarkiBaslatLabel)
                .addGap(66, 66, 66)
                .addComponent(sarkiDurdurma)
                .addGap(62, 62, 62)
                .addComponent(sarkiEkleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sarkiPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(sarkiKapamaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(sarkiPanelLayout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(sarkiTempoLabel)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sarkiPanelLayout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(sarkiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        sarkiPanelLayout.setVerticalGroup(
            sarkiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sarkiPanelLayout.createSequentialGroup()
                .addComponent(sarkiKapamaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(sarkiTempoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sarkiLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(sarkiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sarkiEkleLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sarkiDurdurma, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sarkiBaslatLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(sarkiPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 390, 400, 269));

        gozatButton.setBackground(new java.awt.Color(255, 255, 255));
        gozatButton.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        gozatButton.setText("gözat");
        gozatButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        gozatButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gozatButtonMouseClicked(evt);
            }
        });
        jPanel1.add(gozatButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 178, 76, -1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/spotify2.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(186, 79, 84));
        jLabel2.setText("                               D İ L E D İ Ğ İ N İ Z   H E R  Ş A R K I Y I , S A N A T Ç I Y I , A L B Ü M Ü   A R A Y I P   B U L A B İ L İ R S İ N İ Z.");

        jButton1.setBackground(new java.awt.Color(204, 0, 0));
        jButton1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jButton1.setText("X");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(358, 358, 358)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1159, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 2827, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 114, -1, 44));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/list.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 166, -1, -1));

        sanatciPanel.setBackground(new java.awt.Color(186, 79, 84));
        sanatciPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sanatci.png"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/country2.png"))); // NOI18N

        sanatciUlke.setEditable(false);
        sanatciUlke.setBackground(new java.awt.Color(186, 79, 84));
        sanatciUlke.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        sanatciUlke.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        sanatciAd.setEditable(false);
        sanatciAd.setBackground(new java.awt.Color(186, 79, 84));
        sanatciAd.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        sanatciAd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        sanatciAd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sanatciAdActionPerformed(evt);
            }
        });

        sanatciKapamaPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        sanatciKapamaPanel.setText("       X");
        sanatciKapamaPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sanatciKapamaPanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout sanatciPanelLayout = new javax.swing.GroupLayout(sanatciPanel);
        sanatciPanel.setLayout(sanatciPanelLayout);
        sanatciPanelLayout.setHorizontalGroup(
            sanatciPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sanatciPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sanatciPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(sanatciPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sanatciUlke, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addComponent(sanatciAd))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sanatciPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sanatciKapamaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        sanatciPanelLayout.setVerticalGroup(
            sanatciPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sanatciPanelLayout.createSequentialGroup()
                .addComponent(sanatciKapamaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(sanatciPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sanatciPanelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(sanatciAd, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(sanatciPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(sanatciUlke, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jPanel1.add(sanatciPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 390, 400, -1));

        albumPanel.setBackground(new java.awt.Color(186, 79, 84));
        albumPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/album.png"))); // NOI18N

        albumAd.setEditable(false);
        albumAd.setBackground(new java.awt.Color(186, 79, 84));
        albumAd.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        albumAd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tur.png"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tarih.png"))); // NOI18N

        albumTur.setEditable(false);
        albumTur.setBackground(new java.awt.Color(186, 79, 84));
        albumTur.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        albumTur.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        albumTarih.setEditable(false);
        albumTarih.setBackground(new java.awt.Color(186, 79, 84));
        albumTarih.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        albumTarih.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        albumKapamaPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        albumKapamaPanel.setText("         X");
        albumKapamaPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                albumKapamaPanelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout albumPanelLayout = new javax.swing.GroupLayout(albumPanel);
        albumPanel.setLayout(albumPanelLayout);
        albumPanelLayout.setHorizontalGroup(
            albumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(albumPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(albumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(albumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(albumPanelLayout.createSequentialGroup()
                        .addGroup(albumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(albumTur, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                            .addComponent(albumTarih))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(albumPanelLayout.createSequentialGroup()
                        .addComponent(albumAd, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(albumKapamaPanel)))
                .addContainerGap())
        );
        albumPanelLayout.setVerticalGroup(
            albumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(albumPanelLayout.createSequentialGroup()
                .addGroup(albumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(albumPanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(albumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(albumAd, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(albumKapamaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(albumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(albumTur, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(albumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(albumTarih, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(albumPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 390, -1, 269));

        takip_listesi.setBackground(new java.awt.Color(186, 79, 84));
        takip_listesi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        takip_listesi.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(takip_listesi);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 300, 203, 220));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Takip edebileceğin kullanıcılar");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1480, 250, -1, 32));

        listedekileri_takip_et.setIcon(new javax.swing.ImageIcon(getClass().getResource("/takip.png"))); // NOI18N
        listedekileri_takip_et.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listedekileri_takip_etMouseClicked(evt);
            }
        });
        jPanel1.add(listedekileri_takip_et, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 470, -1, -1));
        jPanel1.add(mesaj_yazisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(2009, 879, 529, 45));

        CalmaListelerinPanel.setBackground(new java.awt.Color(186, 79, 84));
        CalmaListelerinPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Çalma Listelerin");

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton2.setText("+");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        CalmaListeList.setBackground(new java.awt.Color(186, 79, 84));
        CalmaListeList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "liste" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        CalmaListeList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalmaListeListMouseClicked(evt);
            }
        });
        CalmaListeList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                CalmaListeListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(CalmaListeList);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Takip ettiğin listeler");

        CalmaListeList2.setBackground(new java.awt.Color(186, 79, 84));
        CalmaListeList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "liste2" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        CalmaListeList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        CalmaListeList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CalmaListeList2MouseClicked(evt);
            }
        });
        CalmaListeList2.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                CalmaListeList2ValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(CalmaListeList2);

        ListeSilButton.setBackground(new java.awt.Color(51, 51, 51));
        ListeSilButton.setText("liste sil");
        ListeSilButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListeSilButtonActionPerformed(evt);
            }
        });

        TakiptenCikar.setText("takipten çıkar");
        TakiptenCikar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TakiptenCikarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CalmaListelerinPanelLayout = new javax.swing.GroupLayout(CalmaListelerinPanel);
        CalmaListelerinPanel.setLayout(CalmaListelerinPanelLayout);
        CalmaListelerinPanelLayout.setHorizontalGroup(
            CalmaListelerinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CalmaListelerinPanelLayout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
            .addComponent(jScrollPane3)
            .addGroup(CalmaListelerinPanelLayout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(CalmaListelerinPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CalmaListelerinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ListeSilButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TakiptenCikar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CalmaListelerinPanelLayout.setVerticalGroup(
            CalmaListelerinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CalmaListelerinPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CalmaListelerinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ListeSilButton)
                .addGap(22, 22, 22)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TakiptenCikar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(CalmaListelerinPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 190, 520));

        CalmaListeEklePanel.setBackground(new java.awt.Color(186, 79, 84));
        CalmaListeEklePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        jLabel13.setText("liste adı");

        CalmaListeEkleTextField.setBackground(new java.awt.Color(186, 79, 84));
        CalmaListeEkleTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalmaListeEkleTextFieldActionPerformed(evt);
            }
        });

        CalmaListeEklePanelButton.setBackground(new java.awt.Color(51, 51, 51));
        CalmaListeEklePanelButton.setText("oluştur");
        CalmaListeEklePanelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalmaListeEklePanelButtonActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 51, 51));
        jButton3.setText("X");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CalmaListeEklePanelLayout = new javax.swing.GroupLayout(CalmaListeEklePanel);
        CalmaListeEklePanel.setLayout(CalmaListeEklePanelLayout);
        CalmaListeEklePanelLayout.setHorizontalGroup(
            CalmaListeEklePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CalmaListeEklePanelLayout.createSequentialGroup()
                .addGroup(CalmaListeEklePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CalmaListeEklePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CalmaListeEkleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CalmaListeEklePanelLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(CalmaListeEklePanelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CalmaListeEklePanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton3))
        );
        CalmaListeEklePanelLayout.setVerticalGroup(
            CalmaListeEklePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CalmaListeEklePanelLayout.createSequentialGroup()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CalmaListeEklePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CalmaListeEkleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(CalmaListeEklePanelButton)
                .addContainerGap())
        );

        jPanel1.add(CalmaListeEklePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, 230, 120));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Takip ettiğin kullanıcılar");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 540, 210, 20));

        KullaniciTakipList.setBackground(new java.awt.Color(186, 79, 84));
        KullaniciTakipList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        KullaniciTakipList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        KullaniciTakipList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KullaniciTakipListMouseClicked(evt);
            }
        });
        KullaniciTakipList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                KullaniciTakipListValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(KullaniciTakipList);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1500, 570, 200, 250));

        jLabel15.setBackground(new java.awt.Color(0, 153, 153));
        jLabel15.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("takipten çıkar");
        jLabel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 770, 100, 50));

        CalmaListPanel.setBackground(new java.awt.Color(51, 51, 51));
        CalmaListPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        jButton4.setBackground(new java.awt.Color(204, 0, 0));
        jButton4.setText("X");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        CalmaListPanelLabel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        CalmaListPanelLabel.setText("playlist");

        CalmaListPanelList.setBackground(new java.awt.Color(51, 51, 51));
        CalmaListPanelList.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        CalmaListPanelList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        CalmaListPanelList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                CalmaListPanelListValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(CalmaListPanelList);

        HepsiniEkleButton.setBackground(new java.awt.Color(102, 0, 0));
        HepsiniEkleButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        HepsiniEkleButton.setText("Hepsini Ekle");
        HepsiniEkleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HepsiniEkleButtonActionPerformed(evt);
            }
        });

        Sarki_Calma_Liste_Sil.setBackground(new java.awt.Color(102, 0, 0));
        Sarki_Calma_Liste_Sil.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        Sarki_Calma_Liste_Sil.setText("Şarkı Sil");
        Sarki_Calma_Liste_Sil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sarki_Calma_Liste_SilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CalmaListPanelLayout = new javax.swing.GroupLayout(CalmaListPanel);
        CalmaListPanel.setLayout(CalmaListPanelLayout);
        CalmaListPanelLayout.setHorizontalGroup(
            CalmaListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CalmaListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CalmaListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CalmaListPanelLayout.createSequentialGroup()
                        .addGap(0, 426, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addGroup(CalmaListPanelLayout.createSequentialGroup()
                        .addGroup(CalmaListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CalmaListPanelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(CalmaListPanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addGroup(CalmaListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(HepsiniEkleButton, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                    .addComponent(Sarki_Calma_Liste_Sil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        CalmaListPanelLayout.setVerticalGroup(
            CalmaListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CalmaListPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addGroup(CalmaListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(CalmaListPanelLayout.createSequentialGroup()
                        .addComponent(Sarki_Calma_Liste_Sil, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(HepsiniEkleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CalmaListPanelLayout.createSequentialGroup()
                        .addComponent(CalmaListPanelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel1.add(CalmaListPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 230, 500, 540));

        KullaniciListeleriPanel.setBackground(new java.awt.Color(51, 51, 51));
        KullaniciListeleriPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        KullaniciListeleriPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel16.setText("kullanıcının listeleri:");
        KullaniciListeleriPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 20, -1, -1));

        jScrollPane6.setBackground(new java.awt.Color(51, 51, 51));

        KullaniciListeleriList.setBackground(new java.awt.Color(51, 51, 51));
        KullaniciListeleriList.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        KullaniciListeleriList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane6.setViewportView(KullaniciListeleriList);

        KullaniciListeleriPanel.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 46, 150, 154));

        KullaniciListe.setBackground(new java.awt.Color(102, 0, 0));
        KullaniciListe.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        KullaniciListe.setText("takip et");
        KullaniciListe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KullaniciListeActionPerformed(evt);
            }
        });
        KullaniciListeleriPanel.add(KullaniciListe, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 208, 86, -1));

        KullaniciListeleriKapat.setBackground(new java.awt.Color(153, 0, 0));
        KullaniciListeleriKapat.setText("X");
        KullaniciListeleriKapat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KullaniciListeleriKapatActionPerformed(evt);
            }
        });
        KullaniciListeleriPanel.add(KullaniciListeleriKapat, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        jPanel1.add(KullaniciListeleriPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 570, 190, 250));

        jPanel3.setBackground(new java.awt.Color(222, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        GenelTop10Label.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        GenelTop10Label.setText("genel top10");
        jPanel3.add(GenelTop10Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 31, 151, -1));

        UlkeTop10Label.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        UlkeTop10Label.setText("ülke top10");
        jPanel3.add(UlkeTop10Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 31, 187, -1));

        PopTop10Label.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        PopTop10Label.setText("pop top10");
        jPanel3.add(PopTop10Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(455, 31, 187, -1));

        JazzTop10Label.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        JazzTop10Label.setText("jazz top10");
        jPanel3.add(JazzTop10Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(654, 31, 187, -1));

        GenelTop10List.setBackground(new java.awt.Color(170, 79, 79));
        GenelTop10List.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        GenelTop10List.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        GenelTop10List.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GenelTop10ListMouseClicked(evt);
            }
        });
        GenelTop10List.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                GenelTop10ListValueChanged(evt);
            }
        });
        jScrollPane7.setViewportView(GenelTop10List);

        jPanel3.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 68, 187, 347));

        UlkeTop10List.setBackground(new java.awt.Color(159, 40, 40));
        UlkeTop10List.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        UlkeTop10List.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        UlkeTop10List.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                UlkeTop10ListMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(UlkeTop10List);

        jPanel3.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 68, 187, 347));

        PopTop10List.setBackground(new java.awt.Color(179, 30, 30));
        PopTop10List.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        PopTop10List.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        PopTop10List.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PopTop10ListMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(PopTop10List);

        jPanel3.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(455, 68, 187, 347));

        JazzTop10List.setBackground(new java.awt.Color(117, 17, 17));
        JazzTop10List.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        JazzTop10List.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JazzTop10List.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JazzTop10ListMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(JazzTop10List);

        jPanel3.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(654, 68, 187, 347));

        KlasikTop10Label.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        KlasikTop10Label.setText("klasik top10");
        jPanel3.add(KlasikTop10Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(853, 31, 187, -1));

        KlasikTop10List.setBackground(new java.awt.Color(109, 4, 4));
        KlasikTop10List.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        KlasikTop10List.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        KlasikTop10List.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KlasikTop10ListMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(KlasikTop10List);

        jPanel3.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(853, 68, 187, 347));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 1100, 510));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 1722, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void aramaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aramaButtonActionPerformed
        // arama butonunun kullanımı ve aranan kelimeyi içeren verilerin bulunup combobox'a aktarılması
        for (int i = 0; i < arama_tablo_ad.length; i++) {
            arama_tablo_ad[i] = null;
            arama_tablo_id[i] = -1;

        }

        System.out.println(kullanici_ad + " -" + kullanici_id + " -" + uyelik_tip_ad + " -" + ulke_ad);
        String arama;
        arama = aramaTextField.getText();

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        aramaListe.setModel(model);
        try {
            int k = 0;
            musicManager2.preparedStatement = musicManager2.con.prepareStatement("select arama_ad,arama_tablo,arama_id from arama  where arama_ad LIKE'%" + arama + "%';");

            musicManager2.rs = musicManager2.preparedStatement.executeQuery();
            while (musicManager2.rs.next()) {
                model.addElement(musicManager2.rs.getString("arama_ad") + "     " + musicManager2.rs.getString("arama_tablo"));
                arama_tablo_ad[k] = musicManager2.rs.getString("arama_tablo");
                arama_tablo_id[k] = musicManager2.rs.getInt("arama_id");
                
                k++;
            }

        } catch (SQLException ex) {
            System.out.println("hata 194");
        }


    }//GEN-LAST:event_aramaButtonActionPerformed

    public void listeDoldur(){
        try {
            int temp=0;liste.clear();
            musicManager2.preparedStatement = musicManager2.con.prepareStatement("select calma_liste_ad,id from calma_liste where kullanici_id='"+kullanici_id+"';");
            musicManager2.rs=musicManager2.preparedStatement.executeQuery();
            while(musicManager2.rs.next()){
                calmalistelerin=musicManager2.rs.getString("calma_liste_ad");
                liste.addElement(calmalistelerin);
                calma_liste1_id[temp]=musicManager2.rs.getInt(2);
                temp++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void liste2Doldur(){
        try {
            int temp2=0; liste2.clear();
            musicManager2.preparedStatement = musicManager2.con.prepareStatement("select calma_liste_ad,cl1.id,cl2.id from calma_liste as cl1,kullanici_calma_liste_goruntuleme as cl2 where cl2.kullanici_id='"+kullanici_id+"' and cl1.id=calma_liste_id;");
            musicManager2.rs=musicManager2.preparedStatement.executeQuery();
            while(musicManager2.rs.next()){
                calmalistelerin=musicManager2.rs.getString("calma_liste_ad");
                liste2.addElement(calmalistelerin);
                calma_liste2_id[temp2]=musicManager2.rs.getInt(2);
                kullanici_calma_liste_id[temp2]=musicManager2.rs.getInt(3);
                temp2++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gozatButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gozatButtonMouseClicked
        // TODO add your handling code here:

        index = arama_tablo_id[aramaListe.getSelectedIndex()];
        
        if (arama_tablo_ad[aramaListe.getSelectedIndex()].equals("sarki")) {

            sarkiLabel.setText(aramaListe.getSelectedItem().toString());
            sanatciPanel.setVisible(false);
            albumPanel.setVisible(false);
            sarkiPanel.setVisible(true);
        } else if (arama_tablo_ad[aramaListe.getSelectedIndex()].equals("sanatci")) {
            
            sanatciAd.setText(aramaListe.getSelectedItem().toString());
            String sorgu = "Select ulke_ad from sanatci where sanatci.id=?";
            try {
                musicManager2.preparedStatement = musicManager2.con.prepareStatement(sorgu);
                musicManager2.preparedStatement.setInt(1, index);
                musicManager2.rs = musicManager2.preparedStatement.executeQuery();
                while (musicManager2.rs.next()) {
                    sanatciUlke.setText(musicManager2.rs.getString("ulke_ad"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(AnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
            }
            sarkiPanel.setVisible(false);
            albumPanel.setVisible(false);
            sanatciPanel.setVisible(true);

        } else if (arama_tablo_ad[aramaListe.getSelectedIndex()].equals("album")) {
            int index = arama_tablo_id[aramaListe.getSelectedIndex()];
            albumAd.setText(aramaListe.getSelectedItem().toString());
            String sorgu = "Select album_turu,album_tarih from album where album.id='"+index+"';";
            try {
                musicManager2.preparedStatement = musicManager2.con.prepareStatement(sorgu);
                
                musicManager2.rs = musicManager2.preparedStatement.executeQuery();
                while (musicManager2.rs.next()) {
                    albumTur.setText(musicManager2.rs.getString("album_turu"));
                    albumTarih.setText(musicManager2.rs.getString("album_tarih"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(AnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
            }
            sarkiPanel.setVisible(false);
            sanatciPanel.setVisible(false);
            albumPanel.setVisible(true);
        } else {
            sarkiPanel.setVisible(false);
            sanatciPanel.setVisible(false);
            albumPanel.setVisible(false);
        }


    }//GEN-LAST:event_gozatButtonMouseClicked

    private void aramaTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_aramaTextFieldFocusGained
        aramaTextField.setText("");
    }//GEN-LAST:event_aramaTextFieldFocusGained

    private void listedekileri_takip_etMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listedekileri_takip_etMouseClicked
        mesaj_yazisi.setText("");
        int selectedindex = takip_listesi.getSelectedIndex();
        if (selectedindex == -1) {
            if (models.getSize() == 0) {
                mesaj_yazisi.setText("Premium kullanıcı takip etme listenizde yoktur. Listeniz boştur");
            } else {
                mesaj_yazisi.setText("Herhangi bir takip edilecek kullanıcı seçmediniz...");
            }
        } else {

            
                    try {
                        //Databaseden kullanici_takip tablosuna ekleme işlemi...
                        //oto incremant olayının yeri burda olamyabilir aga constructor içinde de olabilir emin değilim sen kodu okuyunca anlarsın zaten.
                        musicManager2.preparedStatement = musicManager2.con.prepareStatement("select id\n"
                                + " from kullanici_takip as o1 \n"
                                + " where id>all(select id from kullanici_takip as o2\n"
                                + "						where o1.id<>o2.id);");

                        musicManager2.rs = musicManager2.preparedStatement.executeQuery();
                        while (musicManager2.rs.next()) {
                            idd = (musicManager2.rs.getInt(1) + 1);
                        }

                        String sorgu = "Insert into kullanici_takip(id,kullanici_id,takip_edilen_id) values(?,?,?) ";
                        musicManager2.preparedStatement = musicManager2.con.prepareStatement(sorgu);
                        musicManager2.preparedStatement.setInt(1, idd);
                        musicManager2.preparedStatement.setInt(2, kullanici_id);
                        musicManager2.preparedStatement.setInt(3, arama_list_id[selectedindex]);
                        musicManager2.preparedStatement.execute();
                        kullaniciTakipGoruntule();

                    } catch (SQLException ex) {
                        Logger.getLogger(AnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    mesaj_yazisi.setText("İstediğiniz premium kullanıcı takip edildi");
                
           
                    

        }

    }//GEN-LAST:event_listedekileri_takip_etMouseClicked

    private void sarkiKapamaPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sarkiKapamaPanelMouseClicked
        sarkiPanel.setVisible(false);
    }//GEN-LAST:event_sarkiKapamaPanelMouseClicked

    private void sanatciKapamaPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sanatciKapamaPanelMouseClicked
        sanatciPanel.setVisible(false);
    }//GEN-LAST:event_sanatciKapamaPanelMouseClicked

    private void albumKapamaPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_albumKapamaPanelMouseClicked
        albumPanel.setVisible(false);
    }//GEN-LAST:event_albumKapamaPanelMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        System.exit(0);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void sanatciAdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sanatciAdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sanatciAdActionPerformed

    private void CalmaListeEkleTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalmaListeEkleTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CalmaListeEkleTextFieldActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        CalmaListeEklePanel.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        CalmaListeEklePanel.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void CalmaListeEklePanelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalmaListeEklePanelButtonActionPerformed
        // TODO add your handling code here:
        String s= CalmaListeEkleTextField.getText();
        
        try {
            
            musicManager2.preparedStatement = musicManager2.con.prepareStatement("select id\n"
                   + " from calma_liste as o1 \n"
                   + " where id>all(select id from calma_liste as o2\n"
                   + "						where o1.id<>o2.id);");
            musicManager2.rs=musicManager2.preparedStatement.executeQuery();
            while (musicManager2.rs.next()) {
                calma_liste_id = (musicManager2.rs.getInt(1) + 1);
            }
            musicManager2.preparedStatement=musicManager2.con.prepareStatement("insert into calma_liste(id,calma_liste_ad,kullanici_id) values(?,?,?);");
            musicManager2.preparedStatement.setInt(1, calma_liste_id);
            musicManager2.preparedStatement.setString(2, s);
            musicManager2.preparedStatement.setInt(3, kullanici_id);
            musicManager2.preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        listeDoldur();
        CalmaListeEklePanel.setVisible(false);
    }//GEN-LAST:event_CalmaListeEklePanelButtonActionPerformed

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        try {
            // TODO add your handling code here:
            String selected=KullaniciTakipList.getSelectedValue();
          
            musicManager2.preparedStatement=musicManager2.con.prepareStatement("delete from kullanici_takip where kullanici_id='"+kullanici_id+"' and takip_edilen_id IN(select id from kullanici where kullanici_ad='"+selected+"');");
            musicManager2.preparedStatement.execute();
            kullaniciTakipGoruntule();
        } catch (SQLException ex) {
            Logger.getLogger(AnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel15MouseClicked

    private void CalmaListeListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_CalmaListeListValueChanged
        // TODO add your handling code here:
      
    }//GEN-LAST:event_CalmaListeListValueChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        CalmaListPanel.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void CalmaListeList2ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_CalmaListeList2ValueChanged
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_CalmaListeList2ValueChanged

    private void CalmaListPanelListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_CalmaListPanelListValueChanged
        // TODO add your handling code here:
        sarkiLabel.setText(CalmaListPanelList.getSelectedValue());
            sanatciPanel.setVisible(false);
            albumPanel.setVisible(false);
            
            if(CalmaListPanelList.getSelectedIndex()!=-1)
            index = sarki_id[CalmaListPanelList.getSelectedIndex()];
            sarkiPanel.setVisible(true);
            
    }//GEN-LAST:event_CalmaListPanelListValueChanged

    private void sarkiEkleLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sarkiEkleLabelMouseClicked
        try {
            // TODO add your handling code here:
            
            
            
             String cevap = JOptionPane.showInputDialog("Şarkıyı hangi çalma listenize eklemek istersiniz ?");

            
             musicManager2.preparedStatement = musicManager2.con.prepareStatement("select id\n"
                    + " from calma_liste as o1 \n"
                    + " where calma_liste_ad='"+cevap+"' and kullanici_id='"+kullanici_id+"';");
            musicManager2.rs=musicManager2.preparedStatement.executeQuery();
            while (musicManager2.rs.next()) {
                sarki_calma_liste_calmalisteid = (musicManager2.rs.getInt(1));
            }
            
            
            sarki_calma_liste_ekle(sarki_calma_liste_calmalisteid);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sarkiEkleLabelMouseClicked

    public void sarki_calma_liste_ekle(int sarki_calma_liste_calmalisteid){
        try {
            int pop_id=0,sarki_calma_liste_id=0;
            
            musicManager2.preparedStatement = musicManager2.con.prepareStatement("select id\n"
                    + " from sarki_calma_liste as o1 \n"
                    + " where id>all(select id from sarki_calma_liste as o2\n"
                    + "						where o1.id<>o2.id);");
            musicManager2.rs=musicManager2.preparedStatement.executeQuery();
            while (musicManager2.rs.next()) {
                sarki_calma_liste_id = (musicManager2.rs.getInt(1) + 1);
            }
            
           
            
            //sarkiyi türüne göre pop,jazz veya klasik listesine otomatik olarak ekleme ve kullanıcının özel olarak istediği listeye ekleme
           
                
                
            
                musicManager2.preparedStatement=musicManager2.con.prepareStatement("insert into sarki_calma_liste(id,sarki_id,calma_liste_id) values(?,?,?);");
            musicManager2.preparedStatement.setInt(1, sarki_calma_liste_id);
            musicManager2.preparedStatement.setInt(2, index);
            musicManager2.preparedStatement.setInt(3, sarki_calma_liste_calmalisteid);
            musicManager2.preparedStatement.execute();
            
            musicManager2.preparedStatement = musicManager2.con.prepareStatement("select id\n"
                    + " from calma_liste  \n"
                    + " where calma_liste_ad='pop' and kullanici_id='"+kullanici_id+"';");
            musicManager2.rs=musicManager2.preparedStatement.executeQuery();
            while (musicManager2.rs.next()) {
                pop_id = (musicManager2.rs.getInt(1));
            }
            
            musicManager2.preparedStatement=musicManager2.con.prepareStatement("select tur_ad from sarki where id='"+index+"';");
            musicManager2.rs=musicManager2.preparedStatement.executeQuery();
            while(musicManager2.rs.next()){
                if(musicManager2.rs.getString("tur_ad").equals("pop")){
                    musicManager2.preparedStatement=musicManager2.con.prepareStatement("insert into sarki_calma_liste(id,sarki_id,calma_liste_id) values(?,?,?);");
                    musicManager2.preparedStatement.setInt(1, sarki_calma_liste_id+1);
                    musicManager2.preparedStatement.setInt(2, index);
                    musicManager2.preparedStatement.setInt(3, pop_id);
                    musicManager2.preparedStatement.execute();
                }else if(musicManager2.rs.getString("tur_ad").equals("jazz")){
                    musicManager2.preparedStatement=musicManager2.con.prepareStatement("insert into sarki_calma_liste(id,sarki_id,calma_liste_id) values(?,?,?);");
                    musicManager2.preparedStatement.setInt(1, sarki_calma_liste_id+1);
                    musicManager2.preparedStatement.setInt(2, index);
                    musicManager2.preparedStatement.setInt(3, pop_id+1);
                    musicManager2.preparedStatement.execute();
                }else if(musicManager2.rs.getString("tur_ad").equals("klasik")){
                    musicManager2.preparedStatement=musicManager2.con.prepareStatement("insert into sarki_calma_liste(id,sarki_id,calma_liste_id) values(?,?,?);");
                    musicManager2.preparedStatement.setInt(1, sarki_calma_liste_id+1);
                    musicManager2.preparedStatement.setInt(2, index);
                    musicManager2.preparedStatement.setInt(3, pop_id+2);
                    musicManager2.preparedStatement.execute();
                }
            }
            
            
        } catch (SQLException ex) {
            
        }
            
              
    }
    
    public void sarki_calma_liste_sil(){
       
        try {
            int calmalisteid=calma_liste3_id[CalmaListPanelList.getSelectedIndex()];
            
            musicManager2.preparedStatement=musicManager2.con.prepareStatement("delete from sarki_calma_liste where id='"+calmalisteid+"';");
            musicManager2.preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void HepsiniEkleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HepsiniEkleButtonActionPerformed
        try {
            // TODO add your handling code here:
            int temp=0;
            String cevap = JOptionPane.showInputDialog("Şarkıları hangi çalma listenize eklemek istersiniz ?");
            
            musicManager2.preparedStatement = musicManager2.con.prepareStatement("select id\n"
                    + " from calma_liste as o1 \n"
                    + " where calma_liste_ad='"+cevap+"' and kullanici_id='"+kullanici_id+"';");
            musicManager2.rs=musicManager2.preparedStatement.executeQuery();
            while (musicManager2.rs.next()) {
                sarki_calma_liste_calmalisteid = (musicManager2.rs.getInt(1));
            }
            
            while(temp!=CalmaListPanelList.getLastVisibleIndex()+1){
                CalmaListPanelList.setSelectedIndex(temp);
                index = sarki_id[CalmaListPanelList.getSelectedIndex()];
                sarki_calma_liste_ekle(sarki_calma_liste_calmalisteid);
                temp++;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_HepsiniEkleButtonActionPerformed

    private void Sarki_Calma_Liste_SilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sarki_Calma_Liste_SilActionPerformed
        // TODO add your handling code here:
        sarki_calma_liste_sil();
    }//GEN-LAST:event_Sarki_Calma_Liste_SilActionPerformed

    private void KullaniciTakipListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_KullaniciTakipListValueChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_KullaniciTakipListValueChanged

    private void KullaniciListeleriKapatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KullaniciListeleriKapatActionPerformed
        // TODO add your handling code here:
        KullaniciListeleriPanel.setVisible(false);
    }//GEN-LAST:event_KullaniciListeleriKapatActionPerformed

    private void KullaniciListeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KullaniciListeActionPerformed
        try {
            // TODO add your handling code here:
            int kullanici_calma_liste_goruntuleme_id=0;
            musicManager2.preparedStatement = musicManager2.con.prepareStatement("select id\n"
                    + " from kullanici_calma_liste_goruntuleme as o1 \n"
                    + " where id>all(select id from kullanici_calma_liste_goruntuleme as o2\n"
                    + "						where o1.id<>o2.id);");

            musicManager2.rs = musicManager2.preparedStatement.executeQuery();
            while (musicManager2.rs.next()) {
                kullanici_calma_liste_goruntuleme_id = (musicManager2.rs.getInt(1) + 1);
            }
            
            musicManager2.preparedStatement=musicManager2.con.prepareStatement("insert into kullanici_calma_liste_goruntuleme(id,kullanici_id,calma_liste_id) values(?,?,?);");
            musicManager2.preparedStatement.setInt(1, kullanici_calma_liste_goruntuleme_id);
            musicManager2.preparedStatement.setInt(2, kullanici_id);
            musicManager2.preparedStatement.setInt(3, calma_liste4_id[KullaniciListeleriList.getSelectedIndex()]);
            musicManager2.preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
        liste2Doldur();
    }//GEN-LAST:event_KullaniciListeActionPerformed

    private void TakiptenCikarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TakiptenCikarActionPerformed
        try {
            // TODO add your handling code here:
            musicManager2.preparedStatement=musicManager2.con.prepareStatement("delete from kullanici_calma_liste_goruntuleme where id="+kullanici_calma_liste_id[CalmaListeList2.getSelectedIndex()] +";");
            musicManager2.preparedStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
              liste2Doldur();  
    }//GEN-LAST:event_TakiptenCikarActionPerformed

    private void GenelTop10ListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_GenelTop10ListValueChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_GenelTop10ListValueChanged

    private void KullaniciTakipListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KullaniciTakipListMouseClicked
        // TODO add your handling code here:
        if(KullaniciTakipList.getSelectedIndex()!=-1)
        kullaniciListeleriGoruntule();
    }//GEN-LAST:event_KullaniciTakipListMouseClicked

    private void ListeSilButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListeSilButtonActionPerformed
        try {
            // TODO add your handling code here:
            
            musicManager2.preparedStatement=musicManager2.con.prepareStatement("delete from calma_liste where id="+calma_liste1_id[CalmaListeList.getSelectedIndex()]+";");
            musicManager2.preparedStatement.execute();
            listeDoldur();
        } catch (SQLException ex) {
            Logger.getLogger(AnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ListeSilButtonActionPerformed

    private void CalmaListeListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CalmaListeListMouseClicked
        // TODO add your handling code here:
         if(CalmaListeList.getSelectedIndex()!=-1){
        calmaListPanelGoruntule(CalmaListeList.getSelectedValue(), calma_liste1_id[CalmaListeList.getSelectedIndex()]);
         sarkiPanel.setVisible(false);}
    }//GEN-LAST:event_CalmaListeListMouseClicked

    private void CalmaListeList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CalmaListeList2MouseClicked
        // TODO add your handling code here:
        if(CalmaListeList2.getSelectedIndex()!=-1){
            calmaListPanelGoruntule(CalmaListeList2.getSelectedValue(), calma_liste2_id[CalmaListeList2.getSelectedIndex()]);
        sarkiPanel.setVisible(false);
        }
    }//GEN-LAST:event_CalmaListeList2MouseClicked

    private void GenelTop10ListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GenelTop10ListMouseClicked
        // TODO add your handling code here:
        index=genel_sarki_id[GenelTop10List.getSelectedIndex()];
        sarkiLabel.setText(GenelTop10List.getSelectedValue());
            sanatciPanel.setVisible(false);
            albumPanel.setVisible(false);
            sarkiPanel.setVisible(true);
    }//GEN-LAST:event_GenelTop10ListMouseClicked

    private void UlkeTop10ListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UlkeTop10ListMouseClicked
        // TODO add your handling code here:
        index=ulke_sarki_id[UlkeTop10List.getSelectedIndex()];
        sarkiLabel.setText(UlkeTop10List.getSelectedValue());
            sanatciPanel.setVisible(false);
            albumPanel.setVisible(false);
            sarkiPanel.setVisible(true);
    }//GEN-LAST:event_UlkeTop10ListMouseClicked

    private void PopTop10ListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PopTop10ListMouseClicked
        // TODO add your handling code here:
        index=pop_sarki_id[PopTop10List.getSelectedIndex()];
        sarkiLabel.setText(PopTop10List.getSelectedValue());
            sanatciPanel.setVisible(false);
            albumPanel.setVisible(false);
            sarkiPanel.setVisible(true);
    }//GEN-LAST:event_PopTop10ListMouseClicked

    private void JazzTop10ListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JazzTop10ListMouseClicked
        // TODO add your handling code here:
        index=jazz_sarki_id[JazzTop10List.getSelectedIndex()];
        sarkiLabel.setText(JazzTop10List.getSelectedValue());
            sanatciPanel.setVisible(false);
            albumPanel.setVisible(false);
            sarkiPanel.setVisible(true);
    }//GEN-LAST:event_JazzTop10ListMouseClicked

    private void KlasikTop10ListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KlasikTop10ListMouseClicked
        // TODO add your handling code here:
        index=klasik_sarki_id[KlasikTop10List.getSelectedIndex()];
        sarkiLabel.setText(KlasikTop10List.getSelectedValue());
            sanatciPanel.setVisible(false);
            albumPanel.setVisible(false);
            sarkiPanel.setVisible(true);
    }//GEN-LAST:event_KlasikTop10ListMouseClicked

    public void kullaniciTakipGoruntule(){
        liste3.clear();
        try {
            int temp=0;
            musicManager2.preparedStatement=musicManager2.con.prepareStatement("select kullanici_ad,kullanici.id from kullanici,kullanici_takip where kullanici.id=takip_edilen_id and kullanici_id='"+kullanici_id+"';");
            musicManager2.rs=musicManager2.preparedStatement.executeQuery();
            while(musicManager2.rs.next()){
                liste3.addElement(musicManager2.rs.getString("kullanici_ad"));
                kullanici_takip_id[temp]=musicManager2.rs.getInt(2);
                temp++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void calmaListPanelGoruntule(String calma_liste_ad,int calma_liste_id){
        
        try {
            
            if(calma_liste_ad.equals(CalmaListeList2.getSelectedValue()) && calma_liste_id== calma_liste2_id[CalmaListeList2.getSelectedIndex()]){
                Sarki_Calma_Liste_Sil.setVisible(false);
            }else{
                Sarki_Calma_Liste_Sil.setVisible(true);
            }
           
            int temp3=0;
            CalmaListPanelLabel.setText(calma_liste_ad);
            DefaultListModel<String> CalmaListListe = new DefaultListModel<>() ;
            CalmaListPanelList.setModel(CalmaListListe);
            
            musicManager2.preparedStatement=musicManager2.con.prepareStatement("select sarki_ad,sarki.id,sarki_calma_liste.id from sarki_calma_liste,sarki where calma_liste_id='"+calma_liste_id+"' and sarki.id=sarki_id;");
            musicManager2.rs=musicManager2.preparedStatement.executeQuery();
            while(musicManager2.rs.next()){
                CalmaListListe.addElement(musicManager2.rs.getString("sarki_ad"));
                sarki_id[temp3]=musicManager2.rs.getInt(2);
                calma_liste3_id[temp3]=musicManager2.rs.getInt(3);
                temp3++;
            }
            
             
            
        } catch (SQLException ex) {
            Logger.getLogger(AnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
        CalmaListPanel.setVisible(true);
    }
    
    public void kullaniciListeleriGoruntule(){
        try {
            
            int temp=0;
            liste4.clear();
            musicManager2.preparedStatement=musicManager2.con.prepareStatement("select calma_liste_ad,calma_liste.id from calma_liste,kullanici_takip where kullanici_takip.kullanici_id="+kullanici_id+" and takip_edilen_id=calma_liste.kullanici_id and takip_edilen_id="+kullanici_takip_id[KullaniciTakipList.getSelectedIndex()]+";");
            musicManager2.rs=musicManager2.preparedStatement.executeQuery();
            while(musicManager2.rs.next()){
                liste4.addElement(musicManager2.rs.getString("calma_liste_ad"));
                calma_liste4_id[temp]=musicManager2.rs.getInt(2);
                temp++;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        KullaniciListeleriPanel.setVisible(true);
    }
    
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
            java.util.logging.Logger.getLogger(AnaSayfa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AnaSayfa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AnaSayfa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AnaSayfa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AnaSayfa anasayfa = new AnaSayfa(1,"turkiye");
                    anasayfa.setVisible(true);

                } catch (SQLException ex) {
                    Logger.getLogger(AnaSayfa.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CalmaListPanel;
    private javax.swing.JLabel CalmaListPanelLabel;
    private javax.swing.JList<String> CalmaListPanelList;
    private javax.swing.JPanel CalmaListeEklePanel;
    private javax.swing.JButton CalmaListeEklePanelButton;
    private javax.swing.JTextField CalmaListeEkleTextField;
    private javax.swing.JList<String> CalmaListeList;
    private javax.swing.JList<String> CalmaListeList2;
    private javax.swing.JPanel CalmaListelerinPanel;
    private javax.swing.JLabel GenelTop10Label;
    private javax.swing.JList<String> GenelTop10List;
    private javax.swing.JButton HepsiniEkleButton;
    private javax.swing.JLabel JazzTop10Label;
    private javax.swing.JList<String> JazzTop10List;
    private javax.swing.JLabel KlasikTop10Label;
    private javax.swing.JList<String> KlasikTop10List;
    private javax.swing.JButton KullaniciListe;
    private javax.swing.JButton KullaniciListeleriKapat;
    private javax.swing.JList<String> KullaniciListeleriList;
    private javax.swing.JPanel KullaniciListeleriPanel;
    private javax.swing.JList<String> KullaniciTakipList;
    private javax.swing.JButton ListeSilButton;
    private javax.swing.JLabel PopTop10Label;
    private javax.swing.JList<String> PopTop10List;
    private javax.swing.JButton Sarki_Calma_Liste_Sil;
    private javax.swing.JButton TakiptenCikar;
    private javax.swing.JLabel UlkeTop10Label;
    private javax.swing.JList<String> UlkeTop10List;
    private javax.swing.JTextField albumAd;
    private javax.swing.JLabel albumKapamaPanel;
    private javax.swing.JPanel albumPanel;
    private javax.swing.JTextField albumTarih;
    private javax.swing.JTextField albumTur;
    private javax.swing.JButton aramaButton;
    private javax.swing.JComboBox<String> aramaListe;
    private javax.swing.JTextField aramaTextField;
    private javax.swing.JButton gozatButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel listedekileri_takip_et;
    private javax.swing.JLabel mesaj_yazisi;
    private javax.swing.JTextField sanatciAd;
    private javax.swing.JLabel sanatciKapamaPanel;
    private javax.swing.JPanel sanatciPanel;
    private javax.swing.JTextField sanatciUlke;
    private javax.swing.JLabel sarkiBaslatLabel;
    private javax.swing.JLabel sarkiDurdurma;
    private javax.swing.JLabel sarkiEkleLabel;
    private javax.swing.JLabel sarkiKapamaPanel;
    javax.swing.JLabel sarkiLabel;
    private javax.swing.JPanel sarkiPanel;
    private javax.swing.JLabel sarkiTempoLabel;
    private javax.swing.JList<String> takip_listesi;
    // End of variables declaration//GEN-END:variables
}
