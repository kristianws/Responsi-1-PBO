package view;

import java.util.Scanner;

public class ViewApp {
    Scanner input;

    public ViewApp(Scanner input) {
        this.input = input;
    }

    public void startMenu() {
        System.out.println("-".repeat(40));
        System.out.print(" ".repeat(9));
        System.out.println("Rental Mobil Suka Maju");
        System.out.println("-".repeat(40));
        System.out.println("\t[1] Login");
        System.out.println("\t[2] Register");
        System.out.println("\t[3] Exit");
        System.out.println("-".repeat(40));
        System.out.print("-> ");
    }

    public String getOption() {
        String option = input.nextLine().trim();
        String regex = "\\d+";
        if (option.matches(regex)) {
            return option;
        }
        return "";
    }

    public String getName() {
        String name = input.nextLine().trim();
        String regex = "^[a-zA-Z][a-zA-Z ]{4,}$";
        if (name.matches(regex)) {
            return name;
        }
        return "";
    }

    // public static void main(String[] args) {
    //     Scanner input = new Scanner(System.in);
    //     viewApp frontend = new viewApp(input);
    //     frontend.startMenu();
    // }

}
