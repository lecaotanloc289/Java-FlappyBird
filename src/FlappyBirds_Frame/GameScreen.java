/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlappyBirds_Frame;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

// Extends JFrame nên GameScreen lúc này là một cái JFrame, và mình có thể bật nó lên như một cửa sổ

public abstract class GameScreen extends JFrame implements KeyListener{

    public static int KEY_PRESSED = 0;
    public static int KEY_RELEASED = 1;
    
    public int CUSTOM_WIDTH  = 500;
    public int CUSTOM_HEIGHT = 500;
    
    private GameThread G_Thread;
    
    public static int MASTER_WIDTH = 500, MASTER_HEIGHT = 500;
    
    public GameScreen(){
        InitThread();
        InitScreen();
    }
    public GameScreen(int w, int h){
        this.CUSTOM_WIDTH = w;
        this.CUSTOM_HEIGHT = h;
        MASTER_WIDTH = CUSTOM_WIDTH;
        MASTER_HEIGHT = CUSTOM_HEIGHT;
        InitThread();
        InitScreen();
    }
    

    private void InitThread(){
        G_Thread = new GameThread(this);
        add(G_Thread);
    }
    private void InitScreen(){
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        setSize(CUSTOM_WIDTH, CUSTOM_HEIGHT);
        setVisible(true);
        
    }
    
    public void BeginGame(){
        G_Thread.StartThread();
    }
    

    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        KEY_ACTION(e, GameScreen.KEY_PRESSED);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        KEY_ACTION(e, GameScreen.KEY_RELEASED);
    }
    
    public abstract void GAME_UPDATE(long deltaTime);
    // Hàm vẽ
    public abstract void GAME_PAINT(Graphics2D g2);

    // Hàm lấy sự kiện action:listener
    public abstract void KEY_ACTION(KeyEvent e, int Event);
    
}
