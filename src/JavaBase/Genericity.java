package JavaBase;

/**
 * @author masuo
 * @create 2021/7/17 21:36
 * @Description 泛型
 */
public class Genericity {
    public static void main(String[] args) {
        genericity();

    }

    private static void genericity() {
        class Box<T>{
            private T t;
            public Box(T t){
                this.t = t;
            }

            public Class<?> getClassName(){
                return t.getClass();
            }
        }

        Box<String> b1 = new Box<>("ss");
        Box<Integer> b2 = new Box<>(111);
        System.out.println(b1.getClassName());
        System.out.println(b2.getClassName());
    }
}
