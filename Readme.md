# 🎵 Sistema de Streaming de Música

### Feito por: Victor Candile Monteiro Barbosa

Sistema de streaming de música em console desenvolvido em Java, aplicando todos os pilares de Programação Orientada a Objetos: encapsulamento, herança, polimorfismo, interfaces e classes abstratas.

---

## 📋 Funcionalidades

- **Cadastro de usuários** — Free (gratuito) e Premium (pago), com múltiplos planos
- **Sistema multi-usuário** — login e logoff, cada usuário com seus dados isolados
- **Catálogo de músicas** — cadastro, listagem e busca por título ou artista
- **Reprodução de músicas** — Free exibe anúncios a cada 3 músicas; Premium reproduz em alta qualidade
- **Histórico de reprodução** — registro de todas as músicas ouvidas por sessão
- **Playlists personalizadas** — Free limitado a 3; Premium ilimitado
- **Playlists automáticas** — geradas por critério: *Top*, *Recomendadas* ou *Recentes*
- **Downloads (Premium)** — baixar e remover músicas offline com controle de duplicatas
- **Estatísticas pessoais** — reproduções, playlists, anúncios vistos (Free)
- **Estatísticas do sistema** — visão geral com percentuais por tipo de usuário (Premium)

---

## 🏗️ Arquitetura

### Estrutura de Pacotes

```
src/
└── br/com/streaming/
    ├── modelo/           # Classes de domínio (entidades do sistema)
    │   ├── ItemReproducao.java     (classe abstrata)
    │   ├── Musica.java
    │   ├── Playlist.java
    │   ├── Usuario.java
    │   ├── UsuarioFree.java
    │   └── UsuarioPremium.java
    ├── servico/          # Interfaces e serviços de negócio
    │   ├── Reproduzivel.java       (interface)
    │   ├── Baixavel.java           (interface)
    │   └── GeradorRecomendacoes.java
    ├── util/             # Utilitários reutilizáveis
    │   ├── Validador.java
    │   └── FormatadorTempo.java
    └── principal/        # Ponto de entrada
        └── StreamingMusica.java
```

### Conceitos de POO aplicados

| Conceito | Onde é aplicado |
|---|---|
| **Encapsulamento** | Atributos `private`/`protected` com getters/setters validados em todas as classes |
| **Herança** | `Musica` e `Playlist` estendem `ItemReproducao`; `UsuarioFree` e `UsuarioPremium` estendem `Usuario` |
| **Classe Abstrata** | `ItemReproducao` — define estado e implementação padrão de `pausar()`/`parar()`; força subclasses a implementar `reproduzir()` e `getDuracaoTotal()` |
| **Interfaces** | `Reproduzivel` (contrato para `Musica` e `Playlist`); `Baixavel` (contrato para `UsuarioPremium`) |
| **Polimorfismo** | `ArrayList<Usuario>` armazena Free e Premium; `usuario.reproduzirMusica()` executa a versão correta automaticamente; `playlist.reproduzir()` via interface |
| **`@Override`** | Em todos os métodos sobrescritos: `reproduzirMusica()`, `criarPlaylist()`, `reproduzir()`, `pausar()`, `parar()`, `getDuracaoTotal()` |
| **`instanceof` e casting** | Verificação de tipo em `listarUsuarios()`, `exibirEstatisticasSistema()`, menus e `processarOpcao()` |
| **`final`** | Setores críticos em `Usuario` (`setNome`, `setEmail`, `exibirHistorico`, etc.) e métodos de gerenciamento em `Playlist`; classes utilitárias `Validador` e `FormatadorTempo` |

---

## 🚀 Como Executar

### Pré-requisitos

- Java JDK 11 ou superior instalado
- Terminal / Prompt de Comando

### Compilação

Na raiz do projeto (onde está a pasta `src`), execute:

```bash
# Compilar todos os arquivos para a pasta out/
javac -d out -sourcepath src src/br/com/streaming/principal/StreamingMusica.java
```

### Execução

```bash
java -cp out br.com.streaming.principal.StreamingMusica
```

### Compilação manual (arquivo por arquivo)

Caso prefira compilar sem a flag `-sourcepath`:

```bash
mkdir -p out
javac -d out src/br/com/streaming/util/Validador.java
javac -d out src/br/com/streaming/util/FormatadorTempo.java
javac -d out -cp out src/br/com/streaming/servico/Reproduzivel.java
javac -d out -cp out src/br/com/streaming/modelo/ItemReproducao.java
javac -d out -cp out src/br/com/streaming/modelo/Musica.java
javac -d out -cp out src/br/com/streaming/servico/Baixavel.java
javac -d out -cp out src/br/com/streaming/modelo/Playlist.java
javac -d out -cp out src/br/com/streaming/modelo/Usuario.java
javac -d out -cp out src/br/com/streaming/modelo/UsuarioFree.java
javac -d out -cp out src/br/com/streaming/modelo/UsuarioPremium.java
javac -d out -cp out src/br/com/streaming/servico/GeradorRecomendacoes.java
javac -d out -cp out src/br/com/streaming/principal/StreamingMusica.java
java -cp out br.com.streaming.principal.StreamingMusica
```

---

## 👤 Autor

- **Nome:** Seu Nome Aqui
- **RA:** Seu RA Aqui
- **Curso:** Análise e Desenvolvimento de Sistemas
- **Instituição:** FIAP

---

## 📅 Histórico de Checkpoints

| # | Tema | Conceitos |
|---|---|---|
| CP1 | Classes e Objetos | Criação de classes, atributos, métodos, instâncias |
| CP2 | Encapsulamento | `private`, getters/setters, validações |
| CP3 | Construtores | Construtores padrão e parametrizados, `this()` |
| CP4 | Herança | `extends`, `super`, hierarquia de classes |
| CP5 | Polimorfismo | `@Override`, `instanceof`, casting, `final`, listas polimórficas |
| CP6 | Interfaces e Pacotes | `interface`, classe abstrata, pacotes `br.com.streaming.*`, arquitetura profissional |