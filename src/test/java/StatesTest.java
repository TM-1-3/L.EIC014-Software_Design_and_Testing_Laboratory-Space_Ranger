import States.GameStates;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;
import States.Game;
import States.GameCompletionState;
import States.GameStates;
import Sound.SoundPlayer;
import View.GameCompletionViewer;
import com.googlecode.lanterna.screen.Screen;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class StatesTest {
    @Test
    void testMenuIsFirstEnumValue() {
        GameStates[] states = GameStates.values();
        assertEquals(GameStates.MENU, states[0], "MENU should be the first enum value in GameStates");
    }
    
    @Test
    void testLevelsAreConsecutiveEnumValues() {
        GameStates[] states = GameStates.values();
        int level1Index = -1, level2Index = -1, level3Index = -1;
    
        for (int i = 0; i < states.length; i++) {
            if (states[i] == GameStates.LEVEL_1) level1Index = i;
            if (states[i] == GameStates.LEVEL_2) level2Index = i;
            if (states[i] == GameStates.LEVEL_3) level3Index = i;
        }
    
        assertEquals(level1Index + 1, level2Index, "LEVEL_2 should follow LEVEL_1");
        assertEquals(level2Index + 1, level3Index, "LEVEL_3 should follow LEVEL_2");
    }
    
        @Test
    void testGameOverAndGameWinAreEnumValues() {
        GameStates[] states = GameStates.values();
        boolean hasGameOver = false;
        boolean hasGameWin = false;
    
        for (GameStates state : states) {
            if (state == GameStates.GAME_OVER) hasGameOver = true;
            if (state == GameStates.GAME_WIN) hasGameWin = true;
        }
    
        assertTrue(hasGameOver, "GAME_OVER should be an enum value in GameStates");
        assertTrue(hasGameWin, "GAME_WIN should be an enum value in GameStates");
    }
        @Test
    void testNullIsLastEnumValue() {
        GameStates[] states = GameStates.values();
        assertEquals(GameStates.NULL, states[states.length - 1], "NULL should be the last enum value in GameStates");
    }
}
