package com.bayou.engines;

import com.bayou.domains.Review;
import org.springframework.stereotype.Component;

/**
 * Created by joshuaeaton on 3/31/17.
 */
@Component
public class ReviewEngine {

    public int avgUserRating(Iterable<Review> reviewsList) {

        int fiveStarCount = 0;
        int fourStarCount = 0;
        int threeStarCount = 0;
        int twoStarCount = 0;
        int oneStarCount = 0;

        for(Review r : reviewsList) {

            if(r.getRating() == 5) {
                fiveStarCount++;
            } else if(r.getRating() == 4) {
                fourStarCount++;
            } else if(r.getRating() == 3) {
                threeStarCount++;
            } else if(r.getRating() == 2) {
                twoStarCount++;
            } else if(r.getRating() == 1) {
                oneStarCount++;
            }
        }

        return (5*fiveStarCount + 4*fourStarCount + 3*threeStarCount + 2*twoStarCount + oneStarCount)
                /(fiveStarCount + fourStarCount + threeStarCount + twoStarCount + oneStarCount);
    }

}
