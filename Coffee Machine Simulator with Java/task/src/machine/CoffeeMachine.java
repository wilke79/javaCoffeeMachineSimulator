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

enum State {
    ACTION, BUY, FILL_WATER, FILL_MILK, FILL_BEANS, FILL_CUPS
}

class Machine {
    private State state;
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;
    final private ArrayList<Speciality> specialities;

    public Machine (int water, int milk, int beans, int cups, int money) {
        this.state = State.ACTION;
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
        if (this.water < this.specialities.get(choice - 1).getWater()) {
            System.out.println("Sorry, not enough water!");
        } else if (this.milk < this.specialities.get(choice - 1).getMilk()) {
            System.out.println("Sorry, not enough milk!");
        } else if (this.beans < this.specialities.get(choice - 1).getBeans()) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (this.cups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            this.water -= this.specialities.get(choice - 1).getWater();
            this.milk -= this.specialities.get(choice - 1).getMilk();
            this.beans -= this.specialities.get(choice - 1).getBeans();
            this.cups -= 1;
            this.money += this.specialities.get(choice - 1).getCost();
        }
        this.state = State.ACTION;
    }

    public void buy() {
        this.state = State.BUY;
    }

    public void fill(int numberToAdd) {
        switch (this.state) {
            case FILL_WATER -> {
                this.state = State.FILL_MILK;
                this.water += numberToAdd;
            }
            case FILL_MILK -> {
                this.milk += numberToAdd;
                this.state = State.FILL_BEANS;
            }
            case FILL_BEANS -> {
                this.beans += numberToAdd;
                this.state = State.FILL_CUPS;
            }
            case FILL_CUPS -> {
                this.cups += numberToAdd;
                this.state = State.ACTION;
            }
        }
    }

    public void take() {
        System.out.printf("I gave you $%d\n", this.money);
        this.money = 0;
    }

    public String getMessage() {
        return switch (this.state) {
            case ACTION -> "Write action (buy, fill, take, remaining, exit):";
            case BUY -> "What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:";
            case FILL_WATER -> "Write how many ml of water you want to add:";
            case FILL_MILK -> "Write how many ml of milk you want to add:";
            case FILL_BEANS -> "Write how many grams of coffee beans you want to add:";
            case FILL_CUPS -> "Write how many disposable cups you want to add:";
        };
    }

    public void process(String input) {
        switch (this.state) {
            case ACTION -> {
                switch (input) {
                    case "buy" -> this.buy();
                    case "fill" -> this.state = State.FILL_WATER;
                    case "take" -> this.take();
                    case "remaining" -> this.printState();
                    default -> System.out.println("Invalid input!");
                }
            }
            case BUY -> {
                try {
                    switch (input) {
                        case "1", "2", "3" -> this.makeCoffee(Integer.parseInt(input));
                        case "back" -> this.state = State.ACTION;
                        default -> System.out.println("Invalid input!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input!");
                }

            }
            case FILL_WATER, FILL_MILK, FILL_BEANS, FILL_CUPS -> {
                try {
                    this.fill(Integer.parseInt(input));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input!");
                }

            }
        }
    }
}

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Machine coffeeMachine = new Machine(400, 540, 120, 9, 550);
        String choice;
        do {
            System.out.println(coffeeMachine.getMessage());
            choice = scanner.nextLine();
            if (!choice.equals("exit")) {
                coffeeMachine.process(choice);
            }
        } while (!choice.equals("exit"));
    }
}
