package pack;

import java.util.Comparator;

class CompareByFuelConsumption implements Comparator<Car> {

    public int compare(Car a, Car b) {
        if (a.getFuelConsumption() > b.getFuelConsumption())
            return 1;
        else if (a.getFuelConsumption() < b.getFuelConsumption())
            return -1;
        else
            return 0;
    }
}
