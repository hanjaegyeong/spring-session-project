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
        // 세션에서 cartItem 리스트를 가져옵니다.
        List<Item> cartItems = (List<Item>) session.getAttribute("cartItems");

        if (cartItems == null) {
            cartItems = new ArrayList<>(); // 장바구니가 비어있을 경우 빈 리스트를 생성합니다.
        }

        // 모델에 cartItems를 추가하여 뷰로 전달합니다.
        model.addAttribute("cartItems", cartItems);

        return "cart";
    }
}
