package dimulski.areas.games.controllers;

import dimulski.areas.games.models.viewModels.SmallGameViewModel;
import dimulski.areas.users.entities.User;
import dimulski.areas.users.service.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
public class CartController {
    
    @Autowired
    private UserService userService;

    @ModelAttribute(name = "productCount")
    public int getUserProductCount(Principal principal) {
        if (principal == null) {
            return 0;
        }
        return this.userService.getProductCount(principal.getName());
    }
    
    @GetMapping("/cart")
    public String getCartPage(Principal principal, Model model) {
        List<SmallGameViewModel> usersGames = this.userService.getProducts(principal.getName());
        Double total = usersGames.stream().mapToDouble(g -> g.getPrice()).sum();
        String totalFormatted = String.format("$%.2f", total);
        model.addAttribute("games", usersGames);
        model.addAttribute("total", totalFormatted);
        
        return "cart";
    }
    
    @GetMapping("/cart/add/{gameId}")
    public String addGameToCart(@PathVariable long gameId, Principal principal) {
        this.userService.addGameToUser(gameId, principal.getName());
        
        return "redirect:/games";
    }

    @GetMapping("/cart/remove/{gameId}")
    public String removeGameFromCart(@PathVariable long gameId, Principal principal) {
        this.userService.removeGame(gameId, principal.getName());

        return "redirect:/cart";
    }
}
