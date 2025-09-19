package View;

import Model.*;
import Sound.SoundPlayer;
import States.Game;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

class LevelViewerTest {

    private LevelViewer levelViewer;

    @Mock private Screen mockScreen;
    @Mock private Game mockGame;
    @Mock private Level mockLevel;
    @Mock private Ship mockShip;
    @Mock private SoundPlayer mockSoundPlayer;
    @Mock private TextGraphics mockGraphics;
    @Mock private List<Star> mockStars;
    @Mock private List<Boundary> mockBoundaries;
    @Mock private List<Asteroid> mockAsteroids;
    @Mock private List<Enemy> mockEnemies;
    @Mock private List<ShipProjectile> mockProjectiles;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        levelViewer = new LevelViewer(mockScreen);
        when(mockScreen.newTextGraphics()).thenReturn(mockGraphics);
        when(mockGame.getSoundPlayer()).thenReturn(mockSoundPlayer);
        when(mockGame.getScreen()).thenReturn(mockScreen);

        // Mocking the Level object
        when(mockLevel.getShip()).thenReturn(mockShip);
        when(mockShip.getHealth()).thenReturn(3);
        when(mockLevel.getStars()).thenReturn(mockStars);  // Mock the stars list
        when(mockLevel.getBoundaries()).thenReturn(mockBoundaries);
        when(mockLevel.getAsteroids()).thenReturn(mockAsteroids);
        when(mockLevel.getBombsToFire()).thenReturn(mock(List.class));
        when(mockLevel.getEnemies()).thenReturn(mockEnemies);
        when(mockLevel.getShipProjectiles()).thenReturn(mockProjectiles);
        when(mockLevel.getBombs()).thenReturn(mock(List.class));
        when(mockLevel.getExplosions()).thenReturn(mock(List.class));
        when(mockLevel.getEnemyProjectiles()).thenReturn(mock(List.class));
        when(mockLevel.getLevelNumber()).thenReturn(1);
        when(mockLevel.getMaxNumberEnemies()).thenReturn(5);
    }

    @Test
    void testDrawBeforeGameOver() throws IOException, InterruptedException {
        // Act
        levelViewer.drawBeforeGameOver(mockGame, mockLevel);

        // Assert
        verify(mockSoundPlayer).playSound("src\\main\\resources\\bombExplosion.wav");
        verify(mockLevel).draw(mockGraphics);
        verify(mockScreen).close();
    }

    @Test
    void testDrawScreen() throws IOException {
        // Arrange
        String soundPath = "path/to/sound.wav";

        // Act
        levelViewer.drawScreen(soundPath, mockGame);

        // Assert - Use matchers for all arguments
        verify(mockGame.getScreen()).clear();
        verify(mockGraphics).setBackgroundColor(TextColor.ANSI.BLACK);
        verify(mockGraphics).fillRectangle(any(), eq(mockScreen.getTerminalSize()), eq(' '));
        verify(mockSoundPlayer).playSound(eq(soundPath));
    }

    @Test
    void testDrawLevel() throws IOException {
        // Arrange
        when(mockLevel.getShip()).thenReturn(mockShip);

        // Act
        levelViewer.drawLevel(mockLevel);

        // Assert - Verifying the expected interactions with the mock objects
        verify(mockLevel).draw(mockGraphics);
        verify(mockGraphics).setForegroundColor(TextColor.Factory.fromString("#00ff04"));
        verify(mockGraphics).putString(any(), eq("Health: ♥ ♥ ♥"));
        verify(mockGraphics).putString(eq(new TerminalPosition(100, 1)), eq("☠ Remaining: 5"));
        verify(mockGraphics).putString(eq(new TerminalPosition(55, 1)), eq("LEVEL 1"));
        verify(mockGraphics).putString(eq(new TerminalPosition(2, 0)), eq("Bombs: 0"));
    }

    // Utility mock method for creating lists
    private <T> List<T> mockList() {
        return mock(List.class);
    }
}