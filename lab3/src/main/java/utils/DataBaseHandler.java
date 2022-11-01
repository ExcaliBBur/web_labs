package utils;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


public class DataBaseHandler {
    private static EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("hit");
    private static EntityManager entityManager = factory.createEntityManager();
    private static List<Hit> hits = new ArrayList<>();

    public static void addHit(Hit hit) {
        entityManager.persist(hit);
    }

    public static List<Hit> getHits() {
        Query query = entityManager.createQuery("SELECT e FROM hit e");
        hits = query.getResultList();
        return hits;
    }

}
