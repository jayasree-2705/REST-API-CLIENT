import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Weather_Api_Client {
    public static void main(String[] args) {
        try {
            // Step 1: Define API URL
            String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=11.11&longitude=77.34&current_weather=true";
            
            // Step 2: Create HTTP connection
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Step 3: Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            // Step 4: Parse JSON using org.json
            JSONObject json = new JSONObject(response.toString());
            JSONObject currentWeather = json.getJSONObject("current_weather");

            // Step 5: Display formatted output
            System.out.println("=== Current Weather ===");
            System.out.println("Temperature    : " + currentWeather.getDouble("temperature") + " °C");
            System.out.println("  Windspeed    : " + currentWeather.getDouble("windspeed") + " km/h");
            System.out.println("    Time       : " + currentWeather.getString("time"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
