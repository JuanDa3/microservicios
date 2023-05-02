Feature: La API permite al usuario ver los productos disponibles, agregarlos a su lista y comprarlos

  Scenario: Yo como usuario registrado quiero realizar una compra

    Given Soy un usuario registrado que necesita realizar una compra

    When Uso un metodo de pago
    And obtengo un producto existente
    And invoco el servicio de compras
    Then obtengo un status code 201
    And  obtengo un resumen de la compra





