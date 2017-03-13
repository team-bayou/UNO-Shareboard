package com.bayou.views;

/**
 * File: ReviewView
 * Package: com.bayou.views
 * Author: Stefan Haselwanter
 * Created on: 3/13/17
 */
public class ReviewView extends BaseEntityView {
    private Integer rating;
    private String comments;
    private Long reviewerId;
    private Long revieweeId;

    private UserView reviewer;
    private UserView reviewee;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public Long getRevieweeId() {
        return revieweeId;
    }

    public void setRevieweeId(Long revieweeId) {
        this.revieweeId = revieweeId;
    }

    public UserView getReviewer() {
        return reviewer;
    }

    public void setReviewer(UserView reviewer) {
        this.reviewer = reviewer;
    }

    public UserView getReviewee() {
        return reviewee;
    }

    public void setReviewee(UserView reviewee) {
        this.reviewee = reviewee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReviewView review = (ReviewView) o;

        if (rating != null ? !rating.equals(review.rating) : review.rating != null) return false;
        if (comments != null ? !comments.equals(review.comments) : review.comments != null) return false;
        if (reviewerId != null ? !reviewerId.equals(review.reviewerId) : review.reviewerId != null) return false;
        return revieweeId != null ? revieweeId.equals(review.revieweeId) : review.revieweeId == null;
    }

    @Override
    public int hashCode() {
        int result = rating != null ? rating.hashCode() : 0;
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (reviewerId != null ? reviewerId.hashCode() : 0);
        result = 31 * result + (revieweeId != null ? revieweeId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ReviewView{" +
                "rating=" + rating +
                ", comments='" + comments + '\'' +
                ", reviewerId=" + reviewerId +
                ", revieweeId=" + revieweeId +
                "} " + super.toString();
    }
}
