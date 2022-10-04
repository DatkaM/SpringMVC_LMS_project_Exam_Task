package peaksoft.service;

import peaksoft.entity.Video;

import java.util.List;

public interface VideoService {

    void saveVideoByLessonId(Long id, Video video);

    void deleteVideo(Long id);

    void updateVideoById(Long id,Video video);

    Video getVideoById(Long id);

    List<Video> getAllByLessonId(Long id);
}
