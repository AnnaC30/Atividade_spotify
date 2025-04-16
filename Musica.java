
public class Musica {

    private String nome;
    private String banda;
    private int tempoSegundos;
    private int avaliacao;
    
    public Musica(String nome, String banda, int tempoSegundos, int avaliacao) {
        this.nome = nome;
        this.banda = banda;
        this.tempoSegundos = tempoSegundos;
        this.avaliacao = avaliacao;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getBanda() {
        return banda;
    }
    public void setBanda(String banda) {
        this.banda = banda;
    }
    public int getTempoSegundos() {
        return tempoSegundos;
    }
    public void setTempoSegundos(int tempoSegundos) {
        this.tempoSegundos = tempoSegundos;
    }
    public int getAvaliacao() {
        return avaliacao;
    }
    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

}
