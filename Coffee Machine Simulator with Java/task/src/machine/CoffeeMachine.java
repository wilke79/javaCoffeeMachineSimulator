package machine;

import java.util.Scanner;

class Ingredients {
    private int water;
    private int milk;
    private int beans;

    public Ingredients() {
        this.water = 0;
        this.milk = 0;
        this.beans = 0;
    }

//    public Ingredients(int water, int milk, int beans) {
//        this.water = water;
//        this.milk = milk;
//        this.beans = beans;
//    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public int getBeans() {
        return beans;
    }

    public void setBeans(int beans) {
        this.beans = beans;
    }
}

public class CoffeeMachine {
    public static void main(String[] args) {
        final int WATER_PER_CUP = 200;
        final int MILK_PER_CUP = 50;
        final int BEANS_PER_CUP = 15;
        Scanner scanner = new Scanner(System.in);
        Ingredients supplies = new Ingredients();
        System.out.println("Write how many ml of water the coffee machine has:");
        try {
            supplies.setWater(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
        System.out.println("Write how many ml of milk the coffee machine has:");
        try {
            supplies.setMilk(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        try {
            supplies.setBeans(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
        System.out.println("Write how many cups of coffee you will need:");
        int cups = 0;
        try {
            cups = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }
        int cupsAvailable = Math.min((int) Math.floor((double) supplies.getWater() / WATER_PER_CUP),
                Math.min((int) Math.floor((double) supplies.getMilk() / MILK_PER_CUP),
                        (int) Math.floor((double) supplies.getBeans() / BEANS_PER_CUP)));
        if (cupsAvailable < cups) {
            System.out.printf("No, I can make only %d cup(s) of coffee", cupsAvailable);
        } else {
            System.out.print("Yes, I can make that amount of coffee");
            if (cupsAvailable > cups) {
                System.out.printf("(and even %d more than that)\n", cupsAvailable - cups);
            }
        }
    }
}
