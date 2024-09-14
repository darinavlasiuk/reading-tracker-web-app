package pja.edu.pl.readingtrackerwebapp.Controllers.MVC;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pja.edu.pl.readingtrackerwebapp.DTOs.AuthorDTOs.AuthorDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.BookDTOs.BookDTO;
import pja.edu.pl.readingtrackerwebapp.DTOs.UserDTOs.UserDTO;
import pja.edu.pl.readingtrackerwebapp.Services.AppService;
import java.time.Year;
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
    public String logIn(@RequestParam(required = false) boolean denied,
                        @RequestParam(required = false) Integer bookId,
                        @RequestParam(required = false) Integer markAs, Model model){
        if(denied)
            model.addAttribute("access", false);
        if(bookId != null){
            model.addAttribute("bookId", bookId);
            model.addAttribute("markAs", markAs);
        }

        return "logIn";
    }

    @GetMapping("/account")
    public String getAccountPage(@RequestParam(required = false) String username,
                                 @RequestParam(required = false) String password,
                                 @RequestParam(required = false) Integer bookId,
                                 @RequestParam(required = false) Integer markAs){
        Optional<UserDTO> userDTO = service.existsUserWithUsernameAndPassword(username, password);

        if(userDTO.isPresent()){
            if(bookId != null){
                service.markBookAs(userDTO.get().getId(), bookId, markAs);
            }
            return "redirect:account/"+userDTO.get().getId();
        }

        return "redirect:logIn?denied=true";
    }
    @GetMapping("/account/{id}")
    public String getAccountPageById(@PathVariable Integer id, Model model){
        Optional<UserDTO> userDTO = service.getUserById(id);
        if(userDTO.isEmpty())
            return "redirect:main";

        model.addAttribute("userId", userDTO.get().getId());

        List<BookDTO> wantToReadBooks = service.getWantToReadBooks(userDTO.get());
        if(!wantToReadBooks.isEmpty())
            model.addAttribute("wantToReadBooks", wantToReadBooks.stream().limit(6).toList());
        model.addAttribute("wantToReadNumber", wantToReadBooks.size());

        List<BookDTO> readBooks = service.getReadBooks(userDTO.get());
        if(!readBooks.isEmpty())
            model.addAttribute("readBooks", readBooks.stream().limit(6).toList());
        model.addAttribute("readNumber", readBooks.size());

        if(userDTO.get().getGoal()!=null){
            List<BookDTO> booksReadThisYear = service.getBooksReadThisYearById(id);
            model.addAttribute("booksReadThisYear", booksReadThisYear);

            double daysPerBook = (double) 365 / userDTO.get().getGoal();
            long daysPassed = service.calculateDaysPassed();
            int wasSupposedToRead = (int) (daysPassed / daysPerBook);
            int readThisYear = booksReadThisYear.size();
            model.addAttribute("read", readThisYear);

            double progress = Math.min(((double) readThisYear / userDTO.get().getGoal())*100, 100);
            model.addAttribute("progress", progress);

            int booksBehindSchedule = Math.max(wasSupposedToRead - readThisYear, 0);
            if(booksBehindSchedule>0)
                model.addAttribute("booksBehindSchedule", booksBehindSchedule);

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

        List<BookDTO> wantToReadBooks = service.getWantToReadBooks(userDTO.get());

        model.addAttribute("books", wantToReadBooks);
        model.addAttribute("pageName", "Books you've marked as 'Want to Read'");

        return "books";
    }

    @GetMapping("/account/{id}/read")
    public String getReadPageById(@PathVariable Integer id, Model model){
        Optional<UserDTO> userDTO = service.getUserById(id);
        if(userDTO.isEmpty())
            return "redirect:main";

        List<BookDTO> readBooks = service.getReadBooks(userDTO.get());

        model.addAttribute("books", readBooks);
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
