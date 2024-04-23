public class Vec2 {
    public float x, y;

    public Vec2(float value) {
        this.x = value;
        this.y = value;
    }

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2 add(Vec2 other) {
        return new Vec2(this.x + other.x, this.y + other.y);
    }

    public Vec2 subtract(Vec2 other) {
        return new Vec2(this.x - other.x, this.y - other.y);
    }

    public Vec2 multiply(Vec2 other) {
        return new Vec2(this.x * other.x, this.y * other.y);
    }

    public Vec2 divide(Vec2 other) {
        return new Vec2(this.x / other.x, this.y / other.y);
    }
}
