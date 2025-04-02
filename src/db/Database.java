package db;

import db.exception.EntityNotFoundException;

import java.util.ArrayList;

public class Database {
    private static ArrayList<Entity> entities = new ArrayList<>();
    private static int firstId = 1;

    public static void add(Entity e) {
        e.id = firstId;
        try {
            entities.add(e.clone());
        } catch (CloneNotSupportedException ex) {
            System.out.println("copying failed!");
        }
        firstId++;
    }

    public static Entity get(int id) throws EntityNotFoundException {
        for (Entity entity : entities) {
            if (entity.id == id)
                return entity;
        }
        throw new EntityNotFoundException(id);
    }

    public static void delete(int id) {
        Entity toDelete = get(id);
        entities.remove(toDelete);
    }

    public static void update(Entity e) throws EntityNotFoundException {
        boolean found = false;
        for (Entity entity : entities) {
            if (entity.id == e.id) {
                entity.id = e.id;
                found = true;
                break;
            }
        }
        if (!found) {
            throw new EntityNotFoundException(e.id);
        }
    }

}
