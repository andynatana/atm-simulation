package com.example.demo.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class TransactionReferenceGenerator {

    public static String generateOne() {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Define the date and hour format
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH");

        // Format the date and hour
        String date = now.format(dateFormatter);
        String hour = now.format(hourFormatter);

        // Combine to form the transaction reference
        return "WD" + date + hour;
    }
}
