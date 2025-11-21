package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaksi {
    private String platNoMobil;
    private String namaPelanggan;
    private String nohp;
    private int hariSewa;
    private double totalHarga;
    private LocalDateTime waktuTransaksi;
    private boolean isDone;

    public Transaksi(){}

    public Transaksi(User penyewa, Mobil mobilDisewa, int hari) {
        assert hari >= 0;

        this.platNoMobil = mobilDisewa.getNoPlat();
        this.namaPelanggan = penyewa.getName();
        this.hariSewa = hari;
        this.nohp = penyewa.getPhone();
        this.totalHarga = hari * mobilDisewa.getPrice();
        this.waktuTransaksi = LocalDateTime.now();
        this.isDone = false;
    }

    public void done() {
        if (!this.isDone) {
            this.isDone = true;
        }
    }

    public void setDone(boolean state) {
        this.isDone = state;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getPlatNoMobil() {
        return platNoMobil;
    }

    public void setPlatNoMobil(String platNoMobil) {
        this.platNoMobil = platNoMobil;
    }

    // 2. Untuk namaPelanggan
    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    // 3. Untuk nohp
    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    // 4. Untuk hariSewa
    public int getHariSewa() {
        return hariSewa;
    }

    public void setHariSewa(int hariSewa) {
        if (hariSewa > 0) {
            this.hariSewa = hariSewa;
        } else {
            System.out.println("Error: Hari sewa harus lebih dari 0");
        }
    }

    // 5. Untuk totalHarga
    public double getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(double totalHarga) {
        this.totalHarga = totalHarga;
    }

    public LocalDateTime getWaktuTransaksi() {
        return waktuTransaksi;
    }

    public void setWaktuTransaksi(LocalDateTime timeStamp) {
        this.waktuTransaksi = timeStamp;
    }

    public String getFormattedTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return waktuTransaksi.format(format);
    }

    @Override
    public String toString() {
        String status;
        if (isDone) {
            status = "DONE";
        } else {
            status = "NOT DONE";
        }

        return getFormattedTime() + " - " + namaPelanggan + " (" + platNoMobil + ") - No : " + nohp + " - " + hariSewa + " - Rp" + totalHarga + "(" + status + ")";
    }


    public String toFileString() {
        return platNoMobil + "|" + namaPelanggan + "|" + nohp + "|" + hariSewa + "|" + totalHarga + "|" + getFormattedTime() + "|"+ isDone +"\n";
    }

    public static Transaksi fromFileToString(String line) throws Exception {
        String[] data =line.split("\\|");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        if (data.length != 7) {
            throw new Exception("Format File Transaksi Salah : " + line);
        }

        Transaksi baru = new Transaksi();
        baru.setPlatNoMobil(data[0]);
        baru.setNamaPelanggan(data[1]);
        baru.setNohp(data[2]);
        baru.setHariSewa(Integer.parseInt(data[3]));
        baru.setTotalHarga(Double.parseDouble(data[4]));
        baru.setWaktuTransaksi(LocalDateTime.parse(data[5], format));
        baru.setDone(Boolean.parseBoolean(data[6]));
        
        return baru;
    }



}
