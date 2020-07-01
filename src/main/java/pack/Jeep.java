package pack;

class Jeep extends Car{

    public Jeep(int speed, double price, String name, String color, double weight, double fuelConsumption, String id) {
        super(speed, price, name, color, weight, fuelConsumption, id);
    }

    @Override
    public String toString() {
        return "Jeep-"+super.toString()+"}\n";
    }

}
