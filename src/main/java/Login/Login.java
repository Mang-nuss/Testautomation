package Login;

public class Login {

    private static String output;
    private static String lowercases = "abcdefghijklmnopqrstuvwxyz";
    private static String uppercases = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String numbers = "0123456789";
    private static String specials = "!\"@#€%&/|()=?+-_^¨*'";

    private static String usernameChars = lowercases + uppercases + numbers;
    private static String Chars = usernameChars + specials;

/*    public static void main(String[] args) {

        System.out.println(generateUniqueUsername());
        System.out.println(generateUniqueEmailAddress());
    }*/

    //Only letters and numbers, or the email address
    public static String generateUniqueUsername(int nrOfChars) {

        output = "";

        for(int n=0; n<nrOfChars; n++) {
            double nr = ( Math.random() * (usernameChars.length()-1) ) + 1;
            output+=usernameChars.charAt((int)nr);
        }
        return output;
    }

    /*    One lowercase character
    One uppercase character
    One number
    One special character
    8 characters minimum*/
    public static String generateUniquePassword(int nrOfChars) {

        output = "";

        for(int n=0; n<nrOfChars; n++) {
            double nr = ( Math.random() * (Chars.length()-1) ) + 1;
            output+=Chars.charAt((int)nr);
        }
        return output;
    }

    public static String generateUniqueEmailAddress(int nrOfChars) {

        output = "";

        for(int n=0; n<nrOfChars; n++) {
            double nr = ( Math.random() * (Chars.length()-1) ) + 1;
            output+=Chars.charAt((int)nr);
        }
        return output+"@email.com";
    }
}
