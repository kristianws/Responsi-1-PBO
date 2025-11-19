package service;

import java.util.ArrayList;

import model.*;


public class AdminService {
    
    private ArrayList<Mobil> cars;
    private ArrayList<Pelanggan> customers;
    private ArrayList<TransactionRecord> transactions;
    private StorageUtil storage;


    public AdminService(StorageUtil storage, ArrayList<Mobil> cars, ArrayList<Pelanggan> customers, ArrayList<TransactionRecord> transactions) {
        this.storage =storage;
        this.customers = customers;
        this.transactions = transactions;
        this.cars = cars;
    }

    public void createMobil(Mobil mobil) {
        cars.add(mobil);
        storage.saveMobil(cars);
    }

    public ArrayList<Mobil> getListMobil() { return cars; }

    public Mobil findCarById(String id) {
        for (Mobil mobil : cars) {
            if (mobil.getId().equalsIgnoreCase(id)) {
                return mobil;
            }
        }
        return null;
    }

    public void updateCar(String id, String brand, String type, double pricePerDay) throws Exception {
        Mobil targetUpdate = findCarById(id);
        if (targetUpdate == null) {
            throw new Exception("Mobil Tidak Ada!");
        }
        targetUpdate.setBrand(brand);
        targetUpdate.setType(type);
        targetUpdate.setPrice(pricePerDay);
        storage.saveMobil(cars);
    }

    public void deleteCar(String id) throws Exception {
        Mobil targetDelete = findCarById(id);
        if (targetDelete == null) {
            throw new Exception("Mobil Tidak Ada!");
        }
        cars.remove(targetDelete);
        storage.saveMobil(cars);
    }

    public void createPelanggan(Pelanggan pelangganBaru) {
        customers.add(pelangganBaru);
        storage.savePelanggan(customers);
    }


    public ArrayList<Pelanggan> getListPelanggan() { return customers; }

    public Pelanggan findPelangganById(String id) {
        for (Pelanggan pelanggan : customers) {
            if (pelanggan.getId().equalsIgnoreCase(id)) {
                return pelanggan;
            }
        }
        return null;
    }

    public void updatePelanggan(String id, String nama, String phone) throws Exception {
        Pelanggan targetUpdate = findPelangganById(id);
        if (targetUpdate == null) {
            throw new Exception("Pelanggan Tidak Ada!");
        }
        targetUpdate.setName(nama);
        targetUpdate.setPhone(phone);;
        storage.savePelanggan(customers);
    }

    public void deletePelanggan(String id) throws Exception {
        Pelanggan targetDelete = findPelangganById(id);
        if (targetDelete == null) {
            throw new Exception("Pelanggan Tidak ada!");
        }
        customers.remove(targetDelete);
        storage.savePelanggan(customers);
    }

    public ArrayList<TransactionRecord> getListTransactionRecords() {
        return transactions;
    }

    public double totalRevenue() {
        double total = 0;
        for (TransactionRecord transactionRecord : transactions) {
            total += transactionRecord.getPrice();
        }
        return total;
    }

    public void deleteTransactions(String id) throws Exception {
        TransactionRecord found = null;
        for (TransactionRecord transactionRecord : transactions) {
            if (transactionRecord.getId().equalsIgnoreCase(id)) {
                found = transactionRecord;
            }
        }

        if (found == null) {
            throw new Exception("Transaksi Tidak Ada! ");
        }

        transactions.remove(found);
        storage.saveTransactions(transactions);
    }

}
