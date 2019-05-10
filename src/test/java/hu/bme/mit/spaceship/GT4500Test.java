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
  public void fireTorpedo_Single_Twice_Second_Empty_Success(){
    // Arrange
    when(mockTorpedoStores[0].isEmpty()).thenReturn(false);
    when(mockTorpedoStores[0].fire(1)).thenReturn(true);
    when(mockTorpedoStores[1].isEmpty()).thenReturn(true);

    // Act
    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result1);
    assertEquals(true, result2);
    verify(mockTorpedoStores[0], times(2)).isEmpty();
    verify(mockTorpedoStores[0], times(2)).fire(1);
    verify(mockTorpedoStores[1], times(1)).isEmpty();
  }

  @Test
  public void fireTorpedo_Single_Both_Empty_Failure(){
    // Arrange
    for(TorpedoStore torpedoStore : mockTorpedoStores){
      when(torpedoStore.isEmpty()).thenReturn(true);
      when(torpedoStore.fire(1)).thenReturn(false);
    }

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    for(TorpedoStore torpedoStore : mockTorpedoStores){
      verify(torpedoStore, times(1)).isEmpty();
      verify(torpedoStore, times(0)).fire(1);
    }
  }

  @Test
  public void fireTorpedo_Single_Twice_Both_Empty_Failure(){
    // Arrange
    when(mockTorpedoStores[0].isEmpty()).thenReturn(false);
    when(mockTorpedoStores[0].fire(1)).thenReturn(true);
    when(mockTorpedoStores[1].isEmpty()).thenReturn(true);
    when(mockTorpedoStores[1].fire(1)).thenReturn(false);

    // Act
    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
    when(mockTorpedoStores[0].isEmpty()).thenReturn(true);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result1);
    assertEquals(false, result2);
    verify(mockTorpedoStores[0], times(2)).isEmpty();
    verify(mockTorpedoStores[0], times(1)).fire(1);
    verify(mockTorpedoStores[1], times(1)).isEmpty();
    verify(mockTorpedoStores[1], times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Failure(){
    // Arrange
    when(mockTorpedoStores[0].isEmpty()).thenReturn(false);
    when(mockTorpedoStores[0].fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockTorpedoStores[0], times(1)).isEmpty();
    verify(mockTorpedoStores[0], times(1)).fire(1);
    verify(mockTorpedoStores[1], times(0)).isEmpty();
    verify(mockTorpedoStores[1], times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_First_Empty(){
    // Arrange
    when(mockTorpedoStores[0].isEmpty()).thenReturn(true);
    when(mockTorpedoStores[0].fire(1)).thenReturn(false);
    when(mockTorpedoStores[1].isEmpty()).thenReturn(false);
    when(mockTorpedoStores[1].fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockTorpedoStores[0], times(1)).isEmpty();
    verify(mockTorpedoStores[0], times(0)).fire(1);
    verify(mockTorpedoStores[1], times(1)).isEmpty();
    verify(mockTorpedoStores[1], times(1)).fire(1);
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

  @Test
  public void fireLaser_All(){
    // Arrange

    // Act
    boolean result = ship.fireLaser(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
  }

}
