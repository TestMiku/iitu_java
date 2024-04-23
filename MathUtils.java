import static java.lang.Math.*;

public class MathUtils {

    public static float clamp(float value, float min, float max) {
        return max(min(value, max), min);
    }

    public static double sign(double a) {
        return (a > 0) ? 1 : ((a < 0) ? -1 : 0);
    }

    public static double step(double edge, double x) {
        return (x > edge) ? 1 : 0;
    }

    public static float length(Vec2 v) {
        return (float) sqrt(v.x * v.x + v.y * v.y);
    }

    public static float length(Vec3 v) {
        return (float) sqrt(v.x * v.x + v.y * v.y + v.z * v.z);
    }

    public static Vec3 norm(Vec3 v) {
        return v.divide(new Vec3(length(v)));
    }

    public static float dot(Vec3 a, Vec3 b) {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    public static Vec3 abs(Vec3 v) {
        return new Vec3(abs(v.x), abs(v.y), abs(v.z));
    }

    public static Vec3 sign(Vec3 v) {
        return new Vec3(sign(v.x), sign(v.y), sign(v.z));
    }

    public static Vec3 step(Vec3 edge, Vec3 v) {
        return new Vec3(step(edge.x, v.x), step(edge.y, v.y), step(edge.z, v.z));
    }

    public static Vec3 reflect(Vec3 rd, Vec3 n) {
        return rd.subtract(n.multiply(2 * dot(n, rd)));
    }

    public static Vec3 rotateX(Vec3 a, double angle) {
        Vec3 b = new Vec3(a.x, a.y, a.z);
        b.z = a.z * cos(angle) - a.y * sin(angle);
        b.y = a.z * sin(angle) + a.y * cos(angle);
        return b;
    }

    public static Vec3 rotateY(Vec3 a, double angle) {
        Vec3 b = new Vec3(a.x, a.y, a.z);
        b.x = a.x * cos(angle) - a.z * sin(angle);
        b.z = a.x * sin(angle) + a.z * cos(angle);
        return b;
    }

    public static Vec3 rotateZ(Vec3 a, double angle) {
        Vec3 b = new Vec3(a.x, a.y, a.z);
        b.x = a.x * cos(angle) - a.y * sin(angle);
        b.y = a.x * sin(angle) + a.y * cos(angle);
        return b;
    }

    public static Vec2 sphere(Vec3 ro, Vec3 rd, float r) {
        float b = dot(ro, rd);
        float c = dot(ro, ro) - r * r;
        float h = b * b - c;
        if (h < 0.0f) return new Vec2(-1.0f);
        h = (float) sqrt(h);
        return new Vec2(-b - h, -b + h);
    }

    public static Vec2 box(Vec3 ro, Vec3 rd, Vec3 boxSize, Vec3 outNormal) {
        Vec3 m = new Vec3(1.0f).divide(rd);
        Vec3 n = m.multiply(ro);
        Vec3 k = abs(m).multiply(boxSize);
        Vec3 t1 = n.subtract(k);
        Vec3 t2 = n.add(k);
        float tN = max(max(t1.x, t1.y), t1.z);
        float tF = min(min(t2.x, t2.y), t2.z);
        if (tN > tF || tF < 0.0f) return new Vec2(-1.0f);
        Vec3 yzx = new Vec3(t1.y, t1.z, t1.x);
        Vec3 zxy = new Vec3(t1.z, t1.x, t1.y);
        outNormal.set(
                -sign(rd.x) * step(yzx.x, t1.x) * step(yzx.y, t1.y),
                -sign(rd.y) * step(yzx.y, t1.y) * step(yzx.z, t1.z),
                -sign(rd.z) * step(zxy.x, t1.x) * step(zxy.y, t1.y)
        );
        return new Vec2(tN, tF);
    }

    public static float plane(Vec3 ro, Vec3 rd, Vec3 p, float w) {
        return -(dot(ro, p) + w) / dot(rd, p);
    }
}
