package Builder;

import Allshakes.CoffeeShake;
import Allshakes.Shake;

public class CoffeeShakeBuilder implements ShakeBuilder{
    private CoffeeShake coffeeShake;

    public CoffeeShakeBuilder() {
        coffeeShake = new CoffeeShake();
    }
    public Shake build() {
        return coffeeShake;
    }

    @Override
    public ShakeBuilder addMilk() {
        coffeeShake.addIngredient("Milk");
        return this;
    }

    @Override
    public ShakeBuilder addSugar() {
        coffeeShake.addIngredient("Sugar");
        return this;
    }

    @Override
    public ShakeBuilder addChocolateSyrup() {
        return this;
    }

    @Override
    public ShakeBuilder addChocolateIceCream() {
        return this;
    }

    @Override
    public ShakeBuilder addSweetener() {
        // Not applicable for Coffee Shake
        return this;
    }

    @Override
    public ShakeBuilder addVanillaFlavoring() {
        // Not applicable for Coffee Shake
        return this;
    }

    @Override
    public ShakeBuilder addSugarFreeJello() {
        // Not applicable for Coffee Shake
        return this;
    }

    @Override
    public ShakeBuilder addCoffee() {
        coffeeShake.addIngredient("Coffee");
        return this;
    }

    @Override
    public ShakeBuilder addJello() {
        coffeeShake.addIngredient("Jello");
        return this;
    }

    @Override
    public ShakeBuilder addStrawberrySyrup() {
        return this;
    }

    @Override
    public ShakeBuilder addStrawberryIceCream() {
        return this;
    }
}
