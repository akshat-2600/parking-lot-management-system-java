package com.parkinglot.exceptions;

public class InvalidTicketException extends Exception {
    public InvalidTicketException (String message) {
        super(message);
    }
}