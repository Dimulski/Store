package dimulski.areas.games.models.viewModels;

public class SmallGameViewModel {
    
    private Long id;
    
    private String name;

    private Double price;
    
    private String description;
    
    private byte[] smallPicture;

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

    public byte[] getSmallPicture() {
        return smallPicture;
    }

    public void setSmallPicture(byte[] smallPicture) {
        this.smallPicture = smallPicture;
    }
}
