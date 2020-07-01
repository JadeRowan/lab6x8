package list;

import org.junit.Test;
import pack.Car;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CarParkListTest {
    CarParkList testList;
    Car cOne;
    Car cTwo;
    Car cThree;
    Car cFour;
    Car cFive;
    Car cSix;
    Car cSeven;
    Car cEight;
    Car cNine;
    Car cTen;
    Car cEleven;
    {
        testList = new CarParkList();
        cOne = new Car(40, 1, "Test", "Test", 8, 21,
                "AA2154VA");
        testList.add(cOne);
        cTwo = new Car(130, 2, "Test", "Test", 9, 19,
                "FD1412UD");
        testList.add(cTwo);
        cThree = new Car(50, 3, "Test", "Test", 10, 20,
                "KL4117PL");
        testList.add(cThree);
        cFour = new Car(70, 4, "Test", "Test", 11, 18,
                "OP1235SA");
        testList.add(cFour);
        cFive = new Car(10, 5, "Test", "Test", 12, 16,
                "DF8848LG");
        testList.add(cFive);
        cSix = new Car(60, 6, "Test", "Test", 13, 17,
                "OG8294KG");
        testList.add(cSix);
        cSeven = new Car(50, 7, "Test", "Test", 14, 15,
                "KB7294IV");
        testList.add(cSeven);
    }
    @Test
    public void size() {
        int size = 0;
        for(Car car: testList){
            if(car != null){
                size++;
            }
        }
        assertEquals(size, testList.size());
    }

    @Test
    public void isEmpty() {
        boolean isEmpty = testList.size() == 0;
        assertEquals(testList.isEmpty(), isEmpty);
        assertFalse(new CarParkList().isEmpty());
    }

    @Test
    public void contains() {
        boolean contains = false;
        Car needed = cSix;
        for(Car car: testList){
            if(car.equals(needed)){
                contains = true;
                break;
            }
        }
        assertEquals(contains, testList.contains(needed));
    }

    @Test
    public void add() {
        cEight = new Car (1,2 ,"New","New",5,6, "SD1235DS");
        boolean previousContains = testList.contains(cEight);
        int previousSize = testList.size();
        assertTrue(testList.add(cEight));
        assertNotEquals(previousContains, testList.contains(cEight));
        assertEquals(previousSize + 1, testList.size() );
    }

    @Test
    public void remove() {
        Car delElement = cFour;
        boolean previousContains = testList.contains(delElement);
        int previousSize = testList.size();
        assertTrue(testList.remove(delElement));
        assertNotEquals(previousContains, testList.contains(delElement));
        assertEquals(previousSize - 1, testList.size());
    }

    @Test
    public void testRemove() {
        int delIndex = 1;
        Car delElement = testList.get(delIndex);
        int previousSize = testList.size();
        assertEquals(delElement, testList.remove(delIndex));
        assertEquals(previousSize - 1, testList.size());
        assertFalse(testList.contains(delElement));
    }

    @Test
    public void get() {
        int size = testList.size();
        Car testElement = cFive;
        testList.add(testElement);
        assertEquals(testList.get(size), testElement);
    }

    @Test
    public void set() {
        Car newElement = cSeven;
        int previousSize = testList.size();
        testList.set(5, newElement);
        assertEquals(newElement, testList.get(5));
        assertEquals(previousSize, testList.size());
    }

    @Test
    public void indexOf() {
        cNine = new Car(6,5, "Test", "Test", 2, 1, "AF3919KK");
        testList.add(cNine);
        assertEquals(testList.size()-1, testList.indexOf(cNine));
        Car secondElement = testList.get(1);
        assertEquals(testList.indexOf(secondElement), 1);
    }

    @Test
    public void addAll() {
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(cNine = new Car(9,9, "Test", "Test", 9,9,
                "AS9348DD"));
        cars.add(cTen = new Car(10,10, "Test", "Test", 10,10,
                "SA9134FA"));
        cars.add(cEleven = new Car(11,11, "Test", "Test", 11,11,
                "FA9231FA"));
        int previousSize = testList.size();
        for(Car car: cars){
            assertFalse(testList.contains(car));
        }
        assertTrue(testList.addAll(cars));
        assertEquals(previousSize + cars.size(), testList.size());
        for (Car car: cars){
            assertTrue(testList.contains(car));
        }

    }

    @Test
    public void removeAll() {
        ArrayList<Car> removeList = new ArrayList<>();
        removeList.add(cOne);
        removeList.add(cFive);
        removeList.add(cEight);
        int previousSize = testList.size();
        int modSize = 0;
        for (Car car:removeList) {
            if (testList.contains(car)){
                modSize++;
            }
        }
        testList.removeAll(removeList);
        for(Car car: removeList){
            assertFalse(testList.contains(car));
        }
        assertEquals(previousSize - modSize, testList.size());

    }

    @Test
    public void retainAll() {
        ArrayList<Car> retainList = new ArrayList<>();
        retainList.add(cNine);
        retainList.add(cTen);
        retainList.add(cEleven);
        testList.retainAll(retainList);
        for (Car car: testList){
            assertTrue(retainList.contains  (car));
        }
    }

    @Test
    public void clear() {
        testList.clear();
        assertEquals(testList, new CarParkList());
    }
}