package Allshakes;

public interface Shake {
    void addIngredient(String ingredient);
    double calculatePrice();
    void displayShake();
    double getBasePrice();
}
