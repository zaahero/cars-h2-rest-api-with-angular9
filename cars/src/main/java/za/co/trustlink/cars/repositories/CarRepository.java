package za.co.trustlink.cars.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.trustlink.cars.models.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}

