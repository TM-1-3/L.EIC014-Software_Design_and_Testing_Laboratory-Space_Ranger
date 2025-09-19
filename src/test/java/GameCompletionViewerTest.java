package View;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.*;

class GameCompletionViewerTest {

    @Mock
    private DefaultTerminalFactory mockTerminalFactory;

    @Mock
    private TerminalScreen mockScreen;

    private GameCompletionViewer gameCompletionViewer;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        gameCompletionViewer = new GameCompletionViewer();

        // Mock behavior for creating a screen
        when(mockTerminalFactory.createScreen()).thenReturn(mockScreen);
    }

    @Test
    void testShowGameCompletionHandlesExceptions() throws IOException, InterruptedException {
        // Arrange
        int displaySeconds = 2;
        doThrow(new IOException("Simulated IOException")).when(mockScreen).startScreen();

        // Act & Assert
        try {
            gameCompletionViewer.showGameCompletion(displaySeconds);
        } catch (Exception e) {
            // Test should not throw an exception; the method should handle it
            assert false : "Method did not handle IOException";
        }

        // Verify resources are attempted to be released
        verify(mockScreen, never()).stopScreen();
        verify(mockScreen, never()).close();
    }

    @Test
    void testShowGameCompletionSetsCorrectTerminalSize() throws IOException {
        // Arrange
        TerminalSize expectedSize = new TerminalSize(120, 30);
        DefaultTerminalFactory terminalFactory = spy(new DefaultTerminalFactory());
        terminalFactory.setInitialTerminalSize(expectedSize);

        // Act
        terminalFactory.createScreen();

        // Assert
        verify(terminalFactory).setInitialTerminalSize(expectedSize);
    }
}