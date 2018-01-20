package sns.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sns_user_photo")
public class TimelinePhoto {

  @Id 
  @Column(name = "id") 
  @GeneratedValue(strategy=IDENTITY)
  private int id;

  @Column(name = "user_name")
  private String postedBy;

  @Column(name = "image_path")
  private String imageUrl;

  @Column(name = "image_comment")
  private String comment;

  @Column(name = "image_datetime")
  private Date postedDateTime;

  @Transient
  private String postTime;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPostedBy() {
    return postedBy;
  }

  public void setPostedBy(String postedBy) {
    this.postedBy = postedBy;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getPostTime() {
    return postTime;
  }

  public void setPostTime(String postTime) {
    this.postTime = postTime;
  }

  public Date getPostedDateTime() {
    return postedDateTime;
  }

  public void setPostedDateTime(Date postedDateTime) {
    this.postedDateTime = postedDateTime;
  }

}
