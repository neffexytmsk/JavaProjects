package Hotel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HotelApp extends JFrame {
    private RoomManager roomManager;
    private JTable roomTable;
    private DefaultTableModel tableModel;

    public HotelApp() {
        roomManager = new RoomManager();
        initializeUI();
        loadRoomsIntoTable();
    }

    private void initializeUI() {
        setTitle("Управление гостиничными номерами");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel.setBackground(new Color(245, 245, 245));

        JButton addButton = new JButton("Добавить номер");
        JButton sortPriceButton = new JButton("Сортировать по цене");
        JButton sortPeopleButton = new JButton("Сортировать по местам");
        JButton refreshButton = new JButton("Обновить");

        styleButton(addButton);
        styleButton(sortPriceButton);
        styleButton(sortPeopleButton);
        styleButton(refreshButton);

        addButton.addActionListener(e -> showAddRoomDialog());
        sortPriceButton.addActionListener(e -> sortByPrice());
        sortPeopleButton.addActionListener(e -> sortByPeople());
        refreshButton.addActionListener(e -> loadRoomsIntoTable());

        controlPanel.add(addButton);
        controlPanel.add(sortPriceButton);
        controlPanel.add(sortPeopleButton);
        controlPanel.add(refreshButton);

        String[] columns = {"ID", "Номер", "Тип", "Мест", "Цена (₽)"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        roomTable = new JTable(tableModel);
        roomTable.setRowHeight(30);
        roomTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        roomTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));

        JScrollPane scrollPane = new JScrollPane(roomTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.white);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setFocusPainted(false);
    }

    private void loadRoomsIntoTable() {
        tableModel.setRowCount(0);
        List<Room> rooms = roomManager.getAllRooms();

        for (Room room : rooms) {
            Object[] rowData = new Object[6];
            rowData[0] = room.getId();
            rowData[1] = room.getCodeNumbers();
            rowData[2] = room.getComfortType();
            rowData[3] = room.getNumberPeople();
            rowData[4] = String.format("%.0f ₽", room.getPrice());

            if (room instanceof LuxRoom) {
                LuxRoom luxRoom = (LuxRoom) room;
                rowData[5] = String.format("Аренда: %d-%d дней",
                        luxRoom.getMinRentalDays(), luxRoom.getMaxRentalDays());
            } else {
                rowData[5] = "-";
            }

            tableModel.addRow(rowData);
        }
    }

    private void sortByPrice() {
        tableModel.setRowCount(0);
        List<Room> sortedRooms = roomManager.getRoomsSortedByPrice();

        for (Room room : sortedRooms) {
            Object[] rowData = new Object[6];
            rowData[0] = room.getId();
            rowData[1] = room.getCodeNumbers();
            rowData[2] = room.getComfortType();
            rowData[3] = room.getNumberPeople();
            rowData[4] = String.format("%.0f ₽", room.getPrice());

            if (room instanceof LuxRoom) {
                LuxRoom luxRoom = (LuxRoom) room;
                rowData[5] = String.format("Аренда: %d-%d дней",
                        luxRoom.getMinRentalDays(), luxRoom.getMaxRentalDays());
            } else {
                rowData[5] = "-";
            }

            tableModel.addRow(rowData);
        }
    }

    private void sortByPeople() {
        tableModel.setRowCount(0);
        List<Room> sortedRooms = roomManager.getRoomsSortedByPeople();

        for (Room room : sortedRooms) {
            Object[] rowData = new Object[6];
            rowData[0] = room.getId();
            rowData[1] = room.getCodeNumbers();
            rowData[2] = room.getComfortType();
            rowData[3] = room.getNumberPeople();
            rowData[4] = String.format("%.0f ₽", room.getPrice());

            if (room instanceof LuxRoom) {
                LuxRoom luxRoom = (LuxRoom) room;
                rowData[5] = String.format("Аренда: %d-%d дней",
                        luxRoom.getMinRentalDays(), luxRoom.getMaxRentalDays());
            } else {
                rowData[5] = "-";
            }

            tableModel.addRow(rowData);
        }
    }

    private void showAddRoomDialog() {
        JDialog dialog = new JDialog(this, "Добавить номер", true);
        dialog.setSize(400, 400);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField idField = new JTextField();
        JTextField codeField = new JTextField();
        JTextField peopleField = new JTextField();
        JTextField priceField = new JTextField();

        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Стандарт", "Полулюкс", "Люкс"});
        JTextField minDaysField = new JTextField();
        JTextField maxDaysField = new JTextField();

        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Номер комнаты:"));
        panel.add(codeField);
        panel.add(new JLabel("Количество мест:"));
        panel.add(peopleField);
        panel.add(new JLabel("Цена за сутки:"));
        panel.add(priceField);
        panel.add(new JLabel("Тип комнаты:"));
        panel.add(typeCombo);
        panel.add(new JLabel("Мин. дней аренды:"));
        panel.add(minDaysField);
        panel.add(new JLabel("Макс. дней аренды:"));
        panel.add(maxDaysField);

        JButton saveButton = new JButton("Сохранить");
        JButton cancelButton = new JButton("Отмена");

        saveButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String code = codeField.getText();
                int people = Integer.parseInt(peopleField.getText());
                double price = Double.parseDouble(priceField.getText());
                String type = (String) typeCombo.getSelectedItem();

                Room room;
                switch (type) {
                    case "Люкс":
                        int minDays = Integer.parseInt(minDaysField.getText());
                        int maxDays = Integer.parseInt(maxDaysField.getText());
                        room = new LuxRoom(id, code, people, price, minDays, maxDays);
                        break;
                    case "Полулюкс":
                        room = new SemiLuxRoom(id, code, people, price);
                        break;
                    default:
                        room = new StandardRoom(id, code, people, price);
                }

                roomManager.addRoom(room);
                loadRoomsIntoTable();
                dialog.dispose();
                JOptionPane.showMessageDialog(this, "Номер успешно добавлен!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Пожалуйста, введите корректные числовые значения!",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    static void main() {
        SwingUtilities.invokeLater(() -> {
            HotelApp app = new HotelApp();
            app.setVisible(true);
        });
    }
}