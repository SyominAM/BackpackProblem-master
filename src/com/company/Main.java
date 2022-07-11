package com.company;

// program to solve the backpack problem with an algorithm
// HCSKUL

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // ArrayList for the input table
    private static ArrayList<Item> items = new ArrayList<Item>();

    // ArrayList for the result
    // --> index of outer ArrayList = row
    // --> index of inner ArrayList = column
    private static ArrayList<ArrayList<Cell>> cells = new ArrayList<ArrayList<Cell>>();

    private static Scanner scanner = new Scanner(System.in);
    private static int backPackSize;

    // left >> up >> new
    private static final String left = "<-";
    private static final String up = " ^";
    private static final String newItem = "in";


    public static void main(String[] args) {

        // add an empty item so that the index corresponds to the item in the real world table
        items.add(new Item(0, 0));
        cells.add(new ArrayList<Cell>());

        /* items.add(new Item(3, 2));
        cells.add(new ArrayList<Cell>());
        items.add(new Item(7, 6));
        cells.add(new ArrayList<Cell>());
        items.add(new Item(2, 1));
        cells.add(new ArrayList<Cell>());
        items.add(new Item(4, 3));
        cells.add(new ArrayList<Cell>());
        items.add(new Item(5, 5));
        cells.add(new ArrayList<Cell>());
        items.add(new Item(2, 1));
        cells.add(new ArrayList<Cell>());
         */

        boolean quit = false;
        int choice;
        printMenu();

        while (!quit) {
            System.out.println("Please enter your choice:");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    printMenu();
                    break;

                case 1:
                    System.out.println("Number of items:");
                    int numberofItems = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < numberofItems; i++) {
                        addItem();
                    }
                    quit = true;
                    break;

                case 2:
                    System.out.println("Quitting!");
                    quit = true;
                    break;
            }
        }

        System.out.println("\nBackPack size:\n");
        backPackSize = scanner.nextInt();
        scanner.nextLine();

        printTable();

        cells.add(new ArrayList<Cell>());

        algorithm();

        printResult();

    }

    private static void printMenu() {
        System.out.println("\nMenu: " +
                "\n0 - print menu" +
                "\n1 - add n items" +
                "\n2 - quit adding items and continue");
    }

    private static void printTable() {

        // print the first line of the table --> item
        if (items.size() > 1) {
            System.out.print("item: ");
            for (int i = 1; i < items.size(); i++) {
                System.out.print("\t" + i);
            }

            // print the second line of the table --> value
            System.out.print("\nvalue: ");
            for (int i = 1; i < items.size(); i++) {
                System.out.print("\t" + items.get(i).getItemValue());
            }

            // print the second line of the table --> value
            System.out.print("\nsize: ");
            for (int i = 1; i < items.size(); i++) {
                System.out.print("\t" + items.get(i).getItemSize());
            }

            System.out.println("\n\nBackPack size = " + backPackSize + "\n");
        }
    }

    private static void algorithm() {

        // logic for the 0th item of the list
        // i = iterator through the rows
        ArrayList<Cell> column = cells.get(0);
        for (int i = 0; i <= backPackSize; i++) {
            column.add(new Cell(up, 0));
        }

        // logic for the rest


        // iterating through the columns
        for (int actualColumnIndex = 1; actualColumnIndex < items.size(); actualColumnIndex++) {
            // set the first entry of the column to 0
            cells.get(actualColumnIndex).add(new Cell(left, 0));

            // iterating through the rows
            for (int row = 1; row <= backPackSize; row++) {
                //setting the entry in the first row to left zero

                int actualValue = items.get(actualColumnIndex).getItemValue();
                int actualSize = items.get(actualColumnIndex).getItemSize();

                ArrayList<Cell> actualColumn = cells.get(actualColumnIndex);
                ArrayList<Cell> minusOneColumn = cells.get(actualColumnIndex - 1);

                int leftValue = minusOneColumn.get(row).getResultValue();
                int upperValue = actualColumn.get(row - 1).getResultValue();

                // only compare the left and the upper case
                if (row < actualSize) {
                    if (upperValue > leftValue) {
                        actualColumn.add(new Cell(up, upperValue));
                    } else {
                        actualColumn.add(new Cell(left, leftValue));
                    }
                } else {
                    // also compare with the new value
                    int newValue = actualValue + minusOneColumn.get(row - actualSize).getResultValue();
                    if (newValue > leftValue && newValue > upperValue) {
                        actualColumn.add(new Cell(newItem, newValue));
                    } else if (upperValue > leftValue) {
                        actualColumn.add(new Cell(up, upperValue));
                    } else {
                        actualColumn.add(new Cell(left, leftValue));
                    }

                }

            }
        }

        // logic for the very first entry
        Cell firstEntry = cells.get(0).get(0);
        firstEntry.setDirection(left);
    }

    private static void printResult() {

        // printing the first line
        for (int i = 0; i < items.size(); i++) {
            System.out.print("\t\t" + i);
        }
        System.out.println("");
        for (int row = 0; row <= backPackSize; row++) {
            System.out.print(row);
            for (int column = 0; column < items.size(); column++) {
                System.out.print("\t" + cells.get(column).get(row).printCell());
            }
            System.out.println("");

        }
    }

    private static void addItem() {
        System.out.println("Value:");
        int value = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Size:");
        int size = scanner.nextInt();
        scanner.nextLine();

        items.add(new Item(value, size));
        cells.add(new ArrayList<Cell>());

    }
}
