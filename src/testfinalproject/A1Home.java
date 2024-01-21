
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
import java.text.DateFormatSymbols;


public class A1Home extends javax.swing.JFrame {
    
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    int userID;
    
    public A1Home() {
        initComponents();
        this.setLocation(770, 25);
        con = db.mycon();
        scaleImage(jIcon,"/pictures/Screenshot_20221218_101736.png");
        scaleImage(jUserIcon, "/pictures/Screenshot_20221220_034410.png");
        scaleImage(jBoldLine, "/pictures/Screenshot_20230101_082731.png");
        scaleImage(jHomeIcon, "/pictures/Screenshot_20230102_102227.png");
        scaleImage(jTransactionIcon,"/pictures/Screenshot_20230102_105010.png");
        scaleImage(jAccountIcon,"/pictures/Screenshot_20230102_105620.png");
        scaleImage(jBudgetIcon,"/pictures/Screenshot_20230102_105652.png");
        scaleImage(jProfileIcon,"/pictures/Screenshot_20230102_105711.png");
    }
    
    public void scaleImage(JLabel address,String imgURL) {
        ImageIcon icon = new ImageIcon(getClass().getResource(imgURL));
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(address.getWidth(), address.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        address.setIcon(scaledIcon );
        address.setText("");
    }

    A1Home(int userIDParameter) {
        
        this();
        
        userID = userIDParameter;
        
        setJLabel(userIDParameter);
        time(userIDParameter);
    }
    
    
    
    public void setJLabel(int userID){
        String sql = "SELECT * FROM user WHERE userID = " + userID +";";
        try{
            PreparedStatement pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
                String displayName = rs.getString("userName");
                jDisplayName.setText(displayName); 
            }
      
        } catch(Exception e) {
            
        }
    }
    
    public void time(int userID){
        Timer timer;
        
        timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                
                Date date = new Date();
                SimpleDateFormat st = new SimpleDateFormat("hh:mm");
                
                String clockString = st.format(date);
                
                jClock.setText(clockString);
                
                autoRefreshItems(userID);
            }   
        });
        
        timer.start();
    }
    
    
    
    public void autoRefreshItems(int userID){
        int monthInNumber = jMonthChooser1.getMonth();
        String monthWithFormattedString = setMonthWithFormattedString(monthInNumber);
        int year = jYearChooser1.getYear();
        String yearAndMonthInString = year + monthWithFormattedString;
        
        showingTransaction(userID, yearAndMonthInString);
    }
    
//    //The method below is used to get month in string based on number (1-12)
//    public String getMonth(int month) {
//     return new DateFormatSymbols().getMonths()[month-1];
//    }
    
    public String setMonthWithFormattedString(int monthInNumber){
        String monthInString = null;
        switch (monthInNumber) {
            case 0:
              monthInString = "-01-";
              break;
            case 1:
              monthInString = "-02-";
              break;
            case 2:
              monthInString = "-03-";
              break;
            case 3:
              monthInString = "-04-";
              break;
            case 4:
              monthInString = "-05-";
              break;
            case 5:
              monthInString = "-06-";
              break;
            case 6:
              monthInString = "-07-";
              break;
            case 7:
              monthInString = "-08-";
              break;
            case 8:
              monthInString = "-09-";
              break;
            case 9:
              monthInString = "-010-";
              break;
            case 10:
              monthInString = "-11-";
              break;
            case 11:
              monthInString = "-12-";
              break;
        }
        return monthInString;
    }
    
    public void showingTransaction(int userID, String yearAndMonthInString){
        String sql = "SELECT transactionDate, eMoneyType, amount, transactionType, transactionTime FROM transactionhistory " +
                     "WHERE userID = " + userID + " AND transactionDate LIKE '" + yearAndMonthInString + "%' " +
                     "UNION ALL SELECT transactionDate, eMoneyType, amount, transactionType, transactionTime FROM gopaytransaction " +
                     "WHERE userID = " + userID + " AND transactionDate LIKE '" + yearAndMonthInString + "%' " +
                     "UNION ALL SELECT transactionDate, eMoneyType, amount, transactionType, transactionTime FROM ovotransaction " +
                     "WHERE userID = " + userID + " AND transactionDate LIKE '" + yearAndMonthInString + "%' " +
                     "ORDER BY transactionDate DESC, transactionTime DESC;";
        
        try{
            PreparedStatement pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                model.addRow(new String[]{
                    rs.getString("transactionDate"), 
                    rs.getString("eMoneyType"), 
                    rupiahCurrency.currencyFormat(Integer.valueOf(rs.getString("amount"))), 
                    rs.getString("transactionType")});
            }
      
        } catch(Exception e) {
            
        }
        
        String sqlForExpensesTotalInAMonth = 
            "SELECT SUM(amount) FROM " +
                "( " +
                "SELECT transactionDate, eMoneyType, amount, transactionType FROM transactionhistory " +
                "WHERE userID = " + userID + " AND transactionDate LIKE '" + yearAndMonthInString + "%' AND transactionType = 'Expense' " +
                "UNION ALL " +
                "SELECT transactionDate, eMoneyType, amount, transactionType FROM gopaytransaction " +
                "WHERE userID = " + userID + " AND transactionDate LIKE '" + yearAndMonthInString + "%' AND transactionType = 'Expense' " +
                "UNION ALL " +
                "SELECT transactionDate, eMoneyType, amount, transactionType FROM ovotransaction " +
                "WHERE userID = " + userID + " AND transactionDate LIKE '" + yearAndMonthInString + "%' AND transactionType = 'Expense' " +
                ") " +
                "Expenses;";
        
        try{

           PreparedStatement statement =  con.prepareStatement(sqlForExpensesTotalInAMonth);
           ResultSet result = statement.executeQuery();
           result.next();
           int sumAmount = result.getInt(1);
           jExpensesTotal.setText(rupiahCurrency.currencyFormat(sumAmount));

          } catch(Exception exc){
              System.out.println(exc.getMessage());
          }
        
        String sqlForIncomesTotalInAMonth = 
            "SELECT SUM(amount) FROM " +
                "( " +
                "SELECT transactionDate, eMoneyType, amount, transactionType FROM transactionhistory " +
                "WHERE userID = " + userID + " AND transactionDate LIKE '" + yearAndMonthInString + "%' AND transactionType = 'Income' " +
                "UNION ALL " +
                "SELECT transactionDate, eMoneyType, amount, transactionType FROM gopaytransaction " +
                "WHERE userID = " + userID + " AND transactionDate LIKE '" + yearAndMonthInString + "%' AND transactionType = 'Income' " +
                "UNION ALL " +
                "SELECT transactionDate, eMoneyType, amount, transactionType FROM ovotransaction " +
                "WHERE userID = " + userID + " AND transactionDate LIKE '" + yearAndMonthInString + "%' AND transactionType = 'Income' " +
                ") " +
            "Incomes;";
        
        try{

           PreparedStatement statement =  con.prepareStatement(sqlForIncomesTotalInAMonth);
           ResultSet result = statement.executeQuery();
           result.next();
           int sumAmount = result.getInt(1);
           jIncomesTotal.setText(rupiahCurrency.currencyFormat(sumAmount));

          } catch(Exception exc){
              System.out.println(exc.getMessage());
          }
        
        String sqlAccountsBalance = "SELECT SUM(eMoneyBalance) from accounts WHERE userID = " + userID + 
                                    " AND moneyType = 'E-Money';";
        try{

           PreparedStatement statement =  con.prepareStatement(sqlAccountsBalance);
           ResultSet result = statement.executeQuery();
           result.next();
           int sumAmount = result.getInt(1);
           jAccountBalance.setText(rupiahCurrency.currencyFormat(sumAmount));

          } catch(Exception exc){
              System.out.println(exc.getMessage());
          }
        
        String sqlCashBalance = "SELECT SUM(eMoneyBalance) from accounts WHERE userID = " + userID + 
                                " AND moneyType = 'Cash';";
        try{

           PreparedStatement statement =  con.prepareStatement(sqlCashBalance);
           ResultSet result = statement.executeQuery();
           result.next();
           int sumCashAmount = result.getInt(1);
           jCashBalance.setText(rupiahCurrency.currencyFormat(sumCashAmount));

          } catch(Exception exc){
              System.out.println(exc.getMessage());
          }
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelHome = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jClock = new javax.swing.JLabel();
        jIcon = new javax.swing.JLabel();
        jUserIcon = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jAccountBalance = new javax.swing.JLabel();
        jPanelIncome = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jIncomesTotal = new javax.swing.JLabel();
        jPanelExpenses = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jExpensesTotal = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jBoldLine = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jHomeIcon = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTransactionIcon = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jAccountIcon = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jBudgetIcon = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jProfileIcon = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jDisplayName = new javax.swing.JLabel();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jLabel4 = new javax.swing.JLabel();
        jCashBalance = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelHome.setBackground(new java.awt.Color(241, 255, 252));
        jPanelHome.setPreferredSize(new java.awt.Dimension(375, 750));
        jPanelHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(241, 255, 252));
        jPanel2.setPreferredSize(new java.awt.Dimension(375, 40));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jClock.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jClock.setText("hh:mm");
        jPanel2.add(jClock, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jIcon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jIcon.setText("icon (wifi, etc)");
        jIcon.setPreferredSize(new java.awt.Dimension(67, 12));
        jPanel2.add(jIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(292, 15, -1, -1));

        jPanelHome.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 375, 40));

        jUserIcon.setText(" User");
        jUserIcon.setPreferredSize(new java.awt.Dimension(32, 32));
        jPanelHome.add(jUserIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel1.setText("E-Money Balance");
        jPanelHome.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        jAccountBalance.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jAccountBalance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jAccountBalance.setText("Rp 1.200.000,00");
        jPanelHome.add(jAccountBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 180, -1));

        jPanelIncome.setBackground(new java.awt.Color(0, 168, 107));

        jLabel2.setBackground(new java.awt.Color(0, 168, 107));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Incomes");
        jLabel2.setPreferredSize(new java.awt.Dimension(75, 25));

        jIncomesTotal.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jIncomesTotal.setForeground(new java.awt.Color(255, 255, 255));
        jIncomesTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jIncomesTotal.setText("Rp 1.000.000,00");

        javax.swing.GroupLayout jPanelIncomeLayout = new javax.swing.GroupLayout(jPanelIncome);
        jPanelIncome.setLayout(jPanelIncomeLayout);
        jPanelIncomeLayout.setHorizontalGroup(
            jPanelIncomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jIncomesTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelIncomeLayout.setVerticalGroup(
            jPanelIncomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIncomeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jIncomesTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelHome.add(jPanelIncome, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 160, 70));

        jPanelExpenses.setBackground(new java.awt.Color(253, 60, 74));
        jPanelExpenses.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Expenses");

        jExpensesTotal.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jExpensesTotal.setForeground(new java.awt.Color(255, 255, 255));
        jExpensesTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jExpensesTotal.setText("Rp 1.000.000,00");

        javax.swing.GroupLayout jPanelExpensesLayout = new javax.swing.GroupLayout(jPanelExpenses);
        jPanelExpenses.setLayout(jPanelExpensesLayout);
        jPanelExpensesLayout.setHorizontalGroup(
            jPanelExpensesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jExpensesTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelExpensesLayout.setVerticalGroup(
            jPanelExpensesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelExpensesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jExpensesTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );

        jPanelHome.add(jPanelExpenses, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 160, 70));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Date", "Money Type", "Amount", "Transaction"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanelHome.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 340, 260));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(375, 50));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBoldLine.setText("--------------------------");
        jPanel5.add(jBoldLine, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 126, 8));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(75, 65));

        jHomeIcon.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jHomeIcon.setText("Home");
        jHomeIcon.setPreferredSize(new java.awt.Dimension(32, 32));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel5.setText("Home");
        jLabel5.setPreferredSize(new java.awt.Dimension(32, 32));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jHomeIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jHomeIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(65, 65));

        jTransactionIcon.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jTransactionIcon.setText("Transaction");
        jTransactionIcon.setPreferredSize(new java.awt.Dimension(32, 32));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel7.setText("Transaction");
        jLabel7.setPreferredSize(new java.awt.Dimension(32, 32));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jTransactionIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jTransactionIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(75, 65));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });

        jAccountIcon.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jAccountIcon.setText("Account");
        jAccountIcon.setPreferredSize(new java.awt.Dimension(32, 32));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel8.setText("Account");
        jLabel8.setPreferredSize(new java.awt.Dimension(32, 32));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jAccountIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jAccountIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(65, 65));

        jBudgetIcon.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jBudgetIcon.setText("Budget");
        jBudgetIcon.setPreferredSize(new java.awt.Dimension(32, 32));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel9.setText("Budget");
        jLabel9.setPreferredSize(new java.awt.Dimension(32, 32));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBudgetIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jBudgetIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(75, 65));

        jProfileIcon.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jProfileIcon.setText("Profile");
        jProfileIcon.setPreferredSize(new java.awt.Dimension(32, 32));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel10.setText("Profile");
        jLabel10.setPreferredSize(new java.awt.Dimension(32, 32));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProfileIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jProfileIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, -1));

        jPanelHome.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 660, -1, 90));

        jDisplayName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jDisplayName.setForeground(new java.awt.Color(64, 128, 128));
        jDisplayName.setText("Name");
        jPanelHome.add(jDisplayName, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 180, 32));
        jPanelHome.add(jMonthChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 90, 30));
        jPanelHome.add(jYearChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 70, 30));

        jLabel4.setText("Cash Balance");
        jPanelHome.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, -1, -1));

        jCashBalance.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jCashBalance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCashBalance.setText("Rp 1.200.000,00");
        jPanelHome.add(jCashBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 180, -1));

        jButton1.setBackground(new java.awt.Color(64, 128, 128));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add New Transaction (Cash Only)");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanelHome.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, 320, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanelHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        this.setVisible(false);
        new A2Account(userID).setVisible(true);
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        new A3AddNewCashTransaction(userID).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    
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
            java.util.logging.Logger.getLogger(A1Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(A1Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(A1Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(A1Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new A1Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jAccountBalance;
    private javax.swing.JLabel jAccountIcon;
    private javax.swing.JLabel jBoldLine;
    private javax.swing.JLabel jBudgetIcon;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jCashBalance;
    private javax.swing.JLabel jClock;
    private javax.swing.JLabel jDisplayName;
    private javax.swing.JLabel jExpensesTotal;
    private javax.swing.JLabel jHomeIcon;
    private javax.swing.JLabel jIcon;
    private javax.swing.JLabel jIncomesTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelExpenses;
    private javax.swing.JPanel jPanelHome;
    private javax.swing.JPanel jPanelIncome;
    private javax.swing.JLabel jProfileIcon;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jTransactionIcon;
    private javax.swing.JLabel jUserIcon;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    // End of variables declaration//GEN-END:variables
}
