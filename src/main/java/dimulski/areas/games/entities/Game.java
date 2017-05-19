package dimulski.areas.games.entities;

import dimulski.areas.users.entities.User;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private Double price;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    private String bigPicture;
    
    private String smallPicture;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "games_genres",
    joinColumns = @JoinColumn(name = "game_id"),
    inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;
    
    @ManyToMany(mappedBy = "games", targetEntity = User.class, cascade = CascadeType.DETACH)
    private Set<User> users;

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

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
