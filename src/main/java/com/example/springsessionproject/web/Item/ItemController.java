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

        List<Item> cartItems = (List<Item>) session.getAttribute("cartItems");


        cartItems = new ArrayList<>(); // 매번 새로운 장바구니로 세팅(데이터 축적 방지)


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
