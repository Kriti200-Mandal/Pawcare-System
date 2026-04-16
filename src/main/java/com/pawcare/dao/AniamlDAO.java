package com.pawcare.dao;

import java.sql.*;
import java.util.*;
import com.pawcare.model.Animal;
import com.pawcare.config.DbConfig;

public class AnimalDAO {

    public List<Animal> getAllAnimals() {
        List<Animal> animals = new ArrayList<>();

        try (Connection con = DBConfig.getConnection()) {
            String sql = "SELECT * FROM animals";
            PreparedStatement ps = con.prepareStatement(sql);

ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Animal a = new Animal();
                a.setAnimalId(rs.getInt("animal_id"));
                a.setName(rs.getString("name"));
                a.setSpecies(rs.getString("species"));
                a.setBreed(rs.getString("breed"));
                a.setAge(rs.getInt("age"));
                a.setAdoptionStatus(rs.getString("adoption_status"));

                animals.add(a);
                      }
                  } catch (Exception e) {
                      e.printStackTrace();
                  }
                  return animals;
              }





}
