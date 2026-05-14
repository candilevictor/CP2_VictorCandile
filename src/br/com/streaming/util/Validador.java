package br.com.streaming.util;

/**
 * Classe utilitária com métodos estáticos de validação reutilizáveis.
 * Marcada como final para não ser herdada (é um utilitário, não uma base).
 */
public final class Validador {

    // Impede instanciação
    private Validador() {}

    /**
     * Lança exceção se o texto for nulo ou em branco.
     * @param valor  texto a validar
     * @param campo  nome do campo (usado na mensagem de erro)
     */
    public static void exigirTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " não pode ser nulo ou vazio.");
        }
    }

    /**
     * Lança exceção se o número não estiver no intervalo [min, max).
     */
    public static void exigirIntervalo(int valor, int min, int max, String campo) {
        if (valor < min || valor >= max) {
            throw new IllegalArgumentException(
                    campo + " deve estar entre " + min + " e " + (max - 1) + ".");
        }
    }

    /**
     * Lança exceção se o objeto for nulo.
     */
    public static void exigirNaoNulo(Object obj, String campo) {
        if (obj == null) {
            throw new IllegalArgumentException(campo + " não pode ser nulo.");
        }
    }

    /**
     * Verifica se um e-mail possui formato mínimo válido (contém '@').
     */
    public static boolean emailValido(String email) {
        return email != null && email.contains("@") && !email.isBlank();
    }
}