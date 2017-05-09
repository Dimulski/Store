package dimulski.areas.games.service.contracts;

import dimulski.areas.games.models.bindingModels.EditGenreBindingModel;
import dimulski.areas.games.models.viewModels.GenreNameViewModel;
import dimulski.areas.games.models.viewModels.GenreViewModel;

import java.util.List;
import java.util.Set;

public interface GenreService {
    
    List<GenreViewModel> findAll();
    
    EditGenreBindingModel findById(long id);
    
    void save(EditGenreBindingModel editGenreBindingModel);
    
    void deleteById(long id);
    
    Set<GenreNameViewModel> getAllGenres();
}
