public class Planet {

    public double xxPos; // x position
    public double yyPos; // y position
    public double xxVel; // x velocity
    public double yyVel; // y velocity
    public double mass; // mass
    public String imgFileName; // img file name

    private static final double G = 6.67e-11;

    private static final String baseImgFilePath = "./images/";

    public Planet(double xPos, double yPos, double xVel, double yVel, double m, String img) {
        xxPos = xPos;
        yyPos = yPos;
        xxVel = xVel;
        yyVel = yVel;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {

        double x = p.xxPos - xxPos;
        double y = p.yyPos - yyPos;

        return Math.sqrt(x * x + y * y);
    }

    public double calcForceExertedBy(Planet p) {

        double r = calcDistance(p);

        return G * mass * p.mass / (r * r);
    }

    public double calcForceExertedByX(Planet p) {

        double f = calcForceExertedBy(p);
        double r = calcDistance(p);
        double dx = p.xxPos - xxPos;

        return f * dx / r;
    }

    public double calcForceExertedByY(Planet p) {

        double f = calcForceExertedBy(p);
        double r = calcDistance(p);
        double dy = p.yyPos - yyPos;

        return f * dy / r;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {

        double netX = 0;

        for (Planet p : allPlanets) {
            if (!p.equals(this)) {
                netX += calcForceExertedByX(p);
            }
        }

        return netX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {

        double netY = 0;

        for (Planet p : allPlanets) {
            if (!p.equals(this)) {
                netY += calcForceExertedByY(p);
            }
        }

        return netY;
    }

    public void update(double dt, double fX, double fY) {

        double aX = fX / mass;
        double aY = fY / mass;
        xxVel += dt * aX;
        yyVel += dt * aY;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;

    }

    public static double calcForceExerted(Planet p1, Planet p2) {

        return p1.calcForceExertedBy(p2);
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, baseImgFilePath + imgFileName);
    }
}
