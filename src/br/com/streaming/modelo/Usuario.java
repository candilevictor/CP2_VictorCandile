package br.com.streaming.modelo;

import br.com.streaming.util.Validador;

import java.util.ArrayList;

/**
 * Classe base para todos os tipos de usuário do sistema.
 * Define comportamentos comuns e métodos que subclasses podem sobrescrever.
 */
public class Usuario {

    protected String             nome;
    protected String             email;
    protected ArrayList<Playlist> playlists;
    protected ArrayList<Musica>  historicoReproducao;

    // ── Construtor ─────────────────────────────────────────────

    public Usuario(String nome, String email) {
        setNome(nome);
        setEmail(email);
        this.playlists           = new ArrayList<>();
        this.historicoReproducao = new ArrayList<>();
    }

    // ── Getters ────────────────────────────────────────────────

    public String getNome()  { return nome; }
    public String getEmail() { return email; }

    public ArrayList<Playlist> getPlaylists() {
        return new ArrayList<>(playlists);
    }

    public ArrayList<Musica> getHistoricoReproducao() {
        return new ArrayList<>(historicoReproducao);
    }

    // ── Setters (final — validação crítica, não pode ser alterada) ──

    public final void setNome(String nome) {
        Validador.exigirTexto(nome, "Nome do usuário");
        this.nome = nome.trim();
    }

    public final void setEmail(String email) {
        if (!Validador.emailValido(email)) {
            throw new IllegalArgumentException("Email inválido: deve conter '@' e não ser vazio.");
        }
        this.email = email.trim();
    }

    // ── Reprodução (sobrescrito nas subclasses) ────────────────

    public void reproduzirMusica(Musica musica) {
        Validador.exigirNaoNulo(musica, "Música");
        musica.reproduzir();
        historicoReproducao.add(musica);
    }

    // ── Histórico (final — mesmo comportamento para todos) ─────

    public final void exibirHistorico() {
        System.out.println("\n--- HISTÓRICO DE REPRODUÇÃO ---");
        if (historicoReproducao.isEmpty()) {
            System.out.println("Nenhuma música reproduzida ainda.");
            return;
        }
        for (int i = 0; i < historicoReproducao.size(); i++) {
            System.out.print((i + 1) + ". ");
            historicoReproducao.get(i).exibir();
        }
    }

    // ── Playlists ──────────────────────────────────────────────

    public void criarPlaylist(String nome) {
        Playlist p = new Playlist(nome);
        playlists.add(p);
        System.out.println("✅ Playlist \"" + nome + "\" criada!");
    }

    public final void adicionarPlaylist(Playlist playlist) {
        Validador.exigirNaoNulo(playlist, "Playlist");
        playlists.add(playlist);
    }

    public final Playlist getPlaylist(int indice) {
        if (indice < 0 || indice >= playlists.size()) {
            System.out.println("Índice de playlist inválido.");
            return null;
        }
        return playlists.get(indice);
    }

    public final void listarPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist criada.");
            return;
        }
        for (int i = 0; i < playlists.size(); i++) {
            Playlist p = playlists.get(i);
            System.out.printf("  %d. %-25s (%d músicas | %s)%n",
                    (i + 1), p.getNome(), p.getQuantidadeMusicas(), p.getDuracaoFormatada());
        }
    }

    public final int getTotalPlaylists()    { return playlists.size(); }
    public final int getTotalReproducoes()  { return historicoReproducao.size(); }
}