package creational.builder.products;


import creational.builder.abstracts.CPUS;

/**
 * @author masuo
 * @data 2021/9/3 15:12
 * @Description
 */

public class IntelCPU extends CPUS {
    @Override
    public void makeCPU() {
        System.out.println("This is a N CPU");
    }
}
