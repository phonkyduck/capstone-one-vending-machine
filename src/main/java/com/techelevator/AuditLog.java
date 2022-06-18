package com.techelevator;

import com.techelevator.exceptions.AuditLogException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class AuditLog {

    public static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
    public static LocalDateTime now = LocalDateTime.now();

    public static void feedMoneyLog(String feedMoneyMessage, BigDecimal startingBalance, BigDecimal customerBalance){
        File auditLog = new File("src/main/java/com/techelevator/SalesLog.txt");
        feedMoneyMessage = dateTimeFormat.format(now) + " FEED MONEY: $" + String.format("%.2f", startingBalance) + " $" + String.format("%.2f", customerBalance);
        try(PrintWriter purchaseAuditor = new PrintWriter(new FileOutputStream(auditLog, true))){
            purchaseAuditor.println(feedMoneyMessage);

        }catch (AuditLogException e){
            System.err.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void salesMoneyLog(String productSalesMessage, BigDecimal startingBalance, BigDecimal customerBalance){
        File auditLog = new File("src/main/java/com/techelevator/SalesLog.txt");
        productSalesMessage = dateTimeFormat.format(now) + " FEED MONEY: $" + String.format("%.2f", startingBalance) + " $" + String.format("%.2f", customerBalance);
        try(PrintWriter purchaseAuditor = new PrintWriter(new FileOutputStream(auditLog, true))){
            purchaseAuditor.println(productSalesMessage);

        }catch (AuditLogException e){
            System.err.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
