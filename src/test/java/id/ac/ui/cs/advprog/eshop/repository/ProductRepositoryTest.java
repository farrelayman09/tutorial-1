package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

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
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
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
    void testEdit() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6"); // initial productId
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());

        Product editedProduct = new Product();
        editedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6"); // same productId
        editedProduct.setProductName("Sampo Cap Budi");
        editedProduct.setProductQuantity(1);
        productRepository.edit(editedProduct);

        productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        savedProduct = productIterator.next();

        // Assertion to verify that the saved product is the edited product 
        assertEquals(editedProduct.getProductId(), savedProduct.getProductId());
        assertEquals(editedProduct.getProductName(), savedProduct.getProductName());
        assertEquals(editedProduct.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testEditNegative() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6"); // initial productId
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        // Attempt to edit the product with invalid data
        Product editedProduct = new Product();
        editedProduct.setProductId("foo-bar"); // different productId
        editedProduct.setProductName("Sampo Cap Bambang");
        editedProduct.setProductQuantity(1);
        productRepository.edit(editedProduct);

        // Retrieve the product and ensure it remains unchanged
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();

        // Assertion to verify that the product remains unchanged after failed edit op
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testDelete() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        // Retrieve the product
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext()); // Assert true initially
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());

        // Delete the product
        productRepository.delete(product);
        productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext()); // Assert false because the product should already be removed
    }

    @Test
    void testDeleteNegative() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product); // Create the product

        Product product2 = new Product();
        product2.setProductId("foo-bar");
        product2.setProductName("Sampo Cap Bambang");
        product2.setProductQuantity(100);
        productRepository.delete(product2); // Delete product2

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext()); // Assert true because the first product should still exist
    }
}