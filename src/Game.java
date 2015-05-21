import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;


public class Game extends JPanel 
{

	InfoPanel  iP;
	GamePanel  gP;
	
	BorderLayout bl = new BorderLayout();

	Game()
	{
		setLayout(bl);
		initpP();
		initgP();		
	}
	
	private void initpP()
	{
		iP = new InfoPanel();
		add(iP, BorderLayout.WEST);
	}
	
	private void initgP()
	{
		gP = new GamePanel(iP);
		add(gP, BorderLayout.CENTER);
	}
	

	
}
