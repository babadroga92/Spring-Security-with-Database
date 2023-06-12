public class Test {
    public static void main(String[] args){
        System.out.println(main.x);
    }
}
class main{
    public static final int x = 100; /* with final included, Test class will only print variable x,
     without final included it will print the whole main class*/
    static {
        System.out.println("main -- class static block...");
    }
}