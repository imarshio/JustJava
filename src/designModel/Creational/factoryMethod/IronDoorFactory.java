package designModel.Creational.factoryMethod;

/**
 * @author masuo
 * @data 2021/8/26 17:59
 * @Description 专门生产Banana
 */

public class IronDoorFactory implements DoorFactory{

    @Override
    public IronDoor makeDoor() {
        return new IronDoor();
    }
}
