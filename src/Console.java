import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    public static double readInput(String sentence, double min, double max) {
        double input;
        while (true) {
            System.out.print(sentence);
            input = scanner.nextDouble();
            if (input >= min && input <= max)
                break;
            System.out.println("Enter a number between " + min + " and " + max);
        }
        return input;
    }
}
