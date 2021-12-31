package CodeTest;

public class Tests {
    public static void main(String[] args) {
       int i =0,j=5;
       tp:for(;;){
           i++;
           for(;;){
               if(i>--j){
                   break tp;
               }
           }

       }
       System.out.println(i+","+j);
    }

}

class Subclass extends Tests{
    static {
        System.out.println("Subclass");
    }
    Subclass(){
        System.out.println("Subclass()");
    }
}