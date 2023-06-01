package com.co.uniquindio.compras.steps;

import com.co.uniquindio.compras.dtos.ProductoDTO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class ProductoSteps {

    private Response response;
    private static final String BASE_URL = "http://localhost:8080/productos";

    @Given("Estoy autenticado como usuario válido")
    public void estoyAutenticadoComoUsuarioValido() {
        // Aquí puedes incluir la lógica para autenticar al usuario si es necesario
    }

    @When("Realizo una solicitud GET a \"/productos\"")
    public void realizoSolicitudGETProductos() {
        baseURI = BASE_URL;
        response = given()
                .contentType(ContentType.JSON)
                .when().get();
    }

    @When("Realizo una solicitud GET a \"/productos/all\"")
    public void realizoSolicitudGETProductosAll() {
        baseURI = BASE_URL + "/all";
        response = given()
                .contentType(ContentType.JSON)
                .when().get();
    }

    @When("Realizo una solicitud GET a \"/producto/{id}\" con el ID de un producto existente")
    public void realizoSolicitudGETProductoPorIDValido() {
        int idProductoExistente = 1; // Reemplaza con el ID válido del producto existente
        String endpoint = "/producto/" + idProductoExistente;
        baseURI = BASE_URL + endpoint;
        response = given()
                .contentType(ContentType.JSON)
                .when().get();
    }

    @When("Realizo una solicitud GET a \"/producto/{id}\" con un ID de producto no existente")
    public void realizoSolicitudGETProductoPorIDInvalido() {
        int idProductoNoExistente = 999; // Reemplaza con un ID de producto no existente
        String endpoint = "/producto/" + idProductoNoExistente;
        baseURI = BASE_URL + endpoint;
        response = given()
                .contentType(ContentType.JSON)
                .when().get();
    }

    @When("Realizo una solicitud POST a \"/producto\" con los datos del nuevo producto")
    public void realizoSolicitudPOSTNuevoProducto() {
        ProductoDTO nuevoProducto = new ProductoDTO();
        // Configura los datos del nuevo producto
        nuevoProducto.setNombre("Nuevo Producto");
        // ...

        baseURI = BASE_URL + "/producto";
        response = given()
                .contentType(ContentType.JSON)
                .body(nuevoProducto)
                .when().post();
    }

    @When("Realizo una solicitud PUT a \"/producto\" con los datos actualizados de un producto existente")
    public void realizoSolicitudPUTProductoExistente() {
        ProductoDTO productoActualizado = new ProductoDTO();
        // Configura los datos actualizados del producto existente
        productoActualizado.setId(1); // Reemplaza con el ID válido del producto existente
        productoActualizado.setNombre("Producto Actualizado");
        // ...

        baseURI = BASE_URL + "/producto";
        response = given()
                .contentType(ContentType.JSON)
                .body(productoActualizado)
                .when().put();
    }

    @When("Realizo una solicitud PUT a \"/producto\" con los datos actualizados de un producto no existente")
    public void realizoSolicitudPUTProductoNoExistente() {
        ProductoDTO productoNoExistente = new ProductoDTO();
        // Configura los datos actualizados del producto no existente
        productoNoExistente.setId(999); // Reemplaza con un ID de producto no existente
        productoNoExistente.setNombre("Producto No Existente");
        // ...

        baseURI = BASE_URL + "/producto";
        response = given()
                .contentType(ContentType.JSON)
                .body(productoNoExistente)
                .when().put();
    }

    @When("Realizo una solicitud DELETE a \"/producto/{id}\" con el ID de un producto existente")
    public void realizoSolicitudDELETEProductoExistente() {
        int idProductoExistente = 1; // Reemplaza con el ID válido del producto existente
        String endpoint = "/producto/" + idProductoExistente;
        baseURI = BASE_URL + endpoint;
        response = given()
                .contentType(ContentType.JSON)
                .when().delete();
    }

    @When("Realizo una solicitud DELETE a \"/producto/{id}\" con un ID de producto no existente")
    public void realizoSolicitudDELETEProductoNoExistente() {
        int idProductoNoExistente = 999; // Reemplaza con un ID de producto no existente
        String endpoint = "/producto/" + idProductoNoExistente;
        baseURI = BASE_URL + endpoint;
        response = given()
                .contentType(ContentType.JSON)
                .when().delete();
    }

    @Then("Debería recibir un status code {int}")
    public void deberiaRecibirStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("Debería recibir una lista de productos activos")
    public void deberiaRecibirListaProductosActivos() {
        response.then().body("productos", notNullValue());
        List<ProductoDTO> productos = response.jsonPath().getList("productos", ProductoDTO.class);
        // Puedes realizar más aserciones sobre la lista de productos recibidos si es necesario
    }

    @Then("Debería recibir una lista de todos los productos")
    public void deberiaRecibirListaTodosProductos() {
        response.then().body("productos", notNullValue());
        List<ProductoDTO> productos = response.jsonPath().getList("productos", ProductoDTO.class);
        // Puedes realizar más aserciones sobre la lista de productos recibidos si es necesario
    }

    @Then("Debería recibir los detalles del producto solicitado")
    public void deberiaRecibirDetallesProductoSolicitado() {
        response.then().body("nombre", notNullValue());
        // Puedes realizar más aserciones sobre los detalles del producto recibido si es necesario
    }

    @Then("Debería recibir un mensaje de error indicando que el producto no se encontró")
    public void deberiaRecibirMensajeErrorProductoNoEncontrado() {
        response.then().body("mensaje", equalTo("El producto no se encontró"));
    }

    @Then("Debería recibir un mensaje de éxito indicando que el producto se creó correctamente")
    public void deberiaRecibirMensajeExitoProductoCreado() {
        response.then().body("mensaje", equalTo("El producto se creó correctamente"));
    }

    @Then("Debería recibir los detalles del nuevo producto creado")
    public void deberiaRecibirDetallesNuevoProductoCreado() {
        response.then().body("nombre", notNullValue());
        // Puedes realizar más aserciones sobre los detalles del nuevo producto creado si es necesario
    }

    @Then("Debería recibir un mensaje de éxito indicando que el producto se actualizó correctamente")
    public void deberiaRecibirMensajeExitoProductoActualizado() {
        response.then().body("mensaje", equalTo("El producto se actualizó correctamente"));
    }

    @Then("Debería recibir un mensaje de éxito indicando que el producto se eliminó correctamente")
    public void deberiaRecibirMensajeExitoProductoEliminado() {
        response.then().body("mensaje", equalTo("El producto se eliminó correctamente"));
    }

    @Then("Debería recibir un mensaje de error indicando que el producto no se encontró")
    public void deberiaRecibirMensajeErrorProductoNoEncontrado() {
        response.then().body("mensaje", equalTo("El producto no se encontró"));
    }
}
