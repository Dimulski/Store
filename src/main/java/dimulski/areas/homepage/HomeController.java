package dimulski.areas.homepage;

import dimulski.areas.games.models.viewModels.GenreViewModel;
import dimulski.areas.games.models.viewModels.SmallGameViewModel;
import dimulski.areas.games.repositories.GenreRepository;
import dimulski.areas.games.service.contracts.GameService;
import dimulski.areas.games.service.contracts.GenreService;
import dimulski.areas.users.service.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private GameService gameService;
    
    @Autowired
    private GenreService genreService;
    
    @Autowired
    private UserService userService;

    @ModelAttribute(name = "productCount")
    public int getUserProductCount(Principal principal) {
        if (principal == null) {
            return 0;
        }
        return this.userService.getProductCount(principal.getName());
    }
    
    @GetMapping
    public String getHomePage(Model model) {
        List<GenreViewModel> genreViewModels = this.genreService.findAll();
        List<SmallGameViewModel> gameViewModels = this.gameService.findAll();
        model.addAttribute("genres", genreViewModels);
        model.addAttribute("games", gameViewModels);
        
        return "home";
    }

    @GetMapping("/error")
    public String getErrorPage() {
        return "error";
    }
}
