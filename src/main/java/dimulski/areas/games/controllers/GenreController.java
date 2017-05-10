package dimulski.areas.games.controllers;

import dimulski.areas.games.models.bindingModels.EditGenreBindingModel;
import dimulski.areas.games.models.viewModels.GenreViewModel;
import dimulski.areas.games.service.contracts.GenreService;
import dimulski.areas.users.models.bindingModels.EditUserBindingModel;
import dimulski.areas.users.service.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class GenreController {
    
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
    
    @GetMapping("/genres")
    public String getGenresPage(Model model) {
        List<GenreViewModel> genreViewModels = this.genreService.findAll();
        model.addAttribute("genres", genreViewModels);
        
        return "genres";
    }

    @GetMapping("/genres/create")
    public String getCreateGenrePage(Model model) {
        EditGenreBindingModel editGenreBindingModel = new EditGenreBindingModel();
        model.addAttribute("editGenreBindingModel", editGenreBindingModel);

        return "genres-create";
    }

    @PostMapping("/genres/create")
    public String createGenre(@Valid @ModelAttribute EditGenreBindingModel editGenreBindingModel,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "genres-create";
        }

        genreService.save(editGenreBindingModel);

        return "redirect:/genres";
    }

    @GetMapping("/genres/edit/{genreId}")
    public String getEditGenrePage(@PathVariable long genreId, Model model) {
        EditGenreBindingModel editGenreBindingModel = this.genreService.findById(genreId);
        model.addAttribute("editGenreBindingModel", editGenreBindingModel);

        return "genres-edit";
    }

    @PostMapping("/genres/edit/{genreId}")
    public String editGenre(@PathVariable long genreId,
                                   @Valid @ModelAttribute EditGenreBindingModel editGenreBindingModel,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "genres-edit";
        }

        editGenreBindingModel.setId(genreId);
        this.genreService.save(editGenreBindingModel);

        return "redirect:/genres";
    }

    @GetMapping("/genres/delete/{genreId}")
    public String deleteGenre(@PathVariable long genreId) {
        this.genreService.deleteById(genreId);

        return "redirect:/genres";
    }
}
