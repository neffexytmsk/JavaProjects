package Hotel;

public class SemiLuxRoom extends Room {
    public SemiLuxRoom(int id, String codeNumbers, int numberPeople, double price) {
        super(id, codeNumbers, numberPeople, price);
        this.comfortType = "Полулюкс";
    }

    @Override
    public String toString() {
        return String.format("""
            ==============================
            Комната Полулюкс
            ==============================
            ID: %d
            Номер: %s
            Количество мест: %d
            Комфортность: %s
            Цена за сутки: %.0f ₽
            ==============================
            """, id, codeNumbers, numberPeople, comfortType, price);
    }
}