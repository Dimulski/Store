package dimulski.areas.games.service;

import dimulski.areas.games.entities.Genre;
import dimulski.areas.games.models.bindingModels.EditGenreBindingModel;
import dimulski.areas.games.models.viewModels.GenreViewModel;
import dimulski.areas.games.repositories.GenreRepository;
import dimulski.areas.games.service.contracts.GenreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public EditGenreBindingModel findById(Long id) {
        Genre genre = this.genreRepository.findOne(id);
        EditGenreBindingModel editGenreBindingModel = this.modelMapper.map(genre, EditGenreBindingModel.class);
        
        return editGenreBindingModel;
    }
}
