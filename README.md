# Wallace Dev Bot

Bem-vindo ao Wallace Dev Bot! Este projeto é um bot para Discord que utiliza a biblioteca JDA e Lavalink para fornecer funcionalidades avançadas, como reprodução de músicas e gerenciamento de permissões de usuário em servidores Discord.

## Funcionalidades

O bot possui várias funcionalidades, divididas em comandos que você pode utilizar para interagir com o bot no Discord.

### Comandos de Música

- **`/play [nome]`**: Toca a música especificada. Pode ser uma URL ou uma busca no YouTube. O bot se conectará ao canal de voz onde você está, se necessário.
- **`/nowplaying`**: Exibe a música que está sendo reproduzida atualmente no canal de voz.
- **`/skip`**: Pula a música atual e passa para a próxima na fila.
- **`/stop`**: Para a reprodução de música e limpa a fila de músicas.
- **`/queue`**: Mostra a fila de músicas que está agendada para reprodução.
- **`/repeat`**: Alterna a repetição da música atual. Se ativado, a música atual será repetida até que o comando seja desativado.

### Comandos de Gerenciamento

- **`/mute [usuário]`**: Muta um usuário específico se você tiver a permissão necessária.
- **`/unmute [usuário]`**: Desmuta um usuário específico se você tiver a permissão necessária.

## Gerenciamento de Áudio

O gerenciamento de áudio no bot é realizado através de várias classes que trabalham juntas para fornecer uma experiência de reprodução de música fluida e eficiente.

### PlayerManager

Gerencia instâncias de `GuildMusicManager` para diferentes guildas. Utiliza o `AudioPlayerManager` para carregar e tocar faixas e mantém uma instância única (Singleton) para gerenciar os players.

- **Funções principais:**
  - Cria e mantém instâncias de `GuildMusicManager` para cada guilda.
  - Carrega e toca faixas usando o `AudioPlayerManager`.

### GuildMusicManager

Gerencia o player de áudio e o encaminhador de áudio para uma guilda específica. Cria instâncias de `TrackScheduler` e `AudioForwarder`, e mantém a fila de faixas e o player de áudio.

- **Funções principais:**
  - Gerencia o player de áudio e a fila de faixas para a guilda.
  - Cria e gerencia o `TrackScheduler` e o `AudioForwarder`.

### TrackScheduler

Agenda e controla a reprodução de faixas. Gerencia uma fila de faixas e implementa a lógica para repetir a faixa atual se a repetição estiver ativada.

- **Funções principais:**
  - Agenda e gerencia a fila de faixas.
  - Controla a repetição da faixa atual, se ativada.

### AudioForwarder

Lida com o envio de áudio do `AudioPlayer` para o canal de voz do Discord. Implementa a interface `AudioSendHandler` para fornecer áudio ao canal de voz.

- **Funções principais:**
  - Envia o áudio do `AudioPlayer` para o canal de voz do Discord.
  - Gerencia o buffer de áudio e a conexão com o canal de voz.

## Gerenciamento de Interações

Além dos comandos básicos, o bot também suporta interações avançadas com o Discord, como botões e modais.

### Manager

Coordena várias interações do bot, incluindo a configuração de botões e modais. Garante que as interações sejam gerenciadas de forma eficiente e que as ações dos usuários sejam processadas corretamente.

### Embed

Cria e formata mensagens enriquecidas (embeds) enviadas pelo bot. Essas mensagens podem incluir informações detalhadas, imagens e outros elementos visuais.

- **Funções principais:**
  - Cria e formata mensagens enriquecidas.
  - Adiciona detalhes visuais às respostas do bot.

### Buttons

Permite a criação de botões interativos nas mensagens. Eles permitem que os usuários realizem ações diretamente a partir das mensagens enviadas pelo bot.

- **Funções principais:**
  - Cria botões interativos.
  - Gerencia cliques e interações com botões.

### Modals

Formulários interativos que permitem a coleta de informações dos usuários em uma interface de entrada mais complexa. São usados para obter dados detalhados ou opções do usuário.

- **Funções principais:**
  - Cria e exibe modais para coleta de informações.
  - Processa entradas de usuários a partir de modais.

### Sum

Realiza operações matemáticas básicas, como a soma de valores. Pode ser usada para cálculos e análises simples dentro do bot.

- **Funções principais:**
  - Realiza operações matemáticas básicas.
  - Processa e retorna resultados de cálculos.

### Roles

Gerencia permissões e atribuições de cargos (roles) para usuários dentro do servidor Discord. Permite a adição e remoção de cargos, bem como a verificação das permissões dos usuários.

- **Funções principais:**
  - Adiciona e remove cargos de usuários.
  - Verifica e gerencia permissões de usuários.

## Instalação

Para instalar e rodar o bot, siga os seguintes passos:

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git
   cd SEU_REPOSITORIO



