package Builder;

import Allshakes.Shake;
import Allshakes.ZeroShake;

public class ZeroShakeBuilder implements ShakeBuilder{
    private ZeroShake zeroShake;

    public ZeroShakeBuilder() {
        zeroShake = new ZeroShake();
    }
    public Shake build() {
        return zeroShake;
    }
    @Override
    public ShakeBuilder addMilk() {
        zeroShake.addIngredient("Milk");
        return this;
    }

    @Override
    public ShakeBuilder addSugar() {
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
        zeroShake.addIngredient("Sweetener");
        return this;
    }

    @Override
    public ShakeBuilder addVanillaFlavoring() {
        // Not applicable for Strawberry Shake
        zeroShake.addIngredient("Vanilla Flavoring");
        return this;
    }

    @Override
    public ShakeBuilder addSugarFreeJello() {
        // Not applicable for Strawberry Shake
        zeroShake.addIngredient("Sugar free jello");
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
