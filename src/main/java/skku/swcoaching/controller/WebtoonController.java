package skku.swcoaching.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import skku.swcoaching.domain.webtoon.Webtoon;
import skku.swcoaching.service.WebtoonService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class WebtoonController {

    private final WebtoonService webtoonService;

    @GetMapping("v1/webtoons/{page}")
    public List<Webtoon> list(@PathVariable("id") int page){
        LocalDate today = LocalDate.now();
        DayOfWeek todayOfWeek = today.getDayOfWeek();
        return webtoonService.searchWebtoonForToday(todayOfWeek, page);
    }

}
