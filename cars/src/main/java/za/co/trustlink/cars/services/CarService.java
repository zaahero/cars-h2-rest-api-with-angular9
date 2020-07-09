package za.co.trustlink.cars.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import za.co.trustlink.cars.models.Car;
import za.co.trustlink.cars.repositories.CarRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public void loadCarsFromXMLFile() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:static/cars.xml");
        String dataFromCars = getDataFromCars(file);
//        System.out.println(dataFromCars);

        try {
            JSONObject xmlJSONObj = XML.toJSONObject(dataFromCars);
            String jsonPrettyPrintString = xmlJSONObj.toString(4);
            System.out.println(jsonPrettyPrintString);

            JSONArray jsonArray = xmlJSONObj.getJSONObject("cars").getJSONArray("car");

            List<Car> carsList = new ArrayList<>();

            IntStream.range(0, jsonArray.length()).forEach(e -> {
                System.out.println(e);
                try {
                    carsList.add(new ObjectMapper().readValue(jsonArray.get(e).toString(), Car.class));
                } catch (JsonProcessingException ex) {
                    ex.printStackTrace();
                    carsList.add(new Car());
                }
            });


            carsList.stream()
                    .forEach( car -> {
                        carRepository.saveAndFlush(car);
                    });
        } catch (JSONException je) {
            throw new RuntimeException("Cars loading failed!");
        }
    }

    private static String getDataFromCars(File file) {
        String content = "";

        try {
            content = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    public Object getAllCars() {
        return carRepository.findAll();
    }

    public Object getCarById(Long id) {
        return carRepository.existsById(id) ? carRepository.findById(id) : null;
    }

    public Object saveCar(Car car) {
        return carRepository.save(car);
    }

    public Object updateCar(Car car) {
        HashMap response = new HashMap();
        if (carRepository.existsById(car.getId())) {
            carRepository.saveAndFlush(car);
            response.putIfAbsent("status", true);
            response.putIfAbsent("car", carRepository.getOne(car.getId()));
            return response;
        } else {
            response.putIfAbsent("status", false);
            return response;
        }
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
