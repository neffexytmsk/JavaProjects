package Hotel;

public class StandardRoom extends Room {
    public StandardRoom(int id, String codeNumbers, int numberPeople, double price) {
        super(id, codeNumbers, numberPeople, price);
        this.comfortType = "Стандарт";
    }

    @Override
    public String toString() {
        return String.format("""
            ==============================
            Стандартная комната
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
