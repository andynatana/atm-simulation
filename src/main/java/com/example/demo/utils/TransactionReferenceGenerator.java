package com.example.demo.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class TransactionReferenceGenerator {

    public static String generateOne() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Format the date and time components
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH.mm.ss");

        String date = now.format(dateFormatter);
        String time = now.format(timeFormatter);

        // Combine the components to form the transaction reference
        return "WD." + date + "." + time;
    }
}
