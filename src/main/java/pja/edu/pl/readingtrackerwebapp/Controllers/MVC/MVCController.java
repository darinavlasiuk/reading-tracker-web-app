package pja.edu.pl.readingtrackerwebapp.Controllers.MVC;

import com.sun.source.tree.OpensTree;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pja.edu.pl.readingtrackerwebapp.DTOs.AuthorDTOs.AuthorDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.BookDTOs.BookDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.UserDTOs.UserDTO;
import pja.edu.pl.readingtrackerwebapp.Services.AppService;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class MVCController {
    private final AppService service;

    public MVCController(AppService service) {
        this.service = service;
    }
    @GetMapping("/main")
    public String getMainPage(@RequestParam(required = false) boolean error, Model model){
        Map<String, List<BookDTO>> topBooks = service.getTopBooksInEachGenre();
        model.addAttribute("genres", topBooks);
        if(error)
            model.addAttribute("error", "Nothing was found for your request!");

        return "main";
    }
    @GetMapping("/logIn")
    public String logIn(@RequestParam(required = false) boolean denied, Model model){
        if(denied)
            model.addAttribute("access", false);

        return "logIn";
    }

    @GetMapping("/account")
    public String getAccountPage(@RequestParam(required = false) String username, @RequestParam(required = false) String password, Model model){
        Optional<UserDTO> userDTO = service.existsUserWithUsernameAndPassword(username, password);
        if(userDTO.isEmpty())
            return "redirect:logIn?denied=true";

        model.addAttribute("access", "true");

        if(userDTO.get().getGoal()!=null){
            double daysPerBook = (double) 365 / userDTO.get().getGoal();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy");
            LocalDateTime date1 = LocalDate.now().atStartOfDay();
            LocalDateTime date2 = LocalDate.parse("01 01 "+ Year.now().getValue(), dtf).atStartOfDay();
            long daysPassed = Math.abs(Duration.between(date2, date1).toDays());

            int wasSupposedToRead = (int) (daysPassed / daysPerBook);
            int booksReadThisYear = service.howManyBooksReadThisYear(userDTO.get().getId());
            double progress = ((double) booksReadThisYear / userDTO.get().getGoal())*100;
            int booksBehindSchedule = Math.max(wasSupposedToRead - booksReadThisYear, 0);

            model.addAttribute("progress", progress);
            if(booksBehindSchedule>0)
                model.addAttribute("booksBehindSchedule", booksBehindSchedule);

            model.addAttribute("goal", userDTO.get().getGoal());
            model.addAttribute("year", Year.now().getValue());

            List<BookDTO> readBooks = service.getReadBooksById(userDTO.get().getId());
            model.addAttribute("books", readBooks);
        }

        return "account";
    }
    @GetMapping("/books/{id}")
    public String getAccountPage(@PathVariable Integer id, Model model){
        service.getBookById(id).ifPresent(bookDTO -> model.addAttribute("book", bookDTO));

        return "book";
    }
    @GetMapping("/authors/{id}")
    public String getAuthorPage(@PathVariable Integer id, Model model){
        service.getAuthorById(id).ifPresent(authorDTO -> model.addAttribute("author", authorDTO));

        return "author";
    }
    @GetMapping("/search")
    public String searchBoorOrAuthor(@RequestParam String search, Model model){
        String[] searchSplit = search.split("\s");
        if(searchSplit.length==2){
            Optional<AuthorDTO> authorDTO = service.getAuthorByName(searchSplit[0], searchSplit[1]);
            if(authorDTO.isPresent()){
                model.addAttribute("author", authorDTO);
                return "redirect:authors/"+authorDTO.get().getId();
            }
        }

        Optional<BookDTO> bookDTO = service.getBookByTitle(search);
        if(bookDTO.isPresent()){
            model.addAttribute("book", bookDTO);
            return "redirect:books/"+bookDTO.get().getId();
        }


        return "redirect:/main?error=true";
    }

}
