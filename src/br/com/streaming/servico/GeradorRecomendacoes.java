package br.com.streaming.servico;

import br.com.streaming.modelo.Musica;
import br.com.streaming.modelo.Playlist;

import java.util.ArrayList;

/**
 * Serviço responsável por gerar playlists automáticas com base em critérios.
 * Critérios disponíveis: "top", "recomendadas", "recentes"
 */
public class GeradorRecomendacoes {

    // Impede instanciação — classe utilitária de serviço
    private GeradorRecomendacoes() {}

    /**
     * Gera uma Playlist com músicas selecionadas conforme o critério informado.
     * @param criterio  "top" | "recomendadas" | "recentes"
     * @param catalogo  lista completa de músicas disponíveis
     * @return          Playlist populada, pronta para ser adicionada ao usuário
     */
    public static Playlist gerar(String criterio, ArrayList<Musica> catalogo) {
        if (criterio == null || criterio.isBlank()) {
            throw new IllegalArgumentException("Critério não pode ser nulo ou vazio.");
        }
        if (catalogo == null || catalogo.isEmpty()) {
            throw new IllegalArgumentException("Catálogo não pode ser nulo ou vazio.");
        }

        String nome;
        switch (criterio.toLowerCase()) {
            case "top":          nome = "Top Mais Tocadas";         break;
            case "recomendadas": nome = "Recomendadas para Você";   break;
            case "recentes":     nome = "Adicionadas Recentemente"; break;
            default: throw new IllegalArgumentException(
                    "Critério inválido. Use: top, recomendadas ou recentes.");
        }

        Playlist playlist = new Playlist(nome, "Gerada automaticamente — critério: " + criterio);

        ArrayList<Musica> selecionadas = selecionarMusicas(criterio.toLowerCase(), catalogo);
        for (Musica m : selecionadas) {
            playlist.adicionarMusica(m);
        }

        return playlist;
    }

    // Seleciona até 5 músicas de acordo com o critério
    private static ArrayList<Musica> selecionarMusicas(String criterio, ArrayList<Musica> catalogo) {
        ArrayList<Musica> resultado = new ArrayList<>();
        int limite = Math.min(5, catalogo.size());

        switch (criterio) {
            case "top":
                // Simula mais tocadas: primeiras do catálogo
                for (int i = 0; i < limite; i++) {
                    resultado.add(catalogo.get(i));
                }
                break;

            case "recomendadas":
                // Simula recomendações: intercala do início e do fim (variedade)
                for (int i = 0; i < limite; i++) {
                    if (i % 2 == 0) {
                        resultado.add(catalogo.get(i));
                    } else {
                        resultado.add(catalogo.get(catalogo.size() - 1 - i));
                    }
                }
                break;

            case "recentes":
                // Mais recentes: últimas adicionadas ao catálogo
                for (int i = catalogo.size() - 1; i >= catalogo.size() - limite; i--) {
                    resultado.add(catalogo.get(i));
                }
                break;
        }

        return resultado;
    }
}