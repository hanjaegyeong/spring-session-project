package com.example.springsessionproject.web.cart;

import com.example.springsessionproject.domain.Item.Item;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        List<Item> cartItems = (List<Item>) session.getAttribute("cartItems");

        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        model.addAttribute("cartItems", cartItems);

        return "cart";
    }
}
