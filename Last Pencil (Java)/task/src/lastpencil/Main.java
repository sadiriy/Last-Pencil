package lastpencil;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final char PENCIL = '|';
    private static final List<String> listOfPlayers = List.of("John", "Jack");
    private static final int BOT_INDEX = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // determining pencil count
        System.out.println("How many pencils would you like to use:");
        int pencilCount = getPencilCount(scanner);

        // determining player
        System.out.println("Who will be the first (John, Jack):");
        int currentIndex = getPlayersIndex(scanner);

        displayPencil(pencilCount);

        while (pencilCount > 0) {
            System.out.printf("\n%s's turn!\n", listOfPlayers.get(currentIndex));
            if (currentIndex == BOT_INDEX) {
                pencilCount = subtractAmount(pencilCount);
            }
            else {
                scanner.nextLine();
                pencilCount = subtractAmount(scanner, pencilCount);
            }
            displayPencil(pencilCount);
            currentIndex = (currentIndex + 1) % listOfPlayers.size();
        }
        System.out.printf("%s won!", listOfPlayers.get(currentIndex));
    }

    private static void displayPencil(int pencilCount) {
        for (int i = 0; i < pencilCount; i++) {
            System.out.print(PENCIL);
        }
    }

    private static int getPencilCount(Scanner scanner) {
        String input = scanner.nextLine().trim();
        int result;
        try {
            result = Integer.parseInt(input);
        }
        catch (NumberFormatException e) {
            System.out.println("The number of pencils should be numeric");
            return getPencilCount(scanner);
        }
        if (result <= 0) {
            System.out.println("The number of pencils should be positive");
            return getPencilCount(scanner);
        }
        return result;
    }

    private static int getPlayersIndex(Scanner scanner) {
        String playerName = scanner.next().trim();
        if (listOfPlayers.contains(playerName)) {
            return listOfPlayers.indexOf(playerName);
        }
        System.out.println("Choose between 'John' and 'Jack'");
        scanner.nextLine();
        return getPlayersIndex(scanner);
    }

    private static int subtractAmount(Scanner scanner, int remainderCount) {
        String input = scanner.next().trim();
        int subtractAmount;
        try {
            subtractAmount = Integer.parseInt(input);
        }
        catch (NumberFormatException e) {
            System.out.println("Possible values: '1', '2' or '3'");
            scanner.nextLine();
            return subtractAmount(scanner, remainderCount);
        }
        if (subtractAmount <= 0 || subtractAmount > 3){
                System.out.println("Possible values: '1', '2' or '3'");
                return subtractAmount(scanner, remainderCount);
        }
        if (remainderCount < subtractAmount) {
            System.out.println("Too many pencils were taken");
            scanner.nextLine();
            return subtractAmount(scanner, remainderCount);
        }
        return remainderCount - subtractAmount;
    }

    private static int subtractAmount(int remainderCount) {
        if (remainderCount == 1) {
            System.out.println(1);
            return 0;
        }
        int subtractAmount = 0;
        Random random = new Random();
        subtractAmount = switch (remainderCount % 4) {
            case 0 -> 3;
            case 1 -> random.nextInt(3) + 1;
            case 2 -> 1;
            case 3 -> 2;
            default -> subtractAmount;
        };
        System.out.println(subtractAmount);
        return remainderCount - subtractAmount;
    }
}
