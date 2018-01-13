package Network;

import java.util.Scanner;

/**
 * Created by berku on 13.1.2018.
 */
public class ConsoleInputHandler {
    private static Scanner scanner = new Scanner(System.in);

    public static String getUserInput(String text) {
        System.out.print(text);
        String input = scanner.nextLine();
        return input;
    }
}
