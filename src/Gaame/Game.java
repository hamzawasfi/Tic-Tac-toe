package Gaame;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import Gaame.graphics.level.LevelManager;
import Gaame.inputs.Mouse;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	private static int width = 300;
	private static int height = 200;
	private static int scale = 3;
	
	private static String title = "Tick Tok";
	
	private boolean running = false;
	
	private JFrame frame;
	private Thread thread;
	private static LevelManager level;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	public Game(){
		Dimension size = new Dimension(getWindowWidth(), getWindowHeight());
		setPreferredSize(size);
        level = new LevelManager(getWindowWidth(), getWindowHeight());
		frame = new JFrame();
		
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}
	
	
	public static int getWindowWidth() {
		return width * scale;
	}
	
	public static int getWindowHeight() {
		return height * scale;
	}
	
	public static LevelManager getLevel() {
		return level;
	}
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
    public synchronized void stop() {
    	running = false;
    	try {
    		thread.join();
    	}catch(InterruptedException e) {
    		e.printStackTrace();
    	}
    }
	
    public void run() {
    	long LastTime = System.nanoTime();
    	long timer = System.currentTimeMillis();
    	final double ns = 1000000000.0 / 60.0;
    	double delta = 0;
    	int frames  = 0;
    	int updates = 0;
    	requestFocus();
    	while(running) {
    		long now = System.nanoTime();
    		delta += (now - LastTime) / ns;
    		LastTime = now;
    		while (delta >= 1) {
    			update();
    			updates++;
    			delta--;
    		}
    		render();
    		frames++;
    		
    		if(System.currentTimeMillis() - timer > 1000) {
    			timer += 1000;
    			frame.setTitle(title + "  |  " + updates + " ups, " + frames + "fps");
    			updates = 0;
    			frames = 0;
    		}
    	}
    	stop();
    }
    
    public void update() {
    	level.update();
    }
    
    public void render() {
    	BufferStrategy bs = getBufferStrategy();
    	if(bs == null) {
    		createBufferStrategy(3);
    		return;
    	}
    	clear(); 
    	
    	Graphics g = bs.getDrawGraphics();
    	g.drawImage(image, 0, 0, getWindowWidth(), getWindowHeight(), null);
    	level.render(g);
    	g.dispose();
    	bs.show();
    }
	
	public static void main(String[] args) {
		Game game = new Game();
    	game.frame.setResizable(false);
    	game.frame.setTitle(title);
    	game.frame.add(game);
    	game.frame.pack();
    	game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	game.frame.setLocationRelativeTo(null);
    	game.frame.setVisible(true);
    	
    	game.start();
    }
}
