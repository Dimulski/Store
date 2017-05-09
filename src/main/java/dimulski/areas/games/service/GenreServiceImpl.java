package dimulski.areas.games.service;

import dimulski.areas.games.entities.Genre;
import dimulski.areas.games.models.bindingModels.EditGenreBindingModel;
import dimulski.areas.games.models.viewModels.GenreNameViewModel;
import dimulski.areas.games.models.viewModels.GenreViewModel;
import dimulski.areas.games.repositories.GenreRepository;
import dimulski.areas.games.service.contracts.GenreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GenreServiceImpl implements GenreService {
    
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public List<GenreViewModel> findAll() {
        List<GenreViewModel> genreViewModels = new ArrayList<>();
        List<Genre> genres = this.genreRepository.findAll();
        for (Genre genre : genres) {
            GenreViewModel genreViewModel = this.modelMapper.map(genre, GenreViewModel.class);
            genreViewModels.add(genreViewModel);
        }
        
        return genreViewModels;
    }

    @Override
    public EditGenreBindingModel findById(long id) {
        Genre genre = this.genreRepository.findOne(id);
        EditGenreBindingModel editGenreBindingModel = this.modelMapper.map(genre, EditGenreBindingModel.class);
        
        return editGenreBindingModel;
    }

    @Override
    public void save(EditGenreBindingModel editGenreBindingModel) {
        Genre genre = this.modelMapper.map(editGenreBindingModel, Genre.class);
        this.genreRepository.save(genre);
    }

    @Override
    public void deleteById(long id) {
        this.genreRepository.delete(id);
    }

    @Override
    public Set<GenreNameViewModel> getAllGenres() {
        Set<GenreNameViewModel> genreModels = new HashSet<>();
        Set<Genre> genres = this.genreRepository.findAllAsSet();
        for (Genre genre : genres) {
            GenreNameViewModel genreNameViewModel = this.modelMapper.map(genre, GenreNameViewModel.class);
            genreModels.add(genreNameViewModel);
        }

        return genreModels;
    }
}
