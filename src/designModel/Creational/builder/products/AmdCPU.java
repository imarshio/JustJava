package designModel.Creational.builder.products;


import designModel.Creational.builder.abstracts.CPUS;

/**
 * @author masuo
 * @data 2021/9/3 15:12
 * @Description
 */

public class AmdCPU extends CPUS {
    @Override
    public void makeCPU() {
        System.out.println("This is a A CPU");
    }
}
