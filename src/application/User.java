package application;

import javafx.beans.property.StringProperty ;
import javafx.beans.property.SimpleStringProperty ;

public class User {
	
	public User() {}

    public User(String username, String password) {
        setusername(username);
        setpassword(password);
    }
	
	private final StringProperty username = new SimpleStringProperty(this, "username");
	
    public StringProperty usernameProperty() {
        return username ;
    }
    
    public final String getusername() {
        return usernameProperty().get();
    }
    
    public final void setusername(String username) {
        usernameProperty().set(username);
    }

    private final StringProperty password = new SimpleStringProperty(this, "password");
    
    public StringProperty passwordProperty() {
        return password ;
    }
    
    public final String getpassword() {
        return passwordProperty().get();
    }
    
    public final void setpassword(String password) {
        passwordProperty().set(password);
    }
    
}
