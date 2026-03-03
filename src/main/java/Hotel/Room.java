package Hotel;

public abstract class Room {
    protected int id;
    protected String codeNumbers;
    protected int numberPeople;
    protected String comfortType;
    protected double price;

    public Room(int id, String codeNumbers, int numberPeople, double price) {
        this.id = id;
        this.codeNumbers = codeNumbers;
        this.numberPeople = numberPeople;
        this.price = price;
    }

    public int getId() { return id; }
    public String getCodeNumbers() { return codeNumbers; }
    public int getNumberPeople() { return numberPeople; }
    public String getComfortType() { return comfortType; }
    public double getPrice() { return price; }

    @Override
    public abstract String toString();

    public static class PriceComparator implements java.util.Comparator<Room> {
        @Override
        public int compare(Room r1, Room r2) {
            return Double.compare(r1.getPrice(), r2.getPrice());
        }
    }

    public static class PeopleComparator implements java.util.Comparator<Room> {
        @Override
        public int compare(Room r1, Room r2) {
            return Integer.compare(r1.getNumberPeople(), r2.getNumberPeople());
        }
    }
}