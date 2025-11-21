package Responsi1_PPBO_L0124060_SC4;

import java.util.Scanner;

import Responsi1_PPBO_L0124060_SC3.Transaksi;
import Responsi1_PPBO_L0124060_SC3.User;

public class ViewApp {
    private Scanner input;

    public ViewApp(Scanner input) {
        this.input = input;
    }

    
    public void banner() {
        System.out.println("-".repeat(40));
        System.out.print(" ".repeat(9));
        System.out.println("Rental Mobil Suka Maju");
        System.out.println("-".repeat(40));
    }

    public void startMenu() {
        banner();
        System.out.println("\t[1] Login");
        System.out.println("\t[2] Register");
        System.out.println("\t[3] Exit");
        System.out.println("-".repeat(40));
    }

    public void menuAfterLogin() {
        System.out.println("\t[1] Lihat Daftar Mobil");
        System.out.println("\t[2] Sewa Mobil");
        System.out.println("\t[3] Kembalikan Mobil");
        System.out.println("\t[4] Daftar Transaksi");
        System.out.println("\t[5] Kembali");
        System.out.println("-".repeat(40));
    }

    public String getOption() throws Exception {
        System.out.print("-> ");
        String option = input.nextLine().trim();
        String regex = "\\d+";
        if (option.matches(regex)) {
            return option;
        }
        throw new Exception("Input Harus Angka");
    }

    public String getNoPlat() throws Exception {
        String plat = input.nextLine().trim();
        String regex = "^[A-Z]{1,2}\\s[1-9][0-9]{0,3}\\s[A-Z]{1,3}$";
        if (plat.matches(regex)) {
            return plat;
        }
        throw new Exception("Format Input Plat No Salah");
    }

    public String getName() throws Exception {
        String name = input.nextLine().trim();
        String regex = "^[a-zA-Z][a-zA-Z ]{4,}$";
        if (name.matches(regex)) {
            return name;
        }
        throw new Exception("Nama terdiri dari huruf dengan min 4 huruf!");
    }

    public String getPassword() {
        String password = input.nextLine().trim();
        return password;
    }

    public String getNoHp() throws Exception {
        String nohp = input.nextLine().trim();
        String regex = "^(\\\\+62|62|0)8[0-9]{8,11}$";
        if (nohp.matches(regex)) {
            return nohp;
        }
        throw new Exception("Format No Hp salah, terdiri dari 8-11 angka");
    }

    public double getUangYangDibayar(Transaksi transaksi) throws Exception {
        double money = input.nextDouble();
        input.nextLine();
        if (money >= transaksi.getTotalHarga()) {
            return money;
        }
        throw new Exception("Uang Yang Dibayar Kurang");
    }

    public int getHariSewa() throws Exception {
        int hari = Integer.parseInt(input.nextLine().trim());
        if (hari > 0) {
            return hari;
        }
        throw new Exception("Hari Sewa Harus Lebih dari 0");
    }

    public User regis() {
        banner();
        String nama, password, nohp;

        try {

            System.out.print("Nama \t\t: ");
            nama = this.getName();
            System.out.print("Password \t: ");
            password = this.getPassword();
            System.out.print("No HP \t\t: ");
            nohp = this.getNoHp();

            return new User(nama, password, nohp);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error : " +e.getMessage());
            return null;
        }

    }
}