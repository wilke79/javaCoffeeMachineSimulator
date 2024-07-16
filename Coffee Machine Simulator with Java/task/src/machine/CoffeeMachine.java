package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        final int WATER_PER_CUP = 200;
        final int MILK_PER_CUP = 50;
        final int BEANS_PER_CUP = 15;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many cups of coffee you will need: ");
        int cups = 0;
        try {
            cups = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
        int water = cups * WATER_PER_CUP;
        int milk = cups * MILK_PER_CUP;
        int beans = cups * BEANS_PER_CUP;
        System.out.printf("For %d cups of coffee you will need:\n", cups);
        System.out.printf("%d ml of water\n", water);
        System.out.printf("%d ml of milk\n", milk);
        System.out.printf("%d ml of beans\n", beans);
    }
}
