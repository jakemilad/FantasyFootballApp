package ui;


import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            //new FantasyApp();
            new FantasyAppGui();
        } catch (FileNotFoundException e) {
            System.out.println("Not able to run application: file not found");
        }
    }
}
