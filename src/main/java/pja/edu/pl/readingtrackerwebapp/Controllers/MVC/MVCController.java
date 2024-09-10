package pja.edu.pl.readingtrackerwebapp.Controllers.MVC;

import com.sun.source.tree.OpensTree;
import io.swagger.v3.oas.annotations.headers.Header;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import pja.edu.pl.readingtrackerwebapp.DTOs.AuthorDTOs.AuthorDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.BookDTOs.BookDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.UserDTOs.UserDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.User_BookWithoutUserDTO;
import pja.edu.pl.readingtrackerwebapp.Services.AppService;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public String getAccountPage(@RequestParam(required = false) String username, @RequestParam(required = false) String password){
        Optional<UserDTO> userDTO = service.existsUserWithUsernameAndPassword(username, password);
        return userDTO.map(dto -> "redirect:account/"+dto.getId()).orElse("redirect:logIn?denied=true");
    }
    @GetMapping("/account/{id}")
    public String getAccountPageById(@PathVariable Integer id, Model model){
        Optional<UserDTO> userDTO = service.getUserById(id);
        if(userDTO.isEmpty())
            return "redirect:main";

        model.addAttribute("userId", userDTO.get().getId());

        List<BookDTO> wantToReadBooks = userDTO.get().getUserBooks().stream()
                .filter(userBookWithoutUserDTO -> userBookWithoutUserDTO.getIsWantToRead()!=0)
                .map(User_BookWithoutUserDTO::getBook)
                .toList();
        int wantToReadNumber = wantToReadBooks.size();

        model.addAttribute("wantToReadNumber", wantToReadNumber);
        if(!wantToReadBooks.isEmpty())
            model.addAttribute("wantToReadBooks", wantToReadBooks.stream().limit(6).toList());

        List<BookDTO> readBooks = userDTO.get().getUserBooks().stream()
                .filter(userBookWithoutUserDTO -> userBookWithoutUserDTO.getIsRead()!=0)
                .sorted(Comparator.comparing(User_BookWithoutUserDTO::getDateRead))
                .map(User_BookWithoutUserDTO::getBook)
                .toList();

        int readNumber = readBooks.size();

        model.addAttribute("readNumber", readNumber);
        if(!readBooks.isEmpty())
            model.addAttribute("readBooks", readBooks.stream().limit(6).toList());

        if(userDTO.get().getGoal()!=null){
            List<BookDTO> booksReadThisYear = service.getBooksReadThisYearById(id);
            model.addAttribute("booksReadThisYear", booksReadThisYear);

            double daysPerBook = (double) 365 / userDTO.get().getGoal();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy");
            LocalDateTime date1 = LocalDate.now().atStartOfDay();
            LocalDateTime date2 = LocalDate.parse("01 01 "+ Year.now().getValue(), dtf).atStartOfDay();
            long daysPassed = Math.abs(Duration.between(date2, date1).toDays());

            int wasSupposedToRead = (int) (daysPassed / daysPerBook);
            int readThisYear = booksReadThisYear.size();
            double progress = ((double) readThisYear / userDTO.get().getGoal())*100;
            int booksBehindSchedule = Math.max(wasSupposedToRead - readThisYear, 0);

            model.addAttribute("progress", progress);
            if(booksBehindSchedule>0)
                model.addAttribute("booksBehindSchedule", booksBehindSchedule);

            model.addAttribute("read", readThisYear);
            model.addAttribute("goal", userDTO.get().getGoal());
            model.addAttribute("year", Year.now().getValue());
        }

        return "account";
    }

    @GetMapping("/account/{id}/wantToRead")
    public String getWantToReadPageById(@PathVariable Integer id, Model model){
        Optional<UserDTO> userDTO = service.getUserById(id);
        if(userDTO.isEmpty())
            return "redirect:main";

        List<BookDTO> wantToReadBooks = userDTO.get().getUserBooks().stream()
                .filter(userBookWithoutUserDTO -> userBookWithoutUserDTO.getIsWantToRead()!=0)
                .map(User_BookWithoutUserDTO::getBook)
                .toList();

        model.addAttribute("wantToReadBooks", wantToReadBooks);
        model.addAttribute("pageName", "Books you've marked as 'Want to Read'");

        return "books";
    }

    @GetMapping("/account/{id}/read")
    public String getReadPageById(@PathVariable Integer id, Model model){
        Optional<UserDTO> userDTO = service.getUserById(id);
        if(userDTO.isEmpty())
            return "redirect:main";

        List<BookDTO> readBooks = userDTO.get().getUserBooks().stream()
                .filter(userBookWithoutUserDTO -> userBookWithoutUserDTO.getIsRead()!=0)
                .sorted(Comparator.comparing(User_BookWithoutUserDTO::getDateRead))
                .map(User_BookWithoutUserDTO::getBook)
                .toList();

        model.addAttribute("readBooks", readBooks);
        model.addAttribute("pageName", "Books you've marked as 'Read'");

        return "books";
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
