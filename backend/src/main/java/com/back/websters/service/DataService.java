package com.back.websters.service;

import com.back.websters.domain.bookmark.Bookmark;
import com.back.websters.domain.bookmark.BookmarkRepository;
import com.back.websters.domain.script.Script;
import com.back.websters.domain.script.ScriptRepository;
import com.back.websters.domain.video.Video;
import com.back.websters.domain.video.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DataService {

    private final VideoRepository videoRepository;
    private final BookmarkRepository bookmarkRepository;
    private final ScriptRepository scriptRepository;

    public long saveVideo(String fileName, String fileUri) {
        Video video = Video.builder()
                .name(fileName)
                .uri(fileUri)
                .build();
        return videoRepository.save(video).getId();
    }

    public Video findVideoByName(String fileName) {
        return videoRepository.findByName(fileName)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 동영상이 존재하지 않습니다."));
    }

    public long saveBookmark(long videoId, String time) {
        Bookmark bookmark = Bookmark.builder()
                .time(time)
                .video(videoRepository.findById(videoId)
                        .orElseThrow(() -> new IllegalArgumentException("해당하는 동영상이 존재하지 않습니다.")))
                .build();
        return bookmarkRepository.save(bookmark).getId();
    }

    public long saveScript(long videoId, Script script) {
        script.setVideo(videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 동영상이 존재하지 않습니다.")));
        return scriptRepository.save(script).getId();
    }
}