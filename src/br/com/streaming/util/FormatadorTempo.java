package br.com.streaming.util;

/**
 * Classe utilitária para formatar durações de tempo.
 * Marcada como final — utilitário puro, sem necessidade de herança.
 */
public final class FormatadorTempo {

    // Impede instanciação
    private FormatadorTempo() {}

    /**
     * Converte segundos para o formato "m:ss".
     * Exemplo: 293 → "4:53"
     */
    public static String formatarSegundos(int totalSegundos) {
        if (totalSegundos < 0) {
            throw new IllegalArgumentException("Duração não pode ser negativa.");
        }
        int min = totalSegundos / 60;
        int seg = totalSegundos % 60;
        return String.format("%d:%02d", min, seg);
    }

    /**
     * Converte segundos para o formato "Xh Ym Zs" (por ex. para durações longas de playlist).
     * Exemplo: 3725 → "1h 2m 5s"
     */
    public static String formatarLongo(int totalSegundos) {
        if (totalSegundos < 0) {
            throw new IllegalArgumentException("Duração não pode ser negativa.");
        }
        int horas = totalSegundos / 3600;
        int min   = (totalSegundos % 3600) / 60;
        int seg   = totalSegundos % 60;

        if (horas > 0) {
            return String.format("%dh %dm %ds", horas, min, seg);
        } else if (min > 0) {
            return String.format("%dm %ds", min, seg);
        } else {
            return String.format("%ds", seg);
        }
    }
}