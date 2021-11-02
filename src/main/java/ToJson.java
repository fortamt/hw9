import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToJson{
    public void toJsonFormat(){
        List<User> users = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        try (FileReader reader = new FileReader("objectsToJson.txt")) {
            Scanner scanner = new Scanner(reader);
            while(scanner.hasNext()) {
                strings.add(scanner.nextLine());
            }

            for(int i = 0; i<strings.size(); i++){
                Pattern pattern = Pattern.compile("(\\w+)( )([0-1]?[0-9]?[0-9])");
                Matcher matcher = pattern.matcher(strings.get(i));
                while(matcher.find()){
                    users.add(new User(matcher.group(1), Integer.parseInt(matcher.group(3))));
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (FileWriter writer = new FileWriter("Objects.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(users);
                writer.write(json);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

class toJsonTest{
    public static void main(String[] args) {
        ToJson toJson = new ToJson();
        toJson.toJsonFormat();
    }
}

class User{
    private String name;
    private int age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}