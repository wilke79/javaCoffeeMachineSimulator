package machine;

import java.util.ArrayList;
import java.util.Scanner;

class Speciality {
    final private int water;
    final private int milk;
    final private int beans;
    final private int cost;

    public Speciality(int water, int milk, int beans, int cost) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cost = cost;
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getBeans() {
        return beans;
    }

    public int getCost() {
        return cost;
    }
}

class Machine {
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;
    final private ArrayList<Speciality> specialities;

    public Machine (int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this. beans = beans;
        this.cups = cups;
        this.money = money;
        this.specialities = new ArrayList<>();
        this.specialities.add(new Speciality(250, 0, 16, 4));
        this.specialities.add(new Speciality(350, 75, 20, 7));
        this.specialities.add(new Speciality(200, 100, 12, 6));
    }

    public void printState() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", this.water);
        System.out.printf("%d ml of milk\n", this.milk);
        System.out.printf("%d g of coffee beans\n", this.beans);
        System.out.printf("%d disposable cups\n", this.cups);
        System.out.printf("$%d of money\n", this.money);
    }

    private void makeCoffee(int choice) {
        this.water -= this.specialities.get(choice - 1).getWater();
        this.milk -= this.specialities.get(choice - 1).getMilk();
        this.beans -= this.specialities.get(choice - 1).getBeans();
        this.cups -= 1;
        this.money += this.specialities.get(choice - 1).getCost();
    }

    public void buy() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        String choice = scanner.nextLine();
        try {
            switch (Integer.parseInt(choice)) {
                case 1, 2, 3 -> this.makeCoffee(Integer.parseInt(choice));
                default -> System.out.println("Invalid input!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }

    public void fill() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add:");
        try {
            this.water += Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
        System.out.println("Write how many ml of milk you want to add:");
        try {
            this.milk += Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
        System.out.println("Write how many grams of coffee beans you want to add:");
        try {
            this.beans += Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
        System.out.println("Write how many disposable cups you want to add:");
        try {
            this.cups += Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
    }

    public void take() {
        System.out.printf("I gave you $%d\n", this.money);
        this.money = 0;
    }
}

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Machine coffeeMachine = new Machine(400, 540, 120, 9, 550);
        coffeeMachine.printState();
        System.out.println("Write action (buy, fill, take):");
        String choice = scanner.nextLine();
        switch (choice) {
            case "buy" -> coffeeMachine.buy();
            case "fill" -> coffeeMachine.fill();
            case "take" -> coffeeMachine.take();
            default -> System.out.println("Invalid input!");
        }
        coffeeMachine.printState();
//        System.out.println("Write how many cups of coffee you will need:");
//        int cups = 0;
//        try {
//            cups = Integer.parseInt(scanner.nextLine());
//        } catch (NumberFormatException e) {
//            System.out.println("Invalid input!");
//        }
//        int cupsAvailable = Math.min((int) Math.floor((double) supplies.getWater() / WATER_PER_CUP),
//                Math.min((int) Math.floor((double) supplies.getMilk() / MILK_PER_CUP),
//                        (int) Math.floor((double) supplies.getBeans() / BEANS_PER_CUP)));
//        if (cupsAvailable < cups) {
//            System.out.printf("No, I can make only %d cup(s) of coffee", cupsAvailable);
//        } else {
//            System.out.print("Yes, I can make that amount of coffee");
//            if (cupsAvailable > cups) {
//                System.out.printf("(and even %d more than that)\n", cupsAvailable - cups);
//            }
//        }
    }
}
