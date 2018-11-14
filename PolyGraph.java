package myMath;
import java.awt.Color;
import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;


public class PolyGraph extends JFrame {

	private double x0, x1;
	private Polynom p;
	
	//public void foo(double x0, double x1,polynom p) {
	public PolyGraph() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 800);

		//Polynom p = new Polynom("0.2*x^4-1.5*x^3+3.0*x^2-1*x^1-5*x^0");
		DataTable data = new DataTable(Double.class, Double.class);
		for (double x = -2; x <= 6; x+=0.25) {
			double y = p.f(x);
			data.add(x, y);			
		}
		
		XYPlot plot = new XYPlot(data);
		getContentPane().add(new InteractivePanel(plot));
		LineRenderer lines = new DefaultLineRenderer2D();
		plot.setLineRenderers(data, lines);
		Color color = new Color(0.0f, 0.3f, 1.0f);
		plot.getPointRenderers(data).get(0).setColor(color);
		plot.getLineRenderers(data).get(0).setColor(color);
	}
	
	public static void main(String[] args) {
		PolyGraph frame = new PolyGraph();
		frame.setVisible(true);
	}

}
