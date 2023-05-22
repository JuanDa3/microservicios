package com.co.uniquindio.compras.steps;

import com.co.uniquindio.compras.dtos.*;
import com.co.uniquindio.compras.enums.EnumMedioPago;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class CompraSteps {

    private UsuarioDTO usuarioDTO = new UsuarioDTO();
    private CompraDTO compraDTO = new CompraDTO();

    private String tokenAutorizacion = "";

    private LoginDTO loginDTO;

    private int idCompra = 0;

    private List<ProductoDTO> listaProductos = new ArrayList<>();

    private Response response;
    private static final String BASE_URL = "http://localhost:8080/compras";
    private static final String LOGIN_URL = "http://localhost:8080/login";

    @Given("Soy un usuario registrado del sistema usando credenciales validas")
    public void soyUnUsuarioRegistradoQueNecesitaRealizarUnaCompra() {
        loginDTO = new LoginDTO("jdnaranjo@mail.com","contrasenia");
        usuarioDTO.setId(1);
        usuarioDTO.setCorreo("jdnaranjo@mail.com");
    }

    @Given("soy un usuario registrado que necesita cancelar una compra")
    public void soyUnUsuarioRegistradoQueNecesitaCancelarUnaCompra() {
        usuarioDTO.setId(2);
        usuarioDTO.setCorreo("pedro@gmail.com");
    }

    @When("Uso un metodo de pago")
    public void seleccionaUnMetodoDePago() {
        compraDTO.setMedioPago(EnumMedioPago.TARJETA);
    }

    @And("obtengo un producto existente")
    public void seleccionaLosProductos() {

        ProductoDTO productoDTO = new ProductoDTO();

        productoDTO.setId(1);
        productoDTO.setReferencia("A2628");
        productoDTO.setNombre("iPhone 13");
        productoDTO.setPrecio(BigDecimal.valueOf(4385690));
        productoDTO.setCantidadCompra(2);

        listaProductos.add(productoDTO);
        compraDTO.setProductos(listaProductos);
    }

    @And("invoco el servicio de compras")
    public void invocaElServicioDeCompras() {
        baseURI = BASE_URL;
        compraDTO.setUsuario(usuarioDTO);
        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", tokenAutorizacion)
                .body(compraDTO).when().post();
    }

    @Then("obtengo un status code {int}")
    public void obtengoUnStatusCode(int status) {
        response.then().statusCode(status);
    }

    @And("obtengo un resumen de la compra")
    public void obtienenUnResumenDeLaCompra() {
        response.then().body("resumen", response -> notNullValue());
    }

    @When("selecciono el id de la compra {int}")
    public void seleccionoElIdDeLaCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    @When("invoco el servicio de cancelar compra")
    public void invocoElServicioDeCancelarCompra() {
        baseURI = BASE_URL;
        response = given()
                .header("Authorization", tokenAutorizacion)
                .delete(baseURI + "/" + idCompra);
    }

    @When("invoco el servicio de autenticacion")
    public void invocoElServicioDeAutenticacion() {
        baseURI = LOGIN_URL;
        response = given().contentType(ContentType.JSON)
                .body(loginDTO).when().get();
        tokenAutorizacion = response.getHeader("Authorization");
    }

    @When("invoco el servicio de estado compra")
    public void invocoElServicioDeEstadoCompra() {
        baseURI = BASE_URL;
        response = given()
                .header("Authorization", tokenAutorizacion)
                .get(baseURI + "/" + idCompra);
    }

    @When("invoco el servicio detalle compra con el numero de factura {string}")
    public void consultoElNumeroDeLaFactura(String numeroFactura) {
        baseURI = BASE_URL;
        response = given()
                .header("Authorization", tokenAutorizacion)
                .get(baseURI + "/facturas?numeroFactura=" + numeroFactura);
    }

    @And("obtengo un json con la descripcion {string}")
    public void obtengoUnJsonConElDetalleDeLaCompra(String descripcion) {
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body(descripcion, response -> notNullValue());
    }

    @When("invoco el servicio de historial de compras con el email {string}")
    public void invocoElServicioDeHistorialDeComprasConElCorreo(String email) {
        baseURI = BASE_URL;
        response = given()
                .header("Authorization", tokenAutorizacion)
                .get(baseURI + "?email=" + email);
    }
}
