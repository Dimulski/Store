package dimulski.areas.games.controllers;

import dimulski.areas.games.models.bindingModels.EditGenreBindingModel;
import dimulski.areas.games.models.viewModels.GenreViewModel;
import dimulski.areas.games.service.contracts.GenreService;
import dimulski.areas.users.models.bindingModels.EditUserBindingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class GenreController {
    
    @Autowired
    private GenreService genreService;
    
    @GetMapping("/genres")
    public String getGenresPage(Model model) {
        List<GenreViewModel> genreViewModels = this.genreService.findAll();
        model.addAttribute("genres", genreViewModels);
        
        return "genres";
    }

    @GetMapping("/genres/edit/{genreId}")
    public String getEditGenrePage(@PathVariable long genreId, Model model) {
        EditGenreBindingModel editGenreBindingModel = this.genreService.findById(genreId);
        model.addAttribute("editGenreBindingModel", editGenreBindingModel);

        return "genres-edit";
    }
}
