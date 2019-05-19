package com.berkay22demirel.sinavpuanhesaplama.Exception;

public class DatabaseException extends Exception {

    public final static String UNEXPECTED_ERROR_TEXT = "Beklenmedik bir hata oluştu!";

    public DatabaseException(String message) {
        super(message);
    }
}
