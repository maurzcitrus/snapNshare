package sns.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sns_friend")
public class Friend implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "friend_id")
    private String friendId;
   
    @Column(name = "user_name", length = 20)
    private String userName;
    
    @Column(name = "friend_name", length = 20)
    private String friendName;

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

   

   

 
   
}
