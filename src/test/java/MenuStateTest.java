import Controller.MenuController;
import Sound.SoundPlayer;
import States.Game;
import States.MenuState;
import View.MenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
class MenuStateTest {
    @Mock
    private Game mockGame;
    @Mock
    private MenuViewer mockMenuViewer;
    @Mock
    private MenuController mockMenuController;
    @Mock
    private SoundPlayer mockSoundPlayer;
    @InjectMocks
    private MenuState menuState;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Mock the Game's SoundPlayer
        when(mockGame.getSoundPlayer()).thenReturn(mockSoundPlayer);
        // Mock the MenuViewer and MenuController behavior
        try {
            doNothing().when(mockMenuViewer).showMenu(any(Game.class), any(MenuController.class));
        } catch (IOException e) {
            e.printStackTrace(); // No-op for test setup
        }
    }
    @Test
    void testRunShouldShowMenuSuccessfully() {
        // Call the run method
        boolean result = menuState.run(mockGame);
        // Verify that showMenu was called
        try {
            verify(mockMenuViewer, times(1)).showMenu(eq(mockGame), any(MenuController.class));
        } catch (IOException e) {
            e.printStackTrace(); // No-op for test verification
        }
        // Assert that the method returned true
        assertTrue(result);
    }
    @Test
    void testRunShouldHandleIOExceptionGracefully() {
        // Mock an IOException when showMenu is called
        try {
            doThrow(new IOException("Test exception")).when(mockMenuViewer).showMenu(any(Game.class), any(MenuController.class));
        } catch (IOException e) {
            e.printStackTrace(); // No-op for test setup
        }
        // Call the run method
        boolean result = menuState.run(mockGame);
        // Verify that showMenu was called
        try {
            verify(mockMenuViewer, times(1)).showMenu(eq(mockGame), any(MenuController.class));
        } catch (IOException e) {
            e.printStackTrace(); // No-op for test verification
        }
        // Assert that the method returned false
        assertTrue(!result); // Should exit on error
    }
}
