package Hotel;

import com.google.gson.*;
import java.io.*;
import java.util.*;

public class RoomManager {
    private List<Room> rooms;
    private static final String FILE_NAME = "room.json";
    private Gson gson;

    public RoomManager() {
        this.rooms = new ArrayList<>();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        loadRooms();
    }

    private void loadRooms() {
        try (Reader reader = new FileReader(FILE_NAME)) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();

            for (JsonElement element : jsonArray) {
                JsonObject obj = element.getAsJsonObject();
                String type = obj.get("type").getAsString();

                switch (type) {
                    case "standard":
                        rooms.add(gson.fromJson(obj, StandardRoom.class));
                        break;
                    case "semilux":
                        rooms.add(gson.fromJson(obj, SemiLuxRoom.class));
                        break;
                    case "lux":
                        rooms.add(gson.fromJson(obj, LuxRoom.class));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл room.json не найден. Создан новый список номеров.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveRooms() {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            List<JsonObject> jsonRooms = new ArrayList<>();

            for (Room room : rooms) {
                JsonObject jsonRoom = gson.toJsonTree(room).getAsJsonObject();

                if (room instanceof StandardRoom) {
                    jsonRoom.addProperty("type", "standard");
                } else if (room instanceof SemiLuxRoom) {
                    jsonRoom.addProperty("type", "semilux");
                } else if (room instanceof LuxRoom) {
                    jsonRoom.addProperty("type", "lux");
                }

                jsonRooms.add(jsonRoom);
            }

            gson.toJson(jsonRooms, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addRoom(Room room) {
        rooms.add(room);
        saveRooms();
    }

    public List<Room> getAllRooms() {
        return new ArrayList<>(rooms);
    }

    public List<Room> getRoomsSortedByPrice() {
        List<Room> sorted = new ArrayList<>(rooms);
        sorted.sort(new Room.PriceComparator());
        return sorted;
    }

    public List<Room> getRoomsSortedByPeople() {
        List<Room> sorted = new ArrayList<>(rooms);
        sorted.sort(new Room.PeopleComparator());
        return sorted;
    }
}
