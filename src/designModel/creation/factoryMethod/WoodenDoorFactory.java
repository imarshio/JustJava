package designModel.creation.factoryMethod;

/**
 * @author masuo
 * @data 2021/8/26 17:58
 * @Description 专门生产Apple
 */

public class WoodenDoorFactory implements DoorFactory{

    @Override
    public WoodenDoor makeDoor() {
        return new WoodenDoor();
    }
}
