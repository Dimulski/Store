package dimulski.areas.games.controllers;

import dimulski.areas.games.models.viewModels.GameDetailsViewModel;
import dimulski.areas.games.models.viewModels.GenreNameViewModel;
import dimulski.areas.games.models.viewModels.GenreViewModel;
import dimulski.areas.games.service.contracts.GameService;
import dimulski.areas.games.service.contracts.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

@Controller
public class GameDetailsController {
    
    @Autowired
    private GameService gameService;
    
    @Autowired
    private GenreService genreService;

    @ModelAttribute(name = "genres")
    public List<GenreViewModel> getGenreNames() {
        return this.genreService.findAll();
    }
    
    @GetMapping("/games/{gameId}")
    public String getGameDetailsPage(@PathVariable long gameId, Model model) {
        GameDetailsViewModel gameDetailsViewModel = this.gameService.findByIdToDetails(gameId);
        model.addAttribute("gameDetailsViewModel", gameDetailsViewModel);
        
        return "game";
    }
}
