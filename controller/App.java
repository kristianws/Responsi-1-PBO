package controller;

import java.util.ArrayList;

import model.Mobil;
import model.Transaksi;
import model.Storage;
import model.User;
import view.ViewApp;

public class App {
    private Storage database;
    private ViewApp ui;
    private ArrayList<Mobil> listMobil;
    private ArrayList<User> listUser;
    private ArrayList<Transaksi> listTransaksi;

    public App(Storage database, ViewApp ui) {
        this.ui = ui;
        this.database = database;
        this.listUser = this.database.gettAllUser();
        this.listMobil = this.database.getAllMobil();
        this.listTransaksi = this.database.getAllTransaksi();
    }

    public void run() {
        boolean run = true;
        while (run) {
            ui.startMenu();

            try {
                String option = ui.getOption();

                switch (option) {
                    case "1":
                        doLogin();
                        break;

                    case "2":
                        doRegis();
                        break;

                    case "3":
                        System.out.println("Thank You");
                        run = false;
                        break;

                    default:
                        System.out.println("Opsi Tidak Ada!");
                }
            } catch (Exception e) {
                System.out.println("Error Input Option : " + e.getMessage());
            }

        }

    }

    public void showAllMobil() {
        ui.banner();
        for (Mobil mobil : listMobil) {
            System.out.println(mobil);
        }
    }

    public void Pembayaran(Transaksi transaksi) {
        System.out.println("-".repeat(40));
        try {
            System.out.println("Total Harga Sewa Rp" + transaksi.getTotalHarga());
            System.out.print("Uang Yang Dibayar : ");
            double money = ui.getUangYangDibayar(transaksi);
            double change = money - transaksi.getTotalHarga();
            System.out.println("Kembalian : " + change);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public void sewaMobil(User currUser) {
        int hariSewa;
        String platNo;
        try {
            System.out.println("-".repeat(40));
            this.showAllMobil();
            System.out.println("-".repeat(40));

            System.out.print("Masukan Plat No Mobil \t: ");
            platNo = ui.getNoPlat();
            Mobil sewa = database.getMobilByNoPlat(platNo);

            System.out.print("Mau Sewa Berapa Hari ? ");
            hariSewa = ui.getHariSewa();

            Transaksi baru = new Transaksi(currUser, sewa, hariSewa);
            Pembayaran(baru);
            sewa.rentOut();

            listTransaksi.add(baru);

            database.saveMobil(listMobil);
            database.saveTransaksi(listTransaksi);

            System.out.println("Terima Kasih telah Menggunakan Layanan Kami");
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public void startUser(User currentUser) {
        boolean run = true;

        while (run) {
            try {
                ui.banner();
                ui.menuAfterLogin();
                String option = ui.getOption();

                switch (option) {
                    case "1":
                        showAllMobil();
                        break;

                    case "2":
                        sewaMobil(currentUser);
                        break;

                    case "3":
                        mengembalikan(currentUser);
                        break;

                    case "4":
                        showAllMyTransaction(currentUser);
                        break;

                    case "5":
                        run = false;
                        break;
                    default:
                        break;
                }

            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }
        }

    }

    public void showAllMyTransaction(User currUser) {

        try {
            System.out.println("-".repeat(40));
            for (Transaksi transaksi : listTransaksi) {
                if (transaksi.getNamaPelanggan().equals(currUser.getName())) {
                    String noplat = transaksi.getPlatNoMobil();
                    Mobil history = database.getMobilByNoPlat(noplat);
                    System.out.println(transaksi.getFormattedTime() +" - "+noplat + " - " + history.getBrand() + " " + history.getType() + " - " + history.getPrice() + "(Hari " + transaksi.getHariSewa()+ ")");
                }
            }
            System.out.println("-".repeat(40));
            
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public ArrayList<Mobil> showMobilYangSayaSewa(User currUser) {
        ArrayList<Mobil> list = new ArrayList<>();
        try {
            for (Transaksi transaksi : listTransaksi) {
                if (transaksi.getNamaPelanggan().equals(currUser.getName()) && !transaksi.isDone()) {
                    String platNo = transaksi.getPlatNoMobil();
                    Mobil yangDisewa = database.getMobilByNoPlat(platNo);
                    list.add(yangDisewa);
                }
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return list;
    }

    public void mengembalikan(User currentUser) {

        try {
            System.out.println("-".repeat(40));
            ArrayList<Mobil> list =  this.showMobilYangSayaSewa(currentUser);
            if (list.size() == 0) {
                System.out.println("Tidak ada Mobil Yang Kamu Sewa");
                return;
            }
            for (Mobil mobil : list) {
                System.out.println(mobil);
            }
            System.out.println("-".repeat(40));
            
            System.out.print("Plat Nomor Mobil : ");
            String platNo = ui.getNoPlat();

            for (Transaksi transaksi : listTransaksi) {
                if (transaksi.getNamaPelanggan().equals(currentUser.getName()) && !transaksi.isDone() && transaksi.getPlatNoMobil().equals(platNo)) {
                    transaksi.done();
                    database.saveTransaksi(listTransaksi);
                }
            }
            

            Mobil target = database.getMobilByNoPlat(platNo);
            target.returnMobil();


            database.saveMobil(listMobil);

            System.out.println("Terima Kasih Sudah Sewa di Rental Mobil Suka Maju");

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }

    }

    public void doLogin() {
        ui.banner();
        String nama, password;
        User currentUser;
        try {
            System.out.print("Nama \t\t: ");
            nama = ui.getName();
            System.out.print("Password \t: ");
            password = ui.getPassword();

            if (database.login(nama, password)) {
                currentUser = database.getUserByNama(nama);
                System.out.println("Login berhasil");
                startUser(currentUser);
            } else {
                System.out.println("Login gagal. cek username/password");
            }

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public void doRegis() {
        User baru = ui.regis();

        if (baru != null) {
            listUser.add(baru);
            database.saveUser(listUser);
            System.out.println("Registrasi Berhasil. Silahkan Login");
        } else {
            System.out.println("Registrasi Gagal");
        }
    }
}
