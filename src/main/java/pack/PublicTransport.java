package pack;

class PublicTransport extends Car{
    private int seatsNumber;
    private double length;
    private double height;
    private int doubleDoorsNumber;

    public PublicTransport(int speed, double price, String name, String color, double weight, double fuelConsumption,
                           String id, int seatsNumber, double length, double height, int doubleDoorsNumber) {
        super(speed, price, name, color, weight, fuelConsumption, id);
        this.seatsNumber = seatsNumber;
        this.length = length;
        this.height = height;
        this.doubleDoorsNumber = doubleDoorsNumber;
    }

    @Override
    public String toString() {
        return "PublicTransport-" + super.toString() +
                ", seatsNumber=" + seatsNumber +
                ", length=" + length +
                ", height=" + height +
                ", doubleDoorsNumber=" + doubleDoorsNumber +
                '}' + "\n";
    }
}
