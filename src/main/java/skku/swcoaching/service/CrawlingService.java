package skku.swcoaching.service;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skku.swcoaching.domain.crawling.CrawlingTarget;
import skku.swcoaching.domain.crawling.CrawlingType;
import skku.swcoaching.domain.webtoon.Webtoon;
import skku.swcoaching.repository.CrawlingRepository;
import skku.swcoaching.repository.WebtoonRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;


@Service
@Transactional(readOnly = true)
public class CrawlingService {

    private final CrawlingRepository crawlingRepository;
    private final WebtoonRepository webtoonRepository;
    private final WebDriver driver;

    public CrawlingService(CrawlingRepository crawlingRepository, WebtoonRepository webtoonRepository) {
        //RequiredArgConstructor 에 의존하지말고 그냥 생성자를 만들기
        this.crawlingRepository = crawlingRepository;
        this.webtoonRepository = webtoonRepository;
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        this.driver = new ChromeDriver(options);
    }

    // 텍스트를 합쳐서 메소드로 만들기도 가능할지
    // Dynamic Reflection 로 만들 수 있긴 한데, 비즈니스에선 안쓴다.
    // 찾아갈 수도 없고, 컴파일할 때 확인도 못함.
    // targetWebtoon.set{targetFieldName}(changeText);

    @Transactional
    public void crawlThumbnailUrl(CrawlingTarget crawlingTarget){
        // crawling 해서 가져올 수 도 있고 open API 해서 가져올 수도 있으니까
        // 추상화해서 webtoon 정보를 가져온다. 방법은?
        // 1. 크롤링
        // 2. openApi 를 통해서 가져온다.
        // 이렇게 만들면 Mock 으로 만들 수 있다.

        // *******
        // 코딩은 작동하는 것부터 만들기
        // 이후 리팩터링하기
        // *******

        final String targetUrl = crawlingTarget.getUrl();
        final String targetXpath = crawlingTarget.getXpath();

        Webtoon targetWebtoon = webtoonRepository.findOne(crawlingTarget.getWebtoon().getId());

        driver.get(targetUrl);
        String changeThumbnailUrl = driver.findElement(By.xpath(targetXpath)).getAttribute("src");
        targetWebtoon.setThumbnailUrl(changeThumbnailUrl);
        // Crawling 해서 가져오는 것들을 따로 만들어서 가져오기
        // Crawling service 를 만들어서 service 통해서 가져오기

        webtoonRepository.save(targetWebtoon);
        // 해줄 필요가 없지만, JPA 에 익숙하지 않은 개발자를 위해 명시적 저장
    }

    @Transactional
    public void crawlTitle(CrawlingTarget crawlingTarget){

        final String targetUrl = crawlingTarget.getUrl();
        final String targetXpath = crawlingTarget.getXpath();

        Webtoon targetWebtoon = webtoonRepository.findOne(crawlingTarget.getWebtoon().getId());

        driver.get(targetUrl);
        String changeText = driver.findElement(By.xpath(targetXpath)).getText();
        targetWebtoon.setTitle(changeText);

        webtoonRepository.save(targetWebtoon);
    }


    @Transactional
    public void crawlWriter(CrawlingTarget crawlingTarget){

        final String targetUrl = crawlingTarget.getUrl();
        final String targetXpath = crawlingTarget.getXpath();

        Webtoon targetWebtoon = webtoonRepository.findOne(crawlingTarget.getWebtoon().getId());

        driver.get(targetUrl);
        String changeText = driver.findElement(By.xpath(targetXpath)).getText();
        targetWebtoon.setWriter(changeText);

        webtoonRepository.save(targetWebtoon);
    }

    @Transactional
    public void crawlDate(CrawlingTarget crawlingTarget){
        // Java 8 이후로는 Calendar 안쓰고 LocalDateTime 사용.
        LocalDate today = LocalDate.now();
        DayOfWeek todayOfWeek = today.getDayOfWeek();

        Webtoon targetWebtoon = webtoonRepository.findOne(crawlingTarget.getWebtoon().getId());
        targetWebtoon.setUpdateDate(todayOfWeek);

        webtoonRepository.save(targetWebtoon);
    }

    @Transactional
    public Long makeCrawlingTarget(Webtoon webtoon, String name, String url, String xpath, DayOfWeek day, CrawlingType ct){
        CrawlingTarget crawlingTarget = CrawlingTarget.createTarget(name, ct, day, url, xpath, webtoon);
        crawlingRepository.save(crawlingTarget);

        return crawlingTarget.getId();
    }

}
