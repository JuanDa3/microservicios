Feature: La API permite al usuario ver los productos disponibles, agregarlos a su lista y comprarlos

  Scenario: Yo como usuario registrado quiero realizar una compra

    Given Soy un usuario registrado que necesita realizar una compra

    When Uso un metodo de pago
    And obtengo un producto existente
    And invoco el servicio de compras
    Then obtengo un status code 201
    And  obtengo un resumen de la compra

  Scenario: Yo como usuario registrado quiero cancelar una compra

    Given soy un usuario registrado que necesita cancelar una compra

    When selecciono el id de la compra 5
    When invoco el servicio de cancelar compra
    And obtengo un status code 204





