package pack;

public class Car /*implements Comparable<Car>*/ {
    private int speed;
    private double price;
    private String brandName;
    private String color;
    private double weight;
    private double fuelConsumption;
    private String id;


    public Car(int speed, double price, String brandName, String color, double weight, double fuelConsumption, String id) {
        this.speed = speed;
        this.price = price;
        this.brandName = brandName;
        this.color = color;
        this.weight = weight;
        this.fuelConsumption = fuelConsumption;
        this.id = id;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Car{" +
                "speed=" + speed +
                ", price=" + price +
                ", brandName='" + brandName + '\'' +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                ", fuelConsumption=" + fuelConsumption +
                ", id='" + id + '\'';
    }
}
