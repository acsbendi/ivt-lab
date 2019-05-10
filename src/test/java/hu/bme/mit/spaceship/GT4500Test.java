package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore mockFirstTorpedoStore;
  private TorpedoStore mockSecondTorpedoStore;

  @BeforeEach
  public void init(){
    this.mockFirstTorpedoStore = mock(TorpedoStore.class);
    this.mockSecondTorpedoStore = mock(TorpedoStore.class);
    this.ship = new GT4500(this.mockFirstTorpedoStore, this.mockSecondTorpedoStore);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockFirstTorpedoStore.isEmpty()).thenReturn(false);
    when(mockFirstTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockFirstTorpedoStore, times(1)).isEmpty();
    verify(mockFirstTorpedoStore, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockFirstTorpedoStore.isEmpty()).thenReturn(false);
    when(mockFirstTorpedoStore.fire(1)).thenReturn(true);
    when(mockSecondTorpedoStore.isEmpty()).thenReturn(false);
    when(mockSecondTorpedoStore.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockFirstTorpedoStore, times(1)).isEmpty();
    verify(mockFirstTorpedoStore, times(1)).fire(1);
    verify(mockSecondTorpedoStore, times(1)).isEmpty();
    verify(mockSecondTorpedoStore, times(1)).fire(1);
  }

}
