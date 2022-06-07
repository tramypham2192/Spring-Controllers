package com.codecademy.plants.controllers;

import com.codecademy.plants.entities.Adventure;
import com.codecademy.plants.repositories.AdventureRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.Optional;


@RestController
@RequestMapping("/traveladventures")
public class TravelAdventuresController {

  private final AdventureRepository adventureRepository;

  public TravelAdventuresController(AdventureRepository adventureRepo) {
    this.adventureRepository = adventureRepo;
  }

  // Add controller methods below:
  @GetMapping()
  public Iterable<Adventure> getAdventure(@RequestBody AdventureRepository adventureRepository){
    return adventureRepository.findAll();
  }
  
  @GetMapping("/bycountry")
  public Iterable<Adventure> getAdventureByCountry(@PathVariable String country){
    return adventureRepository.findByCountry(country);
  }

  @GetMapping("/bystate")
  public Iterable<Adventure> getAdventureByState(@RequestParam String state){
    return adventureRepository.findByState(state);
  }

  @PostMapping()
  public String addAdventure(@RequestBody Adventure adventure){
    adventureRepository.save(adventure);
    return "Added successfully";
  }

  @PutMapping("/{id}")
  public Adventure updateAdventure(@PathVariable int id, @RequestBody Adventure adventure){
    boolean blogCompleted = false;
    Optional<Adventure> adventureOptional = this.adventureRepository.findById(id);
    if (!adventureOptional.isPresent()){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    blogCompleted = true;
    adventure = adventureOptional.get();
    return adventure;
  }

  @DeleteMapping("/{id}")
  public String deleteAdventure(@PathVariable int id){
    Optional<Adventure> adventureOptional = this.adventureRepository.findById(id);
    if (!adventureOptional.isPresent()){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    adventureRepository.delete(adventureOptional.get());
    return "delete adventure successfully";
  }
}
