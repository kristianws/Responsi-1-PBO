package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private static String FILE_MOBIL = "mobil.txt";
    private static String FILE_USER = "users.txt";
    private static String FILE_TRANSAKSI = "transaksi.txt";
    private static ArrayList<Mobil> listMobil;
    private static ArrayList<User> listUser;
    private static ArrayList<Transaksi> listTransaksi;

    public Storage() {
        listMobil = new ArrayList<>();
        listUser = new ArrayList<>();
        listTransaksi = new ArrayList<>();

        try {
            listMobil = loadMobil();
            listUser = loadUser();
            listTransaksi = loadTransaksi();
        } catch (Exception e) {
            System.out.println("Error Membaca Data : " + e.getMessage());
        }

    }

    // INPUT OUTPUT UNTUK KELAS MOBIL

    public Mobil getMobilByNoPlat(String NoPlat) throws Exception {
        for (Mobil mobil : listMobil) {
            if (mobil.getNoPlat().equals(NoPlat)) {
                return mobil;
            }
        }
        throw new Exception("Mobil Dengan Plat " + NoPlat + " Tidak Ada");
    }

    public void saveMobil(ArrayList<Mobil> listMobil) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_MOBIL))) {
            bw.write("PlatNo|Brand|Type|Harga|Ketersediaan\n");
            for (Mobil mobil : listMobil) {
                bw.write(mobil.toFileString());
            }

        } catch (IOException e) {
            System.out.println("Error Menyimpan list Mobil : " + e.getMessage());
        }
    }

    public ArrayList<Mobil> loadMobil() throws Exception {
        ArrayList<Mobil> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_MOBIL))) {
            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                try {
                    list.add(Mobil.fromFileString(line));
                } catch (Exception e) {
                    System.out.println("Skip Read Line : " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println("Error Membca file mobil : " + e.getMessage());
        }

        return list;
    }

    public ArrayList<Mobil> getAllMobil() {
        return listMobil;
    }

    // INPUT OUTPUT KELAS USER

    public User getUserByNama(String Nama) throws Exception {
        for (User user : listUser) {
            if (user.getName().equals(Nama)) {
                return user;
            }
        }
        throw new Exception("User dengan Nama " + Nama + " Tidak Ada");
    }

    public boolean login(String nama, String password) {
        for (User user : listUser) {
            if (user.getName().equals(nama) && user.getPass().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<User> gettAllUser() {
        return listUser;
    }

    public ArrayList<User> loadUser() throws Exception {
        ArrayList<User> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_USER))) {
            br.readLine(); // read firstline;
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                try {
                    list.add(User.fromFileToString(line));
                } catch (Exception e) {
                    System.out.println("Skip read line : " + e.getMessage());
                }

            }
        } catch (Exception e) {
            System.out.println("Error membaca file user : " + e.getMessage());
        }

        return list;
    }

    public void saveUser(ArrayList<User> listUser) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_USER))) {
            bw.write("ID|Nama|Password|No Phone\n");

            for (User user : listUser) {
                bw.write(user.toFileString());
            }

        } catch (Exception e) {
            System.out.println("Error menyimpan user : " + e.getMessage());
        }
    }


    // Untuk Transaksi
    public ArrayList<Transaksi> loadTransaksi() throws Exception {
        ArrayList<Transaksi> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_TRANSAKSI))) {
            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                try {
                    list.add(Transaksi.fromFileToString(line));
                } catch (Exception e) {
                    System.out.println("Skip Read Line : " + e.getMessage());
                }
            }

        } catch (Exception e) {
            System.out.println("Error Membaca file transaksi : " + e.getMessage());
        }
        return list;
    }

    public ArrayList<Transaksi> getAllTransaksi() {
        return listTransaksi;
    }

    public void saveTransaksi(ArrayList<Transaksi> listTransaksi) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_TRANSAKSI))) {
            bw.write("Plat No|Nama|No Hp|Waktu Sewa|Total Harga|TimeStamp\n");

            for (Transaksi transaksi : listTransaksi) {
                bw.write(transaksi.toFileString());
            }

        } catch (Exception e) {
            System.out.println("Error menyimpan Transaksi : " + e.getMessage());
        }
    }
}