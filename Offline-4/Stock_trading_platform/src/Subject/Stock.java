package Subject;

import Observer.Observer;

import java.io.IOException;
import java.util.ArrayList;

public class Stock implements Subject{
    private String stock_name;
    private int count;
    private float prices;
    private ArrayList observers;

    public Stock(String name,int count,float prices) {
        this.stock_name=name;
        this.count=count;
        this.prices=prices;
        observers=new ArrayList<Observer>();
    }
    public void Increase_price(float increased_amount)
    {
        prices+=increased_amount;
    }
    public void Decrease_price(float decreased_amount)
    {
        prices+=decreased_amount;
    }
    public void Change_in_count(int count){
        this.count=count;
    }

    public String getStock_name() {
        return stock_name;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    public boolean isObserverRegistered(Observer observer) {
        return observers.contains(observer);
    }
    @Override
    public void notifyObservers(String ob) {
        for(int i=0;i<observers.size();i++)
        {
            Observer observer = (Observer)observers.get(i);
            try {
                observer.update(ob);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void unregisterObserver(Observer observer) {
        int index = observers.indexOf(observer);
        if(index>=0)
        {
            observers.remove(index);
        }
    }
    public String toString() {
        return "Stock{" +
                "name='" + stock_name + '\'' +
                ", count=" + count +
                ", price=" + prices +
                '}';
    }
}
