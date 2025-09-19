package View;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class GameOverViewerTest {

    private GameOverViewer gameOverViewer;
    private DefaultTerminalFactory mockTerminalFactory;
    private TerminalScreen mockScreen;

    @BeforeEach
    void setUp() {
        gameOverViewer = spy(new GameOverViewer());
        mockTerminalFactory = mock(DefaultTerminalFactory.class);
        mockScreen = mock(TerminalScreen.class);
    }

    @Test
    void testShowGameOver() throws IOException, InterruptedException {
        // Arrange
        TerminalSize expectedTerminalSize = new TerminalSize(120, 30);
        when(mockTerminalFactory.setInitialTerminalSize(expectedTerminalSize)).thenReturn(mockTerminalFactory);
        when(mockTerminalFactory.createScreen()).thenReturn(mockScreen);

        doNothing().when(gameOverViewer).showBlackScreen(any(Screen.class), eq("GAME OVER"));

        // Act
        gameOverViewer.showGameOver(1);

        // Assert
        verify(mockTerminalFactory).setInitialTerminalSize(expectedTerminalSize);
        verify(mockTerminalFactory).createScreen();
        verify(mockScreen).startScreen();
        verify(gameOverViewer).showBlackScreen(mockScreen, "GAME OVER");
        verify(mockScreen).stopScreen();
        verify(mockScreen).close();
    }
}
