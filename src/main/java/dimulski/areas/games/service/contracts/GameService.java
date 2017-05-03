package dimulski.areas.games.service.contracts;

import dimulski.areas.games.models.viewModels.SmallGameViewModel;

import java.util.List;

public interface GameService {
    
    List<SmallGameViewModel> findAll();
}
