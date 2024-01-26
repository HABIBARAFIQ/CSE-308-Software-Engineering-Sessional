package Builder;


import Allshakes.Shake;
import Allshakes.StrawberryShake;

import java.net.http.WebSocket;

public class StrawberryShakeBuilder implements ShakeBuilder {
    private StrawberryShake strawberryShake;

    public StrawberryShakeBuilder() {
        strawberryShake = new StrawberryShake();
    }
    public Shake build() {
        return strawberryShake;
    }
    @Override
    public ShakeBuilder addMilk() {
        strawberryShake.addIngredient("Milk");
        return this;
    }

    @Override
    public ShakeBuilder addSugar() {
        strawberryShake.addIngredient("Sugar");
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
        return this;
    }

    @Override
    public ShakeBuilder addStrawberrySyrup() {
        strawberryShake.addIngredient("Strawberry Syrup");
        return this;
    }

    @Override
    public ShakeBuilder addStrawberryIceCream() {
        strawberryShake.addIngredient("Strawberry ice cream");
        return this;
    }

}
