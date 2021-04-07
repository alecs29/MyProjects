package repository;

import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class UserRepo {
	
	private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primarie");

	public void insertNewUser(User user) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}

	public User findByID(String id){

		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		User user = em.find(User.class,id);
		em.close();
		return user;

	}


	public User findByEmail(String email){
		EntityManager em = entityManagerFactory.createEntityManager();
		em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Query namedQuery = em.createQuery("FROM User");
		List<User> userList = namedQuery.getResultList();

		for(int i=0 ; i<userList.size(); i++){
			if(email.equals(userList.get(i).getEmail())){
				return findByID(userList.get(i).getId());
			}
		}

		em.close();
		return null;
	}

	public List<User> getUsers(){
		EntityManager em = entityManagerFactory.createEntityManager();
		em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Query namedQuery = em.createQuery("FROM User");
		List<User> userList = namedQuery.getResultList();

		em.close();
		return userList;
	}

	public void deleteById(String id){
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		User user = em.find(User.class,id);
		em.remove(user);
		em.getTransaction().commit();
		em.close();
	}


}
