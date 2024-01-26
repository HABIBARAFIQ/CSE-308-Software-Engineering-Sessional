package Builder;

import Allshakes.Shake;

public interface ShakeBuilder {
    Shake build();

    ShakeBuilder addMilk();
    ShakeBuilder addSugar();
    ShakeBuilder addChocolateSyrup();
    ShakeBuilder addChocolateIceCream();
    ShakeBuilder addSweetener();
    ShakeBuilder addVanillaFlavoring();
    ShakeBuilder addSugarFreeJello();
    ShakeBuilder addCoffee();
    ShakeBuilder addJello();
    ShakeBuilder addStrawberrySyrup();
    ShakeBuilder addStrawberryIceCream();
}
