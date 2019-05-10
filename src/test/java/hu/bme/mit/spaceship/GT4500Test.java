package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore[] mockTorpedoStores = new TorpedoStore[2];

  @BeforeEach
  public void init(){
    this.mockTorpedoStores[0] = mock(TorpedoStore.class);
    this.mockTorpedoStores[1] = mock(TorpedoStore.class);
    this.ship = new GT4500(this.mockTorpedoStores[0], this.mockTorpedoStores[1]);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockTorpedoStores[0].isEmpty()).thenReturn(false);
    when(mockTorpedoStores[0].fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockTorpedoStores[0], times(1)).isEmpty();
    verify(mockTorpedoStores[0], times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Twice_Success(){
    // Arrange
    for(TorpedoStore torpedoStore : mockTorpedoStores){
      when(torpedoStore.isEmpty()).thenReturn(false);
      when(torpedoStore.fire(1)).thenReturn(true);
    }

    // Act
    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result1);
    assertEquals(true, result2);
    for(TorpedoStore torpedoStore : mockTorpedoStores){
      verify(torpedoStore, times(1)).isEmpty();
      verify(torpedoStore, times(1)).fire(1);
    }
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    for(TorpedoStore torpedoStore : mockTorpedoStores){
      when(torpedoStore.isEmpty()).thenReturn(false);
      when(torpedoStore.fire(1)).thenReturn(true);
    }

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    for(TorpedoStore torpedoStore : mockTorpedoStores){
      verify(torpedoStore, times(1)).isEmpty();
      verify(torpedoStore, times(1)).fire(1);
    }
  }

}
