package models

type UsuarioDTO struct {
	Id       uint   `json:"id"`
	Name     string `json:"name"`
	Username string `json:"username"`
	Correo   string `json:"correo"`
}
