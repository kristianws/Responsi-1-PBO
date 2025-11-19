package service;

import java.util.ArrayList;
import java.util.Random;

import model.*;


public class RentalService {
    private ArrayList<Mobil> listMobil;
    private ArrayList<Pelanggan> listPelanggan;
    private ArrayList<TransactionRecord> listTransaksi;
    private StorageUtil storage;


    public RentalService(StorageUtil storage, ArrayList<Mobil> listMobil, ArrayList<Pelanggan> listPelanggan, ArrayList<TransactionRecord> listTransactionRecords) {
        this.listMobil =listMobil;
        this.listPelanggan = listPelanggan;
        this.listTransaksi = listTransactionRecords;
        this.storage = storage;
    }

    public ArrayList<Mobil> availableMobil() {
        ArrayList<Mobil> available = new ArrayList<>();
        for (Mobil mobil : listMobil) {
            if (mobil.getAvailable()) {
                available.add(mobil);
            }
        }
        return available;
    }

    public Mobil findMobil(String id) {
        for (Mobil mobil : listMobil) {
            if (mobil.getId().equalsIgnoreCase(id)) {
                return mobil;
            }
        }
        return null;
    }

    public Pelanggan findPelanggan(String id) {
        for (Pelanggan pelanggan : listPelanggan) {
            if (pelanggan.getId().equalsIgnoreCase(id)) {
                return pelanggan;
            }
        }
        return null;
    }

    public double rentCar(String username, String carId, String customerId, int days, double paidMoney) throws Exception {
        if (days <= 0) {
            throw new Exception("Lama sewa harus > 0 ");
        }
        Mobil mobilPesanan = findMobil(carId);
        if (mobilPesanan == null) {
            throw new Exception("Mobil Tidak ada!");
        }
        if (!mobilPesanan.getAvailable()) {
            throw new Exception("Mobil Sedang digunakan!");
        }

        Pelanggan pelanggan = findPelanggan(customerId);
        if (pelanggan == null) {
            throw new Exception("Pelanggan tidak ada!");
        }

        double total = mobilPesanan.getPrice() * days;
        if (paidMoney < total) {
            throw new Exception("Pembayaran Kurang. Total = Rp" + total);
        }

        mobilPesanan.rentOut();
        storage.saveMobil(listMobil);

        String transaksiId = "T" + (listTransaksi.size() + 1 + new Random().nextInt(999));
        TransactionRecord transaksiBaru = new TransactionRecord(transaksiId, username, carId, days, paidMoney);

        listTransaksi.add(transaksiBaru);
        storage.saveTransactions(listTransaksi);

        return paidMoney - total;
    }


}
