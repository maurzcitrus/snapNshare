package sns.data;

import java.util.ArrayList;
import java.util.List;
import sns.model.TimelinePhoto;

public class TimelinePhotoQuery {

  public List<TimelinePhoto> findTimeline(String userName) {
    List<TimelinePhoto> timelinePhotos = new ArrayList<>();
    
    TimelinePhoto dummy = new TimelinePhoto();
    dummy.setComment("dummy photo comment");
    dummy.setImageUrl("/img/coca-cola.jpg");
    dummy.setPostTime("6 November 2017");
    dummy.setPostedBy("dummy user");
    
    timelinePhotos.add(dummy);
    
    return timelinePhotos;
  }
}
