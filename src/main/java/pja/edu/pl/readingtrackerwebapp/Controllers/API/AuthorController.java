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
import pja.edu.pl.readingtrackerwebapp.DTOs.AuthorDTOs.AuthorDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.AuthorDTOs.NewAuthorDTO;
import pja.edu.pl.readingtrackerwebapp.Services.AppService;

import java.net.URI;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Tag(name = "Authors")
@RestController
@RequestMapping(path = "api/authors",
        produces = {MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE})
public class AuthorController {
    private final AppService service;
    private final ObjectMapper objectMapper;

    public AuthorController(AppService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable Integer id) {
        return service.getAuthorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> saveAuthor(@Valid @RequestBody NewAuthorDTO author) {
        AuthorDTO savedAuthor = service.saveAuthor(author);
        if(savedAuthor==null)
            return ResponseEntity.badRequest().build();

        URI savedAuthorLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAuthor.getId())
                .toUri();

        return ResponseEntity.created(savedAuthorLocation).body(savedAuthor);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable int id, @Valid @RequestBody JsonMergePatch patch) {
        try {
            NewAuthorDTO authorDTO = service.getNewAuthorById(id).orElseThrow();
            NewAuthorDTO patchedAuthorDTO = applyPatch(authorDTO, patch);

            service.saveAuthorById(id, patchedAuthorDTO);

        }catch (NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.noContent().build();
    }

    private NewAuthorDTO applyPatch(NewAuthorDTO authorDTO, JsonMergePatch patch) throws JsonProcessingException, JsonPatchException {
        JsonNode authorNode = objectMapper.valueToTree(authorDTO);
        JsonNode patchNode = patch.apply(authorNode);
        return objectMapper.treeToValue(patchNode, NewAuthorDTO.class);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteAuthor(@PathVariable Integer id) {
        if(!service.getAuthorById(id).isEmpty())
            service.deleteAuthor(id);

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
