/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.recommendationsystem;

import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.util.List;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;

public class RecommendationSystem {
    public static void main(String[] args) {
        try {
            // Load the data model from a CSV file
            DataModel dataModel = new FileDataModel(new File("C:\\Users\\parid\\OneDrive\\Documents\\NetBeansProjects\\RecommendationSystem\\src\\main\\java\\com\\mycompany\\recommendationsystem\\data.csv")); // Replace with your CSV file path

            // Use Tanimoto Coefficient for similarity calculation
            UserSimilarity similarity = new TanimotoCoefficientSimilarity(dataModel);

            // Create a neighborhood of N nearest users
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(5, similarity, dataModel);

            // Build the recommender
            Recommender recommender = new GenericUserBasedRecommender(dataModel, neighborhood, similarity);

            // Generate recommendations for a specific user (e.g., user ID 1)
            List<RecommendedItem> recommendations = recommender.recommend(1, 5);

            // Print recommendations
            for (RecommendedItem recommendation : recommendations) {
                System.out.println("Item ID: " + recommendation.getItemID() + ", Value: " + recommendation.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
