package myMath;

import java.awt.Color;
import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.function.Predicate;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import myMath.Monom;

import java.awt.Color;
import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom extends JFrame implements Polynom_able{

	private ArrayList <Monom> polyArr = new ArrayList();
	private String polynom;
	/**
	 * constructor
	 */
	public Polynom () {
		Monom m = new Monom ();
		this.polyArr.add(m);
	}
	/**
	 * copy constructor
	 * @param p is a polynom
	 */
	public Polynom (Polynom p) {
		this.setPolynomArray(p.getPolynomArray());
		this.setPolynom(p.getPolynom());
	}
	/**
	 * constructor
	 * @param s is the string of the polynom
	 */
	public Polynom (String s) {
		Monom m = new Monom ();
		int startIndex = 0;
		for (int i=0; i<s.length(); i++) {//runs the string and split it to conf and pow
			if(i != 0) {
				if(s.charAt(i) == '+' || s.charAt(i) == '-') {
					if(s.charAt(startIndex) == '+') {
						m = new Monom(s.substring(startIndex+1,i));
						polyArr.add(m);
						startIndex = i;
					}
					else {
						m = new Monom (s.substring(startIndex, i));
						polyArr.add(m);
						startIndex = i;
					}
				}
			}
			if(i == s.length()-1) {//this is for the last element
				if(s.charAt(startIndex) == '+') {
					m = new Monom(s.substring(startIndex+1, s.length()));
					polyArr.add(m);
				}
				else {
					m = new Monom(s.substring(startIndex, s.length()));
					polyArr.add(m);
				}
			}
		}
		arange(this.polyArr); // Auxiliary function which arange, sort and fix the string 
	}

	public ArrayList<Monom> getPolynomArray() {
		return polyArr;
	}

	public void setPolynomArray(ArrayList<Monom> polynomArray) {
		this.polyArr = polynomArray;
	}

	public String getPolynom() {
		return polynom;
	}

	public void setPolynom(String polynom) {
		this.polynom = polynom;
	}

	@Override
	public String toString() {
		return "" + this.getPolynom();
	}

	@Override
	/**
	 * This interface represents a simple function of type y=f(x), where both y and x are real numbers.
	 * * @param x is the value of the variable
	 */
	public double f(double x) {
		Iterator<Monom> t = polyArr.iterator();
		double sum = 0;
		while(t.hasNext()) {
			Monom m = new Monom (t.next());
			if(m.get_power() != 0) {
				sum = sum + (Math.pow(x, m.get_power())*m.get_coefficient());
			}
			else {
				sum = sum + m.get_coefficient();
			}
		}
		return sum;
	}

	@Override
	/**
	 * Add p1 to this Polynom
	 * @param p1 is a polynom
	 */
	public void add(Polynom_able p1) {
		Iterator<Monom> t = p1.iteretor();
		Monom m = new Monom ();
		while(t.hasNext()) { // runs the arraylist, monom by monom and add with p1's monoms
			m = new Monom (t.next());
			add(m);
		}
		return;
	}

	@Override
	/**
	 * Add m1 to this Polynom
	 * @param m1 Monom
	 */
	public void add(Monom m1) {
		Iterator<Monom> t = polyArr.iterator();
		Monom m = new Monom ();
		while(t.hasNext()) { // runs the arraylist searches for monom with the same mom  
			m = new Monom (t.next());
			if(m.get_power() == m1.get_power()) {
				t.remove();
				m.add(m1);
				this.polyArr.add(m);
				arange(this.polyArr);
				return;
			}
		}
		this.polyArr.add(m1);
		arange(this.polyArr);
		return;
	}

	@Override
	/**
	 * Subtract p1 from this Polynom
	 * @param p1 is a polynom
	 */
	public void substract(Polynom_able p1) {
		Iterator<Monom> t = p1.iteretor();
		Monom m = new Monom ();
		while(t.hasNext()) {// runs the arraylist, monom by monom and substract with p1's monoms
			m = new Monom (t.next());
			m.set_coefficient(Math.negateExact((long)m.get_coefficient()));
			add(m);
		}
		return;
	}

	@Override
	/**
	 * Multiply this Polynom by p1
	 * @param p1 is a polynom
	 */
	public void multiply(Polynom_able p1) {
		if(p1.isZero() == true || this.isZero()) {
			System.out.println("Zero multiply");
			return;
		}
		ArrayList <Monom> arr = new ArrayList ();
		Iterator<Monom> t1 = polyArr.iterator();
		Monom m1 = new Monom ();
		Monom m2 = new Monom ();
		while(t1.hasNext()){ // runs the arraylist, monom by monom and multiply with p1's monoms
			m1 = new Monom (t1.next());
			Iterator<Monom> t2 = p1.iteretor();
			while(t2.hasNext()) {
				m2 = new Monom (t2.next());
				m2.multiply(m1);
				arr.add(m2);
			}
		}
		this.polyArr = arr;
		arange(this.polyArr);
		return;
	}

	@Override
	/**
	 * Test if this Polynom is logically equals to p1.
	 * @param p1 is a polynom
	 * @return true iff this pulynom represents the same function ans p1
	 */
	public boolean equals(Polynom_able p1) {
		Iterator<Monom> t3 = p1.iteretor();
		ArrayList <Monom> arr = new ArrayList ();
		while (t3.hasNext()) {
			arr.add(t3.next());
		}
		arange(arr);// arange the array
		Iterator<Monom> t1 = this.polyArr.iterator();
		Iterator<Monom> t2 = arr.iterator();
		while (t1.hasNext()) { // runs the arraylist and check if every monom similar to the monom in p1
			if(!(t1.next().getMonom().equals(t2.next().getMonom()))) {
				return false;
			}
			if(t1.hasNext() == true && t2.hasNext() == false) {
				return false;
			}
			if(t1.hasNext() == false && t2.hasNext() == true) {
				return false;
			}
		}
		if(t2.hasNext() == true) {
			return false;
		}
		return true;
	}

	@Override
	/**
	 * Test if this is the Zero Polynom
	 * @return true if it's the zero polynom 
	 */
	public boolean isZero() {
		Iterator<Monom> t = this.polyArr.iterator();
		if((this.polyArr.size() == 1) && (t.next().get_coefficient() == 0)) {
			return true;
		}
		return false;
	}

	@Override
	/**
	 * Compute a value x' (x0<=x'<=x1) for with |f(x')| < eps
	 * assuming (f(x0)*f(x1)<=0, returns f(x2) such that:
	 * *	(i) x0<=x2<=x2 && (ii) f(x2)<eps
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps step (positive) value
	 * @return the zero of the function
	 */
	public double root(double x0, double x1, double eps) {
		double mid=x0;
		if (this.f(x0)*this.f(x1)>=0) {
			throw new RuntimeException("x0 and x1 arent opposite nums");
		}
		else {
			while ((x1-x0)>=eps) {
				mid=((x0+x1)/2);
				if (this.f(mid)!=0) {
					if (this.f(mid)*this.f(x0)<0) x1=mid;
					else x0=mid;
				} else {
					return mid;
				}
			}
			return mid;
		}
	}


	@Override
	/**
	 * create a deep copy of this Polynum
	 * @return a copy of the polynom 
	 */
	public Polynom_able copy() {
		Polynom_able p1 = new Polynom (this.getPolynom());
		return p1;
	}

	@Override
	/**
	 * Compute a new Polynom which is the derivative of this Polynom
	 * @return the polynom's derivative
	 */
	public Polynom_able derivative() {
		Iterator<Monom> t = this.polyArr.iterator();
		Polynom p = new Polynom ();
		Monom m = new Monom ();
		if(t.hasNext()) {// first itertion
			m = new Monom (t.next());
			m = m.derivative();
			p = new Polynom (m.getMonom());
		}
		while(t.hasNext()) { //second itertion and so on
			m = new Monom (t.next());
			m = m.derivative();
			p.add(m);
		}
		arange(this.polyArr);
		return p;
	}

	@Override
	/**
	 * Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps,
	 * @return the approximated area above the x-axis below this Polynom and between the [x0,x1] range.
	 */
	public double area(double x0, double x1, double eps) {
		double sum = 0;
		double num = 0;
		for (double i=x0; i<=x1; i=i+eps) {
			num = f(i)*eps;
			if(num > 0){
				sum = sum + num;
			}
		}
		return sum;
	}
	/**
	 * Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps,
	 * @return the approximated area below the x-axis below this Polynom and between the [x0,x1] ran
	 */
	public double areaNegative(double x0, double x1, double eps) {
		double sum = 0;
		double num = 0;
		for (double i=x0; i<=x1; i=i+eps) {
			num = f(i)*eps;
			if(num < 0){
				sum = sum + num;
			}
		}
		return Math.abs(sum);
	}
	
	@Override
	/**
	 * @return an Iterator (of Monoms) over this Polynom
	 * @return iterator
	 */
	public Iterator<Monom> iteretor() {
		Iterator<Monom> t = this.polyArr.iterator();
		return t;
	}
	/**
	 * function which combines all the monoms with same power
	 * @param arr is a list of monom
	 */
	private void arange (ArrayList arr) {
		Iterator<Monom> ti = arr.iterator();
		if(arr.size() > 1) {
			while(ti.hasNext()) {
				if(ti.next().get_coefficient() == 0) {
					ti.remove();
				}
			}
		}
		ArrayList <Monom> arr1 = new ArrayList(); 
		Iterator<Monom> t = arr.iterator();
		Monom m = new Monom ();
		Monom m1 = new Monom ();
		boolean b = false;
		if(t.hasNext()) { // first intertion
			m = new Monom (t.next());
			arr1.add(m);
		}
		while(t.hasNext()) { //second itretion and so on
			Iterator<Monom> t1 = arr1.iterator();
			m = new Monom (t.next());
			b = false; 
			while(t1.hasNext() && b == false) { 
				m1 = new Monom (t1.next());
				if(m.get_power() == m1.get_power()) { //check if the monoms have the same power
					m1.add(m); // combine them
					t1.remove(); // remove the old one
					arr1.add(m1); // add the new one
					b = true; // end of loop of monoms with the same power
				}
			}
			if(b == false) {
				arr1.add(m);
			}
		}
		arr = arr1;
		sort(arr); // send to sort function
	}
	/**
	 * function sort the polynom
	 * @param arr is the list
	 */
	private void sort (ArrayList arr) {
		Collections.sort(arr, new Monom_Comperator());
		fixString(arr); //send to function to fix the string
		return;
	}
	/**
	 * function fixes the string of the all polynom
	 * @param arr is the list
	 */
	private void fixString (ArrayList arr) {
		Iterator<Monom> t = arr.iterator();
		Monom m = new Monom ();
		String str = "";
		boolean firstIter = false, lastElement = false; 
		while(t.hasNext()) { // runs monom by monom and copy the info to a string
			m = new Monom (t.next());
			if(m.get_coefficient() == 0) {
				if(t.hasNext()) {
					m = new Monom(t.next());
				}
				else {
					lastElement = true;
				}
			}
			if(lastElement == false) {
				if(firstIter == true) {
					if(m.getMonom().charAt(0) != '-') {
						str = str + "+" + (m.getMonom());
					}
					else {
						str = str + (m.getMonom());
					}
				}
				else {
					str = str + (m.getMonom());
				}
				firstIter = true;
			}
		}
		this.setPolynom(str);
	}
/**
 * function that exhibit a polynom function
 * @param x0 is the starting range
 * @param x1 is the end of range
 * @param eps is the range input 
 */
	public void PolyGraph(double x0, double x1, double eps) {
		if(x0>x1) {
			System.out.println("You have written a wrong range. \nWe have swappen it for you so x0 would be the min num and x1 would be the max num.");
			double temp = x0;
			x0 = x1;
			x1 = temp;
		}

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 800);
		Polynom_able p = new Polynom ();
		p = this.derivative();
		DataTable data = new DataTable(Double.class, Double.class);
		DataTable dataP = new DataTable(Double.class, Double.class);
		for (double i=x0; i<=x1; i+=eps) {
			boolean b = checkPoint(i,p);// true if it's a min/max point
			double y = f(i);
			if(b==false) {
				data.add(i, y);
			}
			else {
				dataP.add(i,y);
			}
		}
		XYPlot plot = new XYPlot(data, dataP);
		getContentPane().add(new InteractivePanel(plot));
		LineRenderer lines = new DefaultLineRenderer2D();
		plot.setLineRenderers(data, lines);
		Color color = new Color(0.0f, 0.3f, 1.0f);
		plot.getPointRenderers(data).get(0).setColor(color);
		plot.getPointRenderers(dataP).get(0).setColor(color.RED);//min/max point
		plot.getLineRenderers(data).get(0).setColor(color);
		


	}
/**
 * function that checks if the point that given is a min/max point
 * @param x is the point
 * @param p is the derivative of "this" polynom
 * @return true if it's a min/max point
 */
	public boolean checkPoint(double x, Polynom_able p) {
		double before = p.f(x-0.0001);
		double after = p.f(x+0.0001);
		if((before>0 && after<0) || (before<0 && after>0))
			return true;
		else return false;
	}
}
