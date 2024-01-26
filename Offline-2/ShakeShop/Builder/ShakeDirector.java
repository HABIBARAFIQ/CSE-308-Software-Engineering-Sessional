package Builder;

import Allshakes.Shake;

public class ShakeDirector {
    public Shake constructShake(ShakeBuilder builder) {
        return builder.
                addChocolateIceCream().
                addChocolateSyrup().
                addCoffee().
                addJello().
                addMilk().
                addStrawberryIceCream().
                addStrawberrySyrup().
                addSugar().
                addSugarFreeJello().
                addSweetener().
                addVanillaFlavoring().build();
    }
}
