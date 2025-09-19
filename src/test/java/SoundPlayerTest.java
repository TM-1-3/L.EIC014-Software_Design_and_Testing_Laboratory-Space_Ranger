import Sound.SoundPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import javax.sound.sampled.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

class SoundPlayerTest {

    @Mock
    private Clip mockClip;

    private SoundPlayer soundPlayer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        soundPlayer = new SoundPlayer();
    }



    @Test
    void testPlaySoundHandlesIOException() throws LineUnavailableException, IOException {
        // Arrange
        String soundFile = "faulty-sound.wav";
        File mockFile = mock(File.class);

        when(mockFile.exists()).thenReturn(true);
        doThrow(IOException.class).when(mockClip).open(any());

        // Act
        soundPlayer.playSound(soundFile);

        // Assert
        // Ensure no clip is started in case of an exception
        verify(mockClip, never()).start();
    }

    @Test
    void testPlaySoundHandlesExceptionsGracefully() throws IOException, LineUnavailableException {
        // Arrange
        String soundFile = "faulty-sound.wav";

        // Mock AudioSystem behaviors
        File mockFile = mock(File.class);
        when(mockFile.exists()).thenReturn(true);

        // Simulate an exception being thrown by AudioSystem methods
        doThrow(IOException.class).when(mockClip).open(any());
        doThrow(LineUnavailableException.class).when(mockClip).open(any());

        // Act
        soundPlayer.playSound(soundFile);

        // Assert
        // Ensure no clip is started in case of an exception
        verify(mockClip, never()).start();
    }

    @Test
    void testPlaySoundHandlesLineUnavailableException() throws LineUnavailableException, IOException {
        // Arrange
        String soundFile = "unavailable-sound.wav";
        File mockFile = mock(File.class);

        when(mockFile.exists()).thenReturn(true);
        doThrow(LineUnavailableException.class).when(mockClip).open(any());

        // Act
        soundPlayer.playSound(soundFile);

        // Assert
        // Ensure no clip is started in case of an exception
        verify(mockClip, never()).start();
    }

    @Test
    void testStopSoundWithoutClip() {
        // Act
        soundPlayer.stopSound();

        // Assert
        // Ensure no interaction occurs as no clip is initialized
        verify(mockClip, never()).stop();
        verify(mockClip, never()).close();
    }
    @Test
    void testPlaySoundWithNonExistentFile() throws LineUnavailableException, IOException {
        // Arrange
        String nonExistentFile = "non-existent-sound.wav";
        File mockFile = mock(File.class);
        when(mockFile.exists()).thenReturn(false);

        // Act
        soundPlayer.playSound(nonExistentFile);

        // Assert
        verify(mockClip, never()).open(any(AudioInputStream.class));
        verify(mockClip, never()).start();
    }
    @Test
    void testStopSoundWithRunningClip() {
        // Arrange
        Clip mockRunningClip = mock(Clip.class);
        when(mockRunningClip.isRunning()).thenReturn(true);

        // Use reflection to set the private clip field
        try {
            Field clipField = SoundPlayer.class.getDeclaredField("clip");
            clipField.setAccessible(true);
            clipField.set(soundPlayer, mockRunningClip);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Failed to set clip field: " + e.getMessage());
        }

        // Act
        soundPlayer.stopSound();

        // Assert
        verify(mockRunningClip).stop();
        verify(mockRunningClip).close();
    }
}
