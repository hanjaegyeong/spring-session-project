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
    public String addToCart(@RequestParam String itemName, @RequestParam int price,
                            @ModelAttribute("cart") Map<String, Integer> cart, Model model) {
        // 카트에 상품 추가 또는 수량 증가
        if (cart.containsKey(itemName)) {
            cart.put(itemName, cart.get(itemName) + 1);
        } else {
            cart.put(itemName, 1);
        }

        model.addAttribute("message", itemName + "이(가) 카트에 추가되었습니다.");
        return "home";
    }

    @PostMapping("/addToCart")
    public String addToCart(@Valid @ModelAttribute ItemSaveForm form, HttpSession session, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "home";
        }

        // 리스트 컬렉션 이용해 장바구니 목록 구성
        List<Item> cartItems = (List<Item>) session.getAttribute("cartItems");

        // 만약 세션에 장바구니 목록이 없으면 새로운 목록을 생성
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }

        // 새로운 상품을 장바구니 목록에 추가
        Item item = new Item(form.getItemName(), form.getPrice());
        cartItems.add(item);

        // 장바구니 목록을 세션에 저장
        session.setAttribute("cartItems", cartItems);
        
        return "redirect:/home";
    }
}
