Feature: Gestión de Productos

  Scenario: Obtener productos activos
    Given Estoy autenticado como usuario válido
    When Realizo una solicitud GET a "/productos"
    Then Debería recibir un status code 200
    And Debería recibir una lista de productos activos

  Scenario: Obtener todos los productos
    Given Estoy autenticado como usuario válido
    When Realizo una solicitud GET a "/productos/all"
    Then Debería recibir un status code 200
    And Debería recibir una lista de todos los productos

  Scenario: Obtener un producto por ID válido
    Given Estoy autenticado como usuario válido
    When Realizo una solicitud GET a "/producto/1" con el ID de un producto existente
    Then Debería recibir un status code 200
    And Debería recibir los detalles del producto solicitado

  Scenario: Obtener un producto por ID inválido
    Given Estoy autenticado como usuario válido
    When Realizo una solicitud GET a "/producto/1" con un ID de producto no existente
    Then Debería recibir un status code 404
    And Debería recibir un mensaje de error indicando que el producto no se encontró

  Scenario: Crear un nuevo producto
    Given Estoy autenticado como usuario válido
    When Realizo una solicitud POST a "/producto" con los datos del nuevo producto
    Then Debería recibir un status code 201
    And Debería recibir un mensaje de éxito indicando que el producto se creó correctamente
    And Debería recibir los detalles del nuevo producto creado

  Scenario: Editar un producto existente
    Given Estoy autenticado como usuario válido
    When Realizo una solicitud PUT a "/producto" con los datos actualizados de un producto existente
    Then Debería recibir un status code 200
    And Debería recibir un mensaje de éxito indicando que el producto se actualizó correctamente

  Scenario: Editar un producto no existente
    Given Estoy autenticado como usuario válido
    When Realizo una solicitud PUT a "/producto" con los datos actualizados de un producto no existente
    Then Debería recibir un status code 404
    And Debería recibir un mensaje de error indicando que el producto no se encontró

  Scenario: Eliminar un producto existente
    Given Estoy autenticado como usuario válido
    When Realizo una solicitud DELETE a "/producto/1" con el ID de un producto existente
    Then Debería recibir un status code 200
    And Debería recibir un mensaje de éxito indicando que el producto se eliminó correctamente

  Scenario: Eliminar un producto no existente
    Given Estoy autenticado como usuario válido
    When Realizo una solicitud DELETE a "/producto/1" con un ID de producto no existente
    Then Debería recibir un status code 404
    And Debería recibir un mensaje de error indicando que el producto no se encontró
