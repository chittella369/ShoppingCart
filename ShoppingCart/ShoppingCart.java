import java.util.Scanner;
import java.util.Set;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.List;
import java.util.ArrayList;

import com.shoppingcart.util.ItemCollection;
import com.shoppingcart.billing.BillingImpl;
import com.shoppingcart.item.ShoppedItem;

/**
 * @author Aditya
 */

public class ShoppingCart {
    
    private static final Pattern ITEM_PATTERN = Pattern.compile("^(\\d+)\\s+(.*?)\\s+at\\s+(\\d+(?:\\.\\d+)?)$");

    public static Logger logger = Logger.getLogger(ShoppingCart.class.getName());

    public static void main(String[] args) throws Exception {

        ShoppingCart shoppingcart = new ShoppingCart();

        shoppingcart.getLogLevel();
        shoppingcart.readShoppingCart();
        Scanner scanner = new Scanner(System.in);

        List<String> cart = new ArrayList<>();
        List<String[]> parsedTokensList = new ArrayList<>();

        System.out.println("==================================================");
        System.out.println("               ITEM INPUT SYSTEM                  ");
        System.out.println("==================================================");
        System.out.println("Enter items in the format: '<quantity> <name> at <price per unit>'");
        System.out.println("Example: 1 imported box of chocolates at 10.00");
        System.out.println("Type 'done' or press ENTER on an empty line to finish.\n");

        while (true) {
            System.out.print("Enter item: ");
            String input = scanner.nextLine().trim();

            // Exit condition
            if (input.isEmpty() || input.equalsIgnoreCase("done")) {
                break;
            }
            // 2. Parse & Validate Input Format
            Matcher matcher = ITEM_PATTERN.matcher(input);
            if (matcher.matches()) {
                // Store the original line
                cart.add(input);

                // Extract parsed tokens: [quantity, description, price]
                String[] tokens = new String[] {
                        matcher.group(1), // Quantity
                        matcher.group(2), // Item Name
                        matcher.group(3) // Price
                };
                parsedTokensList.add(tokens);

                System.out.println("   [✓ Added]");
            } else {
                System.out.println("   [✗ Invalid Format] Expected format: '<quantity> <item> at <price per item>'");
            }
        }
        scanner.close();

        // Print final list of items entered
        System.out.println("\n==================================================");
        System.out.println(" ITEMS (" + cart.size() + " total) ");
        System.out.println("==================================================");

        BillingImpl billing = new BillingImpl();
        String[] itemList = new String[cart.size()];

        for (int i = 0; i < cart.size(); i++) {
            itemList[i] = cart.get(i);
            System.out.println(" " + itemList[i]);
        }
        System.out.println("\n==================================================");
        System.out.println("                     RECEIPT                      ");
        System.out.println("==================================================");

        List<ShoppedItem> list = billing.parseAndCalculate(itemList);
        billing.totalCost(list);
        billing.totalTax(list);
        
        billing.print(list, System.out);

    }

    public void readShoppingCart() throws Exception {
        logger.setLevel(Level.INFO);
        Properties prop = new Properties();
        File f = new File("shoppingcart.properties");
        prop.load(new FileInputStream(f));

        Set<Object> set = prop.keySet();
        Iterator<Object> it = set.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String value = prop.getProperty(key);
            System.setProperty(key, value);            
            ItemCollection.htItems.put(key, value);
        }
    }

    public void getLogLevel() throws Exception {
        Properties config = new Properties();

        try (FileInputStream fis = new FileInputStream("application.config")) {
            // 1. Load the file
            config.load(fis);
            // 2. Read the level string
            String levelStr = config.getProperty("logger.level", "INFO");

            // 3. Convert string to JUL Level object and assign it
            Level targetLevel = Level.parse(levelStr.toUpperCase());
            logger.setLevel(targetLevel);

            Logger rootLogger = LogManager.getLogManager().getLogger("");
            if(rootLogger != null){
                rootLogger.setLevel(targetLevel);
            }
            System.out.println("Logger level successfully set to: " + logger.getLevel());

        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Failed to read configuration: " + e.getMessage());
        }

    }
}
