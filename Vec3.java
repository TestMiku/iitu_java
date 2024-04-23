public class Vec3 {
    public float x, y, z;

    public Vec3(float value) {
        this.x = value;
        this.y = value;
        this.z = value;
    }

    public Vec3(float x, Vec2 v) {
        this.x = x;
        this.y = v.x;
        this.z = v.y;
    }

    public Vec3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3 add(Vec3 other) {
        return new Vec3(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Vec3 subtract(Vec3 other) {
        return new Vec3(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public Vec3 multiply(Vec3 other) {
        return new Vec3(this.x * other.x, this.y * other.y, this.z * other.z);
    }

    public Vec3 divide(Vec3 other) {
        return new Vec3(this.x / other.x, this.y / other.y, this.z / other.z);
    }

    public Vec3 negate() {
        return new Vec3(-this.x, -this.y, -this.z);
    }
}
