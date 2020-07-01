package pack;
import java.util.ArrayList;

//C13=9
//Визначити ієрархію легкових автомобілів. Створити таксопарк. Порахувати вартість автопарку.
//Провести сортування автомобілів парку за витратами палива. Знайти автомобіль у компанії,
//що відповідає заданому діапазону швидкості автомобіля.

public class Main {

    public static void main(String[] args) {
        CarPark publicGarage = new CarPark(Main.getUnsortedCarList());
        System.out.println(publicGarage);
        double totalCost = publicGarage.countTotalCost();
        System.out.println("\nОбщая стиомость таксопарка:\n"+totalCost);

        String testFindStroke = publicGarage.findBySpeed(40,55).toString();
        System.out.println("\nВ дипазоне (40, 55) найдено:\n"+testFindStroke);
        String testFindList = publicGarage.findBySpeed(36,80, 5).toString();
        System.out.println("\n5 машин в дипазоне (36, 80) найдено:\n"+testFindList);

        publicGarage.deleteVehicle(publicGarage.findBySpeed(40,55).get(0));
        publicGarage.sortByFuelConsumption();
        System.out.println(publicGarage);
        totalCost = publicGarage.countTotalCost();
        System.out.println("\nОбщая стиомость таксопарка:\n"+totalCost);

        publicGarage.testSublist();
    }

    private static ArrayList<Car> getUnsortedCarList() {
        ArrayList<Car> list = new ArrayList<>();
        list.add( new Pickup(80, 9500.0,"Tayota", "white", 625.1, 321.2,
                120.5,"FA1241FS") );
        list.add( new Jeep(90, 17100.0,"Mazda", "grey", 825.1, 32.2,
                "DF4201MA") );
        list.add( new Minibus(90, 16500.0,"Mazda", "orange", 815.1,
                54.0, "NG0142CL") );
        list.add( new Bus(60, 81000.0,"KievPassTransport", "black", 415.1,
                109.3, "XZ9114JE",16, 80.0,40, 2));
        list.add( new Tram(45, 90000.0,"KievPassTransport", "blue", 425.1,
                80.9, "DF8601MA",14, 60.0,35, 3));
        list.add( new Trolleybus(55, 90000.0,"KievPassTransport", "blue", 425.1,
                80.9, "AJ8193FE",14, 60.0,35, 4));
        list.add( new PublicTransport(55, 90000.0,"KievPassTransport", "blue", 425.1,
                80.9, "KR9424CM",14, 60.0,35, 4));
        return list;
    }
}



