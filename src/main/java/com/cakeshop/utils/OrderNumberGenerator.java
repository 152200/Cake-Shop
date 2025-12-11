package src.main.java.com.cakeshop.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderNumberGenerator {
    private static AtomicInteger sequence = new AtomicInteger(1000);
    private static final String PREFIX = "CAKE";

    public static String generateOrderNumber() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyMMdd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

        String datePart = now.format(dateFormatter);
        String timePart = now.format(timeFormatter);
        int seqNumber = sequence.getAndIncrement();

        return String.format("%s-%s-%s-%04d",
                PREFIX, datePart, timePart, seqNumber);
    }

    public static String generateVIPOrderNumber() {
        return "VIP-" + generateOrderNumber();
    }
}
