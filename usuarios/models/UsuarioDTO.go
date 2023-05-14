package models

type UsuarioDTO struct {
	Id       uint   `json:"id"`
	Username string `json:"username"`
	Correo   string `json:"correo"`
}
