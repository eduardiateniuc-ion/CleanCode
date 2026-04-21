package com.example.travelling.serviceImpl;

import com.example.travelling.model.Event;
import com.example.travelling.model.Supplier;
import com.example.travelling.service.SupplierService;

public class SupplierServiceImpl implements SupplierService {

    private double roundToOneDecimal(double value) {
        return Math.round(value * 10) / 10.0;
    }

    @Override
    public double getCompanyRank(Supplier supplier) {

        int positiveComments = supplier.getPositiveComments();
        int negativeComments = supplier.getNegativeComments();
        int flightsCount = supplier.getFlightsCount();
        int totalComments = positiveComments + negativeComments;

        if (totalComments == 0) {
            return 0.0;
        }

        if (totalComments > flightsCount
                || positiveComments > flightsCount
                || negativeComments > flightsCount) {
            throw new IllegalArgumentException("Comments count exceeds flights count");
        }

        double reviewScore = (double) positiveComments / totalComments;

        double experience = Math.min(1.0, (double) flightsCount / 100.0);

        double rating = 5.0 * reviewScore * experience;

        return roundToOneDecimal(rating);
    }

    @Override
    public String getComfortLevel(Supplier supplier){
        double rating = getCompanyRank(supplier);

        if (rating >= 4.0) return "Premium";
        if (rating >= 3.0) return "Comfort";
        if (rating >= 1.0) return "So-So...";

        return "Low";
    }


}
