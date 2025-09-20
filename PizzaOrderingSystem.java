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
    private static int DrinkSideCount = 0, GarlicBreadSideCount = 0;

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

        System.out.println("Would you like to add extra cheese for $1.50? (yes/no)");
        if (sc.nextLine().trim().equalsIgnoreCase("yes")) {
            addOnTotal += 1.50;
            ExtraCheeseCount++;
        }
        else{
            System.out.println("No extra cheese added.\n");
        }
        System.out.println("Would you like to add extra olives for $1.00? (yes/no)");
        if (sc.nextLine().trim().equalsIgnoreCase("yes")) {
            addOnTotal += 1.00;
            ExtraOlivesCount++;
        }
        else{
            System.out.println("No extra olives added.\n");
        }
        double PreTaxTotal = pizzaTotal + addOnTotal;
        System.out.println(PreTaxTotal + "\n");

        double tax = 0.10;
        double Total = PreTaxTotal + tax;
        System.out.println(Total + "\n");

        System.out.println("---- Your Receipt ----");
        System.out.println(" Pizza Type: " + pizzaType);
        System.out.println(" Pizza Size: " + pizzaSize);
        System.out.println(" Quantity: " + pizzaQuantity);
        System.out.println("Pre-Tax Total: $" + String.format("%.2f", PreTaxTotal));
        System.out.println("Tax (10%): $" + String.format("%.2f", tax));
        System.out.println("Total Amount Due: $" + String.format("%.2f", Total));
        System.out.println("----------------------\n");

        RecentOrderExists = true;
        // need to impletment actual code
    }


    public static void CompareTwoOrders(Scanner sc) {
        System.out.println("Comparing two orders...\n");
        
        // need to impletment actual code
    }

    public static void SimulateDailySpecial(Scanner sc) {
        System.out.println("Simulating Daily Special...\n");
        // need to impletment actual code
    }

    public static void ViewDailySalesReport() {
        System.out.println("Viewing Daily Sales Report...");
        // need to impletment actual code
    }

    public static void ViewSalesStatistics() {
        System.out.println("Viewing Sales Statistics...\n");
        // need to impletment actual code
    }

    public static void ModifyPreviousOrder(Scanner sc) {
        if (!RecentOrderExists) {
            System.out.println("No recent order to modify.\n");
            return;
        }
        System.out.println("Modifying the previous order...\n");
        // need to impletment actual code
    }

    public static void CancelPreviousOrder() {
        if (!RecentOrderExists) {
            System.out.println("No recent order to cancel.\n");
            return;
        }
        System.out.println("Cancelling the previous order...");
        // need to impletment actual code
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
