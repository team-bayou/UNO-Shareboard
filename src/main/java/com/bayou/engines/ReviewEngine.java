package com.bayou.engines;

import com.bayou.loggers.Loggable;
import com.bayou.views.ReviewView;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Created by joshuaeaton on 3/31/17.
 */
@Component
public class ReviewEngine {

    /*Provided a list of reviews for a user, averages them for the average user star rating*/
    @Loggable
    public int avgUserRating(List<ReviewView> reviewsList) {

        int fiveStarCount = 0;
        int fourStarCount = 0;
        int threeStarCount = 0;
        int twoStarCount = 0;
        int oneStarCount = 0;

        if(reviewsList.size() != 0) {

            for (ReviewView r : reviewsList) {

                if (r.getRating() == 5) {
                    fiveStarCount++;
                } else if (r.getRating() == 4) {
                    fourStarCount++;
                } else if (r.getRating() == 3) {
                    threeStarCount++;
                } else if (r.getRating() == 2) {
                    twoStarCount++;
                } else if (r.getRating() == 1) {
                    oneStarCount++;
                }
            }

        } else {
            return 0;
        }

        return (5*fiveStarCount + 4*fourStarCount + 3*threeStarCount + 2*twoStarCount + oneStarCount)/
                (fiveStarCount + fourStarCount + threeStarCount + twoStarCount + oneStarCount);

    }

}
