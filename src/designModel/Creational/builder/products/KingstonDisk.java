package designModel.Creational.builder.products;


import designModel.Creational.builder.abstracts.Disks;

/**
 * @author masuo
 * @data 2021/9/3 15:18
 * @Description
 */

public class KingstonDisk extends Disks {

    public KingstonDisk(int capacity) {
        super(capacity);
    }

    @Override
    public void makeDisk() {
        System.out.println("This is the Kingston's Disk");
    }
}
