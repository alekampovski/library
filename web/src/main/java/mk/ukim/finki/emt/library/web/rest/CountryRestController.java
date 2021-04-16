package mk.ukim.finki.emt.library.web.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.library.model.Country;
import mk.ukim.finki.emt.library.model.dto.CountryDto;
import mk.ukim.finki.emt.library.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/countries")
@AllArgsConstructor
public class CountryRestController {

    private final CountryService countryService;

    @GetMapping
    public List<Country> findAll() {
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id) {
        return countryService.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Country> save(@RequestBody CountryDto countryDto) {
        return countryService.save(countryDto).map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        countryService.deleteById(id);
        if(countryService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }




}
