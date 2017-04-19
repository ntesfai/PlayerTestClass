/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.io.IOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


/**
 *
 * @author Nubian
 */
public class PlayerTest {
    @Rule
    public MockitoRule mockrule = MockitoJUnit.rule();
    @Mock
    private Player.Ship ship = mock(Player.Ship.class);
    
    private Player player;
    private Player cpu;
     
    public PlayerTest() throws IOException{
        player = new Player("Player");
        cpu = new Player("CPU");
        player.setOpponent(cpu);
        cpu.setOpponent(player);
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeClass
    public static void setUpClass() {        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    
    /**
     * Test of isRange method, of class Player.
     * @throws java.lang.Exception
     */
    @Test
    public void testIsRange() throws Exception {   
        System.out.println("isRange");
        boolean expResult = true;
        Player.Ship ship = mock(Player.Ship.class);
        Player.Ship ship2 = mock(Player.Ship.class);
        ship.size = 2;
        ship2.size = 5;
        
        when(ship.getDirectionOfShip()).thenReturn("Vertical");
        when(ship.getY()).thenReturn(5);
        when(ship.getX()).thenReturn(2);
        
        when(ship2.getDirectionOfShip()).thenReturn("Horizontal");
        when(ship2.getX()).thenReturn(4);
        when(ship2.getY()).thenReturn(3);
        
        assertEquals(expResult, player.isRange(ship, 2, 5));
        verify(ship, times(1)).getDirectionOfShip();
        
        assertEquals(expResult, player.isRange(ship2, 4, 3));
        verify(ship2, times(2)).getDirectionOfShip();
    }
    
    @Test
    public void testcheckWin() throws Exception {
        System.out.println("Testing checkWin");
        player.setHits(17);
        assertFalse(!player.checkWin());
    }
    
    @Test
    public void testcpuTakeShot() throws Exception {
        boolean result = false;
        player.setBoard();
        result = player.cpuTakeShot(8, 3);
        assertTrue(result);
    }
    
    @Test
    public void testcpuFire(){
        
    }
}