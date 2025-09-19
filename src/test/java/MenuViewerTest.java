package View;

import Controller.MenuController;
import Sound.SoundPlayer;
import States.Game;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TerminalSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;
import static org.mockito.Mockito.*;

class MenuViewerTest {

    @Mock private Screen mockScreen;
    @Mock private Game mockGame;
    @Mock private MenuController mockMenuController;
    @Mock private TextGraphics mockGraphics;
    @Mock private SoundPlayer mockSoundPlayer;
    @Mock private TerminalSize mockTerminalSize;

    private MenuViewer menuViewer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        menuViewer = new MenuViewer();
        when(mockGame.getScreen()).thenReturn(mockScreen);
        when(mockGame.getSoundPlayer()).thenReturn(mockSoundPlayer);
        when(mockScreen.newTextGraphics()).thenReturn(mockGraphics);

        // Mock the TerminalSize
        mockTerminalSize = mock(TerminalSize.class);
        when(mockScreen.getTerminalSize()).thenReturn(mockTerminalSize);
        when(mockTerminalSize.getRows()).thenReturn(24);
        when(mockTerminalSize.getColumns()).thenReturn(80);
    }

    @Test
    void testShowMenu() throws IOException {
        // Arrange
        BasicWindow mockMenuWindow = mock(BasicWindow.class);
        MultiWindowTextGUI mockGui = mock(MultiWindowTextGUI.class);
        when(mockScreen.newTextGraphics()).thenReturn(mockGraphics);

        // Act
        menuViewer.showMenu(mockGame, mockMenuController);

        // Assert: Verify sound is played
        verify(mockSoundPlayer).playSound("src\\main\\resources\\Menu.wav");

        // Assert: Verify that the menu was shown
        verify(mockGui).addWindowAndWait(mockMenuWindow);

        // Verify that the correct components were added (buttons, labels)
        verify(mockMenuWindow).setComponent(any(Panel.class));
        verify(mockMenuWindow).setHints(anyList());
    }

    @Test
    void testShowDecision() throws IOException, InterruptedException {
        // Arrange
        Screen mockScreen = mock(Screen.class);
        String message = "Are you sure you want to exit?";
        String prompt = "Y/N";

        // Act
        menuViewer.showDecision(mockScreen, message, prompt, mockGame);

        // Assert: Ensure the correct interactions
        verify(mockScreen).newTextGraphics();  // Verify that new text graphics is created
        verify(mockScreen).getTerminalSize();  // Verify terminal size is fetched
        verify(mockScreen).refresh();          // Verify screen refresh
        verify(mockGame).getSoundPlayer();     // Verify sound player was accessed
    }

    @Test
    void testShowLoadingLevelScreen() throws IOException, InterruptedException {
        // Arrange
        int levelNumber = 1;

        // Act
        menuViewer.showLoadingLevelScreen(mockScreen, levelNumber);

        // Assert: Verify that the clear method is called
        verify(mockScreen).clear(); // Verifying the clear method

        // Verify the other method calls related to drawing and refreshing
        verify(mockScreen).newTextGraphics(); // Verify new TextGraphics is created
        verify(mockScreen).getTerminalSize(); // Verify that terminal size was fetched
        verify(mockScreen).refresh(); // Verify screen refresh
    }
}