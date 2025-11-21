package controller;

import java.util.Scanner;

import model.*;
import view.*;

public class App {
    private Scanner input;
    private Storage database;
    private ViewApp inputOutput;

    public App(Scanner inputLuar, Storage database, ViewApp view) {
        this.input = inputLuar;
        this.database = database;
        this.inputOutput = view;
    }

    public void run() {
        boolean run = true;
        while (run) {
            inputOutput.startMenu();
            String option = inputOutput.getOption();

            switch (option) {
                case "1":
                    System.out.println("Login");
                    break;
            
                case "2":
                    System.out.println("Regist");
                    break;
                
                case "3":
                    System.out.println("Thank You");
                    run = false;
                    break;

                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Storage database = new Storage();
        ViewApp inputOutput = new ViewApp(input);


        App aplikasi = new App(input, database, inputOutput);

        aplikasi.run();

    }

}
