package models

import (
	"golang.org/x/crypto/bcrypt"
	"gorm.io/gorm"
)

type Usuario struct {
	gorm.Model
	Id          uint   `gorm:"not null;unique_index"`
	Username    string `gorm:"not null"`
	Contrasenia string `gorm:"not null"`
	Correo      string `gorm:"type:varchar(30);not null;unique_index"`
}

func (usuario *Usuario) HashContrasenia(contrasenia string) error {
	bytes, err := bcrypt.GenerateFromPassword([]byte(contrasenia), 14)
	if err != nil {
		return err
	}
	usuario.Contrasenia = string(bytes)
	return nil
}

func (usuario *Usuario) VerificarContrasenia(contraseniaProporcionada string) error {
	err := bcrypt.CompareHashAndPassword([]byte(usuario.Contrasenia), []byte(contraseniaProporcionada))
	if err != nil {
		return err
	}
	return nil
}
