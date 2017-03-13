package com.bayou.domains;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * File: Review
 * Package: com.bayou.domains
 * Author: Stefan Haselwanter
 * Created on: 3/13/17
 */
@Entity(name = "Review")
@Table(name = "ads")
@AttributeOverride(name = "id", column = @Column(name = "review_id"))
public class Review extends BaseEntity {
    @Column(name = "rating", columnDefinition = "INTEGER")
    private Integer rating;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @Column(name = "reviewer", nullable = false)
    private Long reviewer;

    @Column(name = "reviewee", nullable = false)
    private Long reviewee;

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

    public Long getReviewer() {
        return reviewer;
    }

    public void setReviewer(Long reviewer) {
        this.reviewer = reviewer;
    }

    public Long getReviewee() {
        return reviewee;
    }

    public void setReviewee(Long reviewee) {
        this.reviewee = reviewee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (rating != null ? !rating.equals(review.rating) : review.rating != null) return false;
        if (comments != null ? !comments.equals(review.comments) : review.comments != null) return false;
        if (reviewer != null ? !reviewer.equals(review.reviewer) : review.reviewer != null) return false;
        return reviewee != null ? reviewee.equals(review.reviewee) : review.reviewee == null;
    }

    @Override
    public int hashCode() {
        int result = rating != null ? rating.hashCode() : 0;
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (reviewer != null ? reviewer.hashCode() : 0);
        result = 31 * result + (reviewee != null ? reviewee.hashCode() : 0);
        return result;
    }
}
