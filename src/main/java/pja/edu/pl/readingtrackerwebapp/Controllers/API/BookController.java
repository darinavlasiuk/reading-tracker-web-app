package pja.edu.pl.readingtrackerwebapp.Controllers.API;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.github.fge.jsonpatch.JsonPatchException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pja.edu.pl.readingtrackerwebapp.DTOs.BookDTOs.BookDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.BookDTOs.NewBookDTO;
import pja.edu.pl.readingtrackerwebapp.Services.AppService;

import java.net.URI;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Tag(name = "Books")
@RestController
@RequestMapping(path = "api/books",
        produces = {MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE})
public class BookController {
    private final AppService service;
    private final ObjectMapper objectMapper;

    public BookController(AppService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Integer id) {
        return service.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookDTO> saveBook(@Valid @RequestBody NewBookDTO book) {
        BookDTO savedBook = service.saveBook(book);
        if(savedBook==null)
            return ResponseEntity.badRequest().build();

        URI savedBookLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBook.getId())
                .toUri();

        return ResponseEntity.created(savedBookLocation).body(savedBook);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable int id, @Valid @RequestBody JsonMergePatch patch) {
        try {
            NewBookDTO bookDTO = service.getNewBookById(id).orElseThrow();
            NewBookDTO patchedBookDTO = applyPatch(bookDTO, patch);

            service.saveBookById(id, patchedBookDTO);

        }catch (NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.noContent().build();
    }

    private NewBookDTO applyPatch(NewBookDTO bookDTO, JsonMergePatch patch) throws JsonProcessingException, JsonPatchException {
        JsonNode bookNode = objectMapper.valueToTree(bookDTO);
        JsonNode patchNode = patch.apply(bookNode);
        return objectMapper.treeToValue(patchNode, NewBookDTO.class);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteBook(@PathVariable Integer id) {
        if(!service.getBookById(id).isEmpty())
            service.deleteBook(id);

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
