import Sound.SoundPlayer;
import States.Game;
import States.GameOverState;
import View.GameOverViewer;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;
import java.lang.reflect.Field;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GameOverStateTest {

    @Mock
    private Game mockGame;

    @Mock
    private SoundPlayer mockSoundPlayer;

    @Mock
    private GameOverViewer mockGameOverViewer;

    @Mock
    private Screen mockScreen;

    private GameOverState gameOverState;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Initialize GameOverState instance
        gameOverState = new GameOverState();

        // Mock behavior for game-related methods
        when(mockGame.getScreen()).thenReturn(mockScreen);

        // Use reflection to inject the mocked GameOverViewer into the GameOverState instance
        try {
            // Inject the mockGameOverViewer into the GameOverState
            Field viewerField = GameOverState.class.getDeclaredField("gameOverViewer");
            viewerField.setAccessible(true);
            viewerField.set(gameOverState, mockGameOverViewer); // Inject the mock
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testRunShouldCloseScreen() throws IOException, InterruptedException {
        // Call the run method
        gameOverState.run(mockGame);

        // Verify that screen.close() was called
        verify(mockScreen, times(1)).close();
    }

    @Test
    void testRunShouldReturnFalse() throws IOException, InterruptedException {
        // Call the run method and capture the return value
        boolean result = gameOverState.run(mockGame);

        // Verify that the result is false (as per the implementation of run())
        assertFalse(result, "run() should return false");
    }
}
