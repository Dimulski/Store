package dimulski.areas.games.models.viewModels;

public class GameDetailsViewModel {
    
    private Long id;
    
    private String name;
    
    private Double price;
    
    private String description;
    
    private String BigPicture;
    
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
        return BigPicture;
    }

    public void setBigPicture(String bigPicture) {
        BigPicture = bigPicture;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }
}
