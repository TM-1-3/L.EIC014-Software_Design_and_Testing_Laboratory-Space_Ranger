import States.*;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.*;
import Controller.LevelController;
import Model.Level;
import Sound.SoundPlayer;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.lang.reflect.Field;
class GameTest {
    private Game game;
    private Level mockLevel1;
    private Level mockLevel2;
    private Level mockLevel3;
    private LevelController mockLevel1Controller;
    private LevelController mockLevel2Controller;
    private LevelController mockLevel3Controller;
    private SoundPlayer mockSoundPlayer;
    @BeforeEach
    void setUp() throws IOException {
        // Mock the dependencies
        mockLevel1 = mock(Level.class);
        mockLevel2 = mock(Level.class);
        mockLevel3 = mock(Level.class);
        mockLevel1Controller = mock(LevelController.class);
        mockLevel2Controller = mock(LevelController.class);
        mockLevel3Controller = mock(LevelController.class);
        mockSoundPlayer = mock(SoundPlayer.class);
        // Spy on the singleton instance to inject mocks
        game = spy(Game.getInstance());
        // Inject mocks into the Game instance
        doReturn(mockLevel1).when(game).getLevel1();
        doReturn(mockLevel2).when(game).getLevel2();
        doReturn(mockLevel3).when(game).getLevel3();
        doReturn(mockLevel1Controller).when(game).getLevel1Controller();
        doReturn(mockLevel2Controller).when(game).getLevel2Controller();
        doReturn(mockLevel3Controller).when(game).getLevel3Controller3();
        doReturn(mockSoundPlayer).when(game).getSoundPlayer();
    }
    @Test
    void testSingletonInstance() throws IOException {
        Game instance1 = Game.getInstance();
        Game instance2 = Game.getInstance();
        assertSame(instance1, instance2, "Game.getInstance() should return the same instance");
    }
    @Test
    void testSetStateToMenu() throws NoSuchFieldException, IllegalAccessException {
        game.setState(GameStates.MENU);
        Field currentStateField = Game.class.getDeclaredField("currentState");
        currentStateField.setAccessible(true);
        GameState currentState = (GameState) currentStateField.get(game);
        assertTrue(currentState instanceof MenuState, "Current state should be MenuState");
    }
    @Test
    void testSetStateToLevel1() throws NoSuchFieldException, IllegalAccessException {
        game.setState(GameStates.LEVEL_1);
        // Use reflection to access the private field `currentState`
        Field currentStateField = Game.class.getDeclaredField("currentState");
        currentStateField.setAccessible(true);
        GameState currentState = (GameState) currentStateField.get(game);
        assertTrue(currentState instanceof Level1State, "Current state should be Level1State");
    }
    @Test
    void testCheckIfGameContinues() {
        // Mock the behavior of the level controller
        when(mockLevel1Controller.runLevel(mockLevel1, game, "testSoundPath")).thenReturn(true);
        boolean result = game.checkIfGameContinues(mockLevel1, mockLevel1Controller, "testSoundPath");
        assertTrue(result, "checkIfGameContinues should return true");
        verify(mockLevel1Controller, times(1)).runLevel(mockLevel1, game, "testSoundPath");
    }
    @Test
    void testCheckIfGameStops() {
        // Mock the behavior of the level controller
        when(mockLevel1Controller.runLevel(mockLevel1, game, "testSoundPath")).thenReturn(false);
        boolean result = game.checkIfGameContinues(mockLevel1, mockLevel1Controller, "testSoundPath");
        assertFalse(result, "checkIfGameContinues should return false");
        verify(mockLevel1Controller, times(1)).runLevel(mockLevel1, game, "testSoundPath");
    }
    @Test
    void testStartGameLoop() throws IOException, InterruptedException, NoSuchFieldException, IllegalAccessException {
        // Create a mock for the GameState
        GameState mockState = mock(GameState.class);
        // Use reflection to access the private 'currentState' field
        Field currentStateField = Game.class.getDeclaredField("currentState");
        currentStateField.setAccessible(true);
        // Set the 'currentState' field to the mock state
        currentStateField.set(game, mockState);
        // Mock the TerminalScreen returned by getScreen
        TerminalScreen mockScreen = mock(TerminalScreen.class);
        doReturn(mockScreen).when(game).getScreen();
        // Simulate the state running successfully and return false to end the game loop
        when(mockState.run(game)).thenReturn(false);
        // Call the method under test
        game.start();
        // Verify that the mock state was run and the screen was closed
        verify(mockState, times(1)).run(game);  // Verify that run() was called
        verify(mockScreen, times(1)).close();   // Verify that close() was called on the mock screen
    }
    @Test
    void testStartGameLoopWithNullState() throws IOException, InterruptedException, NoSuchFieldException, IllegalAccessException {
        // Create a mock for the TerminalScreen
        TerminalScreen mockScreen = mock(TerminalScreen.class);
        // Use reflection to access the private 'currentState' field
        Field currentStateField = Game.class.getDeclaredField("currentState");
        currentStateField.setAccessible(true);
        // Set the 'currentState' field to null using reflection
        currentStateField.set(game, null);
        // Mock the behavior of getScreen() to return the mock screen
        doReturn(mockScreen).when(game).getScreen();  // Mock getScreen() to return the mock screen
        // Call the method under test
        game.start();
        // Verify that screen.close() is called once because the state is null
        verify(mockScreen, times(1)).close();  // Verify that close() was called on the mock screen
    }
}
