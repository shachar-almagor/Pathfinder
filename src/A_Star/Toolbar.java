package A_Star;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Toolbar extends JPanel implements KeyListener{

	private Gameplay gameplay;
	private Settings settings;

	public Toolbar(Gameplay gameplay, Settings settings) {
		this.gameplay = gameplay;
		this.settings = settings;
		this.setLayout(new BorderLayout());

		setFocusable(true);
		setFocusTraversalKeysEnabled(true);

		this.add(gameplay, BorderLayout.CENTER);
		this.add(settings, BorderLayout.WEST);
		addKeyListener(this);

	}

	public Gameplay getGameplay() {
		return this.gameplay;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_SPACE && !gameplay.getCurrent().equals(gameplay.getEnd())) {
			gameplay.startAlgorithm();
		} else if(gameplay.getStart() != null && gameplay.getEnd() != null) {
			Node current = gameplay.getCurrent();
			Node temp = current;
			if(!current.equals(gameplay.getEnd())) {
				// The user hasn't finished the maze
				// FIX - MAKE MOVING INSTANTANEOUS
				if(key == KeyEvent.VK_UP && !current.topWall && current.getRow() > 0) {
					// Only move up if the top wall isn't there
					gameplay.moveUp();
				} else if(key == KeyEvent.VK_RIGHT && !current.rightWall && current.getCol() < gameplay.getTotalRows()) {
					// Only move right if the right wall isn't there
					gameplay.moveRight();
				} else if(key == KeyEvent.VK_DOWN && !current.bottomWall && current.getRow() < gameplay.getTotalRows()) {
					// Only move down if the bottom wall isn't there
					gameplay.moveDown();
				} else if(key == KeyEvent.VK_LEFT && !current.leftWall && current.getCol() > 0) {
					// Only move left if the left wall isn't there
					gameplay.moveLeft();
				}
				current = gameplay.getCurrent();
				if(!temp.equals(gameplay.getStart()) && !temp.equals(gameplay.getEnd())) {
					temp.makePath();
				}
				if(!current.equals(gameplay.getStart()) && !current.equals(gameplay.getEnd())) {
					current.makeOpen();
				}
				temp.draw(gameplay.getGraphics());
				current.draw(gameplay.getGraphics());
				temp.drawLines(gameplay.getGraphics());
				current.drawLines(gameplay.getGraphics());
			}
		} 
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}