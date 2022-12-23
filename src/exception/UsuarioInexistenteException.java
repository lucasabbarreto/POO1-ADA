package exception;

public class UsuarioInexistenteException extends RuntimeException {
    public UsuarioInexistenteException() {
    }

    public String mensagem() {
        return "Este usuário não existe";
    }
}
