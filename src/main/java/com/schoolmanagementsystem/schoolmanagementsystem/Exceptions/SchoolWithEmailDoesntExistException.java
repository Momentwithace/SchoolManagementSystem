package com.schoolmanagementsystem.schoolmanagementsystem.Exceptions;

public class SchoolWithEmailDoesntExistException extends Throwable {
    public SchoolWithEmailDoesntExistException(String message) {
        super(message);
    }
}
