package designModel.creation.builder.abstracts;

/**
 * @author masuo
 * @data 2021/9/3 15:09
 * @Description 抽象产品
 */

public abstract class Disks {

    private static int capacity = 0;

    public Disks(int capacity) {
        Disks.capacity = capacity;
    }

    public abstract void makeDisk();


}
