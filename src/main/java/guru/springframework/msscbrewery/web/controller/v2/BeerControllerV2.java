package guru.springframework.msscbrewery.web.controller.v2;

import guru.springframework.msscbrewery.services.v2.BeerServiceV2;
import guru.springframework.msscbrewery.web.model.v2.BeerDTOV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {
    private final BeerServiceV2 beerServiceV2;

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDTOV2> getBeer(@PathVariable("beerId") UUID beerId){
        return new ResponseEntity<>(beerServiceV2.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping // Create new Beer
    public ResponseEntity handlePost(@Valid @RequestBody BeerDTOV2 beerDTO){
        log.debug("In Handle Post...");
        val savedDTO = beerServiceV2.saveNewBeer(beerDTO);

        val headers = new HttpHeaders();
        // todo add hostname to url
        headers.add("Location","/api/v2/beer/" + savedDTO.getId().toString());
        return new ResponseEntity(headers,HttpStatus.CREATED);
    }

    @PutMapping({"/{beerID}"})
    public ResponseEntity handleUpdate(@PathVariable("beerID") UUID beerID, @Valid @RequestBody BeerDTOV2 beerDTO){
        beerServiceV2.updateBeer(beerID,beerDTO);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping({"/{beerID}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerID") UUID beerID){
        beerServiceV2.deleteBeerById(beerID);
    }



}
