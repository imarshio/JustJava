package creation.builder.products;


import designModel.creation.builder.abstracts.CPUS;
import designModel.creation.builder.abstracts.Disks;
import designModel.creation.builder.abstracts.MainBoards;

/**
 * @author masuo
 * @data 2021/9/3 15:57
 * @Description 技嘉主板
 */

public class GigabateMainBoard extends MainBoards {
    @Override
    public void makeMainBoard(CPUS cpu, Disks disk) {
        System.out.println(setCPU(cpu));
        System.out.println(setDisk(disk));
    }

    @Override
    public boolean setCPU(CPUS cpu) {
        System.out.println("安装" + cpu.toString() + "到技嘉主板上");
        return true;
    }

    @Override
    public boolean setDisk(Disks disk) {
        System.out.println("安装" + disk.toString() + "到技嘉主板上");
        return true;
    }
}
