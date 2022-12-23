import java.util.*;

import perfil.Perfil;
import post.Post;
import exception.SenhaIncorretaException;
import exception.UsuarioInexistenteException;

public class RedeSocial {
    private List<Perfil> usuarios;
    private Perfil usuarioLogado = null;

    public RedeSocial() {
        usuarios = new ArrayList<>();
    }

    public void menuInicial() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Digite C para cadastrar um novo usuário.");
        System.out.println("Digite E para entrar em sua conta.");
        System.out.println("Digite F para fechar o programa.");

        String selecaoMenu = sc.nextLine();
        selecaoMenu = selecaoMenu.toUpperCase();

        switch (selecaoMenu) {
            case "C" -> cadastro();
            case "E" -> {
                try {
                    login();
                } catch (UsuarioInexistenteException e) {
                    System.out.println(e.mensagem());
                    menuInicial();
                } catch (SenhaIncorretaException e) {
                    System.out.println(e.mensagem());
                    menuInicial();
                }
            }
            case "F" -> System.out.println("Até mais!");
            default -> {
                System.out.println("Opção inválida.");
                System.out.println();
                menuInicial();
            }
        }
        sc.close();
    }

    public void cadastro() {
        Scanner sc = new Scanner(System.in);
        boolean perfilExiste = false;
        boolean nome = false;
        boolean login = false;
        boolean senha = false;

        System.out.println("Insira seu nome:");
        String novoNome = sc.nextLine();
        if (novoNome.length() != 0) {
            nome = true;
        }

        System.out.println("Digite um nome de usuário para fazer login:");
        String novoLogin = sc.nextLine();
        if (novoLogin.length() != 0) {
            login = true;
        }

        System.out.println("Crie uma senha:");
        String novaSenha = sc.nextLine();
        if (novaSenha.length() != 0) {
            senha = true;
        }

        if (!nome) {
            System.out.println("Nome não pode ser vazio");
        }

        if (!login) {
            System.out.println("Login não pode ser vazio");
        }

        if (!senha) {
            System.out.println("Senha não pode ser vazia");
        }

        if (novoNome.length() != 0 && novoLogin.length() != 0 && novaSenha.length() != 0) {
            for (Perfil perfis : usuarios) {
                if (Objects.equals(perfis.getLogin(), novoLogin)) {
                    perfilExiste = true;
                    break;
                }
            }

            if (perfilExiste) {
                System.out.println("Este nome de usuário já existe.");
            } else {
                List<Post> timeline = new ArrayList<>();
                Perfil novoPerfil = new Perfil(novoNome, novoLogin, novaSenha, timeline);
                usuarios.add(novoPerfil);
                System.out.println("Conta criada com sucesso!");
            }
            System.out.println();
        }

        menuInicial();
        sc.close();
    }

    public void login() throws UsuarioInexistenteException, SenhaIncorretaException {
        Scanner sc = new Scanner(System.in);
        boolean usuarioExiste = false;
        System.out.println("Insira seu usuário de login");
        String usuario = sc.next();

        for (Perfil perfil : usuarios) {
            if (perfil.getLogin().equals(usuario)) {
                usuarioExiste = true;
                usuarioLogado = perfil;
                break;
            }
        }

        if (!usuarioExiste) {
            throw new UsuarioInexistenteException();
        } else {
            System.out.println(usuarioLogado.getNome() + ", digite sua senha");
            String senha = sc.next();
            if (usuarioLogado.getSenha().equals(senha)) {
                System.out.println();
                System.out.println("Bem vindo(a) " + usuarioLogado.getNome());
                perfilLogado();
                sc.close();
            } else {
                throw new SenhaIncorretaException();
            }
        }
    }

    public void perfilLogado() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Digite P para postar algo novo");
        System.out.println("Digite T para visualizar sua timeline");
        System.out.println("Digite S para sair");
        String opcao = sc.next();
        opcao = opcao.toUpperCase();

        switch (opcao) {
            case "P" -> {
                usuarioLogado.postar();
                perfilLogado();
            }
            case "S" -> {
                System.out.println("Até mais, " + usuarioLogado.getNome() + "!");
                menuInicial();
            }
            case "T" -> {
                usuarioLogado.exibirTimeline();
                perfilLogado();
            }
            default -> {
                System.out.println("Opção Inválida");
                System.out.println();
                perfilLogado();
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        System.out.println("Bem vindo(a) à Rede Social");
        RedeSocial aRede = new RedeSocial();
        aRede.menuInicial();
    }
}
