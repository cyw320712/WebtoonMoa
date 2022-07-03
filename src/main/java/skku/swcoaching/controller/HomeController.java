package skku.swcoaching.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import skku.swcoaching.domain.webtoon.Tag;
import skku.swcoaching.domain.webtoon.Webtoon;
import skku.swcoaching.domain.webtoon.WebtoonSearch;
import skku.swcoaching.service.TagService;
import skku.swcoaching.service.WebtoonService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final WebtoonService webtoonService;
    private final TagService tagService;

    @GetMapping("/")
    public String home(Model model){
        LocalDate today = LocalDate.now();
        DayOfWeek todayOfWeek = today.getDayOfWeek();
        model.addAttribute("dayOfWeek", todayOfWeek.getValue());
        log.info(todayOfWeek.toString());


        WebtoonSearch webtoonSearch = new WebtoonSearch();
        model.addAttribute("webtoonSearch", webtoonSearch);

        List<Webtoon> webtoons = webtoonService.searchWebtoonForToday(todayOfWeek, 0);
        model.addAttribute("webtoons", webtoons);

        List<Tag> tags = tagService.getAllTag();
        model.addAttribute("tags", tags);

        return "redirect:/" + todayOfWeek.getValue();
    }

    @PostMapping("/{dayOfWeek}")
    public String webtoonList(
            @ModelAttribute("webtoonSearch") WebtoonSearch webtoonSearch,
            @PathVariable("dayOfWeek") int dayOfWeek,
            Model model)
    {
        log.info(webtoonSearch.getKeyword());
        List<Tag> getTags = webtoonSearch.getTags();

        List<Webtoon> webtoons = webtoonService.searchWebtoonForConditions(webtoonSearch);
        model.addAttribute("webtoons", webtoons);

        List<Tag> tags = tagService.getAllTag();
        model.addAttribute("tags", tags);

        DayOfWeek todayOfWeek = DayOfWeek.of(dayOfWeek);
        model.addAttribute("dayOfWeek", dayOfWeek);

        return "home";
    }

    @GetMapping("/{dayOfWeek}")
    public String webtoonList(
            @PathVariable("dayOfWeek") int dayOfWeek,
            Model model)
    {
        DayOfWeek todayOfWeek = DayOfWeek.of(dayOfWeek);
        List<Webtoon> webtoons = webtoonService.searchWebtoonForToday(todayOfWeek, 0);
        WebtoonSearch webtoonSearch = new WebtoonSearch();
        model.addAttribute("webtoonSearch", webtoonSearch);
        model.addAttribute("dayOfWeek", dayOfWeek);
        model.addAttribute("webtoons", webtoons);

        List<Tag> tags = tagService.getAllTag();
        model.addAttribute("tags", tags);

        return "home";
    }

}
