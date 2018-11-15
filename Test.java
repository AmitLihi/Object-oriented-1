package myMath;
public class Test {

	public static void main(String[] args) {
		System.out.println("Test Monom: \n");
		testMonom();
		System.out.print("\n--------------------------------------------------------------- \n");
		System.out.println("Test Polynom \n");
		testPolynom();

	}

	public static void testMonom() {
		Monom m = new Monom ();
		Monom m1 = new Monom (-2,1);
		Monom m2 = new Monom ();
		Monom m3 = new Monom ("2.5*x^8");
		Monom m4 = new Monom (1,1);

		//constructor
		System.out.println("constructor: \"-2*x^1\": " + m1 );
		System.out.println("constructor: \"null\": " + m2 );
		System.out.println("constructor: \"2.5*x^8\": " + m3 );
		System.out.println();

		//copy constructor
		m = new Monom (m1);
		System.out.println("copy constructor: \"-2*x^1\": " + m);
		m = new Monom (m2);
		System.out.println("copy constructor: \"null\": " + m );
		m = new Monom (m3);
		System.out.println("copy constructor: \"2.5*x^8\": " + m );
		System.out.println();

		//function f
		System.out.println("function f(-1): \"2\": " + m1.f(-1) );	
		System.out.println("function f(-1): \"0\": " + m2.f(-1) );
		System.out.println("function f(-1): \"2.5\": " + m3.f(-1) );
		System.out.println();

		//add function 
		m1.add(m3);
		m1.add(m4);
		System.out.println("add function: \"-1\": "+ m1);
		System.out.println();

		//substract function
		m1.substract(m3);
		m1.substract(m4);
		System.out.println("substract function: \"-2\": "+ m1);
		System.out.println();

		//multiply function
		m1.multiply(m2);
		m1.multiply(m3);
		System.out.println("multiply function: \"-5*x^9\": "+ m1);
		System.out.println();

		//derivative function
		System.out.println("derivative function: \"-45*x^8\": "+ m1.derivative());
		System.out.println("derivative function: \"null\": "+ m2.derivative());
		System.out.println();

	}

	public static void testPolynom() {
		Monom m = new Monom ("3*x^3");
		Monom m1 = new Monom ();
		Polynom_able p = new Polynom ("2*x^1");
		Polynom_able p1 = new Polynom ("3*x^3+2*x^2+1*x^1+0*x^9");
		Polynom p2 = new Polynom ();
		Polynom p3 = new Polynom ("1*x^2-5*x^1+6*x^0");
		Polynom p4= new Polynom ("-2*x^0+1*x^2");
		Polynom p5 = new Polynom ("1*x^1+0*x^5");


		//constructor
		System.out.println("constructor: \"2*x^1\": " + p );
		System.out.println("constructor: \"1.0*x^1+2.0*x^2+3.0*x^3 \": " + p1 );
		System.out.println("constructor: \"null\": " + p2 );
		System.out.println("constructor: \"6.0*x^0-5.0*x^1+1.0*x^2\": " + p3 );
		System.out.println();

		//copy constructor
		p2 = new Polynom (p3);
		System.out.println("copy constructor: \"6.0*x^0-5.0*x^1+1.0*x^2\": " + p2);
		p2 = new Polynom ();
		System.out.println();

		//function f
		System.out.println("function f: \"6\":  " + p3.f(0));
		System.out.println("function f: \"20\":  " + p3.f(-2));
		System.out.println("function f: \"0\":  " + p2.f(80));
		System.out.println();

		//add Monom function
		p1.add(m);
		System.out.println("add function: \"1.0*x^1+2.0*x^2+6.0*x^3\":  " + p1);
		p2.add(m);
		System.out.println("add function: \"3.0*x^3\":  " + p2);
		p3.add(m);
		System.out.println("add function: \"6.0*x^0-5.0*x^1+1.0*x^2+3.0*x^3\":  " + p3);
		p1.add(m1);
		System.out.println("add function: \"1.0*x^1+2.0*x^2+6.0*x^3\":  " + p1);
		p2.add(m1);
		System.out.println("add function: \"3.0*x^3\":  " + p2);
		p2 = new Polynom ();
		p3.add(m1);
		System.out.println("add function: \"6.0*x^0-5.0*x^1+1.0*x^2+3.0*x^3\":  " + p3);
		System.out.println();

		//add Polynom function
		p1.add(p2);
		System.out.println("add Polynom function: \"1.0*x^1+2.0*x^2+6.0*x^3\":  " + p1);
		p3.add(p);
		System.out.println("add Polynom function: \"6.0*x^0-3.0*x^1+1.0*x^2+3.0*x^3\":  " + p3);
		System.out.println();

		//substract function
		p1.substract(p2);
		System.out.println("substract function: \"-6.0*x^0+6.0*x^1+1.0*x^2+3.0*x^3\":  " + p1);
		p2 = new Polynom ();
		p2.substract(p3);
		System.out.println("substract function: \"-6.0*x^0+3.0*x^1-1.0*x^2-3.0*x^3\":  " + p2);
		System.out.println();

		//multiply function
		p4.multiply(p5);
		System.out.println("multiply function: \"-2.0*x^1+1.0*x^3\":  " + p4);
		p2 = new Polynom ();
		p2.multiply(p4);
		System.out.println("multiply function: \"null\":  " + p2);
		System.out.println();

		//equals function
		System.out.println("equals function: \"true\":  " + p4.equals(p4));
		System.out.println("equals function: \"false\":  " + p4.equals(p1));
		System.out.println();

		//isZero function
		System.out.println("isZero function: \"false\":  " + p4.isZero());
		System.out.println("isZero function: \"true\":  " + p2.isZero());
		System.out.println();

		//derivative function
		System.out.println("derivative function: \"-2.0*x^0+3.0*x^2\":  " + p4.derivative());
		System.out.println("derivative function: \"null\":  " + p2.derivative());
		System.out.println();

		//root function
		Polynom p6 = new Polynom ("1*x^2+4*x^1");
		System.out.println("root function: \"3.0517578125E-5\":  " + p6.root(-1,2, 0.0001));
		System.out.println();
		
		//area function
		System.out.println("area function: \"2.333583334999779\":  " + p6.area(0, 1, 0.0001));
		System.out.println();
		
		//PolyGraph function
		Polynom p7 = new Polynom("0.2*x^4-1.5*x^3+3.0*x^2-1*x^1-5.0*x^0");
		p7.PolyGraph(-2,6,0.25);
		p7.setVisible(true);
		
		//areaNegative function
		System.out.println("areaNegative function: \"25.183742050201328" + 	"\":  " + p7.areaNegative(-2, 6, 0.001));
	}
}
