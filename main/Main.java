package main;

import model.*;
import service.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

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
        rentalService = new RentalService(storage, listMobil, listPelanggan, listTransaksi);
        adminService = new AdminService(storage, listMobil, listPelanggan, listTransaksi);


        
    }


}
