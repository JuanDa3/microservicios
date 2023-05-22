Feature: La API permite al usuario ver los productos disponibles, agregarlos a su lista y comprarlos

  Scenario: Yo como usuario registrado quiero realizar una compra

    Given Soy un usuario registrado del sistema usando credenciales validas

    When  invoco el servicio de autenticacion
    When Uso un metodo de pago
    And obtengo un producto existente
    And invoco el servicio de compras
    Then obtengo un status code 201
    And  obtengo un resumen de la compra

  Scenario: Yo como usuario registrado quiero cancelar una compra

    Given Soy un usuario registrado del sistema usando credenciales validas

    When  invoco el servicio de autenticacion
    When selecciono el id de la compra 7
    When invoco el servicio de cancelar compra
    And obtengo un status code 204

  Scenario: Yo como usuario registrado quiero ver el estado de mi compra

    Given Soy un usuario registrado del sistema usando credenciales validas

    When  invoco el servicio de autenticacion
    When selecciono el id de la compra 3
    When invoco el servicio de estado compra
    Then obtengo un status code 200
    And obtengo un json con la descripcion "estadoCompra"


  Scenario: Yo como usuario registrado quiero ver el detalle de la compra

    Given Soy un usuario registrado del sistema usando credenciales validas
    When  invoco el servicio de autenticacion
    When invoco el servicio detalle compra con el numero de factura "cb631100fe4f42fe8418ca3fa653a6ab"
    Then obtengo un status code 200
    And obtengo un json con la descripcion "detalleCompra"

  Scenario: Yo como usuario registrado quiero ver el historial de mis compras

    Given Soy un usuario registrado del sistema usando credenciales validas
    Given Soy un usuario registrado del sistema usando credenciales validas
    When  invoco el servicio de autenticacion
    When invoco el servicio de historial de compras con el email "jdnaranjo@mail.com"
    Then obtengo un status code 200
    And obtengo un json con la descripcion "historialCompras"

  Scenario: Yo como usuario no registrado no puedo realizar una compra

    Given Soy un usuario registrado del sistema usando credenciales invalidas






