import java.awt.EventQueue;

import javax.swing.JFrame;


public class ColorStrike extends JFrame
{
	
	private Game game;
	
	ColorStrike()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		
		add(game = new Game());
		pack();
		
	}
	
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				ColorStrike cs = new ColorStrike();
			}
		});
	}

}
