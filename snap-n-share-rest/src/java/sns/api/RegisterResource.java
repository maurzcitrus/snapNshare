package sns.api;

import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import sns.model.User;

@RequestScoped
@Path("/register/{userName}")
public class RegisterResource {
    
@EJB private UserBean userBean;
@POST
public Response post(@PathParam("userName") String userName){
    List<User> listusers= userBean.getAllUsers();
    
    if(userBean.checkDuplicate(listusers,userName))
    {
        System.out.println(">> Duplicates found - try different username : " + userName);
        return Response.status(Response.Status.BAD_REQUEST).entity("Username is already Taken try different one").build();
    }
    else
        userBean.addUser(userName);
    System.out.println(">> User added to the table : " + userName);
    return Response.ok().build();
  }
  
  
}
