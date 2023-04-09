package models

import "gorm.io/gorm"

type Usuario struct {
	gorm.Model
	Id          uint   `gorm:"not null;unique_index"`
	Contrasenia string `gorm:"not null"`
	Correo      string `gorm:"type:varchar(30);not null;unique_index"`
}
