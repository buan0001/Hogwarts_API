package kea.exercise.hogwarts_api.repositories;

import kea.exercise.hogwarts_api.models.House;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Integer> {
}
