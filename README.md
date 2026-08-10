# ShoppingCart

A simple Java-based shopping cart application that reads item entries from the console, applies tax rules, and prints a receipt.

## Overview

This project demonstrates:

- console input parsing for items
- tax and exemption configuration via properties files
- logging configuration via `application.config`
- receipt generation with total cost and total tax

## Configuration Files

The application uses three configuration files located in the project root:

- `application.config`

  - controls the application log level
  - used by `ShoppingCart#getLogLevel()`
  - example property: `logger.level=INFO`
- `shoppingcart.properties`

  - defines tax-exempt item categories
  - used by `ShoppingCart#readShoppingCart()` and `ItemCollection`
  - example entry: `MEDICINES=pills,syrup,capsule`
- `tax.properties`

  - defines tax rates used by `ReadTaxRates`
  - example entries:
    - `BASIC_TAX=0.1`
    - `IMPORT_TAX=0.05`
    - `LUXURY=0.15`
    - `ROUND_UPTO=0.05`

## How It Works

1. The application reads `application.config` to set the Java logging level.
2. It loads `shoppingcart.properties` to determine which item names are tax exempt.
3. It loads `tax.properties` to configure the applicable tax rates.
4. The user enters items in the format:
   - `<quantity> <item name> at <price per unit>`
   - example: `1 imported box of chocolates at 10.00`
5. The app calculates taxes and prints the receipt.

## Running the Application

Open a terminal in the project root directory and make sure you are working from that directory before compiling or running the Java classes.

The project root contains the source tree, configuration files, and the JUnit dependency jars under `lib/`.

Example command line from the project root:

```bash
javac -cp "lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" -d bin $(find src -name '*.java')
java -cp bin ShoppingCart
```

If you run the compile command from a different directory, the compiler may not resolve the JUnit test source dependency and you could see an error such as:

```text
error: package junit.framework does not exist
import junit.framework.TestCase;
```

Always navigate to the folder containing `src/`, `lib/`, `application.config`, `shoppingcart.properties`, and `tax.properties` before running the Java compilation command.

If you have a JAR file for the project, you can also run the application with:

```bash
java -jar ShoppingCart.jar
```

Before running the JAR, you can update `application.config`, `shoppingcart.properties`, and `tax.properties` to change the logging level, tax exemptions, and tax rates.

Then enter items one by one and type `done` or press ENTER on an empty line to finish.

## Notes

- `application.config` is specifically for the log level.
- `shoppingcart.properties` lists items that are tax exempt.
- `tax.properties` configures the tax rates the application uses.

## Project Structure

- `src/` - Java source files
- `bin/` - compiled output
- `lib/` - external libraries (if any)
- `application.config`, `shoppingcart.properties`, `tax.properties` - runtime configuration files
