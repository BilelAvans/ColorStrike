import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class InfoPanel extends JPanel
{
	
	JTextField scoreTextField, scoreAmountField;
	
	int scoreAmount = 0;
	
	InfoPanel()
	{
		setPreferredSize(new Dimension(200, 1280));
		setBackground(new Color(80, 130, 200));
		
		initComponents();
	}
	
	private void initComponents()
	{
		scoreTextField = new JTextField("Score");
		scoreAmountField = new JTextField("0");
		
		add(scoreTextField);
		add(scoreAmountField);
	}
	
	public void updateScore(int scoreToAdd)
	{
		scoreAmount += scoreToAdd;
		scoreAmountField.setText(String.valueOf(scoreAmount));
		
		revalidate();
	}

}
