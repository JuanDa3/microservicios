package routes

import (
	"encoding/json"
	"net/http"

	"github.com/JuanDa3/servicio-usuarios/db"
	"github.com/JuanDa3/servicio-usuarios/models"
	"github.com/gorilla/mux"
)

func GetUsuariosHandler(w http.ResponseWriter, r *http.Request) {
	var usuarios []models.Usuario
	db.DB.Find(&usuarios)
	json.NewEncoder(w).Encode(&usuarios)
}

func GetUsuarioHandler(w http.ResponseWriter, r *http.Request) {
	var usuario models.Usuario
	params := mux.Vars(r)
	db.DB.First(&usuario, params["id"])

	if usuario.Id == 0 {
		w.WriteHeader(http.StatusNotFound)
		w.Write([]byte("Usuario no encontrado."))
		return
	}

	json.NewEncoder(w).Encode(&usuario)
}

func POSTUsuarioHandler(w http.ResponseWriter, r *http.Request) {
	var usuario models.Usuario
	json.NewDecoder(r.Body).Decode(&usuario)

	usuarioCreado := db.DB.Create(&usuario)
	err := usuarioCreado.Error

	if err != nil {
		w.WriteHeader(http.StatusBadRequest)
		w.Write([]byte(err.Error()))
	}

	json.NewEncoder(w).Encode(&usuario)
}

func DeleteUsuarioHandler(w http.ResponseWriter, r *http.Request) {
	var usuario models.Usuario
	params := mux.Vars(r)

	db.DB.First(&usuario, params["id"])

	if usuario.Id == 0 {
		w.WriteHeader(http.StatusNotFound)
		w.Write([]byte("Usuario no encontrado."))
		return
	}

	db.DB.Delete(&usuario)
	w.WriteHeader(http.StatusOK)
}
