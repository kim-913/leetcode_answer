import java.util.*;

public class solution_9_27 {
    
    public static void main(String[] args) {
        
    }

    // LC 929. Unique Email Addresses
    public int numUniqueEmails(String[] emails) {
        if(emails.length == 1) return 1;
        Set<String> s = new HashSet<>();
        for(String email: emails){
            String[] each = email.split("@");
            String[] local = each[0].split("\\+");
            s.add(local[0].replace(".", "") + "@" + each[1]);
        }
        return s.size();
    }
}
