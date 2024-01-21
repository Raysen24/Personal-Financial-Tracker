
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class OA1OVOPayment extends javax.swing.JFrame {
    
    Connection con = db.mycon();
    ResultSet rs = null;
    PreparedStatement pst = null;
    String email;
    Statement stmt = null;
    
    public OA1OVOPayment() {
        initComponents();
        this.setLocation(1150, 25);
        time();
        con = db.mycon();
        scaleImage(jIcon,"/pictures/Screenshot_20230103_031419.png");
        scaleImage(jLogo, "/pictures/Screenshot_20230104_044326.png");
        scaleImage(jBackButton, "/pictures/Screenshot_20230104_053644.png");
    }

    OA1OVOPayment(int userID) {
        
        this();
        
        //email = em;
//        showingTransaction(userID);
    }
    
//    public void showingTransaction(int userID){
//        String sql = "SELECT * FROM transactionhistory WHERE userID = " + userID +";";
//        try{
//            PreparedStatement pst = con.prepareStatement(sql);
//            rs = pst.executeQuery();
//            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
//            model.setRowCount(0);
//            while(rs.next()){
//                model.addRow(new String[]{rs.getString("transactionDate"), rs.getString("e-moneyType"), rs.getString("amount"), rs.getString("transactionInfo")});
//            }
//      
//        } catch(Exception e) {
//            
//        }
//    }
    
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
        jPanel2 = new javax.swing.JPanel();
        jClock = new javax.swing.JLabel();
        jIcon = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jItem = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPrice = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLogo = new javax.swing.JLabel();
        jPin = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jBackButton = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(375, 750));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(74, 50, 144));
        jPanel2.setPreferredSize(new java.awt.Dimension(375, 40));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jClock.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jClock.setText("hh:mm");
        jPanel2.add(jClock, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jIcon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jIcon.setText("icon (wifi, etc)");
        jIcon.setPreferredSize(new java.awt.Dimension(67, 12));
        jPanel2.add(jIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 10, 80, 20));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("PAYMENT");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Item :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, -1));

        jItem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jItemActionPerformed(evt);
            }
        });
        jPanel2.add(jItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 300, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Price :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, -1, -1));

        jPrice.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPriceActionPerformed(evt);
            }
        });
        jPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPriceKeyTyped(evt);
            }
        });
        jPanel2.add(jPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 300, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("PIN : ");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, -1, -1));

        jLogo.setText("                            Logo");
        jLogo.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel2.add(jLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 200, 100));

        jPin.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel2.add(jPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 300, -1));

        jButton1.setBackground(new java.awt.Color(153, 153, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("PAY");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 360, 42));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(375, 40));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 375, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jBackButton.setForeground(new java.awt.Color(255, 255, 255));
        jBackButton.setText("Back");
        jBackButton.setToolTipText("");
        jBackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBackButtonMouseClicked(evt);
            }
        });
        jPanel2.add(jBackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 40, 40));

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

    private void jItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jItemActionPerformed

    private void jPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPriceActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Register code here

        String item = jItem.getText();
        String price = jPrice.getText();
        String pin = jPin.getText();
        int balance;
        
        Date date = new Date();
        SimpleDateFormat st = new SimpleDateFormat("yyyy-MM-dd");
        String dateToday = st.format(date);
        
        try {
            
            String sql = "SELECT * FROM ovoaccount WHERE pinNumber=?";
            pst = con.prepareCall(sql);
            
            pst.setString(1, pin); // pin
            
            rs = pst.executeQuery();
            
            if (rs.next()) { // if there is still a table next to the table, then true
                String sqlForPay = "UPDATE ovoaccount " +
                         "SET eMoneyBalance = eMoneyBalance - " + price +
                         " WHERE ovoAccountID = 'OVID_R01' AND pinNumber = "+ pin +";";
            
                Statement s = db.mycon().createStatement();
            
                s.executeUpdate(sqlForPay);
                                
                String sqlForInsertNewTransaction = " INSERT INTO ovotransaction (transactionType, amount, transactionDate) " +
                                                    "VALUES ('Expense','"+price+"','"+dateToday+"')";
                
                s.executeUpdate(sqlForInsertNewTransaction);
                 
//                JOptionPane.showMessageDialog(rootPane, "Payment Successful");

//                this.setVisible(false);
//                new OA1GopayPayment().setVisible(true);
                jItem.setText("");
                jPrice.setText("");
                jPin.setText("");
            } else { // its false DO this
                JOptionPane.showMessageDialog(rootPane, "Payment failed! Check Your PIN");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        try {
            
            String sql = "SELECT * FROM ovoaccount WHERE pinNumber=?";
            pst = con.prepareCall(sql);
            
            pst.setString(1, pin); // pin
            
            rs = pst.executeQuery();
            
            if (rs.next()) { // if there is still a table next to the table, then true
                
                
                balance = rs.getInt("eMoneyBalance");
                
//                System.out.println(balance);
                
                String sqlToConnectBalance = "UPDATE accounts " +
                         "SET eMoneyBalance = " + balance +
                         " WHERE eMoneyID = 'OVID_R01';";
                
                Statement s = db.mycon().createStatement();
                
                s.executeUpdate(sqlToConnectBalance);
                    
                JOptionPane.showMessageDialog(rootPane, "Payment Successful");

//                this.setVisible(false);
//                new OA1GopayPayment().setVisible(true);
                jItem.setText("");
                jPrice.setText("");
                jPin.setText("");
            } else { // its false DO this
//                JOptionPane.showMessageDialog(rootPane, "Payment failed! Check Your PIN");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jBackButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBackButtonMouseClicked
        this.setVisible(false);
        new OA1OVO().setVisible(true);
    }//GEN-LAST:event_jBackButtonMouseClicked

    private void jPriceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPriceKeyTyped
        char c = evt.getKeyChar();
         
        if(!Character.isDigit(c)){
            evt.consume();
        }
    }//GEN-LAST:event_jPriceKeyTyped

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
            java.util.logging.Logger.getLogger(OA1OVOPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OA1OVOPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OA1OVOPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OA1OVOPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OA1OVOPayment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jBackButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jClock;
    private javax.swing.JLabel jIcon;
    private javax.swing.JTextField jItem;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLogo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPin;
    private javax.swing.JTextField jPrice;
    // End of variables declaration//GEN-END:variables
}
