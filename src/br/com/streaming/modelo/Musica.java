package br.com.streaming.modelo;

import br.com.streaming.util.FormatadorTempo;
import br.com.streaming.util.Validador;

/**
 * Representa uma música do catálogo.
 * Herda de ItemReproducao (que implementa Reproduzivel).
 */
public class Musica extends ItemReproducao {

    private static final String[] GENEROS_VALIDOS =
            {"Pop", "Rock", "Jazz", "Eletrônica", "Hip-Hop", "Clássica"};

    private String titulo;
    private String artista;
    private int    duracaoSegundos;
    private String genero;

    // Construtor padrão
    public Musica() {
        this("Sem título", "Desconhecido", 1, "Pop");
    }

    // Construtor parametrizado — delega validação aos setters
    public Musica(String titulo, String artista, int duracaoSegundos, String genero) {
        super();
        setTitulo(titulo);
        setArtista(artista);
        setDuracaoSegundos(duracaoSegundos);
        setGenero(genero);
    }

    // ── Getters ────────────────────────────────────────────────

    public String getTitulo()           { return titulo; }
    public String getArtista()          { return artista; }
    public int    getDuracaoSegundos()  { return duracaoSegundos; }
    public String getGenero()           { return genero; }

    public static String[] getGenerosValidos() { return GENEROS_VALIDOS; }

    // ── Setters com validação ──────────────────────────────────

    public void setTitulo(String titulo) {
        Validador.exigirTexto(titulo, "Título");
        this.titulo = titulo.trim();
    }

    public void setArtista(String artista) {
        Validador.exigirTexto(artista, "Artista");
        this.artista = artista.trim();
    }

    public void setDuracaoSegundos(int duracaoSegundos) {
        Validador.exigirIntervalo(duracaoSegundos, 1, 3600, "Duração");
        this.duracaoSegundos = duracaoSegundos;
    }

    public void setGenero(String genero) {
        Validador.exigirTexto(genero, "Gênero");
        for (String g : GENEROS_VALIDOS) {
            if (g.equalsIgnoreCase(genero.trim())) {
                this.genero = g;
                return;
            }
        }
        throw new IllegalArgumentException(
                "Gênero inválido. Escolha entre: Pop, Rock, Jazz, Eletrônica, Hip-Hop, Clássica.");
    }

    // ── Implementação de Reproduzivel (via ItemReproducao) ─────

    @Override
    public void reproduzir() {
        emReproducao = true;
        pausado      = false;
        System.out.printf("▶  %s — %s  [%s]%n", titulo, artista, getDuracaoFormatada());
    }

    @Override
    public int getDuracaoTotal() {
        return duracaoSegundos;
    }

    // ── Métodos utilitários ────────────────────────────────────

    public void exibir() {
        System.out.printf("Título: %s | Artista: %s | Duração: %s | Gênero: %s%n",
                titulo, artista, getDuracaoFormatada(), genero);
    }

    public String getDuracaoFormatada() {
        return FormatadorTempo.formatarSegundos(duracaoSegundos);
    }

    public boolean contemTitulo(String busca) {
        return titulo.toLowerCase().contains(busca.toLowerCase());
    }

    public boolean contemArtista(String busca) {
        return artista.toLowerCase().contains(busca.toLowerCase());
    }
}