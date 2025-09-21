<img src='https://sigarra.up.pt/feup/pt/imagens/LogotipoSI' width="30%"/>

<div align="center">
🌍 <a href="README.md">English</a> | 🇵🇹 <a href="README.pt.md">Português</a>
</div>

<h3 align="center">BSc in Informatics and Computing Engineering<br> L.EIC014 - Software Design and Testing Laboratory<br> 2024/2025 </h3>

---
<h3 align="center"> Collaborators &#129309 </h2>

<div align="center">

| Name         | Number      |
|--------------|-------------|
| João Marques | up202307612 |
| Tomás Morais | up202304692 |
| Tomás Silva  | up202307796 |

Grade : 16,2

</div>

# Space Ranger Project Report

*The Space can be a dangerous place. In this 2D space shooter, traverse through the cosmos in a heart pumping adventure where you will dodge asteroids, fight the hardest battles and reclaim space from your enemies*

* [Controls](#controls)  
  * [Menu Controls](#menu-controls)  
  * [Level Controls](#level-controls)  
* [Game Screenshots](#game-screenshots)  
* [Implemented Features](#implemented-features)  
* [Structure and Design Patterns](#structure-and-design-patterns)  
  * [Singleton](#singleton)  
  * [Model - View - Controller (MVC)](#model---view---controller-mvc)  
  * [State](#state)  
  * [Game Loop Pattern](#game-loop-pattern)  
* [Sound Management](#sound-management)  
* [Known Code Smells](#known-code-smells)  
* [UML Class Diagram](#uml-class-diagram)  
* [Testing](#testing)  
  * [Test Coverage](#test-coverage)  

## Controls

### Menu Controls

- Up/Down Arrow Key -> Navigate through options;
- `ENTER` -> Select option;
- Key `y`/`Y` -> Confirm exit of the game; *
- Key `n`/`N` -> Go back to the menu. *
  
 * When the `Exit` option is selected

### Level Controls

- Up Arrow Key -> Move the ship up;
- Down Arrow Key -> Move the ship down;
- Key `a`/`A` -> Fire projectile;
- Key `s`/`S` -> Fire bomb;
- Key `q`/`Q` -> Exit the game.

## Game Screenshots

<div align="center">

<img src="docs/Screenshots/Menu.gif" alt="Menu" width="1000"/><br>

*Gif 1 - Menu*

<br><br>

<img src="docs/Screenshots/Level1.gif" alt="Level 1" width="1000"/><br>

*Gif 2 - Level 1*

<br><br>

<img src="docs/Screenshots/Level2.gif" alt="Level 2" width="1000"/><br>

*Gif 3 - Level 2*

<br><br>

<img src="docs/Screenshots/Level3.gif" alt="Level 3" width="1000"/><br>

*Gif 4 - Level 3*

<br><br>

<img src="docs/Screenshots/gameOverScreen.png" alt="Game Over Screen" width="1000"/><br>

Fig. 1 - Game Over Screen

<br><br>

<img src="docs/Screenshots/victoryScreen.png" alt="Victory Screen" width="1000"/><br>

*Fig. 2 - Victory Screen*

</div>


## Implemented Features

**Menu** - When the game starts, a menu is displayed. On top the name of the game, `Space Ranger`, is shown, followed by four available options: `Start Game (Level1)`, `Start Level 2`, `Start Level 3` and `Exit`. The player can navigate through the menu using the `Up` and `Down` arrow keys and select an option by pressing `ENTER`.

**Menu Music** - While the player stays in the menu, background music will play.

**Start Level Options** - If the player selects one of the `Start Level` options, the game begins at the referenced level. For example, selecting `Start Level 2` starts at level 2. 

**Starting Level Screen** - After one of the `Start Level` options is selected, the screen turns black and displays `Starting Level X`, for a few seconds before the level begins.

**Exit Option** - If the player selects `Exit`, the screen turns black with the message `Are you sure you want to exit?`. At this point, pressing `Y` ends the game; pressing `N` returns to the menu.

**Levels** - The game has 3 levels, each progressively more difficult. Difficulty increases due to faster asteroids, more enemies, tougher enemy types, and more projectiles.

**Ship** - The player controls a ship with `3` health points. Each hit reduces health by `1`. At `0`, it’s `GAME OVER`.

**Moving The Ship** - The ship moves up or down with arrow keys.

**Firing Projectiles** - Pressing `A` fires a projectile. It’s destroyed if it hits an asteroid, enemy, or enemy projectile.

**Firing Bombs** - Pressing `S` fires a bomb. Bombs cannot be destroyed and clear a `2x2` radius. The ship has `5` bombs.

**Asteroids** - Infinite asteroids appear from the right. Colliding with one = `GAME OVER`.

**Enemies** - Enemies come in three types: `blue` (`1` health), `yellow` (`3`), `red` (`5`). All are destroyed by a single bomb.

**Moving Enemies** - Enemies move left but stop at a certain `x` coordinate.

**Projectiles Fired by Enemies** - Enemies occasionally fire projectiles, destroyed upon collision.

**Explosions** - Triggered when an enemy dies, the ship is hit, or projectiles collide.

**Level Design** - Space-themed scenery, bordered by grey frame. Level number at top, controls at bottom.

**Health Bar** - Top left, shown as green hearts.

**Remaining Bombs Displayer** - Below health bar, shows bombs left.

**Remaining Enemies Displayer** - Top right, shows enemies remaining.

**Stars** - Constantly flash in the background.

**Level Music** - Unique track per level.

**Sound Effects** - For projectiles, bombs, and explosions.

**Game Progression** - Defeating all enemies progresses to the next level.

**Victory Screen** - After level 3, displays `YOU WON!! THANKS FOR PLAYING`, with victory audio.

**Quitting the Game** - Pressing `Q` exits the game.

**Game Over Screen** - If the ship is destroyed or game is exited, displays `GAME OVER`, with audio.

## Structure and Design Patterns

The `Application` class contains `main`, which initializes `Game` and calls `start()`.

We implemented: `Singleton`, `Model-View-Controller (MVC)`, `State`, and `Game Loop`.

## Singleton

### Reasons

Certain classes should only have one instance during runtime. For that, `Singleton` was used.

### Implementation

**`Game`, `Level1`, `Level2`, `Level3`** - Use `getInstance()` to check/create a single instance.

### Consequences

- Only one instance exists at runtime;  
- Better runtime control.

## Model - View - Controller (MVC)

### Reasons

Originally, one class mixed Model, View, and Controller logic → violating the Single Responsibility Principle. Splitting into `Model`, `View`, and `Controller` solved this.

### Implementation

#### Model

Contains the data and logic of game elements.

- **`Element`** - General base for all elements (with coordinates).  
- **`Position`** - Defines `x`/`y` coordinates.  
- **`LiveElement`** - Elements with health (e.g., `Ship`, `Enemy`).  
- **`Ship`** - Subclass of `LiveElement`.  
- **`Enemy`** - Abstract subclass of `LiveElement`. Generalizes:  
  - **`EasierEnemy`**, **`AverageEnemy`**, **`HarderEnemy`**.  
- **`ShipProjectile`**, **`EnemyProjectile`**, **`Bomb`**, **`Explosion`**, **`Boundary`**, **`Asteroid`**, **`Star`** - Constructors & drawing logic.  
- **`Level`** - Generalizes levels; has abstract methods for specifics.  
- **`Level1`**, **`Level2`**, **`Level3`** - Implement details of each level.  

#### View

Responsible for visuals.

- **`LevelViewer`** - `drawScreen()`, `drawLevel()`, `drawBeforeGameOver()`.  
- **`ElementGroupViewer`** - Interface for drawing grouped elements.  
- **`AsteroidViewer`**, **`ProjectileViewer`**, **`BombViewer`**, **`ExplosionViewer`**, **`EnemyViewer`**, **`BoundaryViewer`**, **`ShipProjectileViewer`**, **`EnemyProjectileViewer`**, **`StarViewer`** - Implement element drawing.  
- **`ShipViewer`** - Draws the ship.  
- **`StatusBar`** - Interface for status bars.  
- **`BombBar`**, **`ControlsBar`**, **`EnemiesBar`**, **`HealthBar`**, **`LevelBar`** - Implement status bars.  
- **`BlackScreenWithText`** - Interface for black screen + white text.  
- **`GameCompletionViewer`**, **`GameOverViewer`** - Implement `BlackScreenWithText`.  
- **`MenuViewer`** - Draws menu + black screens (`Loading Level`, `Exit?`).  

#### Controller

Handles input and element behavior.

- **`LevelController`** - `processKey()`, `updateGame()`, `runLevel()`.  
- **`ShipController`** - Handles ship movement.  
- **`EnemyController`** - Handles enemy movement.  
- **`ProjectileController`** - General base for projectile logic.  
- **`AgainstEnemiesProjectileController`** - For `ShipProjectileController`, `BombController`. Moves right.  
- **`AgainstShipProjectileController`** - For `EnemyProjectileController`, `AsteroidController`. Moves left.  
- **`ShipProjectileController`**, **`EnemyProjectileController`**, **`BombController`**, **`AsteroidController`** - Collision + movement logic.  
- **`MenuController`** - `startLevel()`, `handleExit()`, `handleInputInExit()`.  

### Consequences

- More organized code;  
- Easier to modify;  
- Closer to Single Responsibility Principle.  

## State

### Reasons

`Menu`, `Game Over`, `Victory`, and `Level` classes can all be treated as states of `Game`.

### Implementation

- **`GameStates`** - Enum of 6 states + `NULL`.  
- **`GameState`** - Interface for state logic + transitions.  
- **`MenuState`** - Menu logic.  
- **`Level1State`**, **`Level2State`** - Handle transitions between levels or to `Game Over`.  
- **`Level3State`** - Transitions to `Game Completion` or `Game Over`.  
- **`GameOverState`**, **`GameCompletionState`** - End screens.  
- **`Game`** - Maintains running state via `start()`, updates with `setState()`.  

### Consequences

- State transitions separated from rest of code;  
- Clearer and explicit game flow.  

## Game Loop Pattern

### Reasons

We wanted frame rate control (`60 FPS`) for smoothness.

### Implementation

- **`LevelController`** - `runLevel()` loop calls `updateGame()`, `drawLevel()`, `processKey()`.  
- **`Game`** - `start()` initializes resources, runs while state ≠ `NULL`.  

### Consequences

- Smooth frame rate;  
- Continuous updates;  
- More immersive experience.  

## Sound Management

**`SoundPlayer`** - `playSound(path)`, `stopSound()`.  

Audio files (`.wav`) stored in `resources/`.  

## Known Code Smells

All `Error Prone` issues fixed, no major smells remain.  

## UML Class Diagram

![Alt text](docs/UML_Class_Diagram.png)

## Testing

### Test Coverage

***Note:** despite some failed tests, we kept them to increase coverage.*

![Alt text](docs/testCoverage.png)
