<img src='https://sigarra.up.pt/feup/pt/imagens/LogotipoSI' width="30%"/>

<div align="center">
🌍 <a href="README.md">English</a> | 🇵🇹 <a href="README.pt.md">Português</a>
</div>

<h3 align="center">Licenciatura em Engenharia Informática e Computação<br> L.EIC014 - Laboratório de Desenho e Teste de Software<br> 2024/2025 </h3>

---
<h3 align="center"> Colaboradores &#129309 </h2>

<div align="center">

| Nome         | Número      |
|--------------|-------------|
| João Marques | up202307612 |
| Tomás Morais | up202304692 |
| Tomás Silva  | up202307796 |

Nota: 16,2

</div>

# Relatório do Projeto *Space Ranger*

*O Espaço pode ser um lugar perigoso. Neste shooter 2D, atravessa o cosmos numa aventura emocionante onde vais desviar-te de asteroides, enfrentar batalhas difíceis e recuperar o espaço dos teus inimigos.*

* [Controlos](#controlos)  
  * [Controlos do Menu](#controlos-do-menu)  
  * [Controlos do Nível](#controlos-do-nível)  
* [Capturas de Ecrã do Jogo](#capturas-de-ecrã-do-jogo)  
* [Funcionalidades Implementadas](#funcionalidades-implementadas)  
* [Estrutura e Padrões de Design](#estrutura-e-padrões-de-design)  
  * [Singleton](#singleton)  
  * [Model - View - Controller (MVC)](#model---view---controller-mvc)  
  * [State](#state)  
  * [Padrão Game Loop](#padrão-game-loop)  
* [Gestão de Som](#gestão-de-som)  
* [Code Smells Conhecidos](#code-smells-conhecidos)  
* [Diagrama de Classes UML](#diagrama-de-classes-uml)  
* [Testes](#testes)  
  * [Cobertura de Testes](#cobertura-de-testes)  

## Controlos

### Controlos do Menu

- Seta para Cima/Baixo -> Navegar pelas opções;
- `ENTER` -> Selecionar opção;
- Tecla `y`/`Y` -> Confirmar saída do jogo; *
- Tecla `n`/`N` -> Voltar ao menu. *
  
 * Quando a opção `Exit` está selecionada

### Controlos do Nível

- Seta para Cima -> Mover a nave para cima;
- Seta para Baixo -> Mover a nave para baixo;
- Tecla `a`/`A` -> Disparar projétil;
- Tecla `s`/`S` -> Disparar bomba;
- Tecla `q`/`Q` -> Sair do jogo.

## Capturas de Ecrã do Jogo

<div align="center">

<img src="docs/Screenshots/Menu.gif" alt="Menu" width="1000"/><br>

*Gif 1 - Menu*

<br><br>

<img src="docs/Screenshots/Level1.gif" alt="Nível 1" width="1000"/><br>

*Gif 2 - Nível 1*

<br><br>

<img src="docs/Screenshots/Level2.gif" alt="Nível 2" width="1000"/><br>

*Gif 3 - Nível 2*

<br><br>

<img src="docs/Screenshots/Level3.gif" alt="Nível 3" width="1000"/><br>

*Gif 4 - Nível 3*

<br><br>

<img src="docs/Screenshots/gameOverScreen.png" alt="Ecrã de Game Over" width="1000"/><br>

*Fig. 1 - Ecrã de Game Over*

<br><br>

<img src="docs/Screenshots/victoryScreen.png" alt="Ecrã de Vitória" width="1000"/><br>

*Fig. 2 - Ecrã de Vitória*

</div>

## Funcionalidades Implementadas

**Menu** - Quando o jogo começa, é apresentado um menu. No topo aparece o nome do jogo, `Space Ranger`, seguido de quatro opções: `Start Game (Level1)`, `Start Level 2`, `Start Level 3` e `Exit`. O jogador pode navegar com as setas `Cima` e `Baixo`, e selecionar com `ENTER`.

**Música no Menu** - Enquanto o jogador permanece no menu, uma música de fundo é reproduzida.

**Opções de Início de Nível** - Se o jogador selecionar `Start Level`, o jogo inicia diretamente no nível escolhido.

**Ecrã de Início de Nível** - Após escolher um nível, o ecrã fica preto e mostra `Starting Level X` durante alguns segundos.

**Opção Exit** - Se o jogador selecionar `Exit`, o ecrã mostra `Are you sure you want to exit?`. Pressionar `Y` sai do jogo; `N` retorna ao menu.

**Níveis** - O jogo possui 3 níveis, cada vez mais difíceis. A dificuldade aumenta com asteroides mais rápidos, mais inimigos, inimigos mais fortes e mais projéteis.

**Nave** - O jogador controla uma nave com `3` pontos de vida. Cada colisão tira `1`. A `0`, é `GAME OVER`.

**Movimento da Nave** - A nave move-se para cima/baixo com as setas.

**Disparo de Projéteis** - Tecla `A` dispara um projétil, destruído ao atingir asteroides, inimigos ou projéteis inimigos.

**Disparo de Bombas** - Tecla `S` dispara uma bomba, indestrutível e que limpa uma área `2x2`. A nave possui `5` bombas.

**Asteroides** - Aparecem infinitamente da direita. Colidir com um = `GAME OVER`.

**Inimigos** - Existem 3 tipos: `azul` (`1` vida), `amarelo` (`3`), `vermelho` (`5`). Uma bomba destrói todos.

**Movimento dos Inimigos** - Movem-se para a esquerda até uma certa coordenada `x`.

**Projéteis dos Inimigos** - Alguns disparam projéteis que se destroem em colisão.

**Explosões** - Ocorrem quando inimigos morrem, a nave é atingida ou projéteis colidem.

**Design dos Níveis** - Cenário espacial com moldura cinzenta. Número do nível no topo, controlos no fundo.

**Barra de Vida** - No canto superior esquerdo, representada por corações verdes.

**Contador de Bombas** - Mostra quantas bombas restam.

**Contador de Inimigos Restantes** - No canto superior direito, mostra inimigos restantes.

**Estrelas** - Piscam constantemente no fundo.

**Música dos Níveis** - Cada nível tem uma faixa única.

**Efeitos Sonoros** - Para projéteis, bombas e explosões.

**Progressão** - Eliminar todos os inimigos avança para o próximo nível.

**Ecrã de Vitória** - Após o nível 3, aparece `YOU WON!! THANKS FOR PLAYING`, com música de vitória.

**Sair do Jogo** - Tecla `Q` sai do jogo.

**Ecrã de Game Over** - Se a nave for destruída ou sair do jogo, aparece `GAME OVER`, com áudio.

## Estrutura e Padrões de Design

A classe `Application` contém o `main`, que inicializa `Game` e chama `start()`.

Foram usados os padrões: `Singleton`, `Model-View-Controller (MVC)`, `State` e `Game Loop`.

## Singleton

### Razões

Algumas classes só devem ter uma instância em execução. Para isso, usámos `Singleton`.

### Implementação

**`Game`, `Level1`, `Level2`, `Level3`** - usam `getInstance()` para garantir uma única instância.

### Consequências

- Apenas uma instância em execução;  
- Maior controlo em runtime.  

## Model - View - Controller (MVC)

### Razões

Inicialmente, uma classe misturava lógica de Modelo, Vista e Controlador → violando SRP. Separar em `Model`, `View` e `Controller` resolveu.

### Implementação

#### Model

Contém dados e lógica dos elementos.

- **`Element`** - Base de todos os elementos (com coordenadas).  
- **`Position`** - Define `x`/`y`.  
- **`LiveElement`** - Elementos com vida (ex: `Ship`, `Enemy`).  
- **`Ship`** - Subclasse de `LiveElement`.  
- **`Enemy`** - Subclasse abstrata de `LiveElement`. Especializa:  
  - **`EasierEnemy`**, **`AverageEnemy`**, **`HarderEnemy`**.  
- **`ShipProjectile`**, **`EnemyProjectile`**, **`Bomb`**, **`Explosion`**, **`Boundary`**, **`Asteroid`**, **`Star`** - construtores + lógica de desenho.  
- **`Level`** - Generaliza níveis; métodos abstratos para detalhes.  
- **`Level1`**, **`Level2`**, **`Level3`** - Implementam cada nível.  

#### View

Responsável pelos visuais.

- **`LevelViewer`** - `drawScreen()`, `drawLevel()`, `drawBeforeGameOver()`.  
- **`ElementGroupViewer`** - Interface para grupos de elementos.  
- **`AsteroidViewer`**, **`ProjectileViewer`**, **`BombViewer`**, **`ExplosionViewer`**, **`EnemyViewer`**, **`BoundaryViewer`**, **`ShipProjectileViewer`**, **`EnemyProjectileViewer`**, **`StarViewer`** - desenham elementos.  
- **`ShipViewer`** - desenha a nave.  
- **`StatusBar`** - Interface para barras de estado.  
- **`BombBar`**, **`ControlsBar`**, **`EnemiesBar`**, **`HealthBar`**, **`LevelBar`** - implementam as barras.  
- **`BlackScreenWithText`** - Interface para ecrã preto + texto.  
- **`GameCompletionViewer`**, **`GameOverViewer`** - implementam `BlackScreenWithText`.  
- **`MenuViewer`** - desenha o menu + ecrãs pretos (`Loading Level`, `Exit?`).  

#### Controller

Trata input e comportamento.

- **`LevelController`** - `processKey()`, `updateGame()`, `runLevel()`.  
- **`ShipController`** - movimento da nave.  
- **`EnemyController`** - movimento dos inimigos.  
- **`ProjectileController`** - lógica geral de projéteis.  
- **`AgainstEnemiesProjectileController`** - para projéteis da nave. Movem-se para a direita.  
- **`AgainstShipProjectileController`** - para projéteis dos inimigos/asteroides. Movem-se para a esquerda.  
- **`ShipProjectileController`**, **`EnemyProjectileController`**, **`BombController`**, **`AsteroidController`** - colisão + movimento.  
- **`MenuController`** - `startLevel()`, `handleExit()`, `handleInputInExit()`.  

### Consequências

- Código mais organizado;  
- Mais fácil de alterar;  
- Mais próximo do SRP.  

## State

### Razões

`Menu`, `Game Over`, `Victory` e `Level` são estados do `Game`.

### Implementação

- **`GameStates`** - Enum de 6 estados + `NULL`.  
- **`GameState`** - Interface para lógica + transições.  
- **`MenuState`** - lógica do menu.  
- **`Level1State`**, **`Level2State`** - transições entre níveis ou para `Game Over`.  
- **`Level3State`** - transição para `Game Completion` ou `Game Over`.  
- **`GameOverState`**, **`GameCompletionState`** - ecrãs finais.  
- **`Game`** - mantém o estado em `start()`, muda com `setState()`.  

### Consequências

- Transições separadas do resto do código;  
- Fluxo do jogo mais claro.  

## Padrão Game Loop

### Razões

Queríamos controlar FPS (`60`) para fluidez.

### Implementação

- **`LevelController`** - `runLevel()` chama `updateGame()`, `drawLevel()`, `processKey()`.  
- **`Game`** - `start()` inicializa recursos e corre até estado = `NULL`.  

### Consequências

- Taxa de frames fluida;  
- Atualizações contínuas;  
- Experiência mais imersiva.  

## Gestão de Som

**`SoundPlayer`** - `playSound(path)`, `stopSound()`.  

Ficheiros `.wav` em `resources/`.  

## Code Smells Conhecidos

Todos os problemas `Error Prone` corrigidos, sem `smells` relevantes.  

## Diagrama de Classes UML

![Alt text](docs/UML_Class_Diagram.png)

## Testes

### Cobertura de Testes

***Nota:** apesar de alguns testes falhados, mantivemo-los para aumentar cobertura.*

![Alt text](docs/testCoverage.png)
