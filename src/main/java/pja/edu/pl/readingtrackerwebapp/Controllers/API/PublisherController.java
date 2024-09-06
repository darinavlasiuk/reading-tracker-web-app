package pja.edu.pl.readingtrackerwebapp.Controllers.API;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pja.edu.pl.readingtrackerwebapp.DTOs.PublisherDTOs.PublisherDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.PublisherDTOs.NewPublisherDTO;
import pja.edu.pl.readingtrackerwebapp.Services.AppService;

import java.net.URI;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Tag(name = "Publishers")
@RestController
@RequestMapping(path = "api/publishers",
        produces = {MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE})
public class PublisherController {
    private final AppService service;
    private final ObjectMapper objectMapper;

    public PublisherController(AppService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }
    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getPublisher(@PathVariable Integer id) {
        return service.getPublisherById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PublisherDTO> savePublisher(@Valid @RequestBody NewPublisherDTO publisher) {
        PublisherDTO savedPublisher = service.savePublisher(publisher);
        if(savedPublisher==null)
            return ResponseEntity.badRequest().build();

        URI savedPublisherLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPublisher.getId())
                .toUri();

        return ResponseEntity.created(savedPublisherLocation).body(savedPublisher);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePublisher(@PathVariable int id, @Valid @RequestBody JsonMergePatch patch) {
        try {
            NewPublisherDTO publisherDTO = service.getNewPublisherById(id).orElseThrow();
            NewPublisherDTO patchedPublisherDTO = applyPatch(publisherDTO, patch);

            service.savePublisherById(id, patchedPublisherDTO);

        }catch (NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.noContent().build();
    }

    private NewPublisherDTO applyPatch(NewPublisherDTO publisherDTO, JsonMergePatch patch) throws JsonProcessingException, JsonPatchException {
        JsonNode publisherNode = objectMapper.valueToTree(publisherDTO);
        JsonNode patchNode = patch.apply(publisherNode);
        return objectMapper.treeToValue(patchNode, NewPublisherDTO.class);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deletePublisher(@PathVariable Integer id) {
        if(!service.getPublisherById(id).isEmpty())
            service.deletePublisher(id);

        return ResponseEntity.noContent().build();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Map<String, String> handle(MethodArgumentNotValidException ex){
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }
}
