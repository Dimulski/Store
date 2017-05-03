package dimulski.areas.games.service;

import dimulski.areas.games.entities.Game;
import dimulski.areas.games.models.viewModels.SmallGameViewModel;
import dimulski.areas.games.repositories.GameRepository;
import dimulski.areas.games.service.contracts.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    
    @Autowired
    private GameRepository gameRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public List<SmallGameViewModel> findAll() {
        List<SmallGameViewModel> gameViewModels = new ArrayList<>();
        List<Game> games = this.gameRepository.findAll();
        for (Game game : games) {
            SmallGameViewModel gameViewModel = this.modelMapper.map(game, SmallGameViewModel.class);
            gameViewModels.add(gameViewModel);
        }
        
        return gameViewModels;
    }
}
