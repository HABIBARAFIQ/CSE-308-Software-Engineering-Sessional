package Allshakes;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public List<Shake> shakes;

    public Order() {
        this.shakes = new ArrayList<>();
        System.out.println("Hello");
    }

    public void addShake(Shake shake) {
        shakes.add(shake);
    }

    public void displayOrderSummary() {
        double totalOrderPrice = 0;
        for (Shake shake : shakes) {
            shake.displayShake();
            double calculated_price=shake.calculatePrice();
            totalOrderPrice += calculated_price;
            if(calculated_price>shake.getBasePrice())
                System.out.println("Price has increased for customization");
        }

        System.out.println("Total Order Price: Tk " + totalOrderPrice);
    }
}
