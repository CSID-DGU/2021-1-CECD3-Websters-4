package com.back.websters.domain.bookmark;

import com.back.websters.domain.video.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    List<Bookmark> findAllByVideo(Video video);

}
