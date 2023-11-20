public class NBody {

    public static double readRadius(String file) {

        double radius;

        In in = new In(file);
        in.readInt();
        radius = in.readDouble();

        return radius;
    }

    public static Planet[] readPlanets(String file) {

        In in = new In(file);
        int num = in.readInt();
        in.readDouble();
        Planet[] ps = new Planet[num];
        for (int i = 0; i < num; i++) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            ps[i] = new Planet(xPos, yPos, xVel, yVel, mass, img);
        }

        return ps;
    }

    public static void main(String[] args) {

        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        int num = planets.length;

        // draw
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        // buffering
        StdDraw.enableDoubleBuffering();
        double t = 0;
        while (t <= T) {
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            // planets
            for (int i = 0; i < num; i++) {
                Planet p = planets[i];
                double xForec = p.calcNetForceExertedByX(planets);
                double yForec = p.calcNetForceExertedByY(planets);
                xForces[i] = xForec;
                yForces[i] = yForec;
            }
            for (int i = 0; i < num; i++) {
                Planet p = planets[i];
                p.update(dt, xForces[i], yForces[i]);
            }
            // background
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }

}
