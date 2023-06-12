public class NaN {
    public static void main(String[] args){
        /*
        What is Nan - Not a number?
         */
        System.out.println(2/0); //arithmetic exception
        System.out.println(0.0/0.0); //NaN
        System.out.println(Math.sqrt(-1)); // NaN

        System.out.println(Float.NaN == Float.NaN);
        System.out.println(Float.NaN != Float.NaN);

        double nan = 2.1 % 0; //NaN
        System.out.println((2.1%0) == nan); //false
        System.out.println((nan ==nan)); //false


    }
}
