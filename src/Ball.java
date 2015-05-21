import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Random;

public class Ball extends Ellipse2D.Double
{
	// Kleur van het balletje	
	private Color kleur;
	// Randomiser
	private static Random random = new Random();
	// Hoeveel pixels verschuift het balletje over x en y per verschuiving
	int speed = 15;
	// Array voor het bijhouden van hoeken van dit frame
	private Point2D.Double[][] paneelHoeken;
	// Laast onthouden hoek waarin het balletje beweegt
	double hoekInRadialen = 0;
	
	GamePanel gP;
	
	// Balletje
	Ball(GamePanel gP)
	{		
		this(gP, new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255), 200));
	}
	// Balletje met voorgedefinieerde kleur
	Ball(GamePanel gP, Color c)
	{
		super(300, -300, 30, 30);
		Point2D.Double begin = getHoek(random.nextInt(2), random.nextInt(2));
		setFrame(begin.getX(), begin.getY(), 30, 30);
		
		kleur = c;
		
		this.gP = gP;
	}
	
	// Positie van het balletje tekenen
	public void paint(Graphics2D graph)
	{
		graph.draw(this);
		graph.setColor(kleur);
		graph.fill(this);
		
		moveTowardsOrigin(1);
		emitLightInSquare(graph);
		
	}
	// Positie zetten
	public void setPosition()
	{
		this.setFrame(getX(), getX(), getWidth(), getHeight());
	}
	
	public void emitLightInSquare(Graphics2D graph)
	{
		if (Math.abs(getX()) < 5 * speed)
		{
			if (hoekInRadialen > 0 && hoekInRadialen < (Math.PI /2))
				gP.paintVierkant(1, 1, kleur, graph);
			else if (hoekInRadialen > (Math.PI / 2) && hoekInRadialen < Math.PI)
				gP.paintVierkant(1, 0, kleur, graph);
			else if (hoekInRadialen < 0 && hoekInRadialen > -(Math.PI /2))
				gP.paintVierkant(0, 1, kleur, graph);
			else if (hoekInRadialen < (Math.PI / 2) && hoekInRadialen > -Math.PI)
				gP.paintVierkant(0, 0, kleur, graph);
			
			System.out.println(hoekInRadialen * 180 / Math.PI);
		}
			
	}
	
	public void moveTowardsOrigin(int loopSize)
	{
		// Hoek berekenen met behulp van de afstand tot de oorsprong 
		hoekInRadialen = Math.atan2(getY(), getX());
		
		// Verschuiven richting oorpsrong
		setFrame(getX() + speed * loopSize * Math.cos(hoekInRadialen + Math.PI), 
				 getY() + speed * loopSize * Math.sin(hoekInRadialen + Math.PI), 
				 getWidth(), 
				 getHeight());
	}
	
	public void setSpeed(int speed)			// Snelheid aanpassen (Echt nodig?)
	{
		this.speed = speed;
	}
	
	public boolean areWeDoneYet()			// Zodra het balletje bij de oorpsrong is true
	{
		if (Math.abs(getX()) < speed || Math.abs(getY()) < speed)
			return true;
		else
			return false;
	}
	
	private Point2D.Double getHoek(int rij, int kolom)
	{
		if (paneelHoeken == null)
			setHoeken();
		
		return paneelHoeken[rij][kolom];
	}
	
	private void setHoeken()
	{
		paneelHoeken = new Point2D.Double[][]
		{
			{
				new Point2D.Double(-412, -640),
				new Point2D.Double(412, - 640)
			},
			
			{
				new Point2D.Double(-412, 640),
				new Point2D.Double(412, 640)
			}
		};
	}
	
}