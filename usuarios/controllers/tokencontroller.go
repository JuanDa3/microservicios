package controllers

import (
	"net/http"

	"github.com/JuanDa3/servicio-usuarios/auth"
	"github.com/JuanDa3/servicio-usuarios/db"
	"github.com/JuanDa3/servicio-usuarios/models"
	"github.com/gin-gonic/gin"
)

type TokenRequest struct {
	Correo      string `json:"correo"`
	Contrasenia string `json:"contrasenia"`
}

func GenerateToken(context *gin.Context) {
	var request TokenRequest
	var user models.Usuario
	if err := context.ShouldBindJSON(&request); err != nil {
		context.JSON(http.StatusBadRequest, gin.H{"error": err.Error()})
		context.Abort()
		return
	}

	// check if email exists and password is correct
	record := db.DB.Where("correo = ?", request.Correo).First(&user)
	if record.Error != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"error": record.Error.Error()})
		context.Abort()
		return
	}

	credentialError := user.VerificarContrasenia(request.Contrasenia)
	if credentialError != nil {
		context.JSON(http.StatusUnauthorized, gin.H{"error": "invalid credentials"})
		context.Abort()
		return
	}

	tokenString, err := auth.GenerateJWT(user.Correo, user.Username)
	if err != nil {
		context.JSON(http.StatusInternalServerError, gin.H{"error": err.Error()})
		context.Abort()
		return
	}
	context.JSON(http.StatusOK, gin.H{"token": tokenString})
}
