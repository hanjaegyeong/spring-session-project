package com.example.springsessionproject.web.Item;

import com.example.springsessionproject.domain.Item.Item;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("cart")
public class ItemController {

    @ModelAttribute("cart")
    public Map<String, Integer> getCart() {
        return new HashMap<>();
    }

    @GetMapping("/addToCart")
    public String setItem(@RequestParam List<String> itemName,
                          @RequestParam List<String> price, HttpSession session) {

        List<Item> cartItems;
        cartItems = new ArrayList<>();

        for (int i = 0; i < itemName.size(); i++) {
            Item item = new Item(itemName.get(i), Integer.parseInt(price.get(i)));
            cartItems.add(item);
        }

        session.setAttribute("cartItems", cartItems);

        return "cart";
    }


    @PostMapping("/addToCart")
    public String addToCart(@Valid @ModelAttribute ItemSaveForm form, HttpSession session, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "home";
        }

        List<Item> cartItems = (List<Item>) session.getAttribute("cartItems");

        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        Item item = new Item(form.getItemName(), form.getPrice());
        cartItems.add(item);

        session.setAttribute("cartItems", cartItems);
        
        return "redirect:/home";
    }
}
