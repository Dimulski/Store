package dimulski.areas.games.controllers;

import dimulski.areas.games.models.bindingModels.EditGameBindingModel;
import dimulski.areas.games.models.viewModels.GameItemViewModel;
import dimulski.areas.games.models.viewModels.GenreNameViewModel;
import dimulski.areas.games.models.viewModels.GenreViewModel;
import dimulski.areas.games.models.viewModels.SmallGameViewModel;
import dimulski.areas.games.service.contracts.GameService;
import dimulski.areas.games.service.contracts.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
public class GameController {
    
    @Autowired
    private GameService gameService;
    
    @Autowired
    private GenreService genreService;

    @ModelAttribute(name = "genres")
    public List<GenreViewModel> getGenreNames() {
        return this.genreService.findAll();
    }
    
    @GetMapping("/games")
    public String getGamesPage(Model model) {
        List<SmallGameViewModel> gameViewModels = this.gameService.findAll();
        model.addAttribute("games", gameViewModels);
        
        return "games";
    }
    
    @GetMapping("/games/genre/{genre}")
    public String getGameOfGenrePage(@PathVariable String genre, Model model) {
        List<SmallGameViewModel> gamesOfGenre = this.gameService.findAllOfGenre(genre);
        model.addAttribute("games", gamesOfGenre);
        
        return "games";
    }
    
    @GetMapping("/games-table")
    public String getGamesTablePage(Model model) {
        List<GameItemViewModel> gameItemViewModels = this.gameService.findAllGameItems();
        model.addAttribute("games", gameItemViewModels);
        
        return "games-table";
    }

    @GetMapping("/games/create")
    public String getCreateGamePage(Model model) {
        EditGameBindingModel editGameBindingModel = new EditGameBindingModel();
        model.addAttribute("editGameBindingModel", editGameBindingModel);

        return "games-create";
    }

    @PostMapping("/games/create")
    public String createGame(@Valid @ModelAttribute EditGameBindingModel editGameBindingModel,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "games-create";
        }

        gameService.save(editGameBindingModel);

        return "redirect:/games-table";
    }
    
    @GetMapping("/games/edit/{gameId}")
    public String getEditGamePage(@PathVariable long gameId, Model model) {
        EditGameBindingModel editGameBindingModel = this.gameService.findByIdToEdit(gameId);
        model.addAttribute("editGameBindingModel", editGameBindingModel);
        
        return "games-edit";
    }
    
    @PostMapping("/games/edit/{gameId}")
    public String editGame(@PathVariable long gameId,
                           @Valid @ModelAttribute EditGameBindingModel editGameBindingModel,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "games-edit";
        }
        
        editGameBindingModel.setId(gameId);
        this.gameService.save(editGameBindingModel);
        
        return "redirect:/games-table";
    }

    @GetMapping("/games/delete/{gameId}")
    public String deleteGame(@PathVariable long gameId) {
        this.gameService.deleteById(gameId);

        return "redirect:/games-table";
    }
}
