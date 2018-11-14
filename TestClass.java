package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestClass {

	Monom m1 = new Monom (2,1);
	Polynom p1 = new Polynom("2*x^2+4*x^1");
	Polynom p2 = new Polynom("2*x^1+4*x^0");
	Polynom p3 = new Polynom();	
	Polynom p4 = new Polynom("2*x^2+4*x^1");

	@Test
	void testF (){
		assertEquals (0,p1.f(0));
		assertEquals (6,p1.f(1));
	}

	@Test
	void testAddMonom() {
		p1.add(m1);
		assertEquals("6.0*x^1+2.0*x^2", p1.getPolynom());
	}

	@Test
	void testAddPolynom_able() {
		p1.add(p2);
		assertEquals("4.0*x^0+6.0*x^1+2.0*x^2", p1.getPolynom());
	}

	@Test
	void testSubstract() {
		p1.substract(p2);
		assertEquals("-4.0*x^0+2.0*x^1+2.0*x^2", p1.getPolynom());
	}

	@Test
	void testMultiply() {
		p1.multiply(p2);
		assertEquals("16.0*x^1+16.0*x^2+4.0*x^3", p1.getPolynom());
	}

	@Test
	void testIsZero() {
		assertTrue(p3.isZero());
		assertFalse(p1.isZero());
	}

	@Test
	void testZeros() {
		assertEquals(-1,p1.zeros(-10,10,0.001));
	}

	@Test
	void testEqualsPolynom_able() {
		assertTrue(p1.equals(p4));
		assertFalse(p1.equals(p2));
	}

	@Test
	void testRoot() {
		if(p1.root(-1, 2, 0.0001)!=3.0517578125E-5) {
			fail("The function \"Root\" doesn't work as expected");
		}else {
			System.out.println("\"Root\" function works well");
		}
	}

	@Test
	void testDerivative() {
		Polynom p5 = (Polynom) p1.derivative();
		if(!(p5.getPolynom().equals("4.0*x^0+4.0*x^1"))) {
			fail("The function \"Derivative\" doesn't work as expected");
		}
		else{
			System.out.println("\"Derivative\" function works well");

		}
	}

	@Test
	void testArea() {
		if(p1.area(0,1, 0.0001)!=2.6669666699997197) {
			fail("The function \"Area\" doesn't work as expected");
		}
		else {
			System.out.println("\"Area\" function works well");
		}
	}
}
