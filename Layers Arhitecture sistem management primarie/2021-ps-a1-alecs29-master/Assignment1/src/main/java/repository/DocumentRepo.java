package repository;

import entity.Document;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class DocumentRepo {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primarie");

    public void insertNewDocument(Document document) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(document);
        em.getTransaction().commit();
        em.close();
    }

    public Document findByID(String id){

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Document document = em.find(Document.class,id);
        em.close();
        return document;

    }

    public Document findByName(String name){
        EntityManager em = entityManagerFactory.createEntityManager();
        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query namedQuery = em.createQuery("FROM Document");
        List<Document> documentList = namedQuery.getResultList();

        for(int i=0 ; i<documentList.size(); i++){
            if(name.equals(documentList.get(i).getName())){
                return findByID(documentList.get(i).getId());
            }
        }

        em.close();
        return null;
    }

    public List<Document> getDocuments(){

        EntityManager em = entityManagerFactory.createEntityManager();
        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query namedQuery = em.createQuery("FROM Document");
        List<Document> documentList = namedQuery.getResultList();

        em.close();
        return documentList;

    }

    public void deleteById(String id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Document document = em.find(Document.class,id);
        em.remove(document);
        em.getTransaction().commit();
        em.close();
    }
}
