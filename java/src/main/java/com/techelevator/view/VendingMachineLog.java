package com.techelevator.view;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VendingMachineLog {

    public static void log(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm.ss");
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(formatter);
        try (PrintWriter logger = new PrintWriter(new FileWriter("logs/Log.txt", true))) {
            logger.println(formattedDateTime + " : " + message);
        } catch (FileNotFoundException e) {
            VendingMachineException logException = new VendingMachineException("Caught FileNotFoundException. Message: " + e.getMessage());
        } catch (IOException e) {
            throw new VendingMachineException(e.getMessage());
        }
    }
}
