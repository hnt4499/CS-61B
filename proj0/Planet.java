public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;

    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Planet(Planet p) {
        this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }

    /** Calculates the distance between two bodies. */
    public double calcDistance(Planet p) {
        return Math.sqrt(Math.pow(p.xxPos - this.xxPos, 2) + Math.pow(p.yyPos - this.yyPos, 2));
    }

    /** Calculate the force exerted by a body. */
    public double calcForceExertedBy(Planet p) {
        return G * p.mass * this.mass / Math.pow(this.calcDistance(p), 2);
    }

    /** Calculate the force exerted by a body in x and y planes, respectively. */
    public double calcForceExertedByX(Planet p) {
        if (this.calcDistance(p) == 0) {
            return 0;
        }
        return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        if (this.calcDistance(p) == 0) {
            return 0;
        }
        return this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
    }

    /** Calculate the NET force exerted by multiple bodies in x and y planes, respectively. */
    public double calcNetForceExertedByX(Planet[] p) {
        double res = 0;
        for (Planet planet : p) {
            res += this.calcForceExertedByX(planet);
        }
        return res;
    }

    public double calcNetForceExertedByY(Planet[] p) {
        double res = 0;
        for (Planet planet : p) {
            res += this.calcForceExertedByY(planet);
        }
        return res;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }

    /** Updates the velocity and position of body according to the forces and the time passed. */
    public void update(double time, double x, double y) {
        this.xxVel += time * x / this.mass;
        this.yyVel += time * y / this.mass;

        this.xxPos += time * this.xxVel;
        this.yyPos += time * this.yyVel;
    }
}


























