package pack;

class Trolleybus extends PublicTransport{

    public Trolleybus(int speed, double price, String name, String color, double weight, double fuelConsumption,
                      String id, int seatsNumber, double length, double height, int doubleDoorsNumber) {
        super(speed, price, name, color, weight, fuelConsumption, id, seatsNumber, length, height, doubleDoorsNumber);
    }

    @Override
    public String toString() {
        return "Trolleybus-"+ super.toString();
    }
}
