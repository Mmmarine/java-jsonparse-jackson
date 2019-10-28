import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Parse {

    private final static String JSON_WEATHER_PATH = "weather.json";

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(new File(JSON_WEATHER_PATH));

            String cityName = root.get("name").asText();

            JsonNode coordObject = root.get("coord");
            Double cityLatitude = coordObject.get("lon").asDouble();
            Double cityLongitude = coordObject.get("lat").asDouble();

            JsonNode wind = root.get("wind");
            // TODO : get the "wind" attribute as an Wind object

            // TODO : get the "weather" attribute as an array of Weather objects
            Weather[] weathers = objectMapper.convertValue(root.get("weather"), Weather[].class);

            // Don't touch this !
            System.out.printf("City name: %s%n", cityName);
            System.out.printf("City latitude: %s%n", cityLatitude);
            System.out.printf("City longitude: %s%n", cityLongitude);
            System.out.printf("Wind infos: %s%n", wind.toString());
            for (Weather weather : weathers) {
                System.out.printf("Weather infos: %s%n", weather.toString());
            }
        }catch (IOException e) {
                e.printStackTrace();
    }
            /*     Expected result :

                City name: London
                City latitude: 51.51
                City longitude: -0.13
                Wind infos: src.main.Wind{speed=4.1, deg=80.0}
                Weather infos: src.main.Weather{id=300, main='Drizzle', description='light intensity drizzle', icon='09d'}
                Weather infos: src.main.Weather{id=800, main='Clear', description='clear sky', icon='01n'}
            */
    }
}
