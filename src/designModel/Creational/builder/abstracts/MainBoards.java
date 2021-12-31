package designModel.Creational.builder.abstracts;

/**
 * @author masuo
 * @data 2021/9/3 15:09
 * @Description 抽象产品
 */

public abstract class MainBoards {

    public abstract void makeMainBoard(CPUS cpu,Disks disk);

    protected abstract boolean setCPU(CPUS cpu);

    protected abstract boolean setDisk(Disks disk);
}
