package skku.swcoaching.domain.webtoon;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import skku.swcoaching.domain.crawling.CrawlingTarget;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Webtoon {

    @Id
    @GeneratedValue
    @Column(name = "webtoon_id")
    private Long id;

    private String title; // 웹툰 제목
    private String thumbnailUrl; // 이미지가 저장된 url (계속 바뀔 수도 있음)
    private String writer; // 웹툰 작가
    private DayOfWeek updateDate; // 업데이트 요일
    private String platform; // 플랫폼
    private String url; // 접속 가능한 url

    @ManyToMany(mappedBy = "webtoons")
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "webtoon", cascade = CascadeType.ALL)
    private List<CrawlingTarget> crawlingTargets = new ArrayList<>();

    public void addTag(Tag tag){
        tags.add(tag);
    }

    public Webtoon(String title, String thumbnailUrl, String writer, DayOfWeek updateDate, String platform, String url, List<Tag> tags){
        Webtoon webtoon = new Webtoon();
        webtoon.setTitle(title);
        webtoon.setThumbnailUrl(thumbnailUrl);
        webtoon.setWriter(writer);
        webtoon.setUpdateDate(updateDate);
        webtoon.setPlatform(platform);
        webtoon.setUrl(url);
        if (tags.size() == 0){
            Tag defaultTag = new Tag("All");
            webtoon.addTag(defaultTag);
        }
        for (Tag tag : tags){
            webtoon.addTag(tag);
        }
    }
}
