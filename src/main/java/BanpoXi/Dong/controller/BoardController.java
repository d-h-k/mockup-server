package BanpoXi.Dong.controller;

import BanpoXi.Dong.domain.Card;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    //
    @GetMapping("/lists")
    @ResponseBody
    public List<Card> allLists() {
        List<Card> Lists = new ArrayList<>();
        Lists.add(new Card(1, "민초아몬드", "마시쪙민초", "TODO"));
        Lists.add(new Card(2, "아반떼와횬대차", "테슬라사라", "TODO"));
        Lists.add(new Card(3, "알래스카무한도전", "무야호할아버지", "PROGRESS"));
        Lists.add(new Card(4, "우리참별나고", "이상한사이야", "PROGRESS"));
        Lists.add(new Card(5, "윈도우 시스템해", "킹버그헌", "DONE"));
        Lists.add(new Card(6, "러닝리엑트공", "알렉스벵크스한빛", "DONE"));
        Lists.add(new Card(7, "윤성우열혈", "윈도우즈시스", "DONE"));
        return Lists;
    }

    @GetMapping("/todo")
    @ResponseBody
    public List<Card> todoLists() {
        List<Card> todoLists = new ArrayList<>();
        todoLists.add(new Card(1, "민초아몬드", "마시쪙민초", "TODO"));
        todoLists.add(new Card(2, "아반떼와횬대차", "테슬라사라", "TODO"));
        return todoLists;
    }

    @GetMapping("/done")
    @ResponseBody
    public List<Card> todoLists() {

    }

    @GetMapping("/todo")
    @ResponseBody
    public List<Card> todoLists() {

    }

    @GetMapping("/todo")
    @ResponseBody
    public List<Card> todoLists() {

    }

}
