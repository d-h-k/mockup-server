package BanpoXi.Dong.controller;


import BanpoXi.Dong.domain.Card;
import BanpoXi.Dong.domain.CardDto;
import BanpoXi.Dong.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final CardRepository cardRepository;

    public BoardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @PostMapping("/lists")
    public Card add(@RequestBody CardDto cardDto) {
        Card card = cardDto.toEntity();
        //cardRepository.save(card);
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
