package pack;

class Pickup extends Car{
    private double bodySize;

    public Pickup(int speed, double price, String name, String color, double weight, double fuelConsumption,
                  double bodySize, String id) {
        super(speed, price, name, color, weight, fuelConsumption, id);
        this.bodySize = bodySize;
    }

    @Override
    public String toString() {
        return "Pickup-"+super.toString() +
                ", bodySize=" + bodySize +
                "}\n";
    }
}
