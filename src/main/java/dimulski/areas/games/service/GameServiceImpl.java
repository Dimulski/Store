package dimulski.areas.games.service;

import dimulski.areas.games.entities.Game;
import dimulski.areas.games.entities.Genre;
import dimulski.areas.games.models.bindingModels.EditGameBindingModel;
import dimulski.areas.games.models.viewModels.GameDetailsViewModel;
import dimulski.areas.games.models.viewModels.GameItemViewModel;
import dimulski.areas.games.models.viewModels.SmallGameViewModel;
import dimulski.areas.games.repositories.GameRepository;
import dimulski.areas.games.repositories.GenreRepository;
import dimulski.areas.games.service.contracts.GameService;
import dimulski.areas.users.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {
    
    @Autowired
    private GameRepository gameRepository;
    
    @Autowired
    private GenreRepository genreRepository;
    
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

    @Override
    public List<SmallGameViewModel> findAllOfGenre(String genre) {
        List<SmallGameViewModel> gameViewModels = new ArrayList<>();
        List<Game> gamesOfGenre = this.gameRepository.findAllOfGenre(genre);
        for (Game game : gamesOfGenre) {
            SmallGameViewModel gameViewModel = this.modelMapper.map(game, SmallGameViewModel.class);
            gameViewModels.add(gameViewModel);
        }
        
        return gameViewModels;
    }

    @Override
    public List<GameItemViewModel> findAllGameItems() {
        List<GameItemViewModel> gameItemViewModels = new ArrayList<>();
        List<Game> games = this.gameRepository.findAll();
        for (Game game : games) {
            GameItemViewModel gameItemViewModel = this.modelMapper.map(game, GameItemViewModel.class);
            gameItemViewModels.add(gameItemViewModel);
        }
        
        return gameItemViewModels;
    }

    @Override
    public EditGameBindingModel findByIdToEdit(long id) {
        Game game = this.gameRepository.findOne(id);
        EditGameBindingModel editGameBindingModel = this.modelMapper.map(game, EditGameBindingModel.class);
        List<String> genres = new ArrayList<>();
        for (Genre genre : game.getGenres()) {
            genres.add(genre.getName());
        }
        editGameBindingModel.setGenres(genres.toArray(new String[0]));
        
        return editGameBindingModel;
    }

    @Override
    public GameDetailsViewModel findByIdToDetails(long id) {
        Game game = this.gameRepository.findOne(id);
        GameDetailsViewModel gameDetailsViewModel = this.modelMapper.map(game, GameDetailsViewModel.class);
        Set<String> genres = new HashSet<>();
        for (Genre genre : game.getGenres()) {
            genres.add(genre.getName());
        }
        gameDetailsViewModel.setGenres(genres.toArray(new String[0]));
        
        return gameDetailsViewModel;
    }

    @Override
    public void save(EditGameBindingModel editGameBindingModel) {
        Game game = this.modelMapper.map(editGameBindingModel, Game.class);
        Set<Genre> genres = this.genreRepository.findAllByNameIn(editGameBindingModel.getGenres());
        game.setGenres(genres);
        this.gameRepository.save(game);
    }

    @Override
    public void deleteById(long id) {
        Game game = this.gameRepository.findOne(id);
        for (User user : game.getUsers()) {
            user.getGames().remove(game);
        }
        this.gameRepository.delete(id);
    }
}
