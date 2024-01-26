package Allshakes;

import java.util.ArrayList;
import java.util.List;

import static Allshakes.Allpricesandtype.*;

public class VanillaShake implements Shake{
    private double basePrice ;
    private List<String> ingredients = new ArrayList<>();

    public VanillaShake() {
        basePrice = Vanilla_Shake;
    }

    @Override
    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    @Override
    public double calculatePrice() {
        double total = basePrice;
        for (String ingredient : ingredients) {
            double price=getPriceForIngredient(ingredient);
            switch (ingredient){
                case Almond:
                {
                    System.out.println("Lactose-free :"+price);
                    break;
                }
                case Candy:{
                    System.out.println("Candy :"+price);
                    break;
                }
                case Cookies:{
                    System.out.println("Cookies :"+price);
                    break;
                }
                default:break;
            }
            total += price;
        }
        return total;
    }

    private double getPriceForIngredient(String ingredient) {
        return switch (ingredient) {
            case Almond -> lactosefree;
            case Candy -> candyontop;
            case Cookies -> cookiesontop;
            default -> 0;
        };
    }

    @Override
    public void displayShake() {
        System.out.println("Vanilla Shake");
        System.out.println("Base Price: Tk " + basePrice);
        if (!ingredients.isEmpty()) {
            System.out.println("Ingredients: " + String.join(", ", ingredients));
        }
    }

    @Override
    public double getBasePrice() {
        return basePrice;
    }

}
