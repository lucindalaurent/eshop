package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(),savedProduct.getProductName());
        assertEquals(product.getProductQuantity(),savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-468e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-98b1-437d-a0bf-d0821dde9896");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testCreateThenSearchById(){
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-468e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-98b1-437d-a0bf-d0821dde9896");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Product foundProduct = productRepository.searchById(product1.getProductId());
        assertEquals(product1.getProductName(), foundProduct.getProductName());
        assertEquals(product1.getProductQuantity(), foundProduct.getProductQuantity());
    }

    @Test
    void testNotFoundById(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-468e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Exception raisedException = assertThrows(ProductRepository.NoSuchProductException.class, () -> {
            productRepository.searchById("not-real-id");
        });

        String expectedMessage = "Product with id not-real-id does not exist!";
        String actualMessage = raisedException.getMessage();
        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void testCreateThenEdit(){
        Product realProduct = new Product();
        realProduct.setProductId("eb558e9f-1c39-468e-8860-71af6af63bd9");
        realProduct.setProductName("Sampo Cap Dua Hati");
        realProduct.setProductQuantity(100);
        productRepository.create(realProduct);

        Product editedProduct = new Product();
        editedProduct.setProductId("eb558e9f-1c39-468e-8860-71af6af63bd9");
        editedProduct.setProductName("Sampo Cap Satu Hati");
        editedProduct.setProductQuantity(10);
        productRepository.update(editedProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product latestProduct = productIterator.next();
        assertEquals(realProduct.getProductId(), latestProduct.getProductId());
        assertEquals(realProduct.getProductName(), latestProduct.getProductName());
        assertEquals(realProduct.getProductQuantity(), latestProduct.getProductQuantity());
    }

    @Test
    void testEditProductNotFound(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-468e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product editedProduct = new Product();
        editedProduct.setProductId("not-real-id");
        editedProduct.setProductName("Sampo Cap Odin");
        editedProduct.setProductQuantity(200);

        assertThrows(ProductRepository.NoSuchProductException.class, () -> {
            productRepository.update(editedProduct);
        });
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product remainingProduct = productIterator.next();
        assertEquals(product.getProductId(), remainingProduct.getProductId());
        assertEquals(product.getProductName(), remainingProduct.getProductName());
        assertEquals(product.getProductQuantity(), remainingProduct.getProductQuantity());
    }

    @Test
    void testCreateThenDelete() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-468e-8860-71af6af63bd6");
        product1.setProductName("Kecap Cap Naga");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-98b1-437d-a0bf-d0821dde9896");
        product2.setProductName("Sampo Anti Rontok");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        productRepository.delete(product1.getProductId());
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product remainingProduct = productIterator.next();
        assertEquals(product2.getProductId(), remainingProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteProductNotFound(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-468e-8a8u-71af6af63bd6");
        product.setProductName("Susu Kotak");
        product.setProductQuantity(20);
        productRepository.create(product);

        assertThrows(ProductRepository.NoSuchProductException.class, () -> {
            productRepository.delete("not-a-real-id");
        });
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
    }
}