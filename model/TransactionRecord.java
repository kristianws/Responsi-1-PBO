package model;

import  java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionRecord {
    private String id;
    private String username;
    private String carId;
    private int days;
    private double pricePerDay;
    private LocalDateTime timeStamp;

    public TransactionRecord(String id, String username, String carId, int days, double price) {
        this.id = id;
        this.username = username;
        this.carId = carId;
        this.days = days;
        this.pricePerDay = price ;
        this.timeStamp = LocalDateTime.now();
    }


    public String getId() { return this.id; }
    public String getUsername() { return this.username; }
    public String getCarId() { return this.carId; }
    public int getDays() { return this.days; }
    public double getPrice() { return this.pricePerDay; }
    public LocalDateTime getTimestamp() { return this.timeStamp; }


    @Override
    public String toString() {
        return id + " | " + username + " | " + carId + " | days:" + days + " | Rp" + this.pricePerDay + " | " + this.timeStamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    // file: id|username|carId|days|amountPaid|timestamp
    public String toFileString() {
        return id + "|" + username + "|" + carId + "|" + days + "|" + this.pricePerDay + "|" + this.timeStamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static TransactionRecord fromFileString(String line) throws Exception {
        String[] p = line.split("\\|");
        if (p.length != 6) throw new Exception("Format transaksi salah: " + line);
        TransactionRecord tr = new TransactionRecord(p[0], p[1], p[2], Integer.parseInt(p[3]), Double.parseDouble(p[4]));
        tr.timeStamp = LocalDateTime.parse(p[5]);
        return tr;
    }
}
