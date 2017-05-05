package dimulski.areas.games.service.contracts;

import dimulski.areas.games.models.bindingModels.EditGenreBindingModel;
import dimulski.areas.games.models.viewModels.GenreViewModel;

import java.util.List;

public interface GenreService {
    
    List<GenreViewModel> findAll();
    
    EditGenreBindingModel findById(Long id);
}
