package sns.data;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sns.model.TimelinePhoto;

@Stateless
public class TimelinePhotoBean {

  @PersistenceContext
  private EntityManager em;

  public void addPhoto(TimelinePhoto photo) {
    em.persist(photo);
  }

  public List<TimelinePhoto> find(String userName) {
//    String jpql = "select po from Product p join p.purchaseOrder po\n" +
//"where (p.quantityOnHand > :quantity)\n" +
//"and (p.productId = :productId)\n" +
//"and (po.customerId.customerId = :customerid)"

//    String jpql = "select p from TimelinePhoto p where p.postedBy in (select f.friendName from Friend f where f.userName = :userName) order by p.postedDateTime";
//
//    List<TimelinePhoto> results = em.createQuery(jpql)
//        .setParameter("userName", userName)
//        .getResultList();

    return new ArrayList<TimelinePhoto>();
  }
}
