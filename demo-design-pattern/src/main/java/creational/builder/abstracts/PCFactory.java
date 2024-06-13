package creational.builder.abstracts;

/**
 * @author masuo
 * @data 2021/9/3 14:37
 * @Description 抽象工厂
 */

public abstract class PCFactory {
    // 抽象工厂指的是多个产品族的组合
    // 如电脑--由CPU，硬盘，主板
    // 这样我们就可以建立一个PCFactory
    // CPUFactory有三个方法，装机

    protected abstract boolean loadCPU(CPUS cpu);

    protected abstract boolean loadDisk(Disks disk);

    protected abstract boolean loadMainBoard(MainBoards mb);

    public PCFactory(CPUS cpu, Disks disk, MainBoards mb) {
        loadCPU(cpu);
        loadDisk(disk);
        loadMainBoard(mb);
    }

    public PCFactory() {
    }
}
