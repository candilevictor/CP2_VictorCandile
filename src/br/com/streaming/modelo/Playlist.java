package br.com.streaming.modelo;

import br.com.streaming.util.FormatadorTempo;
import br.com.streaming.util.Validador;

import java.util.ArrayList;

/**
 * Representa uma playlist de músicas.
 * Herda de ItemReproducao, implementando Reproduzivel.
 * Pode ser usada diretamente ou como base para subclasses (ex: PlaylistAutomatica).
 */
public class Playlist extends ItemReproducao {

    protected String           nome;
    protected String           descricao;
    protected ArrayList<Musica> musicas;

    // ── Construtores ───────────────────────────────────────────

    public Playlist() {
        this("Sem nome", "Sem descrição");
    }

    public Playlist(String nome) {
        this(nome, "Sem descrição");
    }

    public Playlist(String nome, String descricao) {
        super();
        setNome(nome);
        setDescricao(descricao);
        this.musicas = new ArrayList<>();
    }

    // ── Getters ────────────────────────────────────────────────

    public String getNome()      { return nome; }
    public String getDescricao() { return descricao; }

    public ArrayList<Musica> getMusicas() {
        return new ArrayList<>(musicas); // cópia defensiva
    }

    // ── Setters com validação ──────────────────────────────────

    public void setNome(String nome) {
        Validador.exigirTexto(nome, "Nome da playlist");
        this.nome = nome.trim();
    }

    public void setDescricao(String descricao) {
        Validador.exigirNaoNulo(descricao, "Descrição");
        this.descricao = descricao.trim();
    }

    // ── Implementação de Reproduzivel ──────────────────────────

    @Override
    public void reproduzir() {
        emReproducao = true;
        pausado      = false;
        System.out.println("🎵 Reproduzindo playlist: " + nome);
        if (musicas.isEmpty()) {
            System.out.println("  (playlist vazia)");
            return;
        }
        for (Musica m : musicas) {
            System.out.println("  ▶ " + m.getTitulo() + " — " + m.getArtista());
        }
    }

    @Override
    public int getDuracaoTotal() {
        int total = 0;
        for (Musica m : musicas) {
            total += m.getDuracaoSegundos();
        }
        return total;
    }

    // ── Gerenciamento de músicas (final — lógica crítica) ──────

    public final void adicionarMusica(Musica musica) {
        Validador.exigirNaoNulo(musica, "Música");
        musicas.add(musica);
    }

    public final void removerMusica(int indice) {
        if (indice < 0 || indice >= musicas.size()) {
            System.out.println("Índice inválido. Deve ser entre 0 e " + (musicas.size() - 1) + ".");
            return;
        }
        musicas.remove(indice);
    }

    public final void listarMusicas() {
        if (musicas.isEmpty()) {
            System.out.println("  Playlist vazia.");
            return;
        }
        for (int i = 0; i < musicas.size(); i++) {
            System.out.print("  " + (i + 1) + ". ");
            musicas.get(i).exibir();
        }
    }

    public final int getQuantidadeMusicas() { return musicas.size(); }

    public String getDuracaoFormatada() {
        return FormatadorTempo.formatarLongo(getDuracaoTotal());
    }
}