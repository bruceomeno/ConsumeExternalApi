import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    private static int solution(String name) {
        int result = -1;

        try {
            // Public API
            URL url = new URL("https://mocki.io/v1/d4867d8b-b5d5-4a48-a4ab-79131b5809b8");

            // Create a connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET"); // Set request method
            connection.connect();

            // Check if connection is successful
            int responseCode = connection.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode : " + responseCode);
            } else {
                StringBuilder sb = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    sb.append(scanner.nextLine());
                }

                //Close the scanner
                scanner.close();

                //Print JSON Object
                System.out.println(sb);

                // JSON simple library setup with Maven is used to convert strings to JSON
                JSONParser parser = new JSONParser();
                JSONArray data = (JSONArray) parser.parse(String.valueOf(sb));

                for (int i = 0; i < data.size(); i++) {
                    JSONObject user = (JSONObject) data.get(i);

                    //Print Users
                    System.out.println(user.get("name"));

                    if (user.containsValue(name)) {
                        result = 1;
                        break;
                    }
                }

                //Print 1 if data exists and -1 if data does not exist
                System.out.println(result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        solution("Harry Potter");
    }
}
