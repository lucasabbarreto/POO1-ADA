package perfil;

import post.Post;

import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Perfil {
    private String nome;
    private String login;
    private String senha;
    private List<Post> timeline;

    public Perfil(String nome, String login, String senha, List<Post> timeline) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.timeline = timeline;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Post> getTimeline() {
        return this.timeline;
    }

    public void setTimeline(List<Post> timeline) {
        this.timeline = timeline;
    }

    public void exibirTimeline() {
        System.out.println();
        if (timeline.isEmpty()) {
            System.out.println("Você ainda não postou nada.");
        } else {
            for (Post post : timeline) {
                System.out.println(post.getData() + " às " + post.getHora() + " - " + post.getConteudo());
            }
        }
    }

    public void postar() {
        Scanner sc = new Scanner(System.in);
        boolean conteudoCorreto = false;

        LocalDateTime myDateObj = LocalDateTime.now();
        String data = myDateObj.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String hora = myDateObj.format(DateTimeFormatter.ofPattern("HH:mm"));

        System.out.println("Insira o conteúdo do seu post");
        String conteudo = sc.nextLine();
        if (conteudo.length() != 0) {
            conteudoCorreto = true;
        }

        if (conteudoCorreto) {
            Post novoPost = new Post(data, hora, conteudo);
            timeline.add(novoPost);
        } else {
            System.out.println("O conteúdo do seu post está vazio");
        }
    }
}
