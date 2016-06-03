package prob;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "user")
public class User implements Serializable {

   private static final long serialVersionUID = 1L;
   private int id;
   private String username;
   private String password;
   //private GameState state;

   public User(){}
   
   public User(int id, String username, String password){
      this.id = id;
      this.username = username;
      this.password = password;
     // this.state = new GameState();
   }
   
   /*public User(int id, String username, String password, GameState state){
	      this.id = id;
	      this.username = username;
	      this.password = password;
	      this.state = state;
	   }
	*/
   public int getId() {
      return id;
   }

   @XmlElement
   public void setId(int id) {
      this.id = id;
   }
   
   public String getUserName() {
      return username;
   }
   
   @XmlElement
   public void setusername(String username) {
      this.username = username;
   }
   
   public String getPassword() {
      return password;
   }
   
   @XmlElement
   public void setPassword(String password) {
      this.password = password;
   }
   
   /*
   public GameState getGameState(){
	   return state;
   }
   @XmlElement
   public void saveGameState(GameState actual){
	   this.state = actual;
   }
   */
   
   
}