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
import pja.edu.pl.readingtrackerwebapp.DTOs.UserDTOs.UserDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.UserDTOs.NewUserDTO;
import pja.edu.pl.readingtrackerwebapp.Services.AppService;

import java.net.URI;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
@Tag(name = "Users")
@RestController
@RequestMapping(path = "api/users",
        produces = {MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE})
public class UserController {
    private final AppService service;
    private final ObjectMapper objectMapper;

    public UserController(AppService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {
        return service.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody NewUserDTO user) {
        UserDTO savedUser = service.saveUser(user);
        if(savedUser==null)
            return ResponseEntity.badRequest().build();

        URI savedUserLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(savedUserLocation).body(savedUser);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @Valid @RequestBody JsonMergePatch patch) {
        try {
            NewUserDTO userDTO = service.getNewUserById(id).orElseThrow();
            NewUserDTO patchedUserDTO = applyPatch(userDTO, patch);

            service.saveUserById(id, patchedUserDTO);

        }catch (NoSuchElementException ex){
            return ResponseEntity.notFound().build();
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.noContent().build();
    }

    private NewUserDTO applyPatch(NewUserDTO userDTO, JsonMergePatch patch) throws JsonProcessingException, JsonPatchException {
        JsonNode userNode = objectMapper.valueToTree(userDTO);
        JsonNode patchNode = patch.apply(userNode);
        return objectMapper.treeToValue(patchNode, NewUserDTO.class);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        if(!service.getUserById(id).isEmpty())
            service.deleteUser(id);

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


