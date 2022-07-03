package skku.swcoaching.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import skku.swcoaching.domain.webtoon.Webtoon;
import skku.swcoaching.domain.webtoon.WebtoonSearch;
import skku.swcoaching.service.WebtoonService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final WebtoonService webtoonService;

    @GetMapping("/")
    public String home(Model model){
        LocalDate today = LocalDate.now();
        DayOfWeek todayOfWeek = today.getDayOfWeek();
        List<Webtoon> webtoons = webtoonService.searchWebtoonForToday(todayOfWeek, 0);
        WebtoonSearch webtoonSearch = new WebtoonSearch();
        model.addAttribute("webtoonSearch", webtoonSearch);
        model.addAttribute("webtoons", webtoons);

        return "home";
    }

    @PostMapping("/")
    public String webtoonList(
            @ModelAttribute("webtoonSearch") WebtoonSearch webtoonSearch,
            Model model)
    {
        log.info(webtoonSearch.getKeyword());

        List<Webtoon> webtoons = webtoonService.searchWebtoonForConditions(webtoonSearch);
        model.addAttribute("webtoons", webtoons);

        return "home";
    }

    @PostMapping("/{page}")
    public String webtoonList(
            @ModelAttribute("webtoonSearch") WebtoonSearch webtoonSearch,
            @PathVariable("id") int page,
            Model model)
    {
        log.info(webtoonSearch.getKeyword());

        List<Webtoon> webtoons = webtoonService.searchWebtoonForConditions(webtoonSearch);
        model.addAttribute("webtoons", webtoons);

        return "home";
    }

}
