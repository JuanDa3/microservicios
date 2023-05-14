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

    private int idCompra = 0;

    private  List<ProductoDTO> listaProductos=new ArrayList<>();

    private Response response;
    private static final String BASE_URL = "http://localhost:8080/compras";

    @Given("Soy un usuario registrado que necesita realizar una compra")
    public void soyUnUsuarioRegistradoQueNecesitaRealizarUnaCompra() {
        usuarioDTO.setId(2);
        usuarioDTO.setCorreo("pedro@gmail.com");
        compraDTO.setUsuario(usuarioDTO);
    }

    @Given("soy un usuario registrado que necesita cancelar una compra")
    public void soyUnUsuarioRegistradoQueNecesitaCancelarUnaCompra(){
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
        response = given().contentType(ContentType.JSON).body(compraDTO).when().post();
    }

    @Then("obtengo un status code {int}")
    public void obtengoUnStatusCode(int status) {
        response.then().statusCode(status);
    }

    @And("obtengo un resumen de la compra")
    public void obtienenUnResumenDeLaCompra() {
        response.then().body("resumen", response->notNullValue());
    }

    @When("selecciono el id de la compra {int}")
    public void seleccionoElIdDeLaCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    @When("invoco el servicio de cancelar compra")
    public void invocoElServicioDeCancelarCompra() {
        baseURI = BASE_URL;
        response = given().delete(baseURI+"/"+idCompra);
    }
}
