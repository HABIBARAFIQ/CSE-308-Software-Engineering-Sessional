package Builder;

import Allshakes.Shake;
import Allshakes.VanillaShake;

public class VanillaShakeBuilder implements ShakeBuilder{
    private VanillaShake vanillaShake;

    public VanillaShakeBuilder() {
        vanillaShake = new VanillaShake();
    }
    public Shake build() {
        return vanillaShake;
    }
    @Override
    public ShakeBuilder addMilk() {
        vanillaShake.addIngredient("Milk");
        return this;
    }

    @Override
    public ShakeBuilder addSugar() {
        vanillaShake.addIngredient("Sugar");
        return this;
    }

    @Override
    public ShakeBuilder addChocolateSyrup() {
        // Not applicable for Strawberry Shake
        return this;
    }

    @Override
    public ShakeBuilder addChocolateIceCream() {
        // Not applicable for Strawberry Shake
        return this;
    }

    @Override
    public ShakeBuilder addSweetener() {
        // Not applicable for Strawberry Shake
        return this;
    }

    @Override
    public ShakeBuilder addVanillaFlavoring() {
        // Not applicable for Strawberry Shake
        vanillaShake.addIngredient("Vanilla Flavoring");
        return this;
    }

    @Override
    public ShakeBuilder addSugarFreeJello() {
        // Not applicable for Strawberry Shake
        return this;
    }

    @Override
    public ShakeBuilder addCoffee() {
        return this;
    }

    @Override
    public ShakeBuilder addJello() {
        vanillaShake.addIngredient("Jello");
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
