package br.com.streaming.modelo;

/**
 * Usuário do plano gratuito.
 * Limitado a 3 playlists e exibe anúncios a cada 3 reproduções.
 */
public class UsuarioFree extends Usuario {

    private static final int MAX_PLAYLISTS = 3;

    private int contadorReproducoes;

    public UsuarioFree(String nome, String email) {
        super(nome, email);
        this.contadorReproducoes = 0;
    }

    // ── Sobrescrita: exibe anúncio a cada 3 reproduções ────────

    @Override
    public void reproduzirMusica(Musica musica) {
        contadorReproducoes++;
        if (contadorReproducoes % 3 == 0) {
            exibirAnuncio();
        }
        super.reproduzirMusica(musica);
    }

    // ── Sobrescrita: respeita limite de 3 playlists ────────────

    @Override
    public void criarPlaylist(String nome) {
        if (playlists.size() >= MAX_PLAYLISTS) {
            System.out.println("❌ Limite de " + MAX_PLAYLISTS + " playlists atingido!");
            System.out.println("💎 Faça upgrade para Premium e tenha playlists ilimitadas!");
            return;
        }
        super.criarPlaylist(nome);
    }

    // ── Métodos específicos ────────────────────────────────────

    private void exibirAnuncio() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📢 ANÚNCIO: Assine Premium e ouça sem interrupções!");
        System.out.println("=".repeat(50) + "\n");
    }

    public int getContadorReproducoes()  { return contadorReproducoes; }
    public int getPlaylistsDisponiveis() { return MAX_PLAYLISTS - playlists.size(); }
    public int getAnunciosExibidos()     { return contadorReproducoes / 3; }
}