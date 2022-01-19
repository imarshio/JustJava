package designModel.creation.builder.products;


import designModel.creation.builder.abstracts.Disks;

/**
 * @author masuo
 * @data 2021/9/3 15:18
 * @Description
 */

public class SamsungDisk extends Disks {

    public SamsungDisk(int capacity) {
        super(capacity);
    }

    @Override
    public void makeDisk() {
        System.out.println("This is the Samsung's Disk");
    }
}
