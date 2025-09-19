package View;

import Model.Bomb;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class BombViewerTest {

    @Mock
    private TextGraphics mockGraphics;

    @Mock
    private Bomb mockBomb1;

    @Mock
    private Bomb mockBomb2;

    private BombViewer bombViewer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bombViewer = new BombViewer();
    }

    @Test
    void testDrawWithMultipleBombs() throws IOException {
        // Arrange
        List<Bomb> bombs = Arrays.asList(mockBomb1, mockBomb2);

        // Act
        bombViewer.draw(mockGraphics, bombs);

        // Assert
        verify(mockBomb1, times(1)).draw(mockGraphics);
        verify(mockBomb2, times(1)).draw(mockGraphics);
    }

    @Test
    void testDrawWithNoBombs() throws IOException {
        // Arrange
        List<Bomb> bombs = Arrays.asList();

        // Act
        bombViewer.draw(mockGraphics, bombs);

        // Assert
        // No interaction should occur
        verifyNoInteractions(mockBomb1, mockBomb2, mockGraphics);
    }

    @Test
    void testDrawWithSingleBomb() throws IOException {
        // Arrange
        List<Bomb> bombs = Arrays.asList(mockBomb1);

        // Act
        bombViewer.draw(mockGraphics, bombs);

        // Assert
        verify(mockBomb1, times(1)).draw(mockGraphics);
        verifyNoInteractions(mockBomb2);
    }
}