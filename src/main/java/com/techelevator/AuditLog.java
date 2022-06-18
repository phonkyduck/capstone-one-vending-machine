package com.techelevator;

import com.techelevator.exceptions.AuditLogException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class AuditLog {

    public static void feedMoneyLog(String dateTimeFormat.format(now) + "FEED MONEY: " + startingBalance + " " + customerBalance){
        File auditLog = new File("SalesLog.txt");

        try(PrintWriter purchaseAuditor = new PrintWriter(new FileOutputStream(auditLog, true))){
            purchaseAuditor.println(dateTimeFormat.format(now) + "FEED MONEY: " + startingBalance + " " + customerBalance);

        }catch (AuditLogException e){
            System.err.println(message);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
