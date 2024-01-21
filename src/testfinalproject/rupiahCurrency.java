/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testfinalproject;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;
/**
 *
 * @author raysen
 */
public class rupiahCurrency {
    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        while(true){
//        System.out.println("input: ");
//        int number = input.nextInt();
//        System.out.println(CurrencyFormat(number));
//        }
    }
    
    public static String currencyFormat(int number){
        DecimalFormat kursIndonesia;
        DecimalFormatSymbols formatRupiah;
        
        kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        formatRupiah = new DecimalFormatSymbols();
        
        formatRupiah.setCurrencySymbol("Rp ");
        formatRupiah.setMonetaryDecimalSeparator(',');
        formatRupiah.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRupiah);
        
        return kursIndonesia.format(number);
    }
}
