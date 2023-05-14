package main

import (
	"github.com/JuanDa3/servicio-usuarios/controllers"
	"github.com/JuanDa3/servicio-usuarios/db"
	"github.com/JuanDa3/servicio-usuarios/middlewares"
	"github.com/JuanDa3/servicio-usuarios/models"
	"github.com/gin-gonic/gin"
)

func main() {

	db.DBConnection()

	db.DB.AutoMigrate(models.Usuario{})

	router := initRouter()
	router.Run(":3000")
}

func initRouter() *gin.Engine {
	router := gin.Default()
	api := router.Group("/")
	{
		api.POST("/token", controllers.GenerateToken)
		api.POST("usuario", controllers.RegistrarUsuario)
		secured := api.Group("/usuarios").Use(middlewares.Auth())
		{
			secured.GET("ping", controllers.Ping)
			secured.GET(":id", controllers.ObtenerUsuario)
		}
	}
	return router
}
