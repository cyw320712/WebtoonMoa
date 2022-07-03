package skku.swcoaching.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import skku.swcoaching.domain.webtoon.Tag;
import skku.swcoaching.repository.TagRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    @Transactional
    public List<Tag> getAllTag(){
        return tagRepository.findAll();
    }

}
