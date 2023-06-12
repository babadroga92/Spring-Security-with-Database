public class LongNumbers {
    /*
    What will be the output when you use a long number with L and without L suffix
     */
    public static void main(String[] args){
        long longNumberWithoutL= 1000*60*60*24*365; // considers it as 32 bits
        long longNumberWithL= 1000*60*60*24*365L; // considers it as 36 bits and gives correct output

        System.out.println(longNumberWithoutL); //1471228928
        System.out.println(longNumberWithL); //31536000000
    }
}
