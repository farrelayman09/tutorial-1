package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreate() {
        // Arrange
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        when(productRepository.create(any(Product.class))).thenReturn(product);

        // Act
        Product savedProduct = productService.create(product);

        // Assert
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testGetNewID() {
        String product1 = productService.getNewId();
        String product2 = productService.getNewId();
        assertEquals(product1, "1");
        assertEquals(product2, "2");

    }

    @Test
    void testFindAllProduct() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productList.add(product1);
        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productList.add(product2);

        when(productRepository.findAll()).thenReturn(productList.iterator());

        List<Product> savedProductList = productService.findAll();
        assertEquals(productList.size(), savedProductList.size());
        assertEquals(productList, savedProductList);
    }

    @Test
    void testFindByIdProduct() {
        // Create a test product
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        // Mock the behavior of productRepository.findAll() to return an iterator containing the test product
//        Iterator<Product> productIterator = Collections.singletonList(product1).iterator();
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1).iterator());

        // Call the findById method with the product's ID
        Product savedProduct = productService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        // Verify that the returned product matches the test product
        assertEquals(product1, savedProduct);
    }

    @Test
    void testEditProduct() {
        // Create a test product
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        // Mock the behavior of productRepository.findAll() to return an iterator containing the test product
        List<Product> productList = Collections.singletonList(product);
//        Iterator<Product> productIterator = productList.iterator();
//        doReturn(productList.iterator()).when(productRepository.findAll());
        when(productRepository.findAll()).thenReturn(Arrays.asList(product).iterator());

        // Mock the behavior of findById to return the test product
//        when(productService.findById(product.getProductId())).thenReturn(product);

        // Mock the behavior of productRepository.edit() to return the test product
        when(productRepository.edit(product)).thenReturn(product);

        // Call the edit method with the test product
        Product editedProduct = productService.edit(product);

        // Verify that the returned product matches the test product
        assertEquals(product, editedProduct);
    }

    @Test
    void testDeleteProduct() {
        // Create a test product
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        // Mock the behavior of productRepository.findAll() to return an iterator containing the test product
        when(productRepository.findAll()).thenReturn(Arrays.asList(product).iterator());

        // Mock the behavior of findById to return the test product
//        when(productService.findById(product.getProductId())).thenReturn(product);

        // Verify that no exception is thrown when deleting the product
        assertDoesNotThrow(() -> productService.delete(product));
    }

    @Test
    void testDeleteProduct_NotFound() {
        // Create a test product
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        // Mock the behavior of productRepository.findAll() to return an empty iterator
        when(productRepository.findAll()).thenReturn(Arrays.asList(product).iterator());
        productService.delete(product);

        // Verify that a ResponseStatusException is thrown when trying to delete a non-existent product
        assertThrows(ResponseStatusException.class, () -> productService.delete(product));

    }

}
