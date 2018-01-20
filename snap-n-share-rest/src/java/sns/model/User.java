/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sns.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import sns.api.RegisterResource;
/**
 *
 * @author Raghu
 */
@Entity
@Table(name = "sns_user")
public class User implements Serializable {
   
    private static final long serialVersionUID = 1L;
    @Id @Column(name = "user_id")
    @GeneratedValue
	private String user_id;

    
   @Column(name = "user_name")
	private String user_name;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    
    
}
