/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.io.IOException;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

/**
 *
 * @author Nubian
 */
public class PlayerTest {
    @Rule
    public MockitoRule mockrule = MockitoJUnit.rule();
    @Rule
    public ContiPerfRule CPRule = new ContiPerfRule();
    @Mock
    private Player.Ship ship = mock(Player.Ship.class);
    @Mock
    private Player PlayerMock = mock(Player.class);
    
    @InjectMocks
    private Player instance;
     
    public PlayerTest() throws IOException{
        this.instance = new Player("Player");
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
    @PerfTest(invocations = 1000, threads = 1)
    public void testIsRange() throws Exception {   
        System.out.println("isRange");
        boolean expResult = true;
        Player.Ship ship2 = mock(Player.Ship.class);
        ship.size = 2;
        ship2.size = 5;
        
        when(ship.getDirectionOfShip()).thenReturn("Vertical");
        when(ship.getY()).thenReturn(5);
        when(ship2.getDirectionOfShip()).thenReturn("Horizontal");
        when(ship2.getX()).thenReturn(4);

        assertEquals(expResult, instance.isRange(ship, 0, 5));
        assertEquals(expResult, instance.isRange(ship2, 4, 0));
    }
    
    @Test
    public void testremoveOldShip() throws IOException{
        System.out.println("removeOldShip");        
        int x = 0;
        ship.name = "Destroyer";
        ship.size = 5;
        when(ship.getDirectionOfShip()).thenReturn("Vertical");
        when(ship.getX()).thenReturn(5);
        String shipName = "BattleShip";
        int[][] board = instance.getBoard();
        
        //sets board spaces to 1
        while(x < ship.size){
            board[ship.getX()][x] = 1;
            x++;
        }
        
        //sets board spaces to 0
        instance.removeOldShip();
        while(x < ship.size){
        assertEquals(0, board[ship.getX()][x]);
        x++;
        }
    }

}