package designModel.creation.builder;


import designModel.creation.builder.abstracts.CPUS;
import designModel.creation.builder.abstracts.Disks;
import designModel.creation.builder.abstracts.MainBoards;
import designModel.creation.builder.abstracts.PCFactory;

/**
 * @author masuo
 * @data 2021/9/3 16:33
 * @Description
 */

public class Lenovo extends PCFactory {

    public Lenovo() {
        super();
    }



    @Override
    public boolean loadCPU(CPUS cpu) {
        System.out.println("Lenovo 装CPU");
        return false;
    }

    @Override
    public boolean loadDisk(Disks disk) {
        System.out.println("Lenovo 装disk");
        return false;
    }

    @Override
    public boolean loadMainBoard(MainBoards mb) {
        System.out.println("Lenovo 装主板");
        return false;
    }
}
