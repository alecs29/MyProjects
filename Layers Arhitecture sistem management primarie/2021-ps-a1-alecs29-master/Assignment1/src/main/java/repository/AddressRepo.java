package repository;

import entity.Address;
import entity.Document;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class AddressRepo {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primarie");

    public void insertNewAddress(Address address) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(address);
        em.getTransaction().commit();
        em.close();
    }

    public Address findByID(String id){

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Address address = em.find(Address.class,id);
        em.close();
        return address;

    }

    public void deleteById(String id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Address address = em.find(Address.class,id);
        em.remove(address);
        em.getTransaction().commit();
        em.close();
    }

    public List<Address> getAddresses(){

        EntityManager em = entityManagerFactory.createEntityManager();
        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query namedQuery = em.createQuery("FROM Address");
        List<Address> addressList = namedQuery.getResultList();

        em.close();
        return addressList;

    }
    public void updateRequestsPerAddress(Address address, int number){

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Address adr = em.find(Address.class,address.getId());

        //int currentNumberOfrequests = adr.getNumbersOFRequests();
        adr.setNumbersOFRequests(number);

        //em.flush();
        em.persist(adr);
        em.getTransaction().commit();
        em.close();

    }


    public Address findByName(String name){
        EntityManager em = entityManagerFactory.createEntityManager();
        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query namedQuery = em.createQuery("FROM Address");
        List<Address> addressList = namedQuery.getResultList();

        for(int i=0 ; i<addressList.size(); i++){
            if(name.equals(addressList.get(i).getAddressName())){
                return findByID(addressList.get(i).getId());
            }
        }

        em.close();
        return null;
    }


}
