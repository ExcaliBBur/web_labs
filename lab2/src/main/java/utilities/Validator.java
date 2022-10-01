package utilities;

public class Validator {
    private final float x;
    private final float y;
    private final float R;

    public Validator(float x, float y, float R) {
        this.x = x;
        this.y = y;
        this.R = R;
    }

    private boolean validateX() {
        return !(x < -2) && !(x > 2);
    }
    private boolean validateY() {
        return !(y < -3) && !(y > 3);
    }
    private boolean validateR() {
        return !(R < 1) && !(R > 3);
    }
    public boolean validateAll() {
        return validateX() && validateY() && validateR();
    }
}
