package dimulski.areas.games.models.bindingModels;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EditGameBindingModel {
    
    private Long id;

    @NotNull
    @Size(min = 3, max = 50, message = "Invalid name")
    private String name;
    
    private Double price;
    
    private String description;
    
    private String bigPicture;
    
    private String smallPicture;
    
    @Size(min = 1, message = "You must at least one genre")
    private String[] genres;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBigPicture() {
        return bigPicture;
    }

    public void setBigPicture(String bigPicture) {
        this.bigPicture = bigPicture;
    }

    public String getSmallPicture() {
        return smallPicture;
    }

    public void setSmallPicture(String smallPicture) {
        this.smallPicture = smallPicture;
    }
    
    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }
}
