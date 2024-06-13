package creational.factoryMethod;

/**
 * @author masuo
 * @data 2021/8/26 17:38
 * @Description
 */

public class IronDoor implements Door {

    private float width;
    private float height;

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }
}
