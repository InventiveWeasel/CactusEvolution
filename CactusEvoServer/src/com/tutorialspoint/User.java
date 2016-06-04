package com.tutorialspoint;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "user")
public class User implements Serializable {

   private static final long serialVersionUID = 1L;
   private int id;
   private String login;
   private String password;

   public User(){}
   
   public User(int id, String login, String password){
      this.id = id;
      this.login = login;
      this.password = password;
   }

   public int getId() {
      return id;
   }

   @XmlElement
   public void setId(int id) {
      this.id = id;
   }
   public String getLogin() {
      return login;
   }
   @XmlElement
   public void setLogin(String login) {
      this.login = login;
   }
   public String getPassword() {
      return password;
   }
   @XmlElement
   public void setPassword(String password) {
      this.password = password;
   }		
}