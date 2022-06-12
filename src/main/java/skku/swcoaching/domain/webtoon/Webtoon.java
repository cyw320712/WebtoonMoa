package skku.swcoaching.domain.webtoon;

import lombok.Getter;
import lombok.Setter;
import skku.swcoaching.domain.crawling.CrawlingTarget;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Webtoon {

    @Id
    @GeneratedValue
    @Column(name = "webtoon_id")
    private Long id;

    private String title; // 웹툰 제목
    private String thumbnail; // 이미지가 저장된 경로?
    private String writer; // 웹툰 작가
    private Day updateDate; // 업데이트 요일
    private String platform; // 플랫폼

    @ManyToMany(mappedBy = "webtoons")
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "webtoon", cascade = CascadeType.ALL)
    private List<CrawlingTarget> crawlingTargets = new ArrayList<>();

    public void addTag(Tag tag){
        tags.add(tag);
    }

    public static Webtoon makeWebtoon(String title, String thumbnail, String writer, Day updateDate, Tag... tags){
        Webtoon webtoon = new Webtoon();

        webtoon.setTitle(title);
        webtoon.setThumbnail(thumbnail);
        webtoon.setWriter(writer);
        webtoon.setUpdateDate(updateDate);
        if (tags.length == 0){
            Tag defaultTag = new Tag("All");
            webtoon.addTag(defaultTag);
        }
        for (Tag tag : tags){
            webtoon.addTag(tag);
        }

        return webtoon;
    }
}
