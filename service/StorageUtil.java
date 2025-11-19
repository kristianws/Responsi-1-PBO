package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.*;

public class StorageUtil {
    private final String CARS_FILE = "cars.txt";
    private final String CUSTOMERS_FILE = "customers.txt";
    private final String USERS_FILE = "users.txt";
    private final String TRANSACTIONS_FILE = "transactions.txt";

    // === Mobil ===
    public ArrayList<Mobil> loadMobil() {
        ArrayList<Mobil> list = new ArrayList<>();
        File file = new File(CARS_FILE);
        if (!file.exists()) {
            return list;
        }

        boolean isFirstLine = true;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                if (line.isBlank()) {
                    continue;
                }

                try {
                    list.add(Mobil.fromFileString(line));
                } catch (Exception e) {
                    System.out.println("Melewati Membaca Mobil pada baris : " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println("Erorr membaca cars.txt" + e.getMessage());
        }
        return list;
    }

    public void saveMobil(ArrayList<Mobil> cars) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CARS_FILE))) {
            for (Mobil mobil : cars) {
                bw.write(mobil.toFileString() + '\n');
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan ke file, Mobil : " + e.getMessage());
        }
    }

    // === Pelanggan ===
    public ArrayList<Pelanggan> loadCustomer() {
        ArrayList<Pelanggan> list = new ArrayList<>();
        File file = new File(CUSTOMERS_FILE);
        if (!file.exists()) {
            return list;
        }

        boolean isFirstLine = true;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                if (line.isBlank()) {
                    continue;
                }

                try {
                    list.add(Pelanggan.fromFileString(line));
                } catch (Exception e) {
                    System.out.println("Melewati Membaca Pelanggan pada baris : " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println("Erorr membaca customers.txt" + e.getMessage());
        }
        return list;
    }

    public void savePelanggan(ArrayList<Pelanggan> customers) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CUSTOMERS_FILE))) {
            for (Pelanggan pelanggan : customers) {

                bw.write(pelanggan.toFileString());
                bw.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan ke file, pelanggan : " + e.getMessage());
        }
    }

    // === USERS ===
    public ArrayList<UserAccount> loadUsers() {
        ArrayList<UserAccount> list = new ArrayList<>();
        File file = new File(USERS_FILE);
        if (!file.exists()) {
            return list;
        }

        boolean isFirstLine = true;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                if (line.isBlank()) {
                    continue;
                }

                try {
                    list.add(UserAccount.fromFileString(line));
                } catch (Exception e) {
                    System.out.println("Melewati Membaca User pada baris : " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println("Erorr membaca users.txt" + e.getMessage());
        }
        return list;
    }

    public void saveUsers(ArrayList<UserAccount> users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CUSTOMERS_FILE))) {
            for (UserAccount user : users) {

                bw.write(user.toFileString());
                bw.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan ke file, User : " + e.getMessage());
        }
    }

    // === Trasactions ===
    public ArrayList<TransactionRecord> loadTransactionRecords() {
        ArrayList<TransactionRecord> list = new ArrayList<>();
        File file = new File(TRANSACTIONS_FILE);
        if (!file.exists()) {
            return list;
        }

        boolean isFirstLine = true;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                if (line.isBlank()) {
                    continue;
                }

                try {
                    list.add(TransactionRecord.fromFileString(line));
                } catch (Exception e) {
                    System.out.println("Melewati Membaca Transactions Record pada baris : " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println("Erorr membaca transactions.txt" + e.getMessage());
        }
        return list;
    }

    public void saveTransactions(ArrayList<TransactionRecord> transactionRecords) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TRANSACTIONS_FILE))) {
            for (TransactionRecord user : transactionRecords) {

                bw.write(user.toFileString());
                bw.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan ke file, User : " + e.getMessage());
        }
    }

    public void initializeIfNeeded() {
        File fileUsers = new File(USERS_FILE);

        // User
        if (!fileUsers.exists()) {
            ArrayList<UserAccount> defaultUsers = new ArrayList<>();
            defaultUsers.add(new UserAccount("admin", "admin123", "ADMIN"));
            defaultUsers.add(new UserAccount("demoUser", "demo123", "USER"));
            saveUsers(defaultUsers);
            System.out.println("Created default admin & demo user.");
        }

        // Mobil
        File fileMobil = new File(CARS_FILE);
        if (!fileMobil.exists()) {
            ArrayList<Mobil> seed = new ArrayList<>();
            seed.add(new Mobil("C01", "Toyota Avanza", "MPV", 300000));
            seed.add(new Mobil("C02", "Honda HRV", "SUV", 450000));
            seed.add(new Mobil("C03", "Toyota Yaris", "Hatchback", 250000));
            seed.add(new Mobil("C04", "Suzuki Carry", "Pick Up", 200000));
            saveMobil(seed);
            System.out.println("Seeded default cars.");
        }

        // Pelanggan
        File filePelanggan = new File(CUSTOMERS_FILE);
        if (!filePelanggan.exists()) {
            savePelanggan(new ArrayList<>());
        }

        // Transaksi
        File fileTransactions = new File(TRANSACTIONS_FILE);
        if (!fileTransactions.exists()) {
            saveTransactions(new ArrayList<>());
        }
    }

}
