package skku.swcoaching.domain.crawling;

import lombok.Getter;
import lombok.Setter;
import skku.swcoaching.domain.webtoon.Webtoon;

import javax.persistence.*;
import java.time.DayOfWeek;

@Entity
@Getter @Setter
public class CrawlingTarget {
    /**
     * 크롤링 할 데이터를 지정하는 Domain
     *
     * 생각해야할 점
     * 1. 웹툰 들에서 가져올 데이터는 모두 동일한지
     * 2. 크롤링한 데이터를 가져왔다면, 그걸 DB에 바로 저장하는게 가능한지?
     *   2.1. 가능하다면? 업데이트 형식으로 최신 버전의 item 만 집어넣기
     *   2.2. 불가능하다면? 크롤링해온 데이터를 또 저장해서 추가 로직을 통해 가공 후 DB에 넣어야됨.
     */

    @Id
    @GeneratedValue
    @Column(name = "crawling_target_id")
    private Long id;

    private String name; // 수집 데이터 명
    private CrawlingType type; // 수집 데이터 타입
    private DayOfWeek updateDate; // 업데이트 되는 요일

    private String url; // 크롤링 좌표
    private String xpath; // 크롤링 xpath (DOM)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "webtoon_id")
    private Webtoon webtoon;


    public static CrawlingTarget createTarget(String name, CrawlingType type, DayOfWeek updateDate, String url, String xpath, Webtoon webtoon){
        CrawlingTarget crawlingTarget = new CrawlingTarget();

        crawlingTarget.name = name;
        crawlingTarget.type = type;
        crawlingTarget.updateDate = updateDate;
        crawlingTarget.url = url;
        crawlingTarget.xpath = xpath;
        crawlingTarget.webtoon = webtoon;

        return crawlingTarget;
    }
}
