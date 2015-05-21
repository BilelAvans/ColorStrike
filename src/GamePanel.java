import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePanel extends JPanel implements ActionListener
{
	// Lijst met Balletjes
	private List<Ball> balls = new ArrayList<Ball>();
	// Toegang tot InfoPaneel
	private InfoPanel iP;
	// Array voor het opsliten van panele in vierkanten
	private Rectangle2D.Double[][] paneelInVierDelen;
	
	// Timer
	private Timer timer;
	
	Random random = new Random();
	
	GamePanel(InfoPanel iP)
	{
		setPreferredSize(new Dimension(824, 1280));
		
		this.iP = iP;
		timer = new Timer(1000/10, this);
		timer.start();
	}
	
	public void paintComponent(Graphics gr)
	{
		super.paintComponent(gr);
		Graphics2D graph = (Graphics2D) gr;
		graph.translate(getWidth() / 2, getHeight() / 2);
		
		drawLines(graph);
		drawBalls(graph);
		
	}
	
	private void drawLines(Graphics2D graph)
	{
		// Horizontale Lijn
		graph.drawLine(0, -(getHeight() /2), 0, getHeight() / 2);
		// Verticale
		graph.drawLine(-(getWidth() / 2), 0, getWidth() / 2, 0);
		// Diagonaal vanaf linksboven
		graph.drawLine(-(getWidth() / 2), -(getHeight() / 2), getWidth() / 2, getHeight() / 2);
		// Diagonaal vanaf linksonder
		graph.drawLine(-(getWidth() / 2), getHeight() / 2, getWidth() / 2, -(getHeight() / 2) );
	}
	
	private void drawBalls(Graphics2D graph)
	{
		if (balls.size() < 1)
			balls.add(new Ball(this));
		
		Iterator<Ball> it = balls.iterator();
		
		while (it.hasNext())
		{
			Ball b = (Ball) it.next();
			
			if (!b.areWeDoneYet())
				b.paint(graph);
			else
			{
				it.remove();
				iP.updateScore(1);
			}
		}		
	}
	
	public Rectangle2D.Double getDeelUitVier(int rij, int kolom)
	{
		if (paneelInVierDelen == null)
			splitsPaneelInVierDelen();

		return paneelInVierDelen[rij][kolom];
	}
	
	private void splitsPaneelInVierDelen()
	{
		paneelInVierDelen = new Rectangle2D.Double[][] {
				//* Paneel opdelen in 4 delen zodat je de vierkanten makkelijk kunt intekenen
				{
				new Rectangle2D.Double(-(getWidth() / 2), -(getHeight() / 2), getWidth() / 2, getHeight() / 2),
				new Rectangle2D.Double(0, -(getHeight() / 2),  getWidth() / 2, getHeight() / 2),
				},
					
				{
				new Rectangle2D.Double(-(getWidth() / 2), 0, getWidth() / 2, getHeight() / 2),
				new Rectangle2D.Double(0, 0,  getWidth() / 2, getHeight() / 2)
				}
			};
	}
	
	public void paintVierkant(int rij, int kolom, Color c, Graphics2D graph)
	{
		Rectangle2D.Double vierkant = getDeelUitVier(rij, kolom);
		c = new Color(c.getRed(), c.getGreen(), c.getBlue(), 100);
		
		graph.setColor(c);
		graph.draw(vierkant);
		graph.fill(vierkant);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		revalidate();
		repaint();
	}
	
	
}
