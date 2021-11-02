import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneCheker{
    public void validator(){
        List<String> phoneNumbers = new ArrayList<>();
        try (FileReader reader = new FileReader("phoneNumbers.txt")) {
            Scanner scanner = new Scanner(reader);
            while(scanner.hasNext()) {
                phoneNumbers.add(scanner.nextLine());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        for(int i = 0; i < phoneNumbers.size(); i++){
            if(!phoneNumbers.get(i).matches("\\d{3}-\\d{3}-\\d{4}") && !phoneNumbers.get(i).matches("\\(\\d{3}\\) \\d{3}-\\d{4}")){
                phoneNumbers.remove(i);
            }
        }

        try (FileWriter writer = new FileWriter("phoneNumbersValidated.txt")) {
            for(int i = 0; i < phoneNumbers.size(); i++){
                writer.write(phoneNumbers.get(i) + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

class PhoneChekerTest{
    public static void main(String[] args) {
        PhoneCheker cheker = new PhoneCheker();
        cheker.validator();
    }
}