package dimulski.areas.games.service.contracts;

import dimulski.areas.games.models.bindingModels.EditGameBindingModel;
import dimulski.areas.games.models.viewModels.GameDetailsViewModel;
import dimulski.areas.games.models.viewModels.GameItemViewModel;
import dimulski.areas.games.models.viewModels.SmallGameViewModel;

import java.util.List;

public interface GameService {
    
    List<SmallGameViewModel> findAll();
    
    List<GameItemViewModel> findAllGameItems();
    
    EditGameBindingModel findByIdToEdit(long id);
    
    GameDetailsViewModel findByIdToDetails(long id);
    
    void save(EditGameBindingModel editGameBindingModel);
    
    void deleteById(long id);
    
    List<SmallGameViewModel> findAllOfGenre(String genre);
}
