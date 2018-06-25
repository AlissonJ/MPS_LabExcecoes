package business.control;

import static business.control.ControlaUsuario.users;
import business.model.Usuarios;

public class Autenticacao {
    protected static void loginExistente(String login) throws LoginException{
        if(!users.containsKey(login)){
            throw new LoginException("O login inserido não existe");
        }
    }

    protected static void comparaSenha(String login, String senha) throws SenhaException{
        Usuarios u = users.get(login);
        if(!u.getSenha().equals(senha)){
            throw new SenhaException("A senha inserida está incorreta");
        }
    }
}
