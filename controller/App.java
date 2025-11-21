package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.*;
import view.*;

public class App {
    private Storage database;
    private ViewApp ui;
    private ArrayList<Mobil> listMobil;
    private ArrayList<User> listUser;

    public App(Storage database, ViewApp ui) {
        this.ui = ui;
        this.database = database;
        this.listUser = this.database.gettAllUser();
        this.listMobil = this.database.getAllMobil(); 
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
                        System.out.println("Regist");
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

    public void sewaMobil(User currentUser) {
        this.showAllMobil();

        try {
            System.out.print("Plat No \t: ");
            String platno = ui.getNoPlat();
        } catch (Exception e) {
            // TODO: handle exception
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

                        break;

                    case "2":

                        break;

                    case "3":

                        break;

                    case "4":

                        break;

                    case "5":
                        run = false;
                        break;
                    default:
                        break;
                }

            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Error : " + e.getMessage());
            }
        }

    }

    public void doLogin() {
        ui.banner();
        String nama, password;
        User currentUser;
        try {
            System.out.print("Nama \t: ");
            nama = ui.getName();
            System.out.println("Password \t: ");
            password = ui.getPassword();

            if (database.login(nama, password)) {
                currentUser = database.getUserByNama(nama);
                startUser(currentUser);
                return;
            }

        } catch (Exception e) {
            System.out.println("Error : " +  e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Storage database = new Storage();
        ViewApp inputOutput = new ViewApp(input);

        App aplikasi = new App(database, inputOutput);
        aplikasi.run();

        input.close();

    }

}
