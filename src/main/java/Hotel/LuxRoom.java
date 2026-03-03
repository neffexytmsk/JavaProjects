package Hotel;

public class LuxRoom extends Room {
    private int minRentalDays;
    private int maxRentalDays;

    public LuxRoom(int id, String codeNumbers, int numberPeople, double price,
                   int minRentalDays, int maxRentalDays) {
        super(id, codeNumbers, numberPeople, price);
        this.comfortType = "Люкс";
        this.minRentalDays = minRentalDays;
        this.maxRentalDays = maxRentalDays;
    }

    public int getMinRentalDays() { return minRentalDays; }
    public int getMaxRentalDays() { return maxRentalDays; }

    @Override
    public String toString() {
        return String.format("""
            ==============================
            Комната Люкс
            ==============================
            ID: %d
            Номер: %s
            Количество мест: %d
            Комфортность: %s
            Цена за сутки: %.0f ₽
            Мин. срок аренды: %d дней
            Макс. срок аренды: %d дней
            ==============================
            """, id, codeNumbers, numberPeople, comfortType, price,
                minRentalDays, maxRentalDays);
    }
}