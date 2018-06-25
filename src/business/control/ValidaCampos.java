package business.control;

public class ValidaCampos {

    protected static void vLogin(String login) throws LoginException {
        if (login.trim().isEmpty()) {
            throw new LoginException("O campo login n�o pode ficar em branco!");
        } else if (login.length() > 20) {
            throw new LoginException("O login n�o pode ter mais de 20 caracteres!");
        } else if (login.matches(".*\\d.*")) {
            throw new LoginException("O login n�o pode conter numeros");
        } else if(ControlaUsuario.users.containsKey(login)){
            throw new LoginException("O login inserido j� existe");
        }
    }

    protected static void vSenha(String senha) throws SenhaException {
        boolean correto;

        if (senha.trim().isEmpty()) {
            throw new SenhaException("O campo senha n�o pode ficar em branco");
        } else if (senha.length() < 8 || senha.length() > 12) {
            throw new SenhaException("A senha deve conter entre 8 e 12 caracteres");
        }

        correto = senha.matches(".*\\d.*.*\\d.*") && senha.matches(".*\\D.*");

        if (!correto) {
            throw new SenhaException("O campo senha deve conter letras e n�meros, e ao menos 2 n�meros");
        }
    }
}
