
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

public class OA2BCATransfer extends javax.swing.JFrame {
    
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String email;
    
    public OA2BCATransfer() {
        initComponents();
        this.setLocation(10, 25);
        time();
        con = db.mycon();
        scaleImage(jIcon,"/pictures/Screenshot_20230103_031419.png");
        scaleImage(jLogo, "/pictures/Screenshot_20230103_080537.png");
        scaleImage(jBackButton, "/pictures/Screenshot_20230104_053736.png");
    }

    OA2BCATransfer(int userID) {
        
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
        jbcaAccountNumber = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jEMoneyAccountID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLogo = new javax.swing.JLabel();
        jPin = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jBackButton = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jAmount = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(375, 750));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(6, 56, 156));
        jPanel2.setPreferredSize(new java.awt.Dimension(375, 40));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jClock.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jClock.setText("hh:mm");
        jPanel2.add(jClock, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jIcon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jIcon.setText("icon (wifi, etc)");
        jIcon.setPreferredSize(new java.awt.Dimension(67, 12));
        jPanel2.add(jIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 10, 80, 20));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("TRANSFER");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Send From :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        jbcaAccountNumber.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jbcaAccountNumber.setText("0902373726667");
        jbcaAccountNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbcaAccountNumberActionPerformed(evt);
            }
        });
        jPanel2.add(jbcaAccountNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 300, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Destination Account :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        jEMoneyAccountID.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jEMoneyAccountID.setText("GPAYID_R01 or OVID_R01");
        jEMoneyAccountID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEMoneyAccountIDActionPerformed(evt);
            }
        });
        jPanel2.add(jEMoneyAccountID, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 300, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("PIN : ");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, -1, -1));

        jLogo.setText("                        Logo");
        jLogo.setPreferredSize(new java.awt.Dimension(250, 250));
        jPanel2.add(jLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 180, 80));

        jPin.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPin.setText("1234");
        jPin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPinActionPerformed(evt);
            }
        });
        jPanel2.add(jPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 300, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(6, 56, 156));
        jButton1.setText("PAY");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 360, 42));

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
        jPanel2.add(jBackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 40, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Amount :");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, -1, -1));

        jAmount.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAmountActionPerformed(evt);
            }
        });
        jAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jAmountKeyTyped(evt);
            }
        });
        jPanel2.add(jAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 300, -1));

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

    private void jbcaAccountNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbcaAccountNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbcaAccountNumberActionPerformed

    private void jEMoneyAccountIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEMoneyAccountIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jEMoneyAccountIDActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Register code here

        String eMoneyId = jEMoneyAccountID.getText();
        String amount = jAmount.getText();
        String pin = jPin.getText();
        
        Date date = new Date();
        SimpleDateFormat st = new SimpleDateFormat("yyyy-MM-dd");
        String dateToday = st.format(date);
        
        if(eMoneyId.equals("GPAYID_R01")){
            int balance;
            try {

                String sql = "SELECT * FROM bcaaccount WHERE pinNumber=?";
                pst = con.prepareCall(sql);

                pst.setString(1, pin); // pin

                rs = pst.executeQuery();

                if (rs.next()) { // if there is still a table next to the table, then true
                    String sqlForTransfer = "UPDATE bcaaccount " +
                             "SET balance = balance - " + amount +
                             " WHERE bcaAccountID = 'BCA_IDRAYSEN01' AND pinNumber = " + pin +";";

                    Statement s = db.mycon().createStatement();

                    s.executeUpdate(sqlForTransfer);

                    String sqlForInsertNewTransaction = " INSERT INTO bcatransaction (transferTo, amount, transactionDate) " +
                                                        "VALUES ('"+eMoneyId+"','"+amount+"','"+dateToday+"')";

                    s.executeUpdate(sqlForInsertNewTransaction);
                    
                    String sqlForTopUp = "UPDATE gopayaccount " +
                         "SET eMoneyBalance = eMoneyBalance + " + amount +
                         " WHERE gopayAccountID = 'GPAYID_R01' AND pinNumber = " + pin +";";

                    s.executeUpdate(sqlForTopUp);

                    String sqlForInsertNewTransactionGopay = " INSERT INTO gopaytransaction (transactionType, amount, transactionDate) " +
                                                        "VALUES ('Income','"+amount+"','"+dateToday+"')";

                    s.executeUpdate(sqlForInsertNewTransactionGopay);
                    
                    String sqlToUpdateAccounts = "SELECT * FROM gopayaccount WHERE gopayAccountID=?";
                    pst = con.prepareCall(sqlToUpdateAccounts);

                    pst.setString(1, eMoneyId); // pin

                    rs = pst.executeQuery();

                    if (rs.next()) {
                    
                    balance = rs.getInt("eMoneyBalance");
                
                    String sqlToConnectBalance = "UPDATE accounts " +
                         "SET eMoneyBalance = " + balance +
                         " WHERE eMoneyID = 'GPAYID_R01';";
            
                     s.executeUpdate(sqlToConnectBalance);
                    }
                    
                    JOptionPane.showMessageDialog(rootPane, "Payment Successful");

//                    jEMoneyAccountID.setText("");
//                    jAmount.setText("");
//                    jPin.setText("");
                } else { // its false DO this
                    JOptionPane.showMessageDialog(rootPane, "Payment failed! Check Your PIN");
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        } else if(eMoneyId.equals("OVID_R01")){
            int balance;
            try {

                String sql = "SELECT * FROM bcaaccount WHERE pinNumber=?";
                pst = con.prepareCall(sql);

                pst.setString(1, pin); // pin

                rs = pst.executeQuery();

                if (rs.next()) { // if there is still a table next to the table, then true
                    String sqlForTransfer = "UPDATE bcaaccount " +
                             "SET balance = balance - " + amount +
                             " WHERE bcaAccountID = 'BCA_IDRAYSEN01' AND pinNumber = " + pin +";";

                    Statement s = db.mycon().createStatement();

                    s.executeUpdate(sqlForTransfer);

                    String sqlForInsertNewTransaction = " INSERT INTO bcatransaction (transferTo, amount, transactionDate) " +
                                                        "VALUES ('"+eMoneyId+"','"+amount+"','"+dateToday+"')";

                    s.executeUpdate(sqlForInsertNewTransaction);
                    
                    String sqlForTopUp = "UPDATE ovoaccount " +
                         "SET eMoneyBalance = eMoneyBalance + " + amount +
                         " WHERE ovoAccountID = 'OVID_R01' AND pinNumber = " + pin +";";

                    s.executeUpdate(sqlForTopUp);

                    String sqlForInsertNewTransactionOVO = " INSERT INTO ovotransaction (transactionType, amount, transactionDate) " +
                                                        "VALUES ('Income','"+amount+"','"+dateToday+"')";

                    s.executeUpdate(sqlForInsertNewTransactionOVO);
                    
                    String sqlToUpdateAccounts = "SELECT * FROM ovoaccount WHERE ovoAccountID=?";
                    pst = con.prepareCall(sqlToUpdateAccounts);

                    pst.setString(1, eMoneyId); // pin

                    rs = pst.executeQuery();

                    if (rs.next()) {
                    
                    balance = rs.getInt("eMoneyBalance");
                
                    String sqlToConnectBalance = "UPDATE accounts " +
                         "SET eMoneyBalance = " + balance +
                         " WHERE eMoneyID = 'OVID_R01';";
            
                     s.executeUpdate(sqlToConnectBalance);
                    }
                    
                    JOptionPane.showMessageDialog(rootPane, "Payment Successful");

//                    jEMoneyAccountID.setText("");
//                    jAmount.setText("");
//                    jPin.setText("");
                } else { // its false DO this
                    JOptionPane.showMessageDialog(rootPane, "Payment failed! Check Your PIN");
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Account ID Not Found");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jAmountActionPerformed

    private void jAmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAmountKeyTyped
        char c = evt.getKeyChar();
         
        if(!Character.isDigit(c)){
            evt.consume();}
    }//GEN-LAST:event_jAmountKeyTyped

    private void jPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPinActionPerformed
    
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
            java.util.logging.Logger.getLogger(OA2BCALogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OA2BCALogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OA2BCALogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OA2BCALogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OA2BCATransfer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jAmount;
    private javax.swing.JLabel jBackButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jClock;
    private javax.swing.JTextField jEMoneyAccountID;
    private javax.swing.JLabel jIcon;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLogo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPin;
    private javax.swing.JTextField jbcaAccountNumber;
    // End of variables declaration//GEN-END:variables
}
