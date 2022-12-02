package lv.acodemy.step_definition;

import io.cucumber.java.en.Given;
import lv.acodemy.page_object.ProductsPage;

public class ProductsStepDefs {

    ProductsPage productsPage = new ProductsPage();

    @Given("user selects {string} product from a list")
    public void selectProductFromList(String productName) {
       productsPage.selectProductFromList(productName);
    }
}
