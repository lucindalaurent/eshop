package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest{
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductServiceImpl productService;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sample Product");
        product.setProductQuantity(10);
        when(productRepository.create(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertEquals(product, createdProduct);
        verify(productRepository, times(1)).create(any(Product.class));
    }

    @Test
    void testFindAllIfEmpty(){
        List<Product> productData = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(productData.iterator());

        List<Product> allProducts = productService.findAll();
        assertTrue(allProducts.isEmpty());
    }

    @Test
    void testFindAllIfMoreThanOneProduct(){
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-468e-8860-71af6af63bd6");
        Product product2 = new Product();
        product2.setProductId("a0f9de46-98b1-437d-a0bf-d0821dde9896");
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2).iterator());

        List<Product> allProducts = productService.findAll();

        assertEquals(2, allProducts.size());
    }

    @Test
    void testFindById(){
        String productId = "c0f9de46-98b1-437d-a0bf-a0821dde9896";
        Product expectedProduct = new Product();
        expectedProduct.setProductId(productId);
        expectedProduct.setProductName("test product");
        expectedProduct.setProductQuantity(12);
        when(productRepository.searchById(productId)).thenReturn(expectedProduct);

        Product actualProduct = productService.findById(productId);

        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void testUpdateProduct(){
        String productId = "c0f9de46-98b1-437d-a0bf-a0821dde9890";
        Product updatedProduct = new Product();
        updatedProduct.setProductId(productId);
        updatedProduct.setProductName("Updated Name");
        updatedProduct.setProductQuantity(100);

        when(productRepository.update(any(Product.class))).thenReturn(updatedProduct);

        Product result = productService.update(productId, updatedProduct);

        verify(productRepository).update(any(Product.class));
    }

    @Test
    void testDeleteProduct(){
        String productId = "c0f9de46-98b1-437d-a0bf-a0821dde9890";

        productService.deleteProductById(productId);

        verify(productRepository, times(1)).delete(productId);
    }
}