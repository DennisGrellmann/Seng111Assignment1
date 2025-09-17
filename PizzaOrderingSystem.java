import java.util.scanner;
public class PizzaOrderingSystem {
    // Classes varailbes 
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
    private static int ExtraCheeseCount = 0;, ExtraOlivesCount = 0;
    private static int DrinkSideCount = 0;, GarlicBreadSideCount = 0;

}