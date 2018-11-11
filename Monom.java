
package myMath;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	
	private double _coefficient; 
	private int _power;
	private String monom;
	/**
	 * default constructor
	 */
	public Monom(){
		this.set_coefficient(0);
		this.set_power(0);
		this.setMonom("0*x^0");
	}
	/**
	 * This constructor gets two values and create a monom
	 * @param a is the coefficient
	 * @param b is the power
	 */
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
		this.setMonom(this.get_coefficient() + "*x^" + this.get_power());
	}
	/**
	 * copy constructor
	 * @param ot is a monom
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
		this.setMonom(ot.get_coefficient() + "*x^" + ot.get_power());
	}
	/**
	 * This constructor gets string and split it to monom
	 * @param monom
	 */
	public Monom (String monom) {
		this.setMonom(monom);
		for (int i=0; i<monom.length(); i++) {
			if(monom.charAt(i) == '*') {
				this.set_coefficient(Double.parseDouble(monom.substring(0,i)));
				this.set_power(Integer.parseInt(monom.substring(i+3,monom.length())));
			}
		}
	}
	
	public String getMonom() {
		return monom;
	}
	public void setMonom(String monom) {
		this.monom = monom;
	}
	public int get_power() {
		return this._power;
	}
	public double get_coefficient() {
		return this._coefficient;
	}
	public void set_coefficient(double a){
		this._coefficient = a;
		this.setMonom(this.get_coefficient() + "*x^" + this.get_power());
	}
	public void set_power(int p) {
		this._power = p;
		this.setMonom(this.get_coefficient() + "*x^" + this.get_power());
	}
	/**
	 * This interface represents a simple function of type y=f(x), where both y and x are real numbers.
	 * * @param x is the value of the variable
	 */
	@Override
	public double f(double x) {
		return Math.pow(x, this.get_power()) * this.get_coefficient();
	} 
	/**
	 * function which calculate two monoms
	 * @param m is a new monom
	 */
	public void add (Monom m) {
		if(this.get_power() == m.get_power()) {
			this.set_coefficient(this.get_coefficient()+m.get_coefficient());
			this.setMonom(this.get_coefficient() + "*x^" + this.get_power());
		}
		else {
			System.out.println("The monoms has no common power");
		}
	}
	/**
	 * function which substract between two monoms
	 * @param m is a new monom
	 */
	public void substract(Monom m) {
		if(this.get_power() == m.get_power()) {
			this.set_coefficient(this.get_coefficient()-m.get_coefficient());
			this.setMonom(this.get_coefficient() + "*x^" + this.get_power());
		}
		else {
			System.out.println("The monoms has no common power");
		}
	}
	/**
	 * function which multiply between two monoms
	 * @param m is a new monom
	 */
	public void multiply (Monom m) {
		if(m.get_coefficient() != 0) {
		this.set_power(this.get_power() + m.get_power());
		this.set_coefficient(this.get_coefficient() * m.get_coefficient());
		this.setMonom(this.get_coefficient() + "*x^" + this.get_power());
		}
		else {
			System.out.println("Zero multiply");
		}
	}
	/**
	 * function which calculate the monom derivative
	 * @return the monom's derivative
	 */
	public Monom derivative () {
		Monom m = new Monom (this.get_coefficient(),this.get_power());
		if(m.get_power() != 0) {
		m.set_coefficient(m.get_coefficient() * m.get_power());
		m.set_power(m.get_power() - 1);
		m.setMonom(m.get_coefficient() + "*x^" + m.get_power());
		}
		else {
			m.set_coefficient(0);
			m.setMonom(m.get_coefficient() + "*x^" + m.get_power());
		}
		return m;
	}
	@Override
	public String toString() {
		if(this.get_coefficient() != 0) {
		return this.getMonom();
		}
		else {
			return null;
		}
	}
	
}