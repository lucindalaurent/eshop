package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository{
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product searchById(String productId){
        for(Product product: productData){
            if(product.getProductId().equals(productId)){
                return product;
            }
        }
        throw new NoSuchProductException("Product with id " + productId + " does not exist!");
    }

    public Product update(Product originalProduct, String newName, int newQuantity){
        originalProduct.setProductName(newName);
        originalProduct.setProductQuantity(newQuantity);
        return originalProduct;
    }

    public void delete(String productId) {
        Product product = searchById(productId);
        productData.remove(product);
    }

    public static class NoSuchProductException extends RuntimeException {
        public NoSuchProductException(String message) {
            super(message);
        }
    }
}