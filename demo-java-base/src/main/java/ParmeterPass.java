package JavaBase;

/**
 * @author masuo
 * @create 2021/7/8 23:01
 * @Description java中的参数传递 是值传递
 */
public class ParmeterPass {


    public static void main(String[] args) {
        // 此时的dog是一个指针，存储对象的地址，
        // 在将一个参数传入一个方法时，本质上是将对象的地址以值的方式传递到形参中。
        Dog doga = new Dog("doga");
        changeDogName(doga);
        System.out.println(doga.getName());// dogb

        System.out.println(doga.getAddress());// JavaBase.Dog@1b6d3586
        changeDogAddress(doga);
        System.out.println(doga.getName());// dogb，这里名称没变是因为方法中的对象对此处无影响，因为没有返回赋值的操作
        System.out.println(doga.getAddress());// JavaBase.Dog@1b6d3586，与上面相同，在方法中改变对象，对此处无影响
    }

    private static void changeDogAddress(Dog doga) {
        System.out.println(doga.getAddress());// JavaBase.Dog@1b6d3586,在这里还与上面的地址相同，因为地址还没发生改变
        doga = new Dog("dogc");// 生成新的dog对象，
        System.out.println(doga.getName());// dogc
        System.out.println(doga.getAddress());// JavaBase.Dog@4554617c，在这里地址就已经发生了改变
    }

    private static void changeDogName(Dog doga) {
        // 在此方法中，修改对象的字段值会修改元对象字段值，因为此时引用的是同一个对象
        doga.setName("dogb");
    }


}

class Dog {
    String name;

    Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        System.out.println("父类address");
        return super.toString();
    }

    final void finalDog() {
        System.out.println("这是final方法，不能被重写！");
    }

    private void privateDog() {
        System.out.println("这是private方法，不能被重写！");
    }
}