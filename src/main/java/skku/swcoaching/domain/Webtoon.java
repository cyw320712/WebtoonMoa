package skku.swcoaching.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Webtoon {

    @Id
    @GeneratedValue
    @Column(name = "webtoon_id")
    private Long id;

    private String title;
    private String thumbnail; // 이미지가 저장된 경로?
    private String writer;
    private LocalDateTime updateDate;

    @ManyToMany(mappedBy = "webtoons")
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "webtoon", cascade = CascadeType.ALL)
    private List<CrawlingTarget> crawlingTargets = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="platform_id")
    private Platform platform;

    public void addTag(Tag tag){
        tags.add(tag);
    }

    public static Webtoon makeWebtoon(String title, String thumbnail, String writer, LocalDateTime updateDate, Tag... tags){
        Webtoon webtoon = new Webtoon();

        webtoon.setTitle(title);
        webtoon.setThumbnail(thumbnail);
        webtoon.setWriter(writer);
        webtoon.setUpdateDate(updateDate);
        for (Tag tag : tags){
            webtoon.addTag(tag);
        }

        return webtoon;
    }
}
