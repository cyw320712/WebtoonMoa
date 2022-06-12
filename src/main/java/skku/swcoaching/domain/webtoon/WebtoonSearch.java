package skku.swcoaching.domain.webtoon;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class WebtoonSearch {

    private String subString;
    private List<Tag> tags;

}
