package sns.api;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sns.data.TimelinePhotoBean;
import sns.data.TimelinePhotoQuery;
import sns.model.TimelinePhoto;

@RequestScoped
@Path("/timeline/{userName}")
public class TimelineResource {
  
  @EJB
  private TimelinePhotoBean query;
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<TimelinePhoto> get(@PathParam("userName") String userName) {
    
//    TimelinePhotoQuery query = new TimelinePhotoQuery();
//    List<TimelinePhoto> photos = query.findTimeline("dummy");
    List<TimelinePhoto> photos = query.find(userName);
    return photos;
  }
}
