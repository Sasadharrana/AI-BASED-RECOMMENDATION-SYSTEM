package com.example;

import org.apache.mahout.math.*;
import org.apache.mahout.cf.taste.*;
import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;

import java.io.File;

public class RecommendationSystem {
    public static void main(String[] args) {
        try {
            // Load user preferences from CSV file
            File file = new File("user_preferences.csv");
            FileDataModel model = new FileDataModel(file);

            // Create a similarity metric
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

            // Create a recommender
            GenericUser BasedRecommender recommender = new GenericUser BasedRecommender(model, similarity, new NearestNUser Neighborhood(2, similarity, model));

            // Get recommendations for a specific user
            long userId = 1; // Change this to test with different users
            int howManyRecommendations = 2;
            RecommendedItem[] recommendations = recommender.recommend(userId, howManyRecommendations);

            // Display recommendations
            System.out.println("Recommendations for User " + userId + ":");
            for (RecommendedItem recommendation : recommendations) {
                System.out.println("Product ID: " + recommendation.getItemID() + ", Estimated Rating: " + recommendation.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
