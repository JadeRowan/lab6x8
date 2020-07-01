package pack;

class Minibus extends Car{

    public Minibus(int speed, double price, String name, String color, double weight, double fuelConsumption, String id) {
        super(speed, price, name, color, weight, fuelConsumption, id);
    }

    @Override
    public String toString() {
        return "Minibus-"+super.toString()+"}\n";
    }
}
