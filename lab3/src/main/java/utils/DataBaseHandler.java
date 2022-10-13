package utils;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


public class DataBaseHandler {
    private EntityManagerFactory factory;
    private EntityManager entityManager;
    private EntityTransaction transaction;
    private List<Hit> shiftedtHits = new ArrayList<>();
    private List<Hit> hits = new ArrayList<>();


    public void getConnection() {
        factory = Persistence.createEntityManagerFactory("hit");
        entityManager = factory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    public void addHit(Hit hit) {
        transaction.begin();
        entityManager.persist(hit);
        transaction.commit();
    }

    public void shiftHit() {
        transaction.begin();
        Query query = entityManager.createQuery("SELECT e FROM hit e ORDER BY e.id ASC");
        shiftedtHits = query.setMaxResults(1).getResultList();
        Hit hit = shiftedtHits.get(0);
        query = entityManager.createQuery("DELETE FROM hit e WHERE e.id = " + hit.getId());
        query.executeUpdate();
        transaction.commit();
    }

    public List<Hit> getHits() {
        transaction.begin();
        Query query = entityManager.createQuery("SELECT e FROM hit e");
        hits = query.getResultList();
        transaction.commit();
        return hits;
    }
}
