package com.example.travelling.service;

import com.example.travelling.model.Supplier;

public interface SupplierService {
    double getCompanyRank(Supplier supplier);
    String getComfortLevel(Supplier supplier);
}
