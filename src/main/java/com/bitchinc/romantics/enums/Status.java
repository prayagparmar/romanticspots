package com.bitchinc.romantics.enums;

/**
 * User: prayagparmar
 * Date: 7/24/14
 * Time: 11:25 PM
 */
public enum Status {
    SUCCESS("0"),
    BAD_USERNAME_PASSWORD("21");

    String status;

    public String getStatus() {
        return status;
    }

    Status(String status) {
        this.status = status;
    }
}
