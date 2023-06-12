public class DivideByZero {
    public static void main(String[] args){
        /*
        What will be the output when you divide number by zero
         */
        System.out.println(9.0/0); //infinity

        System.out.println(10/0.0); //infinity
        System.out.println(19.99999d/0); // infinity
        System.out.println(0/0); //arithmetic exception
        System.out.println(0.0/0); //NaN

    }
}
