package com.cinar.newsAPI.service;

import com.cinar.newsAPI.dto.*;
import com.cinar.newsAPI.dto.converter.NewsDtoConverter;
import com.cinar.newsAPI.dto.converter.NewsUserDtoConverter;
import com.cinar.newsAPI.dto.converter.UsersNewsDtoConverter;
import com.cinar.newsAPI.exception.NewsNotFoundException;
import com.cinar.newsAPI.exception.UserNotFoundException;
import com.cinar.newsAPI.model.News;
import com.cinar.newsAPI.repository.NewsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {
    private final NewsRepository newsRepository;
    private final NewsDtoConverter newsDtoConverter;
    private  final NewsUserDtoConverter newsUserDtoConverter;
    private final UsersNewsDtoConverter usersNewsDtoConverter;
    private final UserService userService;

    public NewsService(NewsRepository newsRepository, NewsDtoConverter newsDtoConverter, NewsUserDtoConverter newsUserDtoConverter, UsersNewsDtoConverter usersNewsDtoConverter, UserService userService) {
        this.newsRepository = newsRepository;
        this.newsDtoConverter = newsDtoConverter;
        this.newsUserDtoConverter = newsUserDtoConverter;
        this.usersNewsDtoConverter = usersNewsDtoConverter;
        this.userService = userService;
    }
    public List<NewsDto> getAllNews(){
        return  newsRepository.findAll().stream().map(newsDtoConverter::convert).collect(Collectors.toList());
    }
    protected News findNewsById(String id){
        return newsRepository.findById(id).orElseThrow(()->new NewsNotFoundException("News could not find by id "+id));
    }
    public NewsDto getNewsById(String id){
        return newsDtoConverter.convert(findNewsById(id));
    }
    public UsersNewsDto getNewsByUsername(String username){
        var news = newsRepository.findAll();
        var usernameNews = news.stream().filter(news1 -> news1.getUser().getUsername().equals(username)).findFirst().orElseThrow(()->new UserNotFoundException("Users News could not find by username "+username));
        return usersNewsDtoConverter.convert(usernameNews);
    }
    public List<NewsUserDto> getLatestNews(){

        return newsRepository.findAllByOrderByCreatedTimeDesc().stream().map(newsUserDtoConverter::convert).collect(Collectors.toList());
    }
    public List<NewsUserDto> getFirstNews(){

        return newsRepository.findAllByOrderByCreatedTimeAsc().stream().map(newsUserDtoConverter::convert).collect(Collectors.toList());
    }

    public NewsDto createNews(CreateNewsRequest createNewsRequest,String username){
        var user = userService.findUserByUsername(username)
                .orElseThrow(()->new UserNotFoundException("User not found"+username));
        var news = createNewsFromRequest(createNewsRequest);
        news.setUser(user);
        News savedNews = newsRepository.save(news);
        return newsDtoConverter.convert(savedNews);
    }
    protected News createNewsFromRequest(CreateNewsRequest request){
        return new News(
                request.getTitle()
                ,request.getDescription()
                ,request.getPhotoPath()
                ,LocalDateTime.now());
    }
    public NewsDto updateNews(UpdateNewsRequest updateNewsRequest,String id){
        var newsId = newsRepository.findById(id).orElseThrow(()->new NewsNotFoundException("News could not find by id "+id));
        newsId.setDescription(updateNewsRequest.getDescription());
        newsId.setTitle(updateNewsRequest.getTitle());
        newsId.setCreatedTime(LocalDateTime.now());
        newsId.setPhotoPath(updateNewsRequest.getPhotoPath());
        News updatedNews = newsRepository.save(newsId);
        return newsDtoConverter.convert(updatedNews);
    }
    public void deleteNews(String id){
        if(doesNewsExist(id)){
            newsRepository.deleteById(id);
            throw new ResponseStatusException(HttpStatus.OK, "News deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "News no found");
    }
    protected  boolean doesNewsExist(String id){
        return newsRepository.existsById(id);
    }
}
