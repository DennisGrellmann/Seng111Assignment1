import java.util.Scanner;
public class PizzaOrderingSystem {
    // Class variables 
    private static double DailyTotalSales = 0.0;
    private static int TotalOrders = 0;

    //Most recent Pizza order for modification or cancellation.
    private static boolean RecentOrderExists = false;
    private static String RecentPizzaType = "";
    private static String RecentPizzaSize = "";
    private static int RecentPizzaQuantity = 0;
    private static double RecentOrderTotal = 0.0;

    //Pizza Tracking
    private static int MargheritaSmallCount = 0, MargheritaMediumCount = 0, MargheritaLargeCount = 0;
    private static int NeapolitanSmallCount = 0, NeapolitanMediumCount = 0, NeapolitanLargeCount = 0;
    private static int MarinaraSmallCount = 0, MarinaraMediumCount = 0, MarinaraLargeCount = 0;

    //add on tracking
    private static int ExtraCheeseCount = 0, ExtraOlivesCount = 0;
    private static int DrinkSideCount = 0, GarlicBreadCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            MainDisplayMenu();
            int menuChoice = InputValidation(sc, 1, 9, "Please select an option (1-9): \n");

            switch (menuChoice) {
                case 1:
                    DisplayPizzaMenu();
                    System.out.println("Press Enter to return to the main menu...");
                    sc.nextLine();
                    break;
                case 2:
                    PlaceNewOrder(sc);
                    break;
                case 3:
                    CompareTwoOrders(sc);
                    break;
                case 4:
                    SimulateDailySpecial(sc);
                    break;
                case 5:
                    ViewDailySalesReport();
                    break;
                case 6:
                    ViewSalesStatistics();
                    break;
                case 7:
                    ModifyPreviousOrder(sc);
                    break;
                case 8:
                    CancelPreviousOrder();
                    break;
                case 9:
                    System.out.println("Thank you for using the Pizza Ordering System. Goodbye!");
                    exit = true;
                    break; 
                default:
                    System.out.println("Invalid option. Please select a number between 1 and 9.");
                    break;
            }
        }
        sc.close();
    }
    //Place new order
    public static void PlaceNewOrder(Scanner sc) {
        System.out.println("Placing a new order...\n");
        DisplayPizzaMenu();

        System.out.println("Please select a pizza from the menu above.");
        String pizzaType = "";
        while (true) {
            System.out.print("Please write which pizza, Margherita, Neapolitan, or Marinara: ");
            pizzaType = sc.nextLine().trim().toLowerCase();
            if (pizzaType.equals("margherita") ||
            pizzaType.equals("neapolitan") ||
            pizzaType.equals("marinara")) {
            break;
            } 
            System.out.println("Invalid input. Please enter a valid pizza.");
        }
        System.out.println("Please select a size (Small, Medium, Large).");
        String pizzaSize = "";
        while (true) {
            System.out.print("Please write which size, Small, Medium, or Large: ");
            pizzaSize = sc.nextLine().trim().toLowerCase();
            if (pizzaSize.equals("small") ||
            pizzaSize.equals("medium") ||
            pizzaSize.equals("large")) {
            break;
            } 
            System.out.println("Invalid input. Please enter a valid size.");
        }
        int pizzaQuantity = InputValidation(sc, 1, 10, "Please enter a quantity between 1 and 10. ");
        Double BasePrice = GetPizzaPrice(pizzaType, pizzaSize);
        System.out.println(BasePrice);
        
        double pizzaTotal = BasePrice * pizzaQuantity;
        double addOnTotal = 0.0;
        //Input validation for add ons
        boolean CorrectInput = false;
        while (!CorrectInput) {
            System.out.println("Would you like to add extra cheese for $1.50? (yes/no)");
            String response = sc.nextLine().trim().toLowerCase();
            if (response.equals("yes")) {
                addOnTotal += 1.50 * pizzaQuantity;
                ExtraCheeseCount++;
                CorrectInput = true;
            } else if (response.equals("no")) {
                System.out.println("No extra cheese added.\n");
                CorrectInput = true;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }

        CorrectInput = false;
        while (!CorrectInput) {
            System.out.println("Would you like to add extra Olives for $1.00? (yes/no)");
            String response = sc.nextLine().trim().toLowerCase();
            if (response.equals("yes")) {
                addOnTotal += 1.00 * pizzaQuantity;
                ExtraOlivesCount++;
                CorrectInput = true;
            } else if (response.equals("no")) {
                System.out.println("No extra olives added.\n");
                CorrectInput = true;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }

        CorrectInput = false;
        while (!CorrectInput) {
            System.out.println("Would you like to add Garlic Bread for $4.00? (yes/no)");
            String response = sc.nextLine().trim().toLowerCase();
            if (response.equals("yes")) {
                addOnTotal += 4.00 * pizzaQuantity;
                GarlicBreadCount++;
                CorrectInput = true;
            } else if (response.equals("no")) {
                System.out.println("No Garlic Bread added.\n");
                CorrectInput = true;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }

        CorrectInput = false;
        while (!CorrectInput) {
            System.out.println("Would you like to add a drink for $2.50? (yes/no)");
            String response = sc.nextLine().trim().toLowerCase();
            if (response.equals("yes")) {
                addOnTotal += 2.50 * pizzaQuantity;
                DrinkSideCount++;
                CorrectInput = true;
            } else if (response.equals("no")) {
                System.out.println("No drink added.\n");
                CorrectInput = true;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }

 
        double PreTaxTotal = pizzaTotal + addOnTotal;
        double tax = PreTaxTotal * 0.10;
        double Total = PreTaxTotal + tax;
        

        System.out.println("---- Your Receipt ----");
        System.out.println(" Pizza Type: " + pizzaType);
        System.out.println(" Pizza Size: " + pizzaSize);
        System.out.println("  Add-Ons: " + String.format("%.2f", addOnTotal));
        System.out.println(" Quantity: " + pizzaQuantity);
        System.out.println("Pre-Tax Total: $" + String.format("%.2f", PreTaxTotal));
        System.out.println("Tax (10%): $" + String.format("%.2f", tax));
        System.out.println("Total Amount Due: $" + String.format("%.2f", Total));
        System.out.println("----------------------\n");

        //Recent Pizza details
        RecentOrderExists = true;
        RecentPizzaType = pizzaType;
        RecentPizzaSize = pizzaSize;
        RecentPizzaQuantity = pizzaQuantity;
        RecentOrderTotal = Total;

        //Pizza Counter 
        if (pizzaType.equals("margherita")) {
            if (pizzaSize.equals("small")) MargheritaSmallCount += pizzaQuantity;
            else if (pizzaSize.equals("medium")) MargheritaMediumCount += pizzaQuantity;
            else if (pizzaSize.equals("large")) MargheritaLargeCount += pizzaQuantity;
        } else if (pizzaType.equals("neapolitan")) {
            if (pizzaSize.equals("small")) NeapolitanSmallCount += pizzaQuantity;
            else if (pizzaSize.equals("medium")) NeapolitanMediumCount += pizzaQuantity;
            else if (pizzaSize.equals("large")) NeapolitanLargeCount += pizzaQuantity;
        } else if (pizzaType.equals("marinara")) {
            if (pizzaSize.equals("small")) MarinaraSmallCount += pizzaQuantity;
            else if (pizzaSize.equals("medium")) MarinaraMediumCount += pizzaQuantity;
            else if (pizzaSize.equals("large")) MarinaraLargeCount += pizzaQuantity;
        }

        DailyTotalSales += Total;
        TotalOrders++;
    }


    public static void CompareTwoOrders(Scanner sc) {
        System.out.println("Compare Two Pizza Orders\n");
        //Piiza 1
        System.out.println("First Pizza:");
        String firstType = "";
        while (true) {
            System.out.print("Enter pizza type (Margherita, Neapolitan, Marinara): ");
            firstType = sc.nextLine().trim().toLowerCase();
            if (firstType.equals("margherita") || 
            firstType.equals("neapolitan") || 
            firstType.equals("marinara")) {
                break;
            }
            System.out.println("Invalid input. Please enter a valid pizza type.");
        }
        String firstSize = "";
        while (true) {
            System.out.print("Enter pizza size (Small, Medium, Large): ");
            firstSize = sc.nextLine().trim().toLowerCase();
            if (firstSize.equals("small") || 
            firstSize.equals("medium") || 
            firstSize.equals("large")) {
                break;
            }
            System.out.println("Invalid input. Please enter a valid size.");
        }
        double firstPrice = GetPizzaPrice(firstType, firstSize);
        //Pizza 2
        System.out.println("\nSecond Pizza:");
        String secondType = "";
        while (true) {
            System.out.print("Enter pizza type (Margherita, Neapolitan, Marinara): ");
            secondType = sc.nextLine().trim().toLowerCase();
            if (secondType.equals("margherita") || 
            secondType.equals("neapolitan") || 
            secondType.equals("marinara")) {
                break;
            }
            System.out.println("Invalid input. Please enter a valid pizza type.");
        }
        String secondSize = "";
        while (true) {
            System.out.print("Enter pizza size (Small, Medium, Large): ");
            secondSize = sc.nextLine().trim().toLowerCase();
            if (secondSize.equals("small") || 
            secondSize.equals("medium") || 
            secondSize.equals("large")) {
                break;
            }
            System.out.println("Invalid input. Please enter a valid size.");
        }
        double secondPrice = GetPizzaPrice(secondType, secondSize);

        System.out.println("\nFirst Pizza: " + firstType + " (" + firstSize + ") - $" + String.format("%.2f", firstPrice));
        System.out.println("Second Pizza: " + secondType + " (" + secondSize + ") - $" + String.format("%.2f", secondPrice));

        if (firstPrice == secondPrice) {
            System.out.println("Both pizzas cost the same.");
        } else if (firstPrice < secondPrice) {
            System.out.println("The first pizza is cheaper than the second pizza.");
        } else {
            System.out.println("The first pizza is more expensive than the second pizza.");
        }
    }

    public static void SimulateDailySpecial(Scanner sc) {
        System.out.println("Simulating Daily Special...\n");
        //randomaly picks one of three offers
        int offer = (int)(Math.random() * 3);
        System.out.println(offer);
        double discount = 0.0;
        String offerMessage = "";

        switch (offer) {
            case 0:
                offerMessage = "Buy one get one half off on the next pizza!";
                break;
            case 1:
                offerMessage = "20% off your total order!";
                break;
            case 2:
                offerMessage = "Free side with every large pizza!";
                break;
        }

        System.out.println("Today's Special Offer: " + offerMessage + "\n");

        System.out.println("Place a new order to apply the special offer.\n");
        DisplayPizzaMenu();
        System.out.println("Please select a pizza from the menu above.");
        String pizzaType = "";
        while (true) {
            System.out.print("Please write which pizza, Margherita, Neapolitan, or Marinara: ");
            pizzaType = sc.nextLine().trim().toLowerCase();
            if (pizzaType.equals("margherita") ||
            pizzaType.equals("neapolitan") ||
            pizzaType.equals("marinara")) {
            break;
            } 
            System.out.println("Invalid input. Please enter a valid pizza.");
        }
        System.out.println("Please select a size (Small, Medium, Large).");
        String pizzaSize = "";
        while (true) {
            System.out.print("Please write which size, Small, Medium, or Large: ");
            pizzaSize = sc.nextLine().trim().toLowerCase();
            if (pizzaSize.equals("small") ||
            pizzaSize.equals("medium") ||
            pizzaSize.equals("large")) {
            break;
            } 
            System.out.println("Invalid input. Please enter a valid size.");
        }
        int quantity = InputValidation(sc, 1, 10, "Please enter a quantity between 1 and 10. ");
        Double basePrice = GetPizzaPrice(pizzaType, pizzaSize);
        double pizzaTotal = basePrice * quantity;
        double addOnTotal = 0.0;

        Boolean CorrectInput = false;
        while (!CorrectInput) {
            System.out.println("Would you like to add extra cheese for $1.50? (yes/no)");
            String response = sc.nextLine().trim().toLowerCase();
            if (response.equals("yes")) {
                addOnTotal += 1.50 * quantity;
                ExtraCheeseCount++;
                CorrectInput = true;
            } else if (response.equals("no")) {
                System.out.println("No extra cheese added.\n");
                CorrectInput = true;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
        System.out.println("No extra cheese added.\n");
        System.out.println("Would you like to add extra olives for $1.00? (yes/no)");
        if (sc.nextLine().trim().equalsIgnoreCase("yes")) {
            addOnTotal += 1.00 * quantity;
            ExtraOlivesCount++;
        }
        else{
            System.out.println("No extra olives added.\n");
        }   
        if (offer == 2 && pizzaSize.equals("large")) {
            System.out.println("You get a free side with your large pizza!");
            System.out.println("You can choose either Garlic bread or a drink.");
            System.out.println("1. Garlic bread");
            System.out.println("2. Drink");
            int sideChoice = InputValidation(sc, 1, 2, "Please select your free side (1-2): ");
            if (sideChoice == 1) {
                GarlicBreadCount++;
                System.out.println("Garlic bread added as your free side.\n");
            } else {
                DrinkSideCount++;
                System.out.println("Drink added as your free side.\n");
            }
        } else {   
            System.out.println("Would you like to add Garlic bread for $4.00? (yes/no)");
            if (sc.nextLine().trim().equalsIgnoreCase("yes")) {
                addOnTotal += 4.00;
                GarlicBreadCount++;
            }
            else{
                System.out.println("No Garlic bread added.\n");
            }       
            System.out.println("Would you like to add a drink for $2.50? (yes/no)");
            if (sc.nextLine().trim().equalsIgnoreCase("yes")) {
                addOnTotal += 2.50;
                DrinkSideCount++;
            }
            else{
                System.out.println("No drink added.\n");
            }
        }

        double PreTaxTotal = pizzaTotal + addOnTotal;
        double tax = PreTaxTotal * 0.10;


        switch (offer) {
            case 0: // buy one get other half off
            if (quantity >= 2) {
                discount = GetPizzaPrice(pizzaType, pizzaSize) / 2;
            }
                break;
            case 1: // 20% off
                discount = PreTaxTotal * 0.20;
                break;
                // case 3 would be the free large but that is nested in a if statement during the side selection
        }

        double Total = PreTaxTotal -discount + tax;

        System.out.println("---- Your Receipt ----");
        System.out.println(" Pizza Type: " + pizzaType);
        System.out.println(" Pizza Size: " + pizzaSize);
        System.out.println("  Add-Ons: " + String.format("%.2f", addOnTotal));
        System.out.println(" Quantity: " + quantity);
        System.out.println("Pre-Tax Total: $" + String.format("%.2f", PreTaxTotal));
        System.out.println("Discount: $" + String.format("%.2f", discount));
        System.out.println("Tax (10%): $" + String.format("%.2f", tax));
        System.out.println("Total Amount Due: $" + String.format("%.2f", Total));
        System.out.println("----------------------\n"); 

        RecentOrderExists = true;
        RecentPizzaType = pizzaType;
        RecentPizzaSize = pizzaSize;
        RecentPizzaQuantity = quantity;
        RecentOrderTotal = Total;

        DailyTotalSales += Total;
        TotalOrders++;

        if(pizzaType.equals("margherita")) {
            if (pizzaSize.equals("small")) MargheritaSmallCount += quantity;
            else if (pizzaSize.equals("medium")) MargheritaMediumCount += quantity;
            else if (pizzaSize.equals("large")) MargheritaLargeCount += quantity;
        } else if (pizzaType.equals("neapolitan")) {
            if (pizzaSize.equals("small")) NeapolitanSmallCount += quantity;
            else if (pizzaSize.equals("medium")) NeapolitanMediumCount += quantity;
            else if (pizzaSize.equals("large")) NeapolitanLargeCount += quantity;
        } else if (pizzaType.equals("marinara")) {
            if (pizzaSize.equals("small")) MarinaraSmallCount += quantity;
            else if (pizzaSize.equals("medium")) MarinaraMediumCount += quantity;
            else if (pizzaSize.equals("large")) MarinaraLargeCount += quantity;
        }
    }

    public static void ModifyPreviousOrder(Scanner sc) {
        if (!RecentOrderExists) {
            System.out.println("No recent order to modify.\n");
            return;
        }

        System.out.println("Previous order found:");
        System.out.println(" Pizza Type: " + RecentPizzaType);
        System.out.println(" Pizza Size: " + RecentPizzaSize);
        System.out.println(" Quantity: " + RecentPizzaQuantity);
        System.out.println(" Total: $" + String.format("%.2f", RecentOrderTotal) + "\n");
        System.out.println("Select option to modify the previous order.");
        System.out.println("1. Change Pizza Size");
        System.out.println("2. Change Quantity");
        System.out.println("3. Both Size and Quantity");
        System.out.println("4. Cancel Modification and Return to Main Menu\n");

        int modifyChoice = InputValidation(sc, 1, 4, "Please select an option (1-4): \n");
        switch (modifyChoice) {
            case 1:
                // Change Pizza Size
                String newSize = "";
                while (true) {
                    System.out.print("Enter new pizza size (Small, Medium, Large): ");
                    newSize = sc.nextLine().trim().toLowerCase();
                    if (newSize.equals("small") || 
                    newSize.equals("medium") || 
                    newSize.equals("large")) {
                        break;
                    }
                    System.out.println("Invalid input. Please enter a valid size.");
                }
                RecentPizzaSize = newSize;
                break;
            case 2:
                // Change Quantity
                int newQuantity = InputValidation(sc, 1, 10, "Please enter a new quantity between 1 and 10: ");
                RecentPizzaQuantity = newQuantity;
                break;
            case 3:
                // Change Both Size and Quantity
                String updatedSize = "";
                while (true) {
                    System.out.print("Enter new pizza size (Small, Medium, Large): ");
                    updatedSize = sc.nextLine().trim().toLowerCase();
                    if (updatedSize.equals("small") || 
                    updatedSize.equals("medium") || 
                    updatedSize.equals("large")) {
                        break;
                    }
                    System.out.println("Invalid input. Please enter a valid size.");
                }

                int updatedQuantity = InputValidation(sc, 1, 10, "Please enter a new quantity between 1 and 10: ");
                RecentPizzaSize = updatedSize;
                RecentPizzaQuantity = updatedQuantity;
                break;
            case 4:
                // Cancel Modification
                System.out.println("Modification cancelled. Returning to main menu.\n");
                return;
        }

        // Revert previous order counts and totals fir modification
        if (RecentPizzaType.equals("margherita")) {
            if (RecentPizzaSize.equals("small")) MargheritaSmallCount -= RecentPizzaQuantity;
            else if (RecentPizzaSize.equals("medium")) MargheritaMediumCount -= RecentPizzaQuantity;
            else if (RecentPizzaSize.equals("large")) MargheritaLargeCount -= RecentPizzaQuantity;
        } else if (RecentPizzaType.equals("neapolitan")) {
            if (RecentPizzaSize.equals("small")) NeapolitanSmallCount -= RecentPizzaQuantity;
            else if (RecentPizzaSize.equals("medium")) NeapolitanMediumCount -= RecentPizzaQuantity;
            else if (RecentPizzaSize.equals("large")) NeapolitanLargeCount -= RecentPizzaQuantity;
        } else if (RecentPizzaType.equals("marinara")) {
            if (RecentPizzaSize.equals("small")) MarinaraSmallCount -= RecentPizzaQuantity;
            else if (RecentPizzaSize.equals("medium")) MarinaraMediumCount -= RecentPizzaQuantity;
            else if (RecentPizzaSize.equals("large")) MarinaraLargeCount -= RecentPizzaQuantity;
        }

        DailyTotalSales -= RecentOrderTotal;
        TotalOrders--;

        double newBasePrice = GetPizzaPrice(RecentPizzaType, RecentPizzaSize);  
        Double newOrderTotal = newBasePrice * RecentPizzaQuantity;

        if(RecentPizzaType.equals("margherita")) {
            if (RecentPizzaSize.equals("small")) MargheritaSmallCount += RecentPizzaQuantity;
            else if (RecentPizzaSize.equals("medium")) MargheritaMediumCount += RecentPizzaQuantity;
            else if (RecentPizzaSize.equals("large")) MargheritaLargeCount += RecentPizzaQuantity;
        } else if (RecentPizzaType.equals("neapolitan")) {
            if (RecentPizzaSize.equals("small")) NeapolitanSmallCount += RecentPizzaQuantity;
            else if (RecentPizzaSize.equals("medium")) NeapolitanMediumCount += RecentPizzaQuantity;
            else if (RecentPizzaSize.equals("large")) NeapolitanLargeCount += RecentPizzaQuantity;
        } else if (RecentPizzaType.equals("marinara")) {
            if (RecentPizzaSize.equals("small")) MarinaraSmallCount += RecentPizzaQuantity;
            else if (RecentPizzaSize.equals("medium")) MarinaraMediumCount += RecentPizzaQuantity;
            else if (RecentPizzaSize.equals("large")) MarinaraLargeCount += RecentPizzaQuantity;
        }

        DailyTotalSales += newOrderTotal;
        TotalOrders++;
        RecentOrderTotal = newOrderTotal;
        RecentOrderExists = true;

        System.out.println("Order modified successfully. New details:");
        System.out.println(" Pizza Type: " + RecentPizzaType);
        System.out.println(" Pizza Size: " + RecentPizzaSize);
        System.out.println(" Quantity: " + RecentPizzaQuantity);
        newBasePrice = GetPizzaPrice(RecentPizzaType, RecentPizzaSize);
        RecentOrderTotal = newBasePrice * RecentPizzaQuantity;
        System.out.println(" Total: $" + String.format("%.2f", RecentOrderTotal) + "\n");
    }

    public static void CancelPreviousOrder() {
        if (!RecentOrderExists) {
            System.out.println("No recent order to cancel.\n");
            return;
        }

        System.out.println("Previous order found!");
        System.out.println("Canceling the order:");
        if (RecentPizzaType.equals("margherita")) {
            if (RecentPizzaSize.equals("small")) MargheritaSmallCount -= RecentPizzaQuantity;
            else if (RecentPizzaSize.equals("medium")) MargheritaMediumCount -= RecentPizzaQuantity;
            else if (RecentPizzaSize.equals("large")) MargheritaLargeCount -= RecentPizzaQuantity;
        } else if (RecentPizzaType.equals("neapolitan")) {
            if (RecentPizzaSize.equals("small")) NeapolitanSmallCount -= RecentPizzaQuantity;
            else if (RecentPizzaSize.equals("medium")) NeapolitanMediumCount -= RecentPizzaQuantity;
            else if (RecentPizzaSize.equals("large")) NeapolitanLargeCount -= RecentPizzaQuantity;
        } else if (RecentPizzaType.equals("marinara")) {
            if (RecentPizzaSize.equals("small")) MarinaraSmallCount -= RecentPizzaQuantity;
            else if (RecentPizzaSize.equals("medium")) MarinaraMediumCount -= RecentPizzaQuantity;
            else if (RecentPizzaSize.equals("large")) MarinaraLargeCount -= RecentPizzaQuantity;
        }

        DailyTotalSales -= RecentOrderTotal;
        TotalOrders--;

        RecentOrderExists = false;
        RecentPizzaType = "";
        RecentPizzaSize = "";
        RecentPizzaQuantity = 0;
        RecentOrderTotal = 0.0;

        System.out.println("Order cancelled successfully. Returning to main menu.\n");
    }
    public static void ViewDailySalesReport() {
        System.out.println("Viewing Daily Sales Report...");
        System.out.println("Total Orders: " + TotalOrders);
        System.out.println("Total Sales: $" + String.format("%.2f", DailyTotalSales) + "\n");
        if (TotalOrders > 0 && DailyTotalSales > 0) {
            double averageOrderValue = DailyTotalSales / TotalOrders;
            System.out.println("Average Order Value: $" + String.format("%.2f", averageOrderValue) + "\n");
        } else {
            System.out.println("No orders placed today.\n");
        }

    }

    public static void ViewSalesStatistics() {
        System.out.println("Viewing Sales Statistics!\n");
        System.out.println("Pizza Sales Breakdown:");
        System.out.println(" Margherita - Small: " + MargheritaSmallCount + ", Medium: " + MargheritaMediumCount + ", Large: " + MargheritaLargeCount);
        System.out.println(" Neapolitan - Small: " + NeapolitanSmallCount + ", Medium: " + NeapolitanMediumCount + ", Large: " + NeapolitanLargeCount);
        System.out.println(" Marinara   - Small: " + MarinaraSmallCount + ", Medium: " + MarinaraMediumCount + ", Large: " + MarinaraLargeCount + "\n");

        System.out.println("Add-On Sales Breakdown:");
        System.out.println(" Extra Cheese: " + ExtraCheeseCount);
        System.out.println(" Extra Olives: " + ExtraOlivesCount);
        System.out.println(" Garlic Bread: " + GarlicBreadCount);
        System.out.println(" Drinks: " + DrinkSideCount + "\n");
    }


    public static int InputValidation(Scanner sc, int min, int max, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice >= min && choice <= max) {
                    return choice;
                }
            }
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    //main menu
    public static void MainDisplayMenu() {
        System.out.println("Welcome to the Pizza Ordering System!");
        System.out.println(">-----------------------------------<");
        System.out.println("1. View Pizza Menu");
        System.out.println("2. Place an Order");
        System.out.println("3. Compare two Pizza orders");
        System.out.println("4. Simulate Daily Special");
        System.out.println("5. View Daily sales report");
        System.out.println("6. View Sales statistics");
        System.out.println("7. Modify Previous Order");
        System.out.println("8. Cancel Previous Order");
        System.out.println("9. Exit");
        System.out.println(">-----------------------------------<\n");
    }

    //Pizza Menu
    public static void DisplayPizzaMenu() {
        System.out.println("            Welcome to the Pizza Selection Menu!");
        System.out.println(">---------------------------------------------------------------<");
        System.out.println("1. Margherita Pizza - Small: $8.00, Medium: $10.00, Large: $12.00");
        System.out.println("2. Neapolitan Pizza - Small: $9.00, Medium: $11.00, Large: $13.50");
        System.out.println("3. Marinara Pizza - Small: $9.50, Medium: $11.50, Large: $14.00\n");
    }

    public static double GetPizzaPrice(String type, String size) {
        double basePrice = 0.0;
        switch (type.toLowerCase()) {
            case "margherita":
                if (size.equals("small")) basePrice = 8.00;
                else if (size.equals("medium")) basePrice = 10.00;
                else if (size.equals("large")) basePrice = 12.00;
                break;
            case "neapolitan":
                if (size.equals("small")) basePrice = 9.00;
                else if (size.equals("medium")) basePrice = 11.00;
                else if (size.equals("large")) basePrice = 13.50;
                break;
            case "marinara":
                if (size.equals("small")) basePrice = 9.50;
                else if (size.equals("medium")) basePrice = 11.50;
                else if (size.equals("large")) basePrice = 14.00;
                break;
            default:
                System.out.println("Invalid pizza type.");
                return 0.0;
        }
        return basePrice;
    }
}
