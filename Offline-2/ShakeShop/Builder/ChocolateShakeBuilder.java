package Builder;

import Allshakes.ChocolateShake;
import Allshakes.Shake;

public class ChocolateShakeBuilder implements ShakeBuilder{
    private ChocolateShake chocolateShake;

    public ChocolateShakeBuilder() {
        chocolateShake = new ChocolateShake();
    }
    public Shake build() {
        return chocolateShake;
    }

    @Override
    public ShakeBuilder addMilk() {
        chocolateShake.addIngredient("Milk");
        return this;
    }

    @Override
    public ShakeBuilder addSugar() {
        chocolateShake.addIngredient("Sugar");
        return this;
    }

    @Override
    public ShakeBuilder addChocolateSyrup() {
        chocolateShake.addIngredient("Chocolate syrup");
        return this;
    }

    @Override
    public ShakeBuilder addChocolateIceCream() {
        chocolateShake.addIngredient("Chocolate ice cream");
        return this;
    }

    @Override
    public ShakeBuilder addSweetener() {
        // Not applicable for Chocolate Shake
        return this;
    }

    @Override
    public ShakeBuilder addVanillaFlavoring() {
        // Not applicable for Chocolate Shake
        return this;
    }

    @Override
    public ShakeBuilder addSugarFreeJello() {
        // Not applicable for Chocolate Shake
        return this;
    }

    @Override
    public ShakeBuilder addCoffee() {
        return this;
    }

    @Override
    public ShakeBuilder addJello() {
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
