package business.control;

import business.model.Usuarios;
import infra.InfraException;
import infra.Persistencia;
import java.util.HashMap;

public class ControlaUsuario {

    protected static HashMap<String, Usuarios> users = new HashMap<>();

    public static void adicionaUsuario(String login, String senha) throws LoginException, SenhaException, InfraException {
        ValidaCampos.vLogin(login);
        ValidaCampos.vSenha(senha);
        Usuarios u = new Usuarios(login, senha);
        users.put(login, u);
        try {
            Persistencia.salvaUsuarios(users);
        } catch (InfraException ex) {
            users.remove(login);
            throw ex;
        }
    }

    public static void removeUsuario(String login, String senha) throws LoginException, SenhaException, InfraException {
        Usuarios tmp;
        Autenticacao.loginExistente(login);
        Autenticacao.comparaSenha(login, senha);
        tmp = users.remove(login);
        try {
            Persistencia.salvaUsuarios(users);
        } catch (InfraException e) {
            users.put(login, tmp);
            throw e;
        }
    }

    public static void logaUsuario(String login, String senha) throws SenhaException, LoginException {
        Autenticacao.loginExistente(login);
        Autenticacao.comparaSenha(login, senha);
    }

    public static void iniciaSistema() throws InfraException {
        users = Persistencia.carregaUsuarios();
    }
}
