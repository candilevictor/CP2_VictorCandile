package br.com.streaming.modelo;

import br.com.streaming.servico.Baixavel;
import br.com.streaming.util.Validador;

import java.util.ArrayList;

/**
 * Usuário do plano premium.
 * Reprodução em alta qualidade, playlists ilimitadas e suporte a downloads (Baixavel).
 */
public class UsuarioPremium extends Usuario implements Baixavel {

    private String          plano;          // "Mensal", "Anual" ou "Familiar"
    private ArrayList<Musica> musicasBaixadas;

    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email);
        setPlano(plano);
        this.musicasBaixadas = new ArrayList<>();
    }

    // ── Getter / Setter ────────────────────────────────────────

    public String getPlano() { return plano; }

    public void setPlano(String plano) {
        Validador.exigirTexto(plano, "Plano");
        this.plano = plano.trim();
    }

    public ArrayList<Musica> getMusicasBaixadas() {
        return new ArrayList<>(musicasBaixadas); // cópia defensiva
    }

    // ── Sobrescrita: reprodução em alta qualidade ──────────────

    @Override
    public void reproduzirMusica(Musica musica) {
        Validador.exigirNaoNulo(musica, "Música");
        System.out.println("🎵 Reproduzindo em ALTA QUALIDADE: " + musica.getTitulo()
                + " — " + musica.getArtista());
        historicoReproducao.add(musica);
    }

    // ── Implementação da interface Baixavel ────────────────────

    @Override
    public void baixar(Musica musica) {
        Validador.exigirNaoNulo(musica, "Música");
        if (!musicasBaixadas.contains(musica)) {
            musicasBaixadas.add(musica);
            System.out.println("⬇️  Música baixada: " + musica.getTitulo());
        } else {
            System.out.println("ℹ️  \"" + musica.getTitulo() + "\" já está baixada!");
        }
    }

    @Override
    public void removerDownload(Musica musica) {
        if (musicasBaixadas.remove(musica)) {
            System.out.println("🗑️  Download removido: " + musica.getTitulo());
        } else {
            System.out.println("ℹ️  \"" + musica.getTitulo() + "\" não está na lista de downloads.");
        }
    }

    @Override
    public boolean estaBaixada(Musica musica) {
        return musicasBaixadas.contains(musica);
    }

    @Override
    public int getTamanhoBaixados() {
        return musicasBaixadas.size();
    }

    // ── Listagem de downloads ──────────────────────────────────

    public void listarMusicasBaixadas() {
        System.out.println("\n--- MÚSICAS BAIXADAS ---");
        if (musicasBaixadas.isEmpty()) {
            System.out.println("Nenhuma música baixada.");
            return;
        }
        for (int i = 0; i < musicasBaixadas.size(); i++) {
            System.out.print("  " + (i + 1) + ". ");
            musicasBaixadas.get(i).exibir();
        }
    }
}