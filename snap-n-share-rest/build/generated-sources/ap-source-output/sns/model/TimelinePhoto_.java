package sns.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-11-06T21:28:28")
@StaticMetamodel(TimelinePhoto.class)
public class TimelinePhoto_ { 

    public static volatile SingularAttribute<TimelinePhoto, String> postedBy;
    public static volatile SingularAttribute<TimelinePhoto, String> imageUrl;
    public static volatile SingularAttribute<TimelinePhoto, String> comment;
    public static volatile SingularAttribute<TimelinePhoto, Date> postedDateTime;
    public static volatile SingularAttribute<TimelinePhoto, Integer> id;

}