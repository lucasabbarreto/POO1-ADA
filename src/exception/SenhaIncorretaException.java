package exception;

public class SenhaIncorretaException extends RuntimeException {
    public SenhaIncorretaException() {
    }

    public String mensagem() {
        return "A senha está incorreta";
    }
}
