
/**
 * 
 */
public class TestPlanet {

    public static void main(String[] args) {

        checkCalcForceExerted();

    }

    private static void checkEquals(double actual, double expected, String label, double eps) {
        if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }

    private static void checkCalcForceExerted() {

        System.out.println("Checking calcForceExerted");

        Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet p2 = new Planet(2.0, 1.0, 3.0, 4.0, 4e11, "jupiter.gif");
        Planet p3 = new Planet(4.0, 5.0, 3.0, 4.0, 5.0, "jupiter.gif");

        checkEquals(Planet.calcForceExerted(p1, p2), 133.4, "calcForceExerted()", 0);
        checkEquals(Planet.calcForceExerted(p1, p3), 6.67e-11, "calcForceExerted()", 0);
    }
}