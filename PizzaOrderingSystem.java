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
        System.out.println(">-----------------------------------<");
    }

    //Pizza Menu
    public static void DisplayPizzaMenu() {
        System.out.println("Welcome to the Pizza Selection Menu!");
        System.out.println(">-----------------------------------<");
        System.out.println("1. Margherita Pizza");
    }
}