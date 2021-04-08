package BanpoXi.Dong.controller;

import BanpoXi.Dong.domain.Card;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/lists")
    public List<Card> allLists() {
        List<Card> Lists = new ArrayList<>();
        Lists.add(new Card(1, "민초아몬드", "마시쪙민초", "TODO"));
        Lists.add(new Card(2, "아반떼와횬대차", "테슬라사라", "TODO"));
        Lists.add(new Card(3, "알래스카무한도전", "무야호할아버지", "PROGRESS"));
        Lists.add(new Card(4, "우리참별나고", "이상한사이야", "PROGRESS"));
        Lists.add(new Card(5, "윈도우 시스템해", "킹버그헌", "DONE"));
        Lists.add(new Card(6, "러닝리엑트공", "알렉스벵크스한빛", "DONE"));
        Lists.add(new Card(7, "윤성우열혈", "윈도우즈시스", "DONE"));
        Lists.add(new Card(8, "해리포터", "조앤k롤링", "TODO"));
        Lists.add(new Card(9, "나는 애플로출근한다", "그래", "PROGRESS"));
        return Lists;
    }

    @GetMapping("/todo")
    public List<Card> todoLists() {
        List<Card> todoLists = new ArrayList<>();
        todoLists.add(new Card(1, "민초아몬드", "마시쪙민초", "TODO"));
        todoLists.add(new Card(2, "아반떼와횬대차", "테슬라사라", "TODO"));
        todoLists.add(new Card(8, "해리포터", "조앤k롤링", "TODO"));
        return todoLists;
    }

    @GetMapping("/progress")
    public List<Card> progressLists() {
        List<Card> progressLists = new ArrayList<>();
        progressLists.add(new Card(3, "알래스카무한도전", "무야호할아버지", "PROGRESS"));
        progressLists.add(new Card(4, "우리참별나고", "이상한사이야", "PROGRESS"));
        progressLists.add(new Card(9, "나는 애플로출근한다", "그래", "PROGRESS"));
        return progressLists;
    }

    @GetMapping("/done")
    public List<Card> doneLists() {
        List<Card> doneLists = new ArrayList<>();
        doneLists.add(new Card(5, "윈도우 시스템해", "킹버그헌", "DONE"));
        doneLists.add(new Card(6, "러닝리엑트공", "알렉스벵크스한빛", "DONE"));
        doneLists.add(new Card(7, "윤성우열혈", "윈도우즈시스", "DONE"));
        return doneLists;
    }

    @GetMapping("/{id}")
    public Card showPost(@PathVariable("id") int id) {
        List<Card> lists = new ArrayList<>();
        lists.add(new Card(id, "더미데이터미네이터", "마시쪙민초", "TODO"));
        return lists.get(0);
    }

    //포스트 내용 수정	POST	/board/lists/{id}
    @PostMapping("/lists/{id}")
    public Card update(@PathVariable("id") int id) {
        Card card = new Card(id, "민초아몬드", "마시쪙민초", "TODO");
        card.setTitle("변경민초아몬드");
        card.setContents("마시쪙민초");
        return card;
    }


    //포스트 삭제	DELETE	/board/lists/{id}
    @DeleteMapping("/lists/{id}")
    public Card delete(@PathVariable("id") int id) {
        List<Card> doneLists = new ArrayList<>();
        doneLists.add(new Card(1, "윈도우 시스템해", "킹버그헌", "DONE"));
        doneLists.add(new Card(6, "러닝리엑트공", "알렉스벵크스한빛", "DONE"));
        doneLists.add(new Card(7, "윤성우열혈", "윈도우즈시스", "DONE"));
        Card card = doneLists.get(0);
        return card;
    }


    //포스트 완료한 일로 이동	POST	/board/done/{id}
    @PostMapping("done/{id}")
    public Card moveToDone(@PathVariable("id") int id) {
        List<Card> todoLists = new ArrayList<>();
        todoLists.add(new Card(id, "민초아몬드", "마시쪙민초", "TODO"));
        todoLists.add(new Card(2, "아반떼와횬대차", "테슬라사라", "TODO"));
        todoLists.add(new Card(8, "해리포터", "조앤k롤링", "TODO"));
        Card card = todoLists.get(0);
        card.setCategory("DONE");
        return card;
    }



    //포스트 이동	POST	/board/{id}?move=#	(todo-prog-done) 간 이동
    @PostMapping("/{id}")
    public Card move(@PathVariable("id") int id, @RequestParam("move") String category ) {
        List<Card> todoLists = new ArrayList<>();
        todoLists.add(new Card(id, "민초아몬드", "마시쪙민초", "TODO"));
        todoLists.add(new Card(2, "아반떼와횬대차", "테슬라사라", "TODO"));
        todoLists.add(new Card(8, "해리포터", "조앤k롤링", "TODO"));
        Card card = todoLists.get(0);
        card.setCategory(category);
        return card;
    }


    /*

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;//문자가 아닌 객체를 넘긴다!

     */
}
