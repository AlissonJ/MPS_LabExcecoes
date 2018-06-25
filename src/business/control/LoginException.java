package business.control;

public class LoginException extends Exception {
    public LoginException(){
        super("O login digitado é invalido");
    }

    public LoginException(String msg){
        super(msg);
    }
}
