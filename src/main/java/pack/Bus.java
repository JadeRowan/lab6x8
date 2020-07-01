package pack;

class Bus extends PublicTransport{

    public Bus(int speed, double price, String name, String color, double weight, double fuelConsumption, String id,
               int seatsNumber, double length, double height, int doubleDoorsNumber) {
        super(speed, price, name, color, weight, fuelConsumption, id, seatsNumber, length, height, doubleDoorsNumber);
    }

    @Override
    public String toString() {
        return "Bus-"+ super.toString();
    }
}
