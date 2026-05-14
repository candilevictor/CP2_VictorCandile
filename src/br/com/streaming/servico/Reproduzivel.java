package br.com.streaming.servico;

/**
 * Contrato de comportamento para qualquer item que possa ser reproduzido.
 * Implementado por: Musica, Playlist
 */
public interface Reproduzivel {
    void reproduzir();
    void pausar();
    void parar();
    int getDuracaoTotal();
}