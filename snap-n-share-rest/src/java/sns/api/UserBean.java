/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sns.api;

import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sns.model.User;

/**
 *
 * @author Raghu
 */
@Stateless
public class UserBean {
    @PersistenceContext(unitName="snap-n-share-restPU")
    private EntityManager em;
       public void addUser(String username) {
         String  userid = UUID.randomUUID().toString().substring(0, 8);
          User user = new User();
          
          user.setUser_id(userid);
          user.setUser_name(username);
          System.out.println("userid"+user.getUser_id());
          System.out.println("username"+user.getUser_name());
          
          em.persist(user);
       
    }
       
       
       public List<User> getAllUsers() {

		TypedQuery<User> query = em.createQuery("select u from User u", User.class);
		return (query.getResultList());
	}

    public boolean checkDuplicate(List<User> listusers, String userName) {
         for(User u : listusers)
    {
        System.out.println("user id"+u.getUser_id());
        System.out.println("user_name"+u.getUser_name());
        if(!(u.getUser_name().equals(userName))) 
            continue;
        else if (u.getUser_name().equals(userName))
            return true;
    }
         return false;
    }
}
    
    
    

