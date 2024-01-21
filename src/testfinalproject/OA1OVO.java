
package testfinalproject;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class OA1OVO extends javax.swing.JFrame {
    
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    public OA1OVO() {
        initComponents();
        this.setLocation(1150, 25);
        time();
        con = db.mycon();
        scaleImage(jIcon,"/pictures/Screenshot_20230103_031419.png");
        scaleImage(jLabel4, "/pictures/Screenshot_20230104_043040.png");
        scaleImage(jBoldLine, "/pictures/Screenshot_20230101_082731.png");
        scaleImage(jPayButton, "/pictures/Screenshot_20230104_043256.png");
        
    }

   
    
    public void ovoMethod(){
        int balance;
        String sql = "SELECT * FROM ovoaccount";
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                balance = rs.getInt("eMoneyBalance");
                jovoBalance.setText(rupiahCurrency.currencyFormat(balance));
            }           
      
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void time(){
        Timer timer;
        
        timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                
                Date date = new Date();
                SimpleDateFormat st = new SimpleDateFormat("hh:mm");
                
                String clockString = st.format(date);
                
                jClock.setText(clockString);
                
                ovoMethod();
            }   
        });
        
        timer.start();
    }
    
    public void scaleImage(JLabel address,String imgURL) {
        ImageIcon icon = new ImageIcon(getClass().getResource(imgURL));
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(address.getWidth(), address.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        address.setIcon(scaledIcon );
        address.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPayButton = new javax.swing.JLabel();
        jovoBalance = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jClock = new javax.swing.JLabel();
        jIcon = new javax.swing.JLabel();
        jBoldLine = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(375, 750));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPayButton.setText("PAY");
        jPayButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPayButtonMouseClicked(evt);
            }
        });
        jPanel1.add(jPayButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 157, 60, 50));

        jovoBalance.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jovoBalance.setForeground(new java.awt.Color(255, 255, 255));
        jovoBalance.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jovoBalance.setText("Rp 100.000");
        jPanel1.add(jovoBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 200, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("jLabel4");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 375, 700));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(375, 40));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jClock.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jClock.setText("hh:mm");
        jPanel2.add(jClock, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jIcon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jIcon.setText("icon (wifi, etc)");
        jIcon.setPreferredSize(new java.awt.Dimension(67, 12));
        jPanel2.add(jIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 10, 80, 20));

        jBoldLine.setText("--------------------------");
        jPanel2.add(jBoldLine, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 740, 130, 8));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 375, 750));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPayButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPayButtonMouseClicked
        this.setVisible(false);
        new OA1OVOPayment().setVisible(true);
    }//GEN-LAST:event_jPayButtonMouseClicked

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
            java.util.logging.Logger.getLogger(OA1OVO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OA1OVO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OA1OVO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OA1OVO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OA1OVO().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jBoldLine;
    private javax.swing.JLabel jClock;
    private javax.swing.JLabel jIcon;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jPayButton;
    private javax.swing.JLabel jovoBalance;
    // End of variables declaration//GEN-END:variables
}
