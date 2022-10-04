package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.entity.Video;
import peaksoft.repository.VideoRepository;
import peaksoft.service.VideoService;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;

    @Autowired
    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }


    @Override
    public void saveVideoByLessonId(Long id, Video video) {
        videoRepository.saveVideoByLessonId(id, video);
    }

    @Override
    public void deleteVideo(Long id) {
        videoRepository.deleteVideo(id);
    }

    @Override
    public void updateVideoById(Long id, Video video) {
        videoRepository.updateVideoById(id, video);
    }

    @Override
    public Video getVideoById(Long id) {
        return videoRepository.getVideoById(id);
    }

    @Override
    public List<Video> getAllByLessonId(Long id) {
        return videoRepository.getAllByLessonId(id);
    }
}
