package main;

import model.*;
import service.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static StorageUtil storage = new StorageUtil();
    private static Scanner input = new Scanner(System.in);

    private static ArrayList<Mobil> listMobil;
    private static ArrayList<Pelanggan> listPelanggan;
    private static ArrayList<UserAccount> listUsers;
    private static ArrayList<TransactionRecord> listTransaksi;

    private static AuthService authService;
    private static RentalService rentalService;
    private static AdminService adminService;

    public static void main(String[] args) {
        storage.initializeIfNeeded();

        listMobil = storage.loadMobil();
        listPelanggan = storage.loadCustomer();
        listUsers = storage.loadUsers();
        listTransaksi = storage.loadTransactionRecords();

        authService = new AuthService(storage, listUsers);
        rentalService = new RentalService(storage, listMobil, listPelanggan,
                listTransaksi);
        adminService = new AdminService(storage, listMobil, listPelanggan,
                listTransaksi);
        mainLoop();

    }

    private static void mainLoop() {
        while (true) {
            System.out.println();
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║        RENTAL MOBIL SUKA MAJU        ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║ 1. Login (Admin / Staff)             ║");
            System.out.println("║ 2. Registrasi (Staff)                ║");
            System.out.println("║ 3. Keluar                            ║");
            System.out.println("╚══════════════════════════════════════╝");

            System.out.print("-> ");
            String option = input.nextLine().trim();

            switch (option) {
                case "1" -> doLogin();
                case "2" -> doRegister();
                case "3" -> {
                    System.out.println("╔══════════════════════════════════════╗");
                    System.out.println("║        RENTAL MOBIL SUKA MAJU        ║");
                    System.out.println("╠══════════════════════════════════════╣");
                    System.out.println("║   Thank You For Using Our Services   ║");
                    System.out.println("╚══════════════════════════════════════╝");

                    System.exit(0);
                }
                default -> System.out.println("Invalid Input");
            }
        }
    }

    private static void doLogin() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║        RENTAL MOBIL SUKA MAJU        ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("Username : ");
        String username = input.nextLine().trim();
        System.out.print("Password : ");
        String password = input.nextLine().trim();

        UserAccount user = authService.login(username, password);
        if (user == null) {
            System.out.println("Login gagal. Cek Username/password!");
            return;
        }

        System.out.println("Login Berhasil. Role : " + user.getRole());
        if ("ADMIN".equalsIgnoreCase(user.getRole())) {
            adminMenu(user);
        } else {
            userMenu(user);
        }
    }

    private static void doRegister() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║        RENTAL MOBIL SUKA MAJU        ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║     Aturan Username dan Password     ║");
        System.out.println("║1. username : huruf di depan          ║");
        System.out.println("║   alfanum, min 5 karakter            ║");
        System.out.println("║2. password min 4 karakter            ║");
        System.out.println("╚══════════════════════════════════════╝");

        System.out.print("Username : ");
        String username = input.nextLine().trim();
        System.out.print("Password : ");
        String password = input.nextLine().trim();
        System.out.println("No HP : ");
        String nohp = input.nextLine().trim();

        try {
            authService.register(username, password);
            listUsers = storage.loadUsers();
            listPelanggan = storage.loadCustomer();
            System.out.println("Registrasi Berhasil, Bisa Login.");
        } catch (Exception e) {
            System.out.println("Gagal Registrasi : " + e.getMessage());
        }

    }

    // Menu Admin
    private static void adminMenu(UserAccount admin) {
        while (true) {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║          ADMIN MENU          ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1. Manajemen Mobil           ║");
            System.out.println("║ 2. Manajemen Pelanggan       ║");
            System.out.println("║ 3. Manajemen Akun            ║");
            System.out.println("║ 4. Lihat Transaksi/Laporan   ║");
            System.out.println("║ 5. Logout                    ║");
            System.out.println("╚══════════════════════════════╝");

            System.out.print("-> ");
            String option = input.nextLine().trim();
            switch (option) {
                case "1" -> manajemenMobil();
                case "2" -> manajemenPelanggan();
                case "3" -> manajemenAkun();
                case "4" -> lihatLaporan();
                case "5" -> {
                    System.out.println("Logout Admin.");
                    return;
                }
                default -> {
                    System.out.println("Invalid Input");
                }

            }
        }
    }

    private static void manajemenMobil() {
        while (true) {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║        MANAJEMEN MOBIL       ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1. Daftar Mobil              ║");
            System.out.println("║ 2. Tambah Mobil              ║");
            System.out.println("║ 3. Update Mobil              ║");
            System.out.println("║ 4. Hapus Mobil               ║");
            System.out.println("║ 5. Kembali                   ║");
            System.out.println("╚══════════════════════════════╝");

            System.out.print("-> ");
            String option = input.nextLine().trim();

            try {
                switch (option) {
                    case "1" -> listAllMobil();
                    case "2" -> tambahMobil();
                    case "3" -> updateMobil();
                    case "4" -> hapusMobil();
                    case "5" -> {
                        return;
                    }
                    default -> {
                        System.out.println("Invalid Input");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }
        }
    }

    private static void listAllMobil() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║          DAFTAR MOBIL        ║");
        System.out.println("╚══════════════════════════════╝");
        for (Mobil mobil : listMobil) {
            System.out.println(mobil);
        }

    }

    private static void tambahMobil() {

        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║          TAMBAH MOBIL        ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.print("ID Mobil : ");
        String id = input.nextLine().trim();
        System.out.print("Merk : ");
        String merk = input.nextLine().trim();
        System.out.print("Tipe : ");
        String tipe = input.nextLine().trim();
        System.out.print("Harga Per Hari : ");
        double price = Double.parseDouble(input.nextLine().trim());

        Mobil baru = new Mobil(id, merk, tipe, price);
        adminService.createMobil(baru);
        System.out.println("Mobil Baru ditambahkan.");

    }

    private static void updateMobil() throws Exception {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║          UPDATE MOBIL        ║");
        System.out.println("╚══════════════════════════════╝");

        System.out.print("ID Mobil yang akan diupdate : ");
        String id = input.nextLine().trim();
        Mobil targetUpdate = adminService.findCarById(id);
        if (targetUpdate == null) {
            System.out.println("Mobil Tidak ada!");
            return;
        }
        System.out.print("Merk Baru (" + targetUpdate.getBrand() + ") : ");
        String merk = input.nextLine().trim();
        if (merk.isBlank()) {
            merk = targetUpdate.getBrand();
        }

        System.out.print("Tipe Baru (" + targetUpdate.getType() + ") : ");
        String type = input.nextLine().trim();
        if (type.isBlank()) {
            type = targetUpdate.getType();
        }

        System.out.print("Price per Day (" + targetUpdate.getPrice() + ") : ");
        double price = Double.parseDouble(input.nextLine().trim());
        adminService.updateCar(id, merk, type, price);
        System.out.println("Update Sukses.");
    }

    public static void hapusMobil() throws Exception {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║          HAPUS MOBIL         ║");
        System.out.println("╚══════════════════════════════╝");

        System.out.print("ID Mobil yang akan dihapus : ");
        String id = input.nextLine().trim();
        Mobil targetHapus = adminService.findCarById(id);
        System.out.println(targetHapus);
        adminService.deleteCar(id);
        System.out.println("Mobil terhapus.");
    }

    // Manajemen Pelanggan
    private static void manajemenPelanggan() {
        while (true) {

            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║      MANAJEMEN PELANGGAN     ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1. Daftar Pelanggan          ║");
            System.out.println("║ 2. Tambah Pelanggan          ║");
            System.out.println("║ 3. Update Pelanggan          ║");
            System.out.println("║ 4. Hapus Pelanggan           ║");
            System.out.println("║ 5. Kembali                   ║");
            System.out.println("╚══════════════════════════════╝");

            System.out.print("-> ");
            String option = input.nextLine().trim();

            try {
                switch (option) {
                    case "1" -> listAllPelanggan();
                    case "2" -> tambahPelanggan();
                    case "3" -> updatePelanggan();
                    case "4" -> hapusPelanggan();
                    case "5" -> {
                        return;
                    }
                    default -> System.out.println("Invalid Input");
                }
            } catch (Exception e) {
                System.out.println("Error" + e.getMessage());
            }

        }
    }

    private static void listAllPelanggan() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║       DAFTAR PELANGGAN       ║");
        System.out.println("╚══════════════════════════════╝");
        for (Pelanggan pelanggan : listPelanggan) {
            System.out.println(" " + pelanggan);
        }

    }

    private static void tambahPelanggan() {

        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║       TAMBAH PELANGGAN       ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.print(" ID Pelanggan  : ");
        String id = input.nextLine().trim();
        System.out.print(" Nama : ");
        String nama = input.nextLine().trim();
        System.out.print(" Phone : ");
        String phone = input.nextLine().trim();

        Pelanggan baru = new Pelanggan(id, nama, phone);
        adminService.createPelanggan(baru);
        System.out.println(" Pelanggan Baru ditambahkan.");
    }

    private static void updatePelanggan() throws Exception {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║       UPDATE PELANGGAN       ║");
        System.out.println("╚══════════════════════════════╝");

        System.out.print("ID Pelanggan  : ");
        String id = input.nextLine().trim();
        Pelanggan targetUpdate = adminService.findPelangganById(id);
        if (targetUpdate == null) {
            System.out.println("Pelanggan Tidak ada!");
            return;
        }
        System.out.print("Nama Baru (" + targetUpdate.getName() + ") : ");
        String merk = input.nextLine().trim();
        if (merk.isBlank()) {
            merk = targetUpdate.getName();
        }

        System.out.print("Phone Baru (" + targetUpdate.getPhone() + ") : ");
        String type = input.nextLine().trim();
        if (type.isBlank()) {
            type = targetUpdate.getPhone();
        }

        adminService.updatePelanggan(id, merk, type);
        System.out.println("Update Sukses.");
    }

    private static void hapusPelanggan() throws Exception {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║        HAPUS PELANGGAN       ║");
        System.out.println("╚══════════════════════════════╝");

        System.out.print("ID Pelanggan yang akan dihapus : ");
        String id = input.nextLine().trim();
        Pelanggan targetHapus = adminService.findPelangganById(id);
        System.out.println(targetHapus);
        adminService.deletePelanggan(id);
        System.out.println("Pelanggan terhapus.");
    }

    // manajemen akun
    private static void manajemenAkun() {
        while (true) {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║        MANAJEMEN AKUN        ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1. Daftar Akun               ║");
            System.out.println("║ 2. Buat Akun                 ║");
            System.out.println("║ 3. Kembali                   ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("-> ");
            String option = input.nextLine().trim();
            try {
                switch (option) {
                    case "1" -> listAkun();
                    case "2" -> buatUserByAdmin();
                    case "3" -> {
                        return;
                    }
                    default -> System.out.println("Invalid Input");
                }
            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
            }

        }
    }

    private static void listAkun() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║         DAFTAR AKUN          ║");
        System.out.println("╚══════════════════════════════╝");
        for (UserAccount akun : listUsers) {
            System.out.println(" " + akun);
        }

    }

    private static void buatUserByAdmin() throws Exception {

        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║          TAMBAH AKUN         ║");
        System.out.println("╚══════════════════════════════╝");

        System.out.print("Username baru : ");
        String username = input.nextLine().trim();

        System.out.print("Password : ");
        String pass = input.nextLine().trim();

        System.out.println("Role (ADMIN/USER) : ");
        String role = input.nextLine().trim().toUpperCase();

        authService.createUserAccount(username, pass, role);
        listUsers = storage.loadUsers();

        System.out.println("Akun dibuat.");
    }

    // Laporan
    private static void lihatLaporan() {
        while (true) {
            System.out.println("\n╔══════════════════════════════════╗");
            System.out.println("║       TRANSAKSI DAN LAPORAN      ║");
            System.out.println("╠══════════════════════════════════╣");
            System.out.println("║ 1. Daftar Transaksi              ║");
            System.out.println("║ 2. Lihat Total Revenue           ║");
            System.out.println("║ 3. Hapus Transaksi               ║");
            System.out.println("║ 4. Kembali                       ║");
            System.out.println("╚══════════════════════════════════╝");

            System.out.print("-> ");

            String option = input.nextLine().trim();
            try {
                switch (option) {
                    case "1" -> lihatListTransaksi();
                    case "2" -> {
                        System.out.print("Total Revenue : " + adminService.totalRevenue());
                    }
                    case "3" -> hapusTransaksi();
                    case "4" -> {
                        return;
                    }
                    default -> {
                        System.out.println("Invalid Input");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }
        }
    }

    private static void lihatListTransaksi() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║       DAFTAR TRANSAKSI       ║");
        System.out.println("╚══════════════════════════════╝");
        for (TransactionRecord transaksi : listTransaksi) {
            System.out.println(" " + transaksi);
        }
    }

    private static void hapusTransaksi() throws Exception {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║        HAPUS TRANSAKSI       ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.print("ID Transaksi : ");
        String id = input.nextLine().trim();
        adminService.deleteTransactions(id);
        System.out.println("Transaksi dihapus");
    }

    // Menu User/Pelanggan
    private static void userMenu(UserAccount user) {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║              USER MENU (" + user.getUsername() + ")      ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ 1. Lihat Mobil Tersedia                ║");
            System.out.println("║ 2. Sewa Mobil                          ║");
            System.out.println("║ 3. Kembalikan Mobil                    ║");
            System.out.println("║ 4. Lihat Transaksi                     ║");
            System.out.println("║ 5. Logout                              ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print("->");
            String option = input.nextLine().trim();
            try {
                switch (option) {
                    case "1" -> daftarMobilTersedia();
                    case "2" -> sewaMobil(user);
                    case "3" -> mengembalikanMobil();
                    case "4" -> daftarTransaksiSaya(user);
                    case "5" -> {
                        System.out.println("LogOut");
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }

        }
    }

    private static void daftarMobilTersedia() {

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║        RENTAL MOBIL SUKA MAJU        ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println(" Daftar Mobil");
        for (Mobil mobil : rentalService.availableMobil()) {
            System.out.println(" " + mobil);
        }
    }

    private static void sewaMobil(UserAccount user) throws Exception {

        daftarMobilTersedia();

        System.out.print("ID Mobil yang ingin disewa : ");
        String idMobil = input.nextLine().trim();
        Mobil mobil = rentalService.findMobil(idMobil);
        if (mobil == null) {
            System.out.println("Mobil Tidak Ada!");
            return;
        }

        System.out.print("ID Pelanggan (harus terdaftar) : ");
        String idPelanggan = input.nextLine().trim();
        Pelanggan pelanggan = rentalService.findPelanggan(idPelanggan);
        if (pelanggan == null) {
            System.out.println("Pelanggan tidak ada. Coba ke Admin atau regis :) ");
            return;
        }

        System.out.print("Lama sewa (hari) : ");
        int hari = Integer.parseInt(input.nextLine().trim());
        double total = mobil.getPrice() * hari;

        System.out.println("Total Harga : Rp" + total);
        System.out.println("Masukkan Jumlah Pembayaran (Cash) : ");
        double paidMoney = Double.parseDouble(input.nextLine().trim());
        double kembalian = rentalService.rentCar(pelanggan.getName(), idMobil, idPelanggan, hari, paidMoney);
        System.out.println("Sewa berhasil. Kembalian : Rp" + kembalian);
    }

    private static void mengembalikanMobil() throws Exception {
        System.out.print("Masukan ID mobil yang akan dikembalikan : ");
        String id = input.nextLine().trim();
        rentalService.returnCar(id);
        System.out.println("Mobil Dikembalikan. Makasih!");
    }

    private static void daftarTransaksiSaya(UserAccount user) {
        System.out.println("\nTransaksi " + user.getUsername() + ":");
        for (TransactionRecord transactionRecord : listTransaksi) {
            if (transactionRecord.getUsername().equalsIgnoreCase(user.getUsername())) {
                System.out.println(transactionRecord);
            }
        }
    }

}
