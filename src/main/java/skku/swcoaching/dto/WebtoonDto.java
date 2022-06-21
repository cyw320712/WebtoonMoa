package skku.swcoaching.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import skku.swcoaching.domain.webtoon.Day;
import skku.swcoaching.domain.webtoon.Tag;
import skku.swcoaching.domain.webtoon.Webtoon;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
public class WebtoonDto {
    private String title;
    private String writer;
    private String thumbnail;
    private Day updateDate;
    private String platform;
    private String url;
    private List<Tag> tags;

    public WebtoonDto(Webtoon webtoon){
        this.title = webtoon.getTitle();
        this.writer = webtoon.getWriter();
        this.thumbnail = webtoon.getThumbnail();
        this.updateDate = webtoon.getUpdateDate();
        this.platform = webtoon.getPlatform();
        this.url = webtoon.getUrl();
        this.tags = webtoon.getTags();
    }

    public Webtoon toEntity(){
        return new Webtoon(title, writer, thumbnail, updateDate, platform, url, tags);
    }
}
