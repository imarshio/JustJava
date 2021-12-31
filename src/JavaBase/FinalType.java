package JavaBase;

/**
 * @author masuo
 * @create 2021/7/10 9:39
 * @Description final 的使用
 */
public class FinalType {

    public static void main(String[] args) {

        finalOnData();
        finalOnFun();
        finalOnClass();
    }


    /**
     * final 对方法使用
     * 基本类型
     *
     */
    private static void finalOnFun() {
        System.out.println("final 方法");
        DogA dogA = new DogA("doga");

        dogA.finalDog();
        dogA.getAddress();
        dogA.privateDog();

    }


    /**
     * final 对类使用
     */
    private static void finalOnClass() {
        String s = "123";
    }

    /**
     * final 对数据使用
     * 1.基本类型
     * 2.引用类型
     */
    private static void finalOnData() {

        //基本类型
        final int num1 = 10;
        // num1 = 2;//Cannot assign a value to final variable 'num1',意思是不能给一个final变量分配值

        //引用类型
        final Dog dog = new Dog("doga");
        System.out.println(dog.getName());//doga
        dog.setName("dogb");
        System.out.println(dog.getName());//dogb
        // dog = new Dog("dagc");//Cannot assign a value to final variable 'dog'

        //可以看到，对于基本类型，不能改变基本类型变量的值，对于引用类型，不能引用其他对象
    }
}


class DogA extends Dog{

    DogA(String name) {
        super(name);
    }

//    void finalDog(){
//      //'finalDog()' cannot override 'finalDog()' in 'JavaBase.Dog'; overridden method is final
//    }

    @Override
    public String getAddress() {
        System.out.println("子类address");
        return "";
    }

    void privateDog(){
        System.out.println("这是子类的privateDog");
    }
}