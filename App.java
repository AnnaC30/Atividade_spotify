import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        List<Musica> musicas = gerarListaRock();
        int opcao;

        do {
            System.out.println("\n**** MENU ****");
            System.out.println("1 - Listar 3 músicas com maior avaliação");
            System.out.println("2 - Estatísticas da banda");
            System.out.println("3 - Soma dos tempos das músicas por banda");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 0:
                    System.out.println("Fim do programa!");
                    break;
                case 1:
                    listar3Musicas(musicas);
                    voltarMenu(input);
                    break;
                case 2:
                    mostrarEstatisticas(musicas);
                    voltarMenu(input);
                    break;
                case 3:
                    somarTempoPorBanda(musicas);
                    voltarMenu(input);
                    break;
                default:
                    System.out.println("Opção inválida!");
                    voltarMenu(input);
            }
        } while (opcao != 0);

        input.close();
    }

    public static List<Musica> gerarListaRock() {
        List<Musica> lista = new ArrayList<>();
        Random rand = new Random();

        lista.add(new Musica("Back In Black", "AC/DC", 255, 8 + rand.nextInt(3)));
        lista.add(new Musica("Highway to Hell", "AC/DC", 208, 8 + rand.nextInt(3)));
        lista.add(new Musica("Thunderstruck", "AC/DC", 292, 8 + rand.nextInt(3)));

        lista.add(new Musica("Sweet Child O' Mine", "Guns N' Roses", 356, 8 + rand.nextInt(3)));
        lista.add(new Musica("Welcome to the Jungle", "Guns N' Roses", 273, 8 + rand.nextInt(3)));
        lista.add(new Musica("Paradise City", "Guns N' Roses", 406, 8 + rand.nextInt(3)));

        lista.add(new Musica("Bohemian Rhapsody", "Queen", 354, 8 + rand.nextInt(3)));
        lista.add(new Musica("Don't Stop Me Now", "Queen", 210, 8 + rand.nextInt(3)));
        lista.add(new Musica("We Will Rock You", "Queen", 122, 8 + rand.nextInt(3)));
        lista.add(new Musica("Another One Bites the Dust", "Queen", 215, 8 + rand.nextInt(3)));

        return lista;
    }

    public static void listar3Musicas(List<Musica> musicas) {
        System.out.println("\nTop 3 músicas com maior avaliação:");
        musicas.stream()
            .sorted(Comparator.comparingInt(Musica::getAvaliacao).reversed())
            .limit(3)
            .forEach(m -> System.out.println(m.getNome() + " - " + m.getBanda() + " - Avaliação: " + m.getAvaliacao()));
    }

    public static void mostrarEstatisticas(List<Musica> musicas) {
        List<String> bandas = new ArrayList<>();

        for (Musica m : musicas) {
            if (!bandas.contains(m.getBanda())) {
                bandas.add(m.getBanda());
            }
        }

        for (String banda : bandas) {
            int soma = 0;
            int qtd = 0;
            int somaTempo = 0;
            int maior = 0;
            int menor = 0;
            boolean primeira = true;

            for (Musica m : musicas) {
                if (m.getBanda().equals(banda)) {
                    int av = m.getAvaliacao();
                    soma += av;
                    somaTempo += m.getTempoSegundos();
                    qtd++;

                    if (primeira) {
                        maior = av;
                        menor = av;
                        primeira = false;
                    } else {
                        if (av > maior) maior = av;
                        if (av < menor) menor = av;
                    }
                }
            }

            double media = (qtd > 0) ? (double) soma / qtd : 0;

            System.out.println("\nBanda: " + banda);
            System.out.println("  - Maior avaliação: " + maior);
            System.out.println("  - Menor avaliação: " + menor);
            System.out.println("  - Soma das avaliações: " + soma);
            System.out.println("  - Média das avaliações: " + String.format("%.2f", media));
            System.out.println("  - Quantidade de músicas: " + qtd);
            System.out.println("  - Tempo total das músicas: " + somaTempo + " segundos");
        }
    }

    public static void somarTempoPorBanda(List<Musica> musicas) {
        List<String> bandas = new ArrayList<>();

        for (Musica m : musicas) {
            if (!bandas.contains(m.getBanda())) {
                bandas.add(m.getBanda());
            }
        }

        for (String banda : bandas) {
            int somaTempo = 0;

            for (Musica m : musicas) {
                if (m.getBanda().equals(banda)) {
                    somaTempo += m.getTempoSegundos();
                }
            }

            System.out.println("Banda: " + banda + " - Tempo total: " + somaTempo + " segundos");
        }
    }

    private static void voltarMenu(Scanner input) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        input.nextLine();
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            System.out.print("\033[H\033[2J");
        }
        System.out.flush();
    }
}
