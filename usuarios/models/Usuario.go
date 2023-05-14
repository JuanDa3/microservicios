package models

import (
	"golang.org/x/crypto/bcrypt"
	"gorm.io/gorm"
)

type Usuario struct {
	gorm.Model
	Id          uint   `gorm:"not null;unique_index"`
	Name        string `gorm:"type:varchar(50);not null"`
	Username    string `gorm:"not null;unique"`
	Contrasenia string `gorm:"not null"`
	Correo      string `gorm:"type:varchar(30);not null;unique"`
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
