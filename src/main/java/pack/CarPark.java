package pack;

import exeption.WrongIdException;
import list.CarParkList;

import java.util.*;

class CarPark implements Iterable<Car> {
    private CarParkList cars;

    public CarPark() {
        this.cars = new CarParkList();
    }

    public CarPark(ArrayList<Car> cars){
        checkUniq(cars);
        this.cars =  new CarParkList(cars);
    }

    private boolean checkUniq(ArrayList<Car> cars){
        int step = 1;
        String firstId;
        String secondId;
        for (Car car: cars){
            firstId = car.getId();
            for (int i = step;i < cars.size(); i++){
                secondId = cars.get(i).getId();
                if(firstId.equals(secondId)){
                    new WrongIdException(secondId).printStackTrace();
                    System.exit(1);
                }
            }
            step++;
        }
        return true;
    }

    public ArrayList<Car> findBySpeed(double min, double max){

        return findBySpeed(min, max, 1);
    }

    public ArrayList<Car> findBySpeed(double min, double max, int quantity){
        ArrayList<Car> findResultList = new ArrayList<>();
        Car tempCar;
        double tempSpeed;
        if(quantity > cars.size()){
            quantity = cars.size();
        }else if(quantity <= 0){
            return findResultList;
        }
        for(Car car: cars){
            tempSpeed = car.getSpeed();
            if(tempSpeed >= min && tempSpeed <= max){
                findResultList.add(car);
                if(findResultList.size() == quantity){
                    return findResultList;
                }
            }
        }
        return findResultList;
    }



    public double countTotalCost(){
        double totalCost = 0.0;

        for(Car car: cars){
            totalCost += car.getPrice();
        }
        return totalCost;
    }

    public boolean addVehicle(Car vehicle){
        String id = vehicle.getId();
            for (Car car : cars) {
                if (car.getId().equals(id)) {
                    new WrongIdException(id).printStackTrace();
                    System.exit(1);
                }
            }

        return cars.add(vehicle);
    }

    public void deleteVehicle(Car car){
        cars.remove(car);
    }
    public void deleteVehicle(int index){
        cars.remove(index);
    }
    public void sortByFuelConsumption(){
        Comparator<Car> compareRule =  new CompareByFuelConsumption();
        Collections.sort(cars, compareRule);
    }
    @Override
    public String toString() {
        String output = "Car park contains:\n";
        for(Car car: cars){
            output += car.toString();
        }
        return output;
    }

    public void testSublist() {
        for (Car car: cars){
            System.out.println(car);
        }

        System.out.println("\nТестируем sublist:");
        List<Car> sublist = cars.subList(1,5);
        for(Car car: sublist){
            System.out.println(car);
        }
        Car replace = new Pickup(0,0,"","",0,0,0, "");
        sublist.set(0, replace);
        System.out.println("\n\nЗамена елемента sublist-а и вывод rootList");
        for (Car car: cars){
            System.out.println(car);
        }
    }

    @Override
    public Iterator<Car> iterator() {
        return cars.iterator();
    }
}
