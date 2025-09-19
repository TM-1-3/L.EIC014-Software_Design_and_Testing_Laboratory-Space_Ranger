import Controller.MenuController;
import States.Game;
import View.MenuViewer;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuControllerTest {

    @Mock
    private Screen mockScreen;

    @Mock
    private BasicWindow mockMenuWindow;

    @Mock
    private Game mockGame;

    @Mock
    private MenuViewer mockMenuViewer;

    private MenuController menuController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        menuController = new MenuController();
    }

    @Test
    void testHandleInputInExitShouldReturnTrueForYes() throws IOException {
        when(mockScreen.readInput())
                .thenReturn(new KeyStroke('y', false, false))
                .thenReturn(new KeyStroke(KeyType.EOF)); // Exit loop

        boolean result = menuController.handleInputInExit(mockScreen);

        assertTrue(result);
    }

    @Test
    void testHandleInputInExitShouldReturnFalseForNo() throws IOException {
        when(mockScreen.readInput())
                .thenReturn(new KeyStroke('n', false, false))
                .thenReturn(new KeyStroke(KeyType.EOF)); // Exit loop

        boolean result = menuController.handleInputInExit(mockScreen);

        assertFalse(result);
    }
}
