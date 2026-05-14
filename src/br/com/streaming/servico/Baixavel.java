package br.com.streaming.servico;

import br.com.streaming.modelo.Musica;

/**
 * Contrato de comportamento para usuários que podem baixar músicas.
 * Implementado por: UsuarioPremium
 */
public interface Baixavel {
    void baixar(Musica musica);
    void removerDownload(Musica musica);
    boolean estaBaixada(Musica musica);
    int getTamanhoBaixados();
}