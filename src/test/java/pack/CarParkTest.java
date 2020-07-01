package pack;

import exeption.WrongIdException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

public class CarParkTest{
    CarPark testList;
    Car cOne;
    Car cTwo;
    Car cThree;
    Car cFour;
    Car cFive;
    Car cSix;
    Car cSeven;
    {
        testList = new CarPark();
        cOne = new Car(40, 1, "Test", "Test", 8, 21,
                "AA2314SA");
        testList.addVehicle(cOne);
        cTwo = new Car(130, 2, "Test", "Test", 9, 19,
                "AA2354SA");
        testList.addVehicle(cTwo);
        cThree = new Car(50, 3, "Test", "Test", 10, 20,
                "AA8314SA");
        testList.addVehicle(cThree);
        cFour = new Car(70, 4, "Test", "Test", 11, 18,
                "AA2317SA");
        testList.addVehicle(cFour);
        cFive = new Car(10, 5, "Test", "Test", 12, 16,
                "DA9023SA");
        testList.addVehicle(cFive);
        cSix = new Car(60, 6, "Test", "Test", 13, 17,
                "AA2764SA");
        testList.addVehicle(cSix);
        cSeven = new Car(50, 7, "Test", "Test", 14, 15,
                "FA9313CA");
        testList.addVehicle(cSeven);
    }
    @Test
    public void findBySpeed() {
        ArrayList<Car> resultList = testList.findBySpeed(40, 60, 3);
        assertEquals(resultList.size(), 3);
        assertEquals(resultList.get(0), cOne);
        assertEquals(resultList.get(1), cThree);
        assertEquals(resultList.get(2), cSix);
    }

    @Test
    public void countTotalCost() {
        double totalCost = 0.0;
        for(Car car:testList) {
            totalCost += car.getPrice();
        }
        assertEquals(totalCost, testList.countTotalCost(), 0.001);
    }


    @Test
    public void deleteVehicle() {
        //Description in CarParkListTest.remove(Car car)
    }

    @Test
    public void sortByFuelConsumption() {
        testList.sortByFuelConsumption();
        Iterator<Car> iter = testList.iterator();
        double previous = -1;
        double currentFuelConsumption;
        while (iter.hasNext()){
            currentFuelConsumption = iter.next().getFuelConsumption();
            assertTrue(currentFuelConsumption >= previous);
            previous = currentFuelConsumption;
        }
    }

}
