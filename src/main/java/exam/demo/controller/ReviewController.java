package exam.demo.controller;

import exam.demo.entity.Movie;
import exam.demo.entity.Review;
import exam.demo.service.MovieService;
import exam.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class ReviewController {

    private final MovieService movieService;
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(MovieService movieService, ReviewService reviewService) {
        this.movieService = movieService;
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public String showAllReview(Model model) {
        List<Review> reviews = reviewService.getAllReviews();
        model.addAttribute("reviews", reviews);
        return "all-review";
    }
    @GetMapping("/{id}/reviews/new")
    public String showReviewForm(@PathVariable("id") Long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        model.addAttribute("review", new Review());
        return "new-review-form";
    }

    @PostMapping("/{id}/reviews/new")
    public String createReview(@PathVariable("id") Long id, @ModelAttribute Review review) {

        review.setMovie(id);

        reviewService.createReview(review);
        return "redirect:/movies/{id}";
    }

}
