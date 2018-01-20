package sns.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sns.data.FriendCommand;
import sns.model.Friend;

@RequestScoped
@Path("/friends/{userName}")
public class FriendResource {

  @EJB
  FriendCommand friendCommand;

  private List<Friend> friendList;

  Friend friend;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String get(@PathParam("userName") String userName) {

    friendList = friendCommand.findAllFriends(userName);

    JsonArrayBuilder names = Json
        .createArrayBuilder();

    for (Friend f : friendList) {
      names.add(f.getFriendName());
    }

    return names.build().toString();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response post(@PathParam("userName") String userName, List<String> data) {
    List<Friend> friendNameTemp = new ArrayList<>();
    for (String name : data) {

      friend = new Friend();
      String friendId = UUID.randomUUID().toString().substring(0, 8);
      friend.setUserName(userName);
      friend.setFriendId(friendId);
      friend.setFriendName(name);
      friendNameTemp.add(friend);
    }

    friendCommand.deleteFriends(userName);
    friendCommand.addFriends(friendNameTemp);
    return Response.ok().build();
  }

}
