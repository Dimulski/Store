package dimulski.areas.games.controllers;

import dimulski.areas.games.models.viewModels.SmallGameViewModel;
import dimulski.areas.games.service.contracts.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GameController {
    
    @Autowired
    private GameService gameService;
    
    @GetMapping("/games")
    public String getGamesPage(Model model) {
        List<SmallGameViewModel> gameViewModels = this.gameService.findAll();
        model.addAttribute("games", gameViewModels);
        
        return "games";
    }
    
    
}
