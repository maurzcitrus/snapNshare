package sns.data;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import sns.model.Friend;

@Stateless
public class FriendCommand {

  @PersistenceContext(unitName = "snap-n-share-restPU")
  private EntityManager em;

  public List<Friend> findAllFriends(String userName) {
    //todo filter by userName
    TypedQuery<Friend> query = em.createQuery("select f from Friend f", Friend.class);
    return (query.getResultList());
  }

  public void deleteFriends(String userName) {
//    for (int i = 0; i < friend.size(); i++) {
//      Friend friendTemp = em.find(Friend.class, friend.get(i));
//      em.getTransaction();
//      em.remove(friendTemp);
//      em.getTransaction().commit();
//    }

    String jpql = "delete from Friend f "
        + " where f.userName = :userName";

    Query query = em.createQuery(jpql);
    int deletedCount = query.setParameter("userName", userName).executeUpdate();

    System.out.println(">> deleted: " + deletedCount);
  }

  public void addFriends(List<Friend> friend) {
    for (int i = 0; i < friend.size(); i++) {
      em.persist(friend.get(i));
    }
  }
}
