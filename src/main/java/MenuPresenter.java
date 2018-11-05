import analyzers.ACAAnalyzer;
import analyzers.STAnalyzer;
import components.History;
import creators.HistoryCreator;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by DJ Yuhn on 11/5/2018
 */
public class MenuPresenter {
    public static void menu() {
        System.out.println("CS5570 Project - No Name Group");
        Scanner userInput = new Scanner(System.in);

        Boolean exit = false;
        do {
            System.out.println("Main Menu");
            System.out.println("Enter an integer to select:");
            System.out.println("0 : Exit");
            System.out.println("1 : Random History Generator");
            System.out.println("2 : Custom History");

            if (userInput.hasNextInt()) {
                int input = userInput.nextInt();  // read the next input
                switch (input) {
                    case 0:
                        exit = true;
                        break;
                    case 1:
                        randomGeneratorMenu();
                        userInput.nextLine();
                        break;
                    case 2:
                        customHistoryMenu();
                        userInput.nextLine();
                        break;
                }
            } else {
                userInput.nextLine();
            }
        } while(!exit); // and keep repeating
    }

    private static void randomGeneratorMenu() {
        System.out.println("Random History Generator Menu");
        Scanner userInput = new Scanner(System.in);

        Boolean exit = false;
        do {
            System.out.println("Enter an integer to select:");
            System.out.println("0 : Exit");
            System.out.println("1 : Generate Random History");

            if (userInput.hasNextInt()) {
                int input = userInput.nextInt();  // read the next input
                switch (input) {
                    case 0:
                        exit = true;
                        break;
                    case 1:
                        randomHistoryGenerator();
                        userInput.nextLine();
                        break;
                }
            } else {
                userInput.nextLine();
            }
        } while(!exit); // and keep repeating
    }

    private static void customHistoryMenu() {

    }

    private static void randomHistoryGenerator() {
        Scanner userInput = new Scanner(System.in);
        ArrayList<History> historyArrayList = new ArrayList<>();

        System.out.println("How many histories?");
        if (userInput.hasNextInt()) {
            int input = userInput.nextInt();  // read the next input
            for(int i = 0; i < input; i++) {
                historyArrayList.add(HistoryCreator.createRandomHistory(4, 4, 100, 1000));
            }
            for(History history: historyArrayList) {
                System.out.println(history.getHistory());
                System.out.println("Has the following: ");
                System.out.println(ACAAnalyzer.ACAChecking(history));
                System.out.println(STAnalyzer.STChecking(history));
            }
            userInput.nextLine();
        }
    }

}
