package br.com.streaming.modelo;

import br.com.streaming.servico.Reproduzivel;

/**
 * Classe abstrata base para qualquer item que possa ser reproduzido no sistema.
 * Implementa a interface Reproduzivel e fornece estado de reprodução comum.
 *
 * Subclasses: Musica, Playlist
 */
public abstract class ItemReproducao implements Reproduzivel {

    // Estado de reprodução compartilhado por todas as subclasses
    protected boolean emReproducao;
    protected boolean pausado;

    public ItemReproducao() {
        this.emReproducao = false;
        this.pausado      = false;
    }

    // Implementações padrão de pausar() e parar() — comuns a todos os itens.
    // Subclasses podem sobrescrever se precisarem de comportamento diferente.

    @Override
    public void pausar() {
        if (emReproducao) {
            pausado      = true;
            emReproducao = false;
            System.out.println("⏸  Pausado.");
        } else {
            System.out.println("ℹ️  Não está em reprodução.");
        }
    }

    @Override
    public void parar() {
        emReproducao = false;
        pausado      = false;
        System.out.println("⏹  Parado.");
    }

    // Cada subclasse define como se reproduz e qual é sua duração total
    @Override
    public abstract void reproduzir();

    @Override
    public abstract int getDuracaoTotal();

    // Getters de estado
    public boolean isEmReproducao() { return emReproducao; }
    public boolean isPausado()      { return pausado; }
}