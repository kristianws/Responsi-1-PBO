package Responsi1_PPBO_L0124060_SC2;

import java.util.Scanner;

import Responsi1_PPBO_L0124060_SC1.App;
import Responsi1_PPBO_L0124060_SC3.*;
import Responsi1_PPBO_L0124060_SC4.ViewApp;

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