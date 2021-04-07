package repository;

import entity.Address;
import entity.Request;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class RequestRepo {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primarie");

    public void insertNewRequest(Request request) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(request);
        em.getTransaction().commit();
        em.close();
    }

    public Request findByID(String id){

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Request request = em.find(Request.class,id);
        em.close();
        return request;

    }
    public void updateRequestApproved(Request request, String approved){

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Request req = em.find(Request.class,request.getId());

        req.setApproved(approved);

        em.persist(req);
        em.getTransaction().commit();
        em.close();

    }


    public Request findByName(String name){
        EntityManager em = entityManagerFactory.createEntityManager();
        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query namedQuery = em.createQuery("FROM Request");
        List<Request> requestList = namedQuery.getResultList();

        for(int i=0 ; i<requestList.size(); i++){
            if(name.equals(requestList.get(i).getRequestName())){
                return findByID(requestList.get(i).getId());
            }
        }

        em.close();
        return null;
    }

    public List<Request> getRequests(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query namedQuery = em.createQuery("FROM Request");
        List<Request> requestList = namedQuery.getResultList();

        em.close();
        return  requestList;
    }

    public void deleteById(String id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Request request = em.find(Request.class,id);
        em.remove(request);
        em.getTransaction().commit();
        em.close();
    }
}
