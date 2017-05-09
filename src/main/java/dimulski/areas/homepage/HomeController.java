package dimulski.areas.homepage;

import dimulski.areas.games.models.viewModels.GenreViewModel;
import dimulski.areas.games.repositories.GenreRepository;
import dimulski.areas.games.service.contracts.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private GenreService genreService;
    
    @GetMapping
    public String getHomePage(Model model) {
        List<GenreViewModel> genreViewModels = this.genreService.findAll();
        model.addAttribute("genres", genreViewModels);
        
        return "item";
    }

    @GetMapping("/error")
    public String getErrorPage() {
        return "error";
    }
}
