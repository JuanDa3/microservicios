Microservicio para usuarios con las funciones de:
Eliminar Usuario
DELETE
https://dominio.com/usurio/eliminarUsuario/idUsuario
Se realiza la eliminación de un usuario recibiendo como parámetro el id del usuario.
Agregar usuario
POST
https://dominio.com/usuario/estado/idUsuario
se agrega un usuario (teniendo en cuenta su estado y su ID de Usuario).
Buscar Usuario
GET
https://dominio.com/usuario/
Se consulta los usuarios que están activos o no lo están
Detalle Usuario
GET
https://dominio.com/usuario/detalle/idUsuario
Se consulta el detalle de un usuario en específico


Comandos 
docker run --name postgres-db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=mysecretpassword -p 5432:5432 -d postgres

docker exec -it postgres-db bash
