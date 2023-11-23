package com.example.kohbergbackend.service;

import com.example.kohbergbackend.model.Sale;
import com.example.kohbergbackend.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }


    /**
     * Creates a new sale and persists it in the repository.
     *
     * @param sale The sale object to be created.
     * @return The created sale object.
     */
    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }

    /**
     * Retrieves a list of all sales from the repository.
     *
     * @return A list containing all sales.
     */
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    /**
     * Retrieves a sale from the repository based on its unique identifier (ID).
     *
     * @param saleID The ID of the sale to retrieve.
     * @return An optional containing the sale if found, or an empty optional if not found.
     */
    public Optional<Sale> getSaleById(int saleID){
        return saleRepository.findById(saleID);
    }

    /**
     * Updates the product information of a specific sale.
     *
     * @param saleID The ID of the sale to be updated.
     * @param updatedSaleDate The updated sale object containing the new product information.
     * @return The updated sale object if the sale is found and updated, or null if the sale is not found.
     */
    public Sale updateSale(int saleID, Sale updatedSaleDate){
        return saleRepository.findById(saleID)
                .map(existingSale -> {
                    // Perform update operations here if needed
                    if(updatedSaleDate.getProduct() != null){
                        // Update product information
                        existingSale.setProduct(updatedSaleDate.getProduct());
                    }
                    return saleRepository.save(existingSale);
                })
                .orElse(null);
    }

    /**
     * Deletes a sale from the repository based on its unique identifier (ID).
     *
     * @param salesID The ID of the sale to be deleted.
     */
    public void deleteSale (int salesID){
        saleRepository.deleteById(salesID);
    }





}
