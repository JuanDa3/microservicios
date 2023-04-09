package main

import (
	"net/http"

	"github.com/JuanDa3/servicio-usuarios/db"
	"github.com/JuanDa3/servicio-usuarios/models"
	"github.com/JuanDa3/servicio-usuarios/routes"
	"github.com/gorilla/mux"
)

func main() {

	db.DBConnection()

	db.DB.AutoMigrate(models.Usuario{})

	router := mux.NewRouter()

	router.HandleFunc("/", routes.HomeHandler)

	router.HandleFunc("/usuario/{id}", routes.GetUsuarioHandler).Methods("GET")
	router.HandleFunc("/usuarios", routes.GetUsuariosHandler).Methods("GET")
	router.HandleFunc("/usuario", routes.POSTUsuarioHandler).Methods("POST")
	router.HandleFunc("/usuario/{id}", routes.DeleteUsuarioHandler).Methods("DELETE")

	http.ListenAndServe(":3000", router)
}
