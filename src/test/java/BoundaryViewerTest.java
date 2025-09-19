package View;

import Model.Boundary;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class BoundaryViewerTest {

    @Mock
    private TextGraphics mockGraphics;

    @Mock
    private Boundary mockBoundary1;

    @Mock
    private Boundary mockBoundary2;

    private BoundaryViewer boundaryViewer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        boundaryViewer = new BoundaryViewer();
    }

    @Test
    void testDrawWithMultipleBoundaries() throws IOException {
        // Arrange
        List<Boundary> boundaries = Arrays.asList(mockBoundary1, mockBoundary2);

        // Act
        boundaryViewer.draw(mockGraphics, boundaries);

        // Assert
        verify(mockBoundary1, times(1)).draw(mockGraphics);
        verify(mockBoundary2, times(1)).draw(mockGraphics);
    }

    @Test
    void testDrawWithNoBoundaries() throws IOException {
        // Arrange
        List<Boundary> boundaries = Collections.emptyList();

        // Act
        boundaryViewer.draw(mockGraphics, boundaries);

        // Assert
        // No interactions should occur since the list is empty
        verifyNoInteractions(mockBoundary1, mockBoundary2, mockGraphics);
    }

    @Test
    void testDrawWithSingleBoundary() throws IOException {
        // Arrange
        List<Boundary> boundaries = Collections.singletonList(mockBoundary1);

        // Act
        boundaryViewer.draw(mockGraphics, boundaries);

        // Assert
        verify(mockBoundary1, times(1)).draw(mockGraphics);
        verifyNoInteractions(mockBoundary2);
    }
}