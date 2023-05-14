package controllers

import (
	"net/http"

	"github.com/JuanDa3/servicio-usuarios/db"
	"github.com/JuanDa3/servicio-usuarios/models"
	"github.com/gin-gonic/gin"
)

func RegistrarUsuario(context *gin.Context) {
	var usuario models.Usuario
	if err := context.ShouldBindJSON(&usuario); err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		context.Abort()
		return
	}

	if err := usuario.HashContrasenia(usuario.Contrasenia); err != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		context.Abort()
		return
	}

	record := db.DB.Create(&usuario)
	if record.Error != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"error": record.Error.Error()})
		context.Abort()
		return
	}
	context.JSON(http.StatusCreated, gin.H{"userId": usuario.Id, "email": usuario.Correo, "username": usuario.Username})
}

func ObtenerUsuario(context *gin.Context) {
	var usuario models.Usuario
	idUsuario := context.Param("id")
	record := db.DB.Raw("select * from usuarios where id = ?", idUsuario).Scan(&usuario)

	if record.Error != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"error": record.Error.Error()})
		context.Abort()
		return
	}

	usuarioDTO := models.UsuarioDTO{
		Id:       usuario.Id,
		Name:     usuario.Name,
		Username: usuario.Username,
		Correo:   usuario.Correo,
	}

	context.JSON(http.StatusOK, gin.H{"user": &usuarioDTO})
}

func ObtenerUsuarios(context *gin.Context) {
	var usuarios []models.UsuarioDTO
	record := db.DB.Raw("select * from usuarios").Scan(&usuarios)

	if record.Error != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"error": record.Error.Error()})
		context.Abort()
		return
	}
	context.JSON(http.StatusOK, gin.H{"users": &usuarios})
}
