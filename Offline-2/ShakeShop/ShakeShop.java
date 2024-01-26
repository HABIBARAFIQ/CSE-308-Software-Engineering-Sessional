import Allshakes.ChocolateShake;
import Allshakes.Order;
import Allshakes.Shake;
import Builder.*;

import java.util.Scanner;

import static Allshakes.Allpricesandtype.*;

public class ShakeShop {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in).useDelimiter("\n");
            Order currentOrder = null;
            ShakeDirector shakeDirector = new ShakeDirector();

            while (true) {
                System.out.print("Press 'o' to open an order, 'e' to close the order, or 'q' to quit: ");
                char userInput = scanner.next().charAt(0);

                if (userInput == 'q') {
                    break;
                }

                if (userInput == 'o') {
                    if (currentOrder != null) {
                        System.out.print("Error: An order is already open. Do you want to include something else in the current order? (y/n): ");
                        char includeInCurrentOrder = scanner.next().charAt(0);
                        if (includeInCurrentOrder == 'n') {
                            continue;
                        }
                    }
                    currentOrder = new Order();
                    System.out.println("Order opened.");
                } else if (userInput == 'e') {
                    if (currentOrder == null ){
                        System.out.println("Error: No order is currently open . Please open an order first.");
                    } else if (currentOrder.shakes.isEmpty() ) {
                        System.out.println("There are no shakes in the order. Choose any shakes");
                    } else {
                        currentOrder.displayOrderSummary();
                        System.out.println("Order closed.");
                        currentOrder = null;
                    }
                } if (currentOrder != null) {
                    while (true){
                    System.out.println("Available shakes: Chocolate Shake, Coffee Shake, Strawberry Shake, Vanilla Shake, Zero Shake");
                    System.out.print("Enter the name of the shake you want to order(or done to finish or open an order): ");
                    String shakeName = scanner.next();
                    if ("done".equalsIgnoreCase(shakeName)) {
                        break;  // Exit the loop and proceed to closing the order
                    }
                    if("open".equalsIgnoreCase(shakeName))
                    {
                        if (currentOrder != null) {
                            System.out.print("Error: An order is already open. Do you want to include something else in the current order? (y/n): ");
                            char includeInCurrentOrder = scanner.next().charAt(0);
                            if (includeInCurrentOrder == 'y') {
                                System.out.println("Available shakes: Chocolate Shake, Coffee Shake, Strawberry Shake, Vanilla Shake, Zero Shake");
                                System.out.println("Enter the name of the shake you want to order: ");
                                shakeName = scanner.next();
                            } else if (includeInCurrentOrder=='n') {
                                System.out.println("Order closed.");
                                currentOrder=null;
                                break;
                            }
                        }
                    }
                    System.out.println(shakeName);
                    ShakeBuilder shakeBuilder = createShakeBuilder(shakeName);
                    Shake shake = shakeDirector.constructShake(shakeBuilder);

                    System.out.print("Do you want to make it lactose-free? (y/n): ");
                    if (scanner.next().charAt(0) == 'y') {
                        shake.addIngredient(Almond);
                    }

                    System.out.print("Do you want to add candy on top? (y/n): ");
                    if (scanner.next().charAt(0) == 'y') {
                        shake.addIngredient(Candy);
                    }

                    System.out.print("Do you want to add cookies on top? (y/n): ");
                    if (scanner.next().charAt(0) == 'y') {
                        shake.addIngredient(Cookies);
                    }

                    currentOrder.addShake(shake);
                    System.out.println("Shake added to the order.");
                }}// else {
                   // System.out.println("Error: No order is currently open. Please open an order first.");
                //}
                else if(userInput!='e'||userInput!='o'||userInput!='q')
                {
                    System.out.println("Wrong parameters");
                }
            }

            scanner.close();
        }


        private static ShakeBuilder createShakeBuilder(String shakeName) {
            String trimmedName = shakeName.replaceAll("\\s", "");
            System.out.println(trimmedName);
                if("chocolateshake".equals(trimmedName.toLowerCase()))
                    return new ChocolateShakeBuilder();
                else if ("coffeeshake".equals(trimmedName.toLowerCase())) {
                    return new CoffeeShakeBuilder();
                } else if ("strawberryshake".equals(trimmedName.toLowerCase())) {
                    return new StrawberryShakeBuilder();
                } else if ("vanillashake".equals(trimmedName.toLowerCase())) {
                    return new VanillaShakeBuilder();
                } else if ("zeroshake".equals(trimmedName.toLowerCase())) {
                    return new ZeroShakeBuilder();
                }
                else{
                    System.out.println("Error: Invalid shake name.");
                    return null;
            }
        }
    }
