package com.parkinglot.utils;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ConsoleUtils {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void pauseForEnter() {
        System.out.println("Press Enter to continue...");
        SCANNER.nextLine();
    }
}