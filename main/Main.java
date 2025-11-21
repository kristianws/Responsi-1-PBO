package main;

import java.util.Scanner;

import controller.App;
import model.*;
import view.ViewApp;

public class Main {
    public static void main(String[] args) {
        Storage database = new Storage();
        Scanner input = new Scanner(System.in);
        ViewApp tampilan = new ViewApp(input);
        App rentalMobil = new App(database, tampilan);

        rentalMobil.run();
        
        input.close();
    }

}