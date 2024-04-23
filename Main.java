import java.io.Console;

public class Main {
    
    static void setWindow(int width, int height) {
        Console console = System.console();
        if (console != null) {
            console.writer().print("\033[8;" + height + ";" + width + "t");
            console.flush();
        }
    }

    public static void main(String[] args) {
        int width = 120 * 2;
        int height = 30 * 2;
        setWindow(width, height);
        float aspect = (float) width / height;
        float pixelAspect = 11.0f / 24.0f;
        char[] gradient = " .:!/r(l1Z4H9W8$@".toCharArray();
        int gradientSize = gradient.length - 2;

        char[] screen = new char[width * height];

        for (int t = 0; t < 10000; t++) {
            Vec3 light = VecFunctions.norm(new Vec3(-0.5, 0.5, -1.0));
            Vec3 spherePos = new Vec3(0, 3, 0);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    Vec2 uv = new Vec2(i, j).divide(new Vec2(width, height)).multiply(2.0f).subtract(new Vec2(1.0f));
                    uv.x *= aspect * pixelAspect;
                    Vec3 ro = new Vec3(-6, 0, 0);
                    Vec3 rd = VecFunctions.norm(new Vec3(2, uv.x, uv.y));
                    ro = VecFunctions.rotateY(ro, 0.25);
                    rd = VecFunctions.rotateY(rd, 0.25);
                    ro = VecFunctions.rotateZ(ro, t * 0.01);
                    rd = VecFunctions.rotateZ(rd, t * 0.01);
                    float diff = 1;
                    for (int k = 0; k < 5; k++) {
                        float minIt = 99999;
                        Vec2 intersection = VecFunctions.sphere(ro.subtract(spherePos), rd, 1);
                        Vec3 n = new Vec3();
                        float albedo = 1;
                        if (intersection.x > 0) {
                            Vec3 itPoint = ro.subtract(spherePos).add(rd.multiply(intersection.x));
                            minIt = intersection.x;
                            n = VecFunctions.norm(itPoint);
                        }
                        Vec3 boxN = new Vec3();
                        intersection = VecFunctions.box(ro, rd, new Vec3(1), boxN);
                        if (intersection.x > 0 && intersection.x < minIt) {
                            minIt = intersection.x;
                            n = boxN;
                        }
                        intersection = VecFunctions.plane(ro, rd, new Vec3(0, 0, -1), 1);
                        if (intersection.x > 0 && intersection.x < minIt) {
                            minIt = intersection.x;
                            n = new Vec3(0, 0, -1);
                            albedo = 0.5f;
                        }
                        if (minIt < 99999) {
                            diff *= (VecFunctions.dot(n, light) * 0.5 + 0.5) * albedo;
                            ro = ro.add(rd.multiply(minIt - 0.01f));
                            rd = VecFunctions.reflect(rd, n);
                        } else {
                            break;
                        }
                    }
                    int color = (int) (diff * 20);
                    color = Math.max(Math.min(color, gradientSize), 0);
                    char pixel = gradient[color];
                    screen[i + j * width] = pixel;
                }
            }
            System.out.print(screen);
        }
    }
}
